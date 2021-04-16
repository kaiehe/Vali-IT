package ee.bcs.valiit.myprojects;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BankManager {
    private static Map<String, BankAccount> banker = new HashMap<>();


    //    //http://localhost:8080//bankaccount/newaccount?accountNr=1234&balance=100&name=Siim
//    @GetMapping("/bankaccount/newaccount")
//    public void newAccount(@RequestParam("accountNr") String accountNr,
//                           @RequestParam("balance") double balance,
//                           @RequestParam("name") String name) {
//        BankAccount account = new BankAccount();
//        account.setAccountNr(accountNr);
//        account.setBalance(balance);
//        account.setOwner(name);
//        account.setLocked(false);
//        banker.put(accountNr, account); //salvestan sisendandmed BankAccount klassi
//    }
    //http://localhost:8080//bankaccount/createnewaccount
    @PostMapping("/bankaccount/createnewaccount")
    public void newAccount(@RequestBody BankAccount request) {
        banker.put(request.getAccountNr(), request);
    }

    //http://localhost:8080//bankaccount/accountbalance/4444
    @GetMapping("/bankaccount/accountbalance/{accountNr}")
    public String accountBalance(@PathVariable("accountNr") String accountNr) {
        if (banker.get(accountNr).getAccountNr() == null) {
            return "Selline konto puudub";
        } else if (banker.get(accountNr).isAccountStatus()) { //vaikimisi on accountStatus blokeeritud
            return "Konto on blokeeritud. Pöördu panga infotelefoni poole";
        } else {
            return "Konto " + accountNr + " jääk on " + banker.get(accountNr).getBalance();
        }
    }
//    @PutMapping("/bankaccount/deposit/{accountNr}")
//    public String accountDeposit()

}
