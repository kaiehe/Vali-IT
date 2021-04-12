package ee.bcs.valiit.tasks;

public class Lesson2 {

    public static void main(String[] args) {
        // TODO siia saab kirjutada koodi testimiseks
        // int[] returnArray = {1,2,3,4,5};
        //   System.out.println(Arrays.toString(reverseArray(returnArray)));
        //  multiplyTable(3, 3);
        System.out.println(fibonacci(10));
    }

    // TODO loe funktsiooni sisendiks on täisarvude massiiv
    // TODO tagasta massiiv mille elemendid on vastupidises järiekorras
    public static int[] reverseArray(int[] inputArray) {

        int[] returnArray = new int[inputArray.length]; //teen uue array sama pikkusega, mis algne array

        for (int i = inputArray.length - 1; i >= 0; i--) { //for loop algab, talle peab järgnema tegevus loogeliste sulgude vahel
            returnArray[i] = inputArray[inputArray.length - i - 1]; //siia pane see meetod, kuidas täidetakse returnArray väärtusi

        }
        return returnArray;
    }

    // TODO tagasta massiiv mis sisaldab n esimest paaris arvu
    // Näide:
    // Sisend 5
    // Väljund 2 4 6 8 10
    public static int[] evenNumbers(int n) {
        int[] returnArray = new int[n]; //array defineerimine
        for (int i = 0; i < n; i++) { //i<n asemele võib panna ka returnArray.length
            returnArray[i] = (i + 1) * 2;
        }

        return returnArray;
    }

    // TODO, leia massiivi kõige väiksem element
    public static int min(int[] x) {
        int min = x[0]; //defineerime esimese elemendi arrayst
        for (int i = 1; i < x.length; i++)//ei ole mõtet võrrelda eelnevalt defineeritud int min-i iseendaga, selle pärast algab nr 1ga
            if (x[i] < min) //arrayNimi[0] <  minElement -->ei saa võrrelda nulliga
                min = x[i];
        return min;
    }

    // TODO, leia massiivi kõige suurem element
    public static int max(int[] x) {
        int max = x[0];
        for (int i = 1; i < x.length; i++)
            if (x[i] > max)
                max = x[i];

        return max;
    }

    // TODO, leia massiivi kõigi elementide summa
    public static int sum(int[] x) {
        int sum = 0; //ütlen, et summa on null
        for (int i : x) {  //võrdne for(int i=0; i<x.length;i++) -kas see on õige?
            sum += i;
        }
        return sum;
    }

    // TODO trüki välja korrutustabel mis on x ühikut lai ja y ühikut kõrge
    // TODO näiteks x = 3 y = 3
    // TODO väljund
    // 1 2 3
    // 2 4 6
    // 3 6 9
    // TODO 1 trüki korrutustabeli esimene rida (numbrid 1 - x) -ok
    // TODO 2 lisa + " " print funktsiooni ja kasuta print mitte println -ok
    // TODO 3 trüki seda sama rida y korda
    // TODO 4 Kuskile võiks kirjutada System.out.println(),
    //  et saada taebli kuju
    // TODO 5 võrdle ridu. Kas on mingi seaduspärasus ridade vahel,
    // mis on ja mis võiks olla. Äkki tuleb mõni idee

    public static void multiplyTable(int x, int y) {
//        x = 3;
//        y = 3; //assign value to variables

        for (int i = 1; i < x + 1; i++) {
            for (int j = 1; j < y + 1; j++) {
                System.out.print(i * j + " ");

            }
            System.out.println();
        }
    }

    // TODO
    // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
    // 0, 1, 1, 2, 3, 5, 8, 13, 21
    // Tagasta fibonacci jada n element. Võid eeldada, et n >= 0

    public static int fibonacci(int n) {
        int fib1 = 0; //defineerin muutuja üks, mis esindab jadas fib(n-1)
        int fib2 = 1; //defineerin muutuja kaks, mis esindab jadas fib(n-2)
        System.out.print(fib1); //prindin välja esimese numbri

        if (n == 0) { //tagasta esmalt null juhul kui n on 0
            return n;
        }
        for (int i = 1; i < n; i++) { //alustasin loopi 1st, sest 0 tuleb juhul, kui n on 0 ja sellega ei ole mõtet uuesti 0-ga alustada.
            int sum = fib1 + fib2; //kõigepealt pean leidma fib1 ja fib2 summa (fib valemi jaoks tuleb kõik järgnevad arvud kokku liita
            System.out.print(" " + fib2); //prindin välja kõik ülejäänud alates teisest numbrist
            fib1 = fib2; //omistan fib 1-le fib 2 väärtuse
            fib2 = sum; //omistan fib1 ja fib2 summa fib2-le

        }
        return fib2;
    }

    // TODO
    // Kujutame ette numbrite jada, kus juhul kui number on paaris arv siis me jagame selle 2-ga
    // Kui number on paaritu arv siis me korrutame selle 3-ga ja liidame 1. (3n+1)
    // Seda tegevust teeme me niikaua kuni me saame vastuseks 1
    // Näiteks kui sisend arv on 22, siis kogu jada oleks:
    // 22 -> 11 -> 34 -> 17 -> 52 -> 26 -> 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
    // Sellise jada pikkus on 16
    // Kirjutada programm, mis peab leidma kõige pikema jada pikkuse mis jääb kahe täis arvu vahele
    // Näiteks:
    // Sisend 10 20
    // Siis tuleb vaadata, kui pikk jada tuleb kui esimene numbr on 10. Järgmisen tuleb arvutada number 11 jada pikkus.
    // jne. kuni numbrini 20
    // Tagastada tuleb kõige pikem number
    // Näiteks sisendi 10 ja 20 puhul on vastus 20


    //tehke tsükkel x->y
    //kutsuge iga väärtuse korral välja meetodit getSeqLegth
    //salvestage maha kõige suurem ja funktsiooni lõpus tagastage see
    public static int sequence3n(int x, int y) {
        return 0;
    }



}
