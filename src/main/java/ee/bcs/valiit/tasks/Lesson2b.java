package ee.bcs.valiit.tasks;

import java.util.Arrays;

public class Lesson2b {
    public static void main(String[] args) {
        exercise1(5);
        System.out.println(Arrays.toString(sampleArray())); //kuidas seda printida sai? ->  Arrays.toString()
//        sampleArray();
        System.out.println(Arrays.toString(generateArray(5)));
        System.out.println(Arrays.toString(decreasingArray(5)));
    }

    // TODO trüki välja n arvu
    // näiteks
    // sisend: 5
    // trüki välja: 1 2 3 4 5
    public static void exercise1(int n) {
        System.out.println("Excercise 1 tulemus: ");
        int i = 1;
        while (i <= n) {
            System.out.print(i + " ");
            i++;
        }
        System.out.println();
    }

    // TODO tagasta massiiv milles oleks numbrid 1,2,3,4,5
    public static int[] sampleArray() {
        System.out.println("Excercise sampleArray tulemus: ");
        int[] array = new int[]{1, 2, 3, 4, 5};
        //kõige lihtsam lahendus on:
        // return new int[]{1,2,3,4,5};
//        Teine variant:
//        for (int i = 0; i < array.length; i++) {
//            System.out.print(array[i] + " ");
// //           array[i] = i + 1;
//        }
        return array;
    }

    // TODO loo massiiv mis saab sisendiks n ja tagastab massiivi suurusega n
    // Kus esimene element on 1 ja iga järnev arv on 1 võrra suurem
    // näiteks:
    // sisend: 5
    // vastus: {1, 2, 3, 4, 5}
    public static int[] generateArray(int n) {
        System.out.println("Exercise generateArray tulemus:");
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) { //array.lengthi asemele võib kirjutada ka n-i, see teeb sama välja
//            System.out.print(array[i] + " "); //see on ainult enda jaoks, et ekraanil näha, aga sellel ülesandel ei ole vaja for tsüklit
            array[i] = i + 1;
        }
        return array;
    }

    // TODO
    // Tagastada massiiv kus oleks numbrid n-ist 0 ini
    // Näiteks: sisend: 5
    // Väljund: 5, 4, 3, 2, 1, 0
    // Näide2: siend: -3
    // Väljund: -3, -2, -1, 0
    public static int[] decreasingArray(int n) {
        System.out.println("Exercise decreasingArray tulemus:");
        if (n >= 0) { //siia loopi lähed sisse ainult siis kui n on positiivne arv
            int[] array = new int[n+1]; //loome uue array pikkusega n+1
            for (int i = 0; i <= n; i++) {
                array[i] = n - i;
                //int[] array = new int[n + 1];
                //
                // for(int i = n; i>=0; i--)
            }
            return array;
        } else { //siia loopi lähed sisse siis kui n on negatiivne
            int[] array = new int[-n + 1]; //-n teeb miinusarvust positiivse ehk tuleb nagu absoluutarv välja
            for (int i = n; i <= 0; i++) { //vt see üle mõlema puhul, kust int i algab ja miks ta sealt algab
                array[-n + i] = i;
            }
//            array[0]= i; //-(-5)+(-5)=0; i on  igal  real erinev, n on  ette antud n on -5,kas on võimalik väljendada n ja i suhtega kuidagi?
//            array[-n+i]= i; //-(-5)+(-4)=1
//            array[2]= i;
//            array[3]= i;
//            array[4]= i;
//            array[5]= i;
//
            return array;
        }

    }


    // TODO
    // tagasta massiiv pikkusega n, mille kõigi elementide väärtused on 3
    public static int[] yl3(int n) {
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = 3; //kirjutad sisse, et iga i koha peal on väärtuseks 3;
        }

        return array;
    }
}
