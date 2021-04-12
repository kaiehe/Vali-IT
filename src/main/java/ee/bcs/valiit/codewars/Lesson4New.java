package ee.bcs.valiit.codewars;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Lesson4New {
    // Store account nr as a key and account balance as value
    //HashMap<String, Account> accountBalanceMap = new HashMap<>();
    private static Map<String, Double> accountBalanceMap = new HashMap<String, Double>();

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Tere tulemast JAVA panka!");
        while (true) {

            System.out.println("============================================================\n" +
                    "Palun vali toiming, mida soovid teha\n" +
                    "1 - soovin luua arvelduskonto\n" +
                    "2 - soovin vaadata kontojääki\n" +
                    "3 - soovin teha oma kontole sissemakset\n" +
                    "4 - soovin kontolt raha välja võtta\n" +
                    "5 - soovin teha ülekannet\n" +
                    "6 - väljun pangast\n" +
                    "============================\n" +
                    "Sisesta number:");
            int valik = scanner.nextInt();
            scanner.nextLine();

            switch (valik) {
                case 1:
                    //createAccount();
                    System.out.println("Uue konto loomine. Sisesta soovitud konto nr:");
                    String accountNumber = scanner.nextLine();
                    System.out.println("Sisesta algne kontojääk");
                    double balance = scanner.nextDouble();
                    accountBalanceMap.put(accountNumber, balance);
                    System.out.println("Konto number: " + "vabad vahendid: " + balance + "EUR");
                    break;
                case 2:
                    System.out.println("Kontojäägi päring. Palun sisesta oma konto number:");
                    accountNumber = scanner.nextLine();
                    System.out.println("Kontojääk on: " + accountBalanceMap.get(accountNumber));
                   // getBalance();
                    break;
                case 3:
                    System.out.println("Raha sissemakse. Palun sisesta konto number kuhu soovid raha sisse maksta:");
                    accountNumber = scanner.nextLine();
                    System.out.println("Palun sisesta summa, mida soovid sisse maksta:");
                    double deposit = scanner.nextDouble();
                    scanner.nextLine();
                    if (deposit > 0) {
                        balance = accountBalanceMap.get(accountNumber);
                        balance = balance + deposit;
                        accountBalanceMap.replace(accountNumber, balance);
                        System.out.println(deposit + " EUR on lisatud kontole number " + accountNumber + ". Uus kontojääk on: " + balance);
                    } else {
                        System.out.println("Vigane summa. Proovi uuesti.");
                    }
                    //depostitMoney();
                    break;
                case 4:
                    System.out.println("Raha välja võtmine. Palun sisesta konto number, millelt soovid raha välja võtta:");
                    accountNumber = scanner.nextLine();
                    //siia vahele pean sisestama veel konto numbri kontrolli!!
                    System.out.println("Palun sisesta soovitud summa:");
                    double withdraw = scanner.nextDouble();
                    scanner.nextLine();
                    if (withdraw < 0) {
                        System.out.println("Sisestatud summa ei saa olla väiksem kui 0. Palun sisesta soovitud summa.");
                    } else {
                        if (withdraw <= accountBalanceMap.get(accountNumber)) {
                            balance = accountBalanceMap.get(accountNumber);
                            accountBalanceMap.put(accountNumber, balance - withdraw);
                            System.out.println("Välja makstud: " + withdraw + " EUR. Konto jääk: " + accountBalanceMap.get(accountNumber));
                        } else {
                            System.out.println("Kontol puudub piisavalt vahendeid. Vabad vahendid: " + accountBalanceMap.get(accountNumber));
                        }
                    }
                    //withdrawMoney();
                    break;
                case 5:
                    System.out.println("Ülekande tegemine. Palun sisesta konto number, millelt soovid raha üle kanda:");
                    String fromAccount = scanner.nextLine();
                    System.out.println("Sisesta konto number kuhu soovid raha kanda:");
                    String toAccount = scanner.nextLine();
                    System.out.println("Palun sisesta summa, mida soovid üle kanda:");
                    double transferAmount = scanner.nextDouble();
                    scanner.nextLine();
                    if (accountBalanceMap.get(fromAccount)<transferAmount){
                        System.out.println("Kontol puudub piisavalt vaba raha. Proovi uuesti");
                    }
                    else {
                        //vaatan hetkejääki
                        double fromAcccountBalance = accountBalanceMap.get(fromAccount);
                        double toAccountBalance = accountBalanceMap.get(toAccount);
                        //uuendan kontojääki
                        double balanceFromAccountAfterTransfer = fromAcccountBalance - transferAmount;
                        double balanceToAccountAfterTransfer = toAccountBalance + transferAmount;
                        accountBalanceMap.put(fromAccount, balanceFromAccountAfterTransfer);
                        accountBalanceMap.put(toAccount, balanceToAccountAfterTransfer);
                        System.out.println("Ülekanne teostatud. Kontolt: " + fromAccount + " kanti " + transferAmount+ " kontole nr " + toAccount);
                    }
                    //transferMoney();
                    break;
                case 6:
                    System.out.println("Oled edukalt välja logitud. Jällenägemist!");
                    break;
                default:
                    System.out.println("Selline number puudub valikus. Proovi uuesti.");

            }

        }
    }

    //// TODO 1
