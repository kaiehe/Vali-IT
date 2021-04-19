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
        String isBlocked = "SELECT blocked FROM account WHERE accountno = :dbAccountNo"; //küsin andmebaasist välja konkreetse konto staatust
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("dbAccountNo", accountNr);
        Boolean dbBlocked = jdbcTemplate.queryForObject(isBlocked, paraMap, Boolean.class);
        if (dbBlocked) {
            return "Konto on blokeeritud.Tehingute tegemine keelatud.";
        } else {
            String sql = "SELECT balance FROM account WHERE accountno =:dbAccountNo";
            //Map<String, Object> paraMap = new HashMap<>(); //enne blokeeringu kontrolli oli paramMapi siin vaja teha
            paraMap.put("dbAccountNo", accountNr);
            Double balance = jdbcTemplate.queryForObject(sql, paraMap, Double.class); //muutuja klass peab võrdsustuma .classi tagastustüübiga
            return "Konto balanss on: " + balance;
        }
    }

    //http://localhost:8080/bank/deposit/1001/200 -->  OK, töötab!
    @PutMapping("/bank/deposit/{account}/{deposit}")
    ///putMapping on õigem kasutada, sest uuendame olemasolevaid andmeid, mitte ei loo uut
    public String depositMoney(@PathVariable("account") String accountNr, @PathVariable("deposit") Double deposit) {
        String isBlocked = "SELECT blocked FROM account WHERE accountno = :dbAccountNo"; //küsin andmebaasist välja konkreetse konto staatust
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("dbAccountNo", accountNr);
        Boolean dbBlocked = jdbcTemplate.queryForObject(isBlocked, paraMap, Boolean.class);
//        if (accountNr == null) {
//            return "Selline konto puudub.  Kontrolli andmeid!";
        if (dbBlocked) {
            return "Konto on blokeeritud.Tehingute tegemine keelatud.";
        } else if (deposit > 0) {
            String sql = "SELECT balance FROM account WHERE accountno =:dbAccountNo"; //küsin esialgse balance´i
            //Map<String, Object> paraMap = new HashMap<>(); //enne blokeeringu vahekontrolli lisamis tegin uue map-i siia
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
        String isBlocked = "SELECT blocked FROM account WHERE accountno = :dbAccountNo"; //küsin andmebaasist välja konkreetse konto staatust
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("dbAccountNo", accountNr);
        Boolean dbBlocked = jdbcTemplate.queryForObject(isBlocked, paraMap, Boolean.class);
//        if (accountNr == null) {
//            return "Selline konto puudub.  Kontrolli andmeid!";
        if (dbBlocked) {
            return "Konto on blokeeritud.Tehingute tegemine keelatud.";
        } else if (withdrawamount > 0) {
            String sql = "SELECT balance FROM account WHERE accountno =:dbAccountNo"; //küsin esialgse balance´i
            //Map<String, Object> paraMap = new HashMap<>(); //teen Map-i
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

    @PutMapping("/bank/transfer/{fromAcc}/{amount}/{toAcc}")
    public String transferMoney(@PathVariable("fromAcc") String fromAccount, @PathVariable("amount") Double amount, @PathVariable("toAcc") String toAccount) {
        String isBlocked1 = "SELECT blocked FROM account WHERE accountno = :dbAccountNoFrom"; //küsin andmebaasist välja konkreetse konto staatust
        String isBlocked2 = "SELECT blocked FROM account WHERE accountno = :dbAccountNoTo"; //küsin andmebaasist välja konkreetse konto staatust
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("dbAccountNoFrom", fromAccount);
        paraMap.put("dbAccountNoTo", toAccount);
        Boolean dbBlockedFrom = jdbcTemplate.queryForObject(isBlocked1, paraMap, Boolean.class);
        Boolean dbBlockedTo = jdbcTemplate.queryForObject(isBlocked2, paraMap, Boolean.class);
//        if (fromAccount == null || toAccount == null) { //see ei tööta!
//            return "Vigane kontonumber. Kontrolli andmeid!";
        if (dbBlockedFrom) {
            return "Konto, millelt proovite ülekannet teha, on blokeeritud.Tehingute tegemine keelatud.";
        } else if (dbBlockedTo) {
            return "Konto, kuhu proovite raha kanda, on blokeeritud.Tehingute tegemine keelatud.";
        } else if (amount > 0) {
            String sql1 = "SELECT balance FROM account WHERE accountno =:dbAccountNoFrom"; //küsin esialgse balance´i fromAccounti jaoks
            String sql2 = "SELECT balance FROM account WHERE accountno =:dbAccountNoTo"; //küsin esialgse balance´i toAccounti jaoks
            //Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("dbAccountNoFrom", fromAccount);
            paraMap.put("dbAccountNoTo", toAccount);
            Double dbBalanceFromAccount = jdbcTemplate.queryForObject(sql1, paraMap, Double.class); //küsin andmebaasist esialgse kontojäägi FromAccountile
            Double dbBalanceToAccount = jdbcTemplate.queryForObject(sql2, paraMap, Double.class); //küsin andmebaasist esialgse kontojäägi ToAccountile
            dbBalanceFromAccount = dbBalanceFromAccount - amount; //uus kontojääk peale raha ära kandmist
            dbBalanceToAccount = dbBalanceToAccount + amount; //uus kontojääk peale ülekande laekumist
            String sql3 = "UPDATE account SET balance =:dbBalanceFromAccount WHERE accountno=:dbAccountNoFrom"; //uuendan kontojääki andmebaasis FromAccountil
            String sql4 = "UPDATE account SET balance =:dbBalanceToAccount WHERE accountno=:dbAccountNoTo"; //uuendan kontojääki andmebaasis ToAccountil
            paraMap.put("dbBalanceFromAccount", dbBalanceFromAccount);
            paraMap.put("dbBalanceToAccount", dbBalanceToAccount);
            jdbcTemplate.update(sql3, paraMap);
            jdbcTemplate.update(sql4, paraMap);
            return "Ülekanne teostatud. Kontolt " + fromAccount + " kanti " + amount + " kontole nr " + toAccount +
                    ". Konto jääk peale ülekannet: " + dbBalanceFromAccount + ". Konto " + toAccount + " kontoseis peale ülekannet " +
                    dbBalanceToAccount;
        } else {
            return "Ülekande summa ei saa olla väiksem kui 0 EUR. Kontrolli andmeid!";
        }
    }


}
