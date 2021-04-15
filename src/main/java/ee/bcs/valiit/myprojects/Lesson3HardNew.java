package ee.bcs.valiit.myprojects;

import java.util.Random;
//need näitajad, mis ei tohi muutuda, tõsta funktsioonist välja klassi alla instantsimuutujateks
public class Lesson3HardNew {
    final static Random random = new Random();
    final static int randomnumber = random.nextInt(100);
    static int count = 0;

    public static String numbersGuess(int guess) {
        //int guesscount = 0;
        // boolean kasVõitis = false;
        count++;
        if (guess < randomnumber) {
            return "Number on suurem kui " + guess;
        } else if (guess > randomnumber) {
            return "Number on väiksem kui " + guess;
        } else {
            String response = "Juhhei, õige number! Sinu võit! Arvasid ära " + count + " korraga";
            int count=0;
            return "Juhhei, õige number! Sinu võit!";
        }

    }
}