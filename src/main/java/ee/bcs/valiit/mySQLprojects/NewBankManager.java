package ee.bcs.valiit.mySQLprojects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class NewBankManager {
    private static Map<String, NewBankAccount> banker = new HashMap<>();

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    //http://localhost:8080/bank/createnewaccount?accountNr=1006&balance=2000.0
    @PostMapping("/bank/createnewaccount")
    public void createAccount(@RequestParam("accountNr") String accountNr,
                              @RequestParam("balance") double balance) {
        String sql = "INSERT INTO account(accountno, balance) VALUES(:dbAccNo, :dbAmount)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNo", accountNr);
        paramMap.put("dbAmount", balance);
        jdbcTemplate.update(sql, paramMap); //siin paneb tööle sql-i
    }

    //http://localhost:8080/bank/account/1000 --> OK, see töötab
    @GetMapping("/bank/account/{accountNumber}")
    public String getBalance(@PathVariable("accountNumber") String accountNr) {
        String sql = "SELECT balance FROM account WHERE accountno =:dbAccountNo";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("dbAccountNo", accountNr);
        Double balance = jdbcTemplate.queryForObject(sql, paraMap, Double.class); //muutuja klass peab võrdsustuma .classi tagastustüübiga
        return "Konto balanss on: " + balance;
    }

    //http://localhost:8080/bank/deposit/1001/200 --> OK!
    @PutMapping("/bank/deposit/{account}/{deposit}") ///putMapping on õigem kasutada, sest uuendame olemasolevaid andmeid, mitte ei loo uut
    public String depositMoney(@PathVariable("account") String accountNr, @PathVariable("deposit") Double deposit) {
        String sql = "SELECT balance FROM account WHERE accountno =:dbAccountNo"; //küsin esialgse balance´i
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("dbAccountNo", accountNr);
        paraMap.put("dbAmount", deposit);
        Double balance = jdbcTemplate.queryForObject(sql, paraMap, Double.class);
        if (accountNr == null) {
            return "Selline konto puudub.  Kontrolli andmeid!";
        } else if (deposit > 0) {
            String sql2 = "UPDATE account SET balance  =:dbDeposit WHERE accountno=:dbAccountNo";
            paraMap.put("dbAccountNo", accountNr);
            paraMap.put("dbDeposit", deposit);
            balance = jdbcTemplate.queryForObject(sql, paraMap, Double.class);
            balance = balance + deposit;
            jdbcTemplate.queryForObject(sql, paraMap, Double.class);
            return deposit + " EUR on lisatud kontole number " + accountNr + " Konto jääk: " + balance;
        } else {
            return "Sissemakse ei saa olla väiksem kui 0 EUR. Kontrolli andmeid!";
        }
    }
//    //http://localhost:8080/bank/withdraw/4444/200
//    @PutMapping("/bank/withdraw/{accountnumber}/{withdrawamount}")
//    public String withdrawMoney(@PathVariable("accountnumber") String accountNr, @PathVariable("withdraw") double withdrawamount) {
//        String sql = "SELECT balance FROM account WHERE accountno =:dbAccountNo";
//        Map<String, Object> paraMap = new HashMap<>();
//        paraMap.put("dbAccountNo", accountNr);
//
//
//        accountBalanceMap.get(accountNumber);
//        if (withdrawamount < 0) {
//            return "Sisestatud summa ei saa olla väiksem kui 0. Palun sisesta soovitud summa.";
//        } else {
//            if (withdrawamount <= accountBalanceMap.get(accountNumber)) {
//                double balance = accountBalanceMap.get(accountNumber);
//                accountBalanceMap.put(accountNumber, balance - withdrawamount);
//                return "Välja makstud: " + withdrawamount + " EUR. Konto jääk: " + accountBalanceMap.get(accountNumber);
//            } else {
//                return "Kontol puudub piisavalt vahendeid. Vabad vahendid: " + accountBalanceMap.get(accountNumber);
//            }
//        }
//    }


}
