package ee.bcs.valiit.tasks;

import java.util.Scanner;

// TODO kasuta if/else. Ära kasuta Math librarit
public class Lesson1 {
    public static void main(String[] args) {
        Scanner  scanner  = new Scanner(System.in); //initialize scanner
        System.out.println("Sisesta soovitud meetod: min, max, abs, even, min3 või max3");
        String choice = scanner.nextLine(); //save input(Stringina) to use for switch cases

        switch (choice.toLowerCase()) {
            case "min": {
                System.out.println("Sisesta a:"); //1 sisend kasutajalt
                int a = scanner.nextInt(); //salvesta sisend meetodi jaoks
                System.out.println("Sisesta b:"); //2 sisend kasutajalt
                int b = scanner.nextInt(); //salvesta sisend
                System.out.println("Väikseim number on " + min(a, b)); //kuva meetod kasutades kasutaja poolt sisestatud sisendeid
                break;
            }
            case "max": {
                System.out.println("Sisesta a:");
                int a = scanner.nextInt();
                System.out.println("Sisesta b:");
                int b = scanner.nextInt();
                System.out.println("Suurim number on: " + max(a, b));
                break;
            }
            case "abs": {
                System.out.println("Sisesta number:");
                int a = scanner.nextInt();
                System.out.println("Absoluutnumber on: " + abs(a));
                break;
            }
            case "even": {
                System.out.println("Sisesta number:");
                int a = scanner.nextInt();
                if (isEven(a)) { //lihtsustatud, st see isEven(a) tähendab sama mis isEven(a)=true
                    System.out.println(a + " on paarisarv");
                } else {
                    System.out.println(a + " on paaritu arv");
                }
                break;
            }
            case "min3": {
                System.out.println("Sisesta a:");
                int a = scanner.nextInt();
                System.out.println("Sisesta b:");
                int b = scanner.nextInt();
                System.out.println("Sisesta c:");
                int c = scanner.nextInt();
                System.out.println("Väikseim number kolmest on: " + min3(a, b, c));
                break;
            }
            case "max3": {
                System.out.println("Sisesta a:");
                int a = scanner.nextInt();
                System.out.println("Sisesta b:");
                int b = scanner.nextInt();
                System.out.println("Sisesta c:");
                int c = scanner.nextInt();
                System.out.println("Suurim number kolmest on: " + max3(a, b, c));
                break;
            }
            default:
                System.out.println("Sisestuse viga, palun proovi uuesti");
                break;
        }

         // Siia sisse võid sa kirjutada koodi, et testida kas su meetodid töötavad ilusti
        // Katseta IntelliJ shortcuti. Olles intelliJ kirjuta "sout" ja siis vajuta enter
        // System.out.println(min(1, 3)); // trükib miinimumi 1 ja 3
    }

    // TODO tagasta a ja b väikseim väärtus
    public static int min(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }

    // TODO tagasta a ja b suurim väärtus
    public static int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    // TODO tagasta a absoluut arv
    public static int abs(int a) {
       if (a>=0){
           return a;
       } else {
           return a*(-1); //võib kirjutada ka -a selle sama tehte asemel
       }
    }

    // TODO tagasta true, kui a on paaris arv
    // tagasta false kui a on paaritu arv
    public static boolean isEven(int a) {
        if (a % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    // TODO tagasta kolmest arvust kõige väiksem
    public static int min3(int a, int b, int c) {
        if (a <= b && a <= c) {
            return a;
        } else if (b <= a && b <= c) {
            return b;
        } else {
            return c;
        }
    }

    // TODO tagasta kolmest arvust kõige suurem
    public static int max3(int a, int b, int c) {
        //saab ka nii et võtan maksimumi sellest funktsioonist nagu just kirjutasin ja saadust omakorda max
        //return max(max(a,b), c);
        if (a >= b && a >= c) {
            return a;
        } else if (b >= a && b >= c) {
            return b;
        } else {
            return c;
        }
    }
}