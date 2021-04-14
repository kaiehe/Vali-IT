package ee.bcs.valiit.myprojects;

import java.util.Random;

public class Lesson3HardNew {
    final static Random random = new Random();
    final static int randomnumber = random.nextInt(100);
//    int count = 0;

    public static String numbersGuess(int guess) {
        //int guesscount = 0;
        // boolean kasVõitis = false;

        while (guess != randomnumber) {

            if (guess < randomnumber) {
                return "Number on suurem kui " +guess;
            } else {
                return "Number on väiksem kui "+guess;
            }
        }
        return "Juhhei, õige number! Sinu võit!";
    }
}