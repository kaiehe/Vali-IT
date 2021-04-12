package ee.bcs.valiit.tests;

public class Test1 {
    public static void main(String[] args) {
        // System.out.println(jagatisFunktsioon(30));
//        int[] uusArray = {1,2,3,4,5};
//        System.out.println(Arrays.toString(addToArray(5)));

    }
    //    ÜL 1
//    Tee funktsioon, mis tagastab boolean väärtuse ja võtab sisse ühe parameetri
//    funktsioon peab tagastama
//		true: kui sisend parameeter jagub 3 või 7 (aga ei jagu mõlema numbriga)
//            false: kui sisend parameeter ei jagu 3 ega 7 või jagub mõlema numbriga
    public static boolean jagatisFunktsioon(int y) {
        if (y % 3 == 0 && y % 7 == 0 || y % 3 != 0 && y % 7 != 0) {
            return false;
        } else {
            return true;
        }
    }
    // ÜL2
    // lisa x igale array elemendile
    // Näiteks
    // sisend [1,2,3], 5
    // vastus [6,7,8]
    public static int[] addToArray(int[] array, int x) {
               int[] uusArray = new int[x];
        for (int i = 0; i < array.length; i++) {
            uusArray[i] = i + x;
        }
//        int[] uusArray = new int[x];
//        for (int i = 0; i < array.length; i++) {
//            array[i] = i + x;
//        }
        return uusArray;
    }
}
