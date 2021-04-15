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

    @PostMapping("/newaccount")
    public void createAccount2(@RequestBody CreateAccountRequest request){
        accountBalanceMap.put(request.getAccountNumber(), request.getBalance());
    }

    @GetMapping("/getbalance/{accountNumber}")
    public static String getBalance(@PathVariable("accountNumber") String accountNumber) {
        double balance = accountBalanceMap.get(accountNumber);
        return "Kontol seis on: " + accountBalanceMap.get(accountNumber);
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

    public static void transferMoney() {
        System.out.println("Ülekande tegemine. Palun sisesta konto number, millelt soovid raha üle kanda:");
        String fromAccount = scanner.nextLine();
        System.out.println("Sisesta konto number kuhu soovid raha kanda:");
        String toAccount = scanner.nextLine();
        System.out.println("Palun sisesta summa, mida soovid üle kanda:");
        double transferAmount = scanner.nextDouble();
        scanner.nextLine();
        if (accountBalanceMap.get(fromAccount) < transferAmount) {
            System.out.println("Kontol puudub piisavalt vaba raha. Proovi uuesti");
        } else {
            //vaatan hetkejääki
            double fromAcccountBalance = accountBalanceMap.get(fromAccount);
            double toAccountBalance = accountBalanceMap.get(toAccount);
            //uuendan kontojääki
            double balanceFromAccountAfterTransfer = fromAcccountBalance - transferAmount;
            double balanceToAccountAfterTransfer = toAccountBalance + transferAmount;
            accountBalanceMap.put(fromAccount, balanceFromAccountAfterTransfer);
            accountBalanceMap.put(toAccount, balanceToAccountAfterTransfer);
            System.out.println("Ülekanne teostatud. Kontolt: " + fromAccount + " kanti " + transferAmount + " kontole nr " + toAccount + " konto jääk peale ülekannet: " + balanceFromAccountAfterTransfer);
            System.out.println("Ülekanne teostatud. Kontole: " + toAccount + " kanti " + transferAmount + " uus konto jääk " + balanceToAccountAfterTransfer);
        }

    }



}

