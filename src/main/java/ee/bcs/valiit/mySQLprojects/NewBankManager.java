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

    //http://localhost:8080/bank/deposit/1001/200 -->  OK, töötab!
    //puudu veel blokeeritud staatuse vahekontroll
    @PutMapping("/bank/deposit/{account}/{deposit}") ///putMapping on õigem kasutada, sest uuendame olemasolevaid andmeid, mitte ei loo uut
    public String depositMoney(@PathVariable("account") String accountNr, @PathVariable("deposit") Double deposit) {
        if (accountNr == null) {
            return "Selline konto puudub.  Kontrolli andmeid!";
        } else if (deposit > 0) {
            String sql = "SELECT balance FROM account WHERE accountno =:dbAccountNo"; //küsin esialgse balance´i
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("dbAccountNo", accountNr); //salvestan map-i kontonr-i
            Double dbBalance = jdbcTemplate.queryForObject(sql, paraMap, Double.class); //päring andmebaasist kontojäägi kohta
            dbBalance = deposit + dbBalance;
            String sql2 = "UPDATE account SET balance =:dbBalance WHERE accountno=:dbAccountNo"; //uuendan kontojääki andmebaasis
            paraMap.put("dbBalance", dbBalance); //salvestan uue balance´i map-i
            jdbcTemplate.update(sql2, paraMap); //uuendan väärtust andmebaasis
            return deposit + " EUR on lisatud kontole number " + accountNr + " Konto jääk: " + dbBalance;
        } else {
            return "Sissemakse ei saa olla väiksem kui 0 EUR. Kontrolli andmeid!";
        }
    }

    //http://localhost:8080/bank/withdraw/1000/200
    @PutMapping("/bank/withdraw/{account}/{withdraw}")
    public String withdrawMoney(@PathVariable("account") String accountNr, @PathVariable("withdraw") Double withdrawamount) {
        if (accountNr == null) {
            return "Selline konto puudub.  Kontrolli andmeid!";
        } else if (withdrawamount > 0) {
            String sql = "SELECT balance FROM account WHERE accountno =:dbAccountNo"; //küsin esialgse balance´i
            Map<String, Object> paraMap = new HashMap<>(); //teen Map-i
            paraMap.put("dbAccountNo", accountNr); //salvestan map-i kontonr-i
            Double dbBalance = jdbcTemplate.queryForObject(sql, paraMap, Double.class); //küsin andmebaasist kontojäägi;
            dbBalance = dbBalance - withdrawamount; //uus kontojääk peale raha väljavõtmist
            String sql2 = "UPDATE account SET balance =:dbBalance WHERE accountno=:dbAccountNo"; //uuendan kontojääki andmebaasis
            paraMap.put("dbBalance", dbBalance);  //salvestan uue kontojäägi map-i
            jdbcTemplate.update(sql2, paraMap); //uuendan väärtust andmebaasis
            return "Välja makstud: " + withdrawamount + " EUR. Konto jääk: " + dbBalance + " EUR.";
        } else {
            return "Väljamakse ei saa olla väiksem kui 0 EUR. Kontrolli andmeid!";
        }
    }



}
