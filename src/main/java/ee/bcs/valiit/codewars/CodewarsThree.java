package ee.bcs.valiit.codewars;
public class CodewarsThree {

    public static int nthPower(int[] array, int n) {
        int result;
        if(array.length>n){
            result = (int)(Math.pow(array[n],n));
        }else{
            result = -1;
        }
        return result;
    }
}
