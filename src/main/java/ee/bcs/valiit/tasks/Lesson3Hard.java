package ee.bcs.valiit.tasks;

import java.util.Random;
import java.util.Scanner;

public class Lesson3Hard {

    // TODO kirjuta mäng mis leiab suvalise numbri 0-99, mille kasutaja peab ära arvama
    // iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
    // ja kasutaja peab saama uuesti arvata
    // numbri ära aramise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks

    public static void main(String[] args) {

        final Random random = new Random();
        final int i = random.nextInt(100);
        System.out.println("Ma mõtlen ühe arvu peale, mis on vahemikus 1 - 100. Arva, mis number see on?");
        //System.out.println(i);
        boolean kasVõitis = false;
        int guesscount = 0;


        Scanner scanner = new Scanner(System.in);
        for (int j = 0; j < 100; j++) {
            System.out.println("Sisesta oma number:");
            int guess = scanner.nextInt();
            guesscount++;
            if (i < guess) {
                System.out.println("Minu number on väiksem kui " + guess);
            } else if (i > guess) {
                System.out.println("Minu number on suurem  kui " + guess);
            } else {
                kasVõitis = true;
                System.out.println("Juhhei, õige number! Sinu võit! Arvasid õige numbri ära :" + guesscount + " korraga.");
                break;
            }
        }
    }
}

