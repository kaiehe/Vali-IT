package ee.bcs.valiit.myprojects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BankManager {
private static Map<String, BankAccount> accountBalanceMap = new HashMap<>();

    public static void main(String[] args) {

    }

    //http://localhost:8080//bankaccount/newaccount?accountNr=1234&balance=100&name=Siim
    @GetMapping("/bankaccount/newaccount")
    public void newAccount(@RequestParam("accountNr") String accountNr,
                           @RequestParam("balance") double balance,
                           @RequestParam("name") String name) {
        BankAccount account = new BankAccount();
        account.setAccountNr(accountNr);
        account.setBalance(balance);
        account.setOwner(name);
        account.setLocked(false);
        accountBalanceMap.put(accountNr, account); //miks siia tuli account?kas st, et @RequestParam salvestati BankAccount klassi?
    }



}
