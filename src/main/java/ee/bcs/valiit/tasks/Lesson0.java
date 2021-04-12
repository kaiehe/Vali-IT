package ee.bcs.valiit.tasks;

public class Lesson0 {
    public static void main(String[] args) {
        excersie1();
        excersie2();
        excersie3();
        excersie4();
    }

    // TODO
    // defineeri 3 muutujat a = 1, b = 1, c = 3
    // Prindi välja a==b
    // Prindi välja a==c
    // Lisa rida a = c
    // Prindi välja a==b
    // Prindi välja a==c, mis muutus???
    public static void excersie1(){
        int a = 1;
        int b = 1;
        int c = 3;
        System.out.println(a==b);
        System.out.println(a==c);
        a = c;
        System.out.println(a==b);//peale a-le uue väärtuse omastamist, on tingimuslikud väited vastupidised
        System.out.println(a==c);//ehk a ei võrdu b-ga (false, varem true) ja a on võrdne c-ga (true, enne false)
    }

    // TODO
    // Loo muutujad x1 = 10 ja x2 = 20, vali sobiv andmetüüp
    // Tekita muutuja y1 = ++x1, trüki välja nii x1 kui y1
    // Tekita muutuja y2 = x2++, trüki välja nii x2 ja y2
    // Analüüsi tulemusi
    public static void excersie2(){
        int x1 = 10;
        int x2 = 20;
        int y1 = ++x1; //suurendab muutujat enne talle väärtuse omastamist
        System.out.println("x1=" + x1 + " y1=" +y1);
        int y2= x2++;
        System.out.println("peale muutust x2=" + x2 + " y2=" +y2);
    }

    // TODO
    // Loo arvulised muutujad
    // a = 18 % 3
    // b = 19 % 3
    // c = 20 % 3
    // d = 21 % 3
    // Prindi välja kõigi muutujate väärtused
    public static void excersie3(){
        int a  = 18 % 3;
        int b = 19 % 3;
        int c = 20 % 3;
        int d = 21 % 3;
        System.out.println("A=" + a + " B=" + b + " C=" + c + " D=" +d);

    }

    // TODO
    // Defineeri String tüüpi muutuja mille sisu oleks "\"\\""
    // Trüki muutuja sisu välja
    public static void excersie4(){
        String kriips = "\"\\\"\\\\\"\"";
        System.out.println(kriips);
    }
}
