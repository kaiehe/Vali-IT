package ee.bcs.valiit.codewars;

public class LoopSample {
    public static void main(String[] args) {
        int[] array  = {4,3,7,2,7,9,3,7,4,76,2,65,7,745};

        System.out.println(array[0]);

        int x  =  0;
        while (x < array.length) { //peame lõpetama ühe võrra enne kui jõuame array lõpuni, sellepärast ei saa olla väiksem-võrdne
            System.out.println("while: " + array[x]  + " index: " +  x);
            x++;
        }

        for (int i= 0;i < array.length;i++){
            if (i%2 ==0){
                continue;
            }
            System.out.println("for väärtus: " + array[i] + " index: " +i);
        }

        for (int value:array) {
            System.out.println("for each väärtus: " +  value); //siin kaotab indeksi, selle for-eachiga ei saa seda kuidagi kätte
        }
    }
}
