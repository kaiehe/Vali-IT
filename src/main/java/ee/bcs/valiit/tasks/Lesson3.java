package ee.bcs.valiit.tasks;

import java.util.HashMap;
import java.util.Map;

public class Lesson3 {

    public static void main(String[] args) {
        // reverseString("Hello");
        evenFibonacci(8);
    }

    // TODO tagasta x faktoriaal.
    // Näiteks
    // x = 5
    // return 5*4*3*2*1 = 120
    public static int factorial(int x) {
        int factorial = 1;
        for (int i = 1; i <= x; i++) {
            factorial = factorial * i;
        }

        return factorial;
    }

    // TODO tagasta string tagurpidi
    public static String reverseString(String a) {

        System.out.println("Algne sõna: " + a);
        String uusLause = ""; //teen uue stringi
        for (int i = 0; i < a.length(); i++) {
            uusLause += a.charAt(a.length() - i - 1); //võtan järjest tagantpoolt tähti ja panen uude stringi
        }
        System.out.println("Tagurpidi lause: " + uusLause);

        return uusLause;

    }

    // TODO tagasta kas sisestatud arv on primaar arv (jagub ainult 1 ja iseendaga)
    public static boolean isPrime(int x) {
        if (x == 1) { //kui on 1, siis ei alusta edasist otsimist
            return false;
        }
        for (int i = 2; i < x; i++) { //siin algab tsükkel, 2 on siin sel põhjusel, et nr 1 on juba eelduses ära kontrollitud
            if (x % i == 0) { //kui jagub kahega, tagasta false, sest ei ole primaararv
                return false;
            }
        }
        return true;
    }

    // TODO sorteeri massiiv suuruse järgi.
    // TODO kasuta tsükleid, ära kasuta ühtegi olemasolevat sort funktsiooni
    public static int[] sort(int[] a) {
        int max = a[0];
        for (int i = 0; i < a.length; i++) { //alustan kohalt 0, sest array esimene element alati kohal 0. Jätkan nii kaua kui pikk on jada
            for (int j = i + 1; j < a.length; j++) { //alustan i+1st sest esimene number on arrays juba hinnatud
                int ajutine = 0;//toon uue muutuja sisse vahemälu jaoks
                if (a[i] > a[j]) {//võrdlen esimest ja järgnevat muutujat
                    ajutine = a[i]; //salvestan esimese muutuja ajutisele kohale
                    max = a[i]; //salvestan  hetkeseisuga leitud maksimumi
                    a[i] = a[j]; //salvestan esimesse arraysse i väärtuse
                    a[j] = ajutine;
                }
            }
        }
        return a;
    }

    public static int evenFibonacci(int x) {
        // TODO liida kokku kõik paaris fibonacci arvud kuni numbrini x
        int fib1 = 0; //defineerin muutuja üks, mis esindab jadas fib(n-1)
        int fib2 = 1; //defineerin muutuja kaks, mis esindab jadas fib(n-2)
        int sumEven = 0;

        if (x == 0) { //tagasta null juhul kui n on 0
            return x;
        }
        //   for (int i = 1; i < x; i++) {
        while (fib1 + fib2 <= x) {
            int sum = fib1 + fib2; //kõigepealt pean leidma fib1 ja fib2 summa (fib valemi jaoks tuleb kõik järgnevad arvud kokku liita
            fib1 = fib2; //omistan fib 1-le fib 2 väärtuse
            fib2 = sum; //omistan fib1 ja fib2 summa fib2-le
            if (fib2 % 2 == 0) {
                sumEven += fib2;
            }
        }
        return sumEven;
    }

    public static String morseCode(String text) {
        // TODO kirjuta programm, mis tagastab sisestatud teksti morse koodis (https://en.wikipedia.org/wiki/Morse_code)
        // Kasuta sümboleid . ja - ning eralda kõik tähed tühikuga
        Map<String, String> morse = new HashMap<>();

        //sisestan vajalikud tähed:
        morse.put("s", "...");
        morse.put("o", "---");
        morse.put("h", "....");
        morse.put("e", ".");
        morse.put("l", ".-..");

        //sama versioon nagu allpool toodud char i:text.toCharArray, lihtsalt teist for tsüklit kasutades
        //for (int i  =  0; i<text.length();i++){
         //   char c  = text.charAt(i);
        //}

        String morseks = ""; //teen tühja Stringi, kuhu salvestan vajalikud tähed
        for (char i : text.toCharArray()) { //tekitan eraldi character array, iga indeksi i väärtus tagastatakse iga järgneva korraga järgmisel real. Nt i=s, järgmisena i=0 jne
            morseks += morse.get((i + "")) + " "; //tsükkel käib kogu array läbi ja lisab/salvestab saadud tähed String morseks reale.
        }

        return morseks.trim(); //võtan ebavajaliku tühiku lõpust ära trim-iga
    }
}
