package ee.bcs.valiit.tasks;

public class Lesson2c {

    public static void main(String[] args) {
        System.out.println(getSeqLength(2));

    }

    // TODO
    // Täüsem lugemine: https://onlinejudge.org/external/1/100.pdf
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

    // TODO 3
    // tehke tsükkel x -> y
    // kutsuge iga väärtuse korral välja meetodit getSeqLength
    // salvestage maha kõige suurem ja funktsiooni lõpus tagastage see
    public static int sequence3n(int x, int y) {
        int maxJada = 1; //ehk et ka 0 puhul on jada pikkus 1
        int jadaPikkus; //muutuja kuhu   salvestada n hetkel saadud jada pikkus
        for (int i = x; i <= y; i++) { //tsükkel käivitub muutujate x ja y suuruste vahel
            jadaPikkus = getSeqLength(i); //salvestan jadaPikkuse alla hetke loopis oleva jada pikkuse
            if (maxJada < jadaPikkus) { //võrdlen max jada ja viimati salvestatud jada suurust
                maxJada = jadaPikkus; //kui viimati salvestatud jada on suurem, siis see lähebki maxJada kohale
            }
        }
        return maxJada;
    }

    // TODO 2
    // x = 1 ->1
    // x = 2 -> 2
    // kutsuge välja meetodit nextElement nii kaua kuni vastus tuleb 1
    // tagastage korduste arv + 1 -juhulkuilugemist alustad 0-st

    //saab teha ka for tsükliga
    // int i =1;
    //for(i=1;
    //
    //
    //

    public static int getSeqLength(int x) {
        int count = 1; // count tähendab mitmenda numbriga meil on tegu, st kui x =1, siis jada pikkus  on ka 1
        while (x > 1) { //x-ga tähistame  jada VÄÄRTUST, vaatame kas parajasti käesolev väärtus, ehk nt 5 on suurem kui 1
            x = nextElement(x); //siis  läheb tsüklisse ja arvutame järgmise elemendi selles jadas,  5*3+1 on 16
            count++;  //tsüklit suurendame 1võrra.
        }
        return count;
    }

    // TODO 1
    // x = 1 -> 4
    // x = 2 -> 1
    // x = 3 -> 10
    public static int nextElement(int x) {
        // TODO tagasta sequence järgmine element

        if (x % 2 == 0) { //kui x on paarisarv, siis:
            return x / 2; //tagasta see arv jagatud kahega
        } else { //kui on paaritu
            x = x * 3 + 1; //korruta x kolmega ja lisa üks
            return x; //ja tagasta saadud arv
        }
    }
}
