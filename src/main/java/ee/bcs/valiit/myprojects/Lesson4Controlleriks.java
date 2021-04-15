package ee.bcs.valiit.myprojects;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Lesson4Controlleriks {

    private static Map<String, Double> accountBalanceMap = new HashMap<String, Double>();

    @GetMapping("/newbankaccount")
    public static String createAccount(@RequestParam("accountNr") String accountNumber, @RequestParam("balance") Double balance) {
        accountBalanceMap.put(accountNumber, balance);
        return "Loodud uus  konto numbriga " + accountNumber + " Vabad vahendid: " + balance + "EUR";
    }

    //http://localhost:8080/newaccount ---> OK, töötab
    @PostMapping("/newaccount")
    //kui on soov saada mitut eraldi muutujat, tuleb teha eraldi klass, kus sees on need muutujad defineeritud
    public void createAccount2(@RequestBody CreateAccountRequest request) { //requestbodysse ei saa kahte muutujat panna
        accountBalanceMap.put(request.getAccountNumber(), request.getBalance());//put-is alati kaks elementi - key ja value
    }

    //http://localhost:8080/getbalance/EE0000--->OK, töötab
    @GetMapping("/getbalance/{accountNumber}")

    public String getBalance(@PathVariable("accountNumber") String accountNumber) {
        Double balance = accountBalanceMap.get(accountNumber);
        return "Konto seis on: " + balance;
    }

    //http://localhost:8080//depositmoney/4444/200 --> OK
    @PutMapping("/depositmoney/{account}/{deposit}")
    //vaata see osa üle, kuhu mitu parameetrit kirjutama peab, et Postmanis tuleks õige tulemus
    public String depositMoney(@PathVariable("account") String accountNumber, @PathVariable("deposit") Double deposit) {
        Double balance = accountBalanceMap.get(accountNumber);
        if (deposit > 0) {
            balance = accountBalanceMap.get(accountNumber);
            balance = balance + deposit;
            accountBalanceMap.put(accountNumber, balance);
            return deposit + " EUR on lisatud kontole number " + accountNumber + " Konto jääk: " + balance;

        } else {
            return "Vigane summa. Proovi uuesti.";
        }
    }

    //http://localhost:8080/withdraw/4444/200/1234 --> ok!
    @PutMapping("/withdraw/{accountnumber}/{withdrawamount}")
    public String withdrawMoney(@PathVariable("accountnumber") String accountNumber, @PathVariable double withdrawamount) {
        accountBalanceMap.get(accountNumber);
        if (withdrawamount < 0) {
            return "Sisestatud summa ei saa olla väiksem kui 0. Palun sisesta soovitud summa.";
        } else {
            if (withdrawamount <= accountBalanceMap.get(accountNumber)) {
                double balance = accountBalanceMap.get(accountNumber);
                accountBalanceMap.put(accountNumber, balance - withdrawamount);
                return "Välja makstud: " + withdrawamount + " EUR. Konto jääk: " + accountBalanceMap.get(accountNumber);
            } else {
                return "Kontol puudub piisavalt vahendeid. Vabad vahendid: " + accountBalanceMap.get(accountNumber);
            }
        }
    }
    //http://localhost:8080/transferMoney/1111/4444/25 --> OK
    @GetMapping("/transferMoney/{fromAccountNr}/{toAccountNr}/{transfer}")
    public String transferMoney(@PathVariable("fromAccountNr") String fromAccountNr, @PathVariable("toAccountNr") String toAccountNr, @PathVariable("transfer") double transfer){
        Double balance = accountBalanceMap.get(fromAccountNr);
        if (transfer>accountBalanceMap.get(fromAccountNr)){
            return "Kontol puudub piisavalt vaba raha";
        } else if (transfer<=accountBalanceMap.get(fromAccountNr)) {
            //vaatan hetkejääki, st selle konto oma, millelt raha kannan:
            balance = balance - transfer;
            //hetkejääk sellel kontol, kuhu raha kantakse
            double toAccountBalance = accountBalanceMap.get(toAccountNr);
            //uuendan kontojääki:
            double balanceFromAccountAfterTransfer = balance - transfer;
            double balanceToAccountAfterTransfer = toAccountBalance + transfer;
            accountBalanceMap.put(fromAccountNr, balanceFromAccountAfterTransfer);
            accountBalanceMap.put(toAccountNr, balanceToAccountAfterTransfer);
            return "Ülekanne teostatud. Kontolt " + fromAccountNr + " kanti " + transfer + " kontole nr " + toAccountNr +
                    ". Konto jääk peale ülekannet: " + balanceFromAccountAfterTransfer + ". Konto " + toAccountNr + " kontoseis peale ülekannet " +
                    balanceToAccountAfterTransfer;
        } else {
            return "Vigane sisend";
        }
    }

//    @GetMapping("/transferMoney/{fromAccount}/{toAccount}/{transferAmount}")
//    public String transferMoney(PathVariable("fromAccount") String fromAccount, @PathVariable("toAccount") String toAccount, @PathVariable
//    double transferAmount){
//        Double balance = accountBalanceMap.get(fromAccount); //fromAccount kontojääk
//        // toAccount = accountBalanceMap.get(toAccount);
//        if (accountBalanceMap.get(fromAccount) < transferAmount) {
//
//            return "Kontol puudub piisavalt vaba raha.";
//
//        } else if (transferAmount <= accountBalanceMap.get(fromAccount)) {
//            //vaatan hetkejääki
//            balance = balance - transferAmount;
//            //Double fromAccountBalance = accountBalanceMap.get(fromAccount);
//            double toAccountBalance = accountBalanceMap.get(toAccount);
//            //uuendan kontojääki
//            Double balanceFromAccountAfterTransfer = balance - transferAmount;
//            Double balanceToAccountAfterTransfer = toAccountBalance + transferAmount;
//            accountBalanceMap.put(fromAccount, balanceFromAccountAfterTransfer);
//            accountBalanceMap.put(toAccount, balanceToAccountAfterTransfer);
//            return "Ülekanne teostatud. Kontolt: " + fromAccount + " kanti " + transferAmount + " kontole nr " + toAccount +
//                    " konto jääk peale ülekannet: " + balanceFromAccountAfterTransfer +
//                    "Ülekanne teostatud. Kontole: " + toAccount + " kanti " + transferAmount + " uus konto jääk " + balanceToAccountAfterTransfer;
//        } else {
//            return "Vigane sisend";
//        }
//
//    }


}

