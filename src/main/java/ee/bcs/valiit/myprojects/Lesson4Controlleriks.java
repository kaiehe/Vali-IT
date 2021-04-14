package ee.bcs.valiit.myprojects;

import java.util.HashMap;
import java.util.Map;

public class Lesson4Controlleriks {
    private static Map<String, Double> accountBalanceMap = new HashMap<String, Double>();

    public static String createAccount(String accountNumber) {
        double balance = 0.00;
        accountBalanceMap.put(accountNumber, balance);
        return "Loodud uus  konto numbriga " +accountNumber+ " Vabad vahendid: " + balance + "EUR";
    }

    public static String getBalance(String accountNumber, double balance) {
        accountBalanceMap.get(accountNumber);
        return "Kontol number: " + accountNumber + " on " + balance + " EUR";
    }


}

