package ee.bcs.valiit.myprojects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Lesson4Controlleriks {

    private static Map<String, Double> accountBalanceMap = new HashMap<String, Double>();

    @GetMapping("/newbankaccount")
    public static String createAccount(@RequestParam String accountNumber) {
        double balance = 0.00;
        accountBalanceMap.put(accountNumber, balance);
        return "Loodud uus  konto numbriga " + accountNumber + " Vabad vahendid: " + balance + "EUR";
    }

    @GetMapping("/getbalance")
    public static String getBalance(@RequestParam String accountNumber) {
        double balance = accountBalanceMap.get(accountNumber);
        return "Kontol number: " + accountNumber + " on " + balance + " EUR";
    }

    public static String depositMoney(String accountNumber, double deposit) {
        accountBalanceMap.get(accountNumber);
        if (deposit > 0) {
            double balance = accountBalanceMap.get(accountNumber);
            balance = balance + deposit;
            accountBalanceMap.put(accountNumber, balance);
            return deposit + " EUR on lisatud kontole number " + accountNumber;

        } else {
            return "Vigane summa. Proovi uuesti.";
        }
    }

    public static String withdrawMoney(String accountNumber, double withdrawamount) {
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


}