//// Add command: "createAccount ${accountNr}"
//// this has to store accountNr with 0 balance
//    public static void createAccount() {
//
//        System.out.println("Uue konto loomine. Sisesta soovitud konto nr:");
//        double balance = 0;
//        String accountNumber = scanner.nextLine();
//        accountBalanceMap.put(accountNumber, balance);
//        System.out.println("Konto number: " + "vabad vahendid: " + balance + "EUR");
//
//    }

    //// TODO 2
//// Add command: "getBalance ${accountNr}"
//// this has to display account balance of specific acount
//    public static void getBalance() {
//        System.out.println("Kontojäägi päring. Palun sisesta oma konto number:");
//        String accountNumber = scanner.nextLine();
//        System.out.println("Kontojääk on: " + accountBalanceMap.get(accountNumber));
//    }

    //// TODO 3
//// Add command: "depositMoney ${accountNr} ${amount}
//// this has to add specified amount of money to account
//// You have to check that amount is positive number
//    public static void depostitMoney() {
//        System.out.println("Raha sissemakse. Palun sisesta konto number kuhu soovid raha sisse maksta:");
//        String accountNumber = scanner.nextLine();
//        System.out.println("Palun sisesta summa, mida soovid sisse maksta:");
//        double deposit = scanner.nextDouble();
//        scanner.nextLine();
//        if (deposit > 0) {
//            double balance = accountBalanceMap.get(accountNumber);
//            balance = balance + deposit;
//            accountBalanceMap.replace(accountNumber, balance);
//            System.out.println(deposit + " EUR on lisatud kontole number " + accountNumber + ". Uus kontojääk on: " + balance);
//        } else {
//            System.out.println("Vigane summa. Proovi uuesti.");
//        }
//    }

    //// TODO 4
//// Add command: "withdrawMoney ${accountNr} ${amount}
//// This has to remove specified amount of money from account
//// You have to check that amount is positive number
//// You may not allow this transaction if account balance would become negative
//    public static void withdrawMoney() {
//        System.out.println("Raha välja võtmine. Palun sisesta konto number, millelt soovid raha välja võtta:");
//        String accountNumber = scanner.nextLine();
//        //siia vahele pean sisestama veel konto numbri kontrolli!!
//        System.out.println("Palun sisesta soovitud summa:");
//        double withdraw = scanner.nextDouble();
//        scanner.nextLine();
//        if (withdraw < 0) {
//            System.out.println("Sisestatud summa ei saa olla väiksem kui 0. Palun sisesta soovitud summa.");
//        } else {
//            if (withdraw <= accountBalanceMap.get(accountNumber)) {
//                double balance = accountBalanceMap.get(accountNumber);
//                accountBalanceMap.put(accountNumber, balance - withdraw);
//                System.out.println("Välja makstud: " + withdraw + " EUR. Konto jääk: " + accountBalanceMap.get(accountNumber));
//            } else {
//                System.out.println("Kontol puudub piisavalt vahendeid. Vabad vahendid: " + accountBalanceMap.get(accountNumber));
//            }
//        }
//    }

    //// TODO 5
//// Add command: "transfer ${fromAccount} ${toAccount} ${amount}
//// This has to remove specified amount from fromAccount and add it to toAccount
//// Your application needs to check that toAccount is positive
//// And from account has enough money to do that transaction
//    public static void transferMoney() {
//        System.out.println("Ülekande tegemine. Palun sisesta konto number, millelt soovid raha üle kanda:");
//        String fromAccount = scanner.nextLine();
//        System.out.println("Sisesta konto number kuhu soovid raha kanda:");
//        String toAccount = scanner.nextLine();
//        System.out.println("Palun sisesta summa, mida soovid üle kanda:");
//        double transferAmount = scanner.nextDouble();
//        scanner.nextLine();
//        if (accountBalanceMap.get(fromAccount)<transferAmount){
//            System.out.println("Kontol puudub piisavalt vaba raha. Proovi uuesti");
//        }
//        else {
//            //vaatan hetkejääki
//            double fromAcccountBalance = accountBalanceMap.get(fromAccount);
//            double toAccountBalance = accountBalanceMap.get(toAccount);
//            //uuendan kontojääki
//            double balanceFromAccountAfterTransfer = fromAcccountBalance - transferAmount;
//            double balanceToAccountAfterTransfer = toAccountBalance + transferAmount;
//            accountBalanceMap.put(fromAccount, balanceFromAccountAfterTransfer);
//            accountBalanceMap.put(toAccount, balanceToAccountAfterTransfer);
//            System.out.println("Ülekanne teostatud. Kontolt: " + fromAccount + " kanti " + transferAmount+ " kontole nr " + toAccount);
//        }
//
//    }


}
