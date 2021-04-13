package ee.bcs.valiit.codewars;

public class CodewarsOne {
    public static int howOld(final String herOld) {
//      int a = 0;
        char firstNumber = herOld.charAt(0); //esmalt leian char-i, vihje oli, et nr on alati esimene täht
        int rightAge  = (char)(Character.getNumericValue(firstNumber));  //siin teisendan saadud tulemuse ASCII koodi

        return rightAge;

    }
}
