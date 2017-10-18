package project4;

/**
 * Testing methods used by other classes.
 * Created by Harry on 5/28/2017.
 */
public class Testing {

    private int numPass, numFail;

    /**
     * A Testing constructor
     */
    public Testing(){
        this.numPass = 0;
        this.numFail = 0;
    }

    private void isCorrect(boolean result){
        if(result){
            numPass++;
            System.out.println("=PASS=");
        }
        else{
            numFail++;
            System.out.println("=Fail=");
        }
    }

    /**
     * Compares the expected result and actual result
     * @param compareToResult the result from compareTo()
     * @param expectedResult expected result
     * @param main main hand
     * @param other other hand
     */
    public void compareResults(int compareToResult, int expectedResult, PokerHand main, PokerHand other){
        System.out.println("Main hand: " + main + "\nOther hand: " + other + "\ncompareTo() result: " + compareToResult + "\nExpected result: " + expectedResult);
        isCorrect(compareToResult == expectedResult);
    }

    /**
     * Prints out the number of passing and failing tests
     */
    public void end(){
        System.out.println("\nNumber of passing tests: " + numPass + "\nNumber of failing tests: " + numFail);
    }
}
