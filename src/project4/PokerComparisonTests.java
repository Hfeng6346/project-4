package project4;

/**
 * Testing PokerHands.
 * Created by Harry on 5/28/2017.
 */
public class PokerComparisonTests {
    private Testing test;
    private static final int MAIN_HAND_WINS = 1;
    private static final int OTHER_HAND_WINS = -1;
    private static final int TIE = 0;

    /**
     * A PokerComparisonTest constructor
     */
    public PokerComparisonTests(){
        this.test = new Testing();
    }

    private void testCompareDifferentHandType(){
        differentHandTypeScenarioHighCard();
        differentHandTypeScenarioTwoPairs();
        differentHandTypeScenarioSinglePair();
    }

    private void differentHandTypeScenarioHighCard(){
        System.out.println("===COMPARING FLUSH AND HIGH CARDS===");

        Card m1 = new Card(10, "Ten", "Diamonds");
        Card m2 = new Card(11, "Jack", "Diamonds");
        Card m3 = new Card(12, "Queen", "Diamonds");
        Card m4 = new Card(13, "King", "Diamonds");
        Card m5 = new Card(14, "Ace", "Diamonds");
        Card o1 = new Card(10, "Ten", "Clubs");
        Card o2 = new Card(11, "Jack", "Hearts");
        Card o3 = new Card(12, "Queen", "Spades");
        Card o4 = new Card(13, "King", "Spades");
        Card o5 = new Card(14, "Ace", "Clubs");

        PokerHand main = new PokerHand();
        PokerHand other = new PokerHand();
        main.addCard(m1);
        main.addCard(m2);
        main.addCard(m3);
        main.addCard(m4);
        main.addCard(m5);
        other.addCard(o1);
        other.addCard(o2);
        other.addCard(o3);
        other.addCard(o4);
        other.addCard(o5);

        test.compareResults(main.compareTo(other), MAIN_HAND_WINS, main, other);
    }

    private void differentHandTypeScenarioTwoPairs(){
        System.out.println("\n===COMPARING FLUSH AND TWO PAIRS===");

        Card m1 = new Card(10, "Ten", "Diamonds");
        Card m2 = new Card(11, "Jack", "Diamonds");
        Card m3 = new Card(12, "Queen", "Diamonds");
        Card m4 = new Card(13, "King", "Diamonds");
        Card m5 = new Card(14, "Ace", "Diamonds");
        Card o1 = new Card(10, "Ten", "Clubs");
        Card o2 = new Card(14, "Ace", "Hearts");
        Card o3 = new Card(10, "Ten", "Diamonds");
        Card o4 = new Card(14, "Ace", "Spades");
        Card o5 = new Card(14, "Ace", "Clubs");

        PokerHand main = new PokerHand();
        PokerHand other = new PokerHand();
        main.addCard(m1);
        main.addCard(m2);
        main.addCard(m3);
        main.addCard(m4);
        main.addCard(m5);
        other.addCard(o1);
        other.addCard(o2);
        other.addCard(o3);
        other.addCard(o4);
        other.addCard(o5);

        test.compareResults(main.compareTo(other), MAIN_HAND_WINS, main, other);
    }

    private void differentHandTypeScenarioSinglePair(){
        System.out.println("\n===COMPARING FLUSH AND SINGLE PAIR===");

        Card m1 = new Card(10, "Ten", "Diamonds");
        Card m2 = new Card(11, "Jack", "Diamonds");
        Card m3 = new Card(12, "Queen", "Diamonds");
        Card m4 = new Card(13, "King", "Diamonds");
        Card m5 = new Card(14, "Ace", "Diamonds");
        Card o1 = new Card(3, "Three", "Clubs");
        Card o2 = new Card(14, "Ace", "Hearts");
        Card o3 = new Card(8, "Eight", "Diamonds");
        Card o4 = new Card(14, "Ace", "Spades");
        Card o5 = new Card(14, "Ace", "Clubs");

        PokerHand main = new PokerHand();
        PokerHand other = new PokerHand();
        main.addCard(m1);
        main.addCard(m2);
        main.addCard(m3);
        main.addCard(m4);
        main.addCard(m5);
        other.addCard(o1);
        other.addCard(o2);
        other.addCard(o3);
        other.addCard(o4);
        other.addCard(o5);

        test.compareResults(main.compareTo(other), MAIN_HAND_WINS, main, other);
    }

    private void testCompareFlush(){
        flushScenarioMain();
        flushScenarioOther();
        flushScenarioTie();
    }

    private void flushScenarioMain(){
        System.out.println("\n===COMPARING FLUSH AND FLUSH===");
        Card m1 = new Card(10, "Ten", "Diamonds");
        Card m2 = new Card(11, "Jack", "Diamonds");
        Card m3 = new Card(12, "Queen", "Diamonds");
        Card m4 = new Card(13, "King", "Diamonds");
        Card m5 = new Card(14, "Ace", "Diamonds");
        Card o1 = new Card(10, "Ten", "Clubs");
        Card o2 = new Card(11, "Jack", "Clubs");
        Card o3 = new Card(12, "Queen", "Clubs");
        Card o4 = new Card(13, "King", "Clubs");
        Card o5 = new Card(3, "Three", "Clubs");

        PokerHand main = new PokerHand();
        PokerHand other = new PokerHand();
        main.addCard(m1);
        main.addCard(m2);
        main.addCard(m3);
        main.addCard(m4);
        main.addCard(m5);
        other.addCard(o1);
        other.addCard(o2);
        other.addCard(o3);
        other.addCard(o4);
        other.addCard(o5);

        test.compareResults(main.compareTo(other), MAIN_HAND_WINS, main, other);
    }

    private void flushScenarioOther(){
        System.out.println("\n===COMPARING FLUSH AND FLUSH===");
        Card m1 = new Card(2, "Two", "Diamonds");
        Card m2 = new Card(11, "Jack", "Diamonds");
        Card m3 = new Card(12, "Queen", "Diamonds");
        Card m4 = new Card(13, "King", "Diamonds");
        Card m5 = new Card(14, "Ace", "Diamonds");
        Card o1 = new Card(10, "Ten", "Clubs");
        Card o2 = new Card(11, "Jack", "Clubs");
        Card o3 = new Card(12, "Queen", "Clubs");
        Card o4 = new Card(13, "King", "Clubs");
        Card o5 = new Card(14, "Ace", "Clubs");

        PokerHand main = new PokerHand();
        PokerHand other = new PokerHand();
        main.addCard(m1);
        main.addCard(m2);
        main.addCard(m3);
        main.addCard(m4);
        main.addCard(m5);
        other.addCard(o1);
        other.addCard(o2);
        other.addCard(o3);
        other.addCard(o4);
        other.addCard(o5);

        test.compareResults(main.compareTo(other), OTHER_HAND_WINS, main, other);
    }

    private void flushScenarioTie(){
        System.out.println("\n===COMPARING FLUSH AND FLUSH===");
        Card m1 = new Card(10, "Ten", "Diamonds");
        Card m2 = new Card(11, "Jack", "Diamonds");
        Card m3 = new Card(12, "Queen", "Diamonds");
        Card m4 = new Card(13, "King", "Diamonds");
        Card m5 = new Card(14, "Ace", "Diamonds");
        Card o1 = new Card(10, "Ten", "Clubs");
        Card o2 = new Card(11, "Jack", "Clubs");
        Card o3 = new Card(12, "Queen", "Clubs");
        Card o4 = new Card(13, "King", "Clubs");
        Card o5 = new Card(14, "Ace", "Clubs");

        PokerHand main = new PokerHand();
        PokerHand other = new PokerHand();
        main.addCard(m1);
        main.addCard(m2);
        main.addCard(m3);
        main.addCard(m4);
        main.addCard(m5);
        other.addCard(o1);
        other.addCard(o2);
        other.addCard(o3);
        other.addCard(o4);
        other.addCard(o5);

        test.compareResults(main.compareTo(other), TIE, main, other);
    }

    private void compareTwoPairs(){
        twoPairsScenarioMain();
        twoPairsScenarioOther();
        twoPairsScenarioTie();
    }

    private void twoPairsScenarioMain(){
        System.out.println("\n===COMPARING TWO PAIRS AND TWO PAIRS===");
        Card m1 = new Card(10, "Ten", "Spades");
        Card m2 = new Card(10, "Ten", "Clubs");
        Card m3 = new Card(12, "Queen", "Hearts");
        Card m4 = new Card(12, "Queen", "Spades");
        Card m5 = new Card(10, "Ten", "Diamonds");
        Card o1 = new Card(4, "Four", "Diamonds");
        Card o2 = new Card(10, "Ten", "Clubs");
        Card o3 = new Card(4, "Four", "Hearts");
        Card o4 = new Card(12, "Queen", "Clubs");
        Card o5 = new Card(12, "Queen", "Diamonds");

        PokerHand main = new PokerHand();
        PokerHand other = new PokerHand();
        main.addCard(m1);
        main.addCard(m2);
        main.addCard(m3);
        main.addCard(m4);
        main.addCard(m5);
        other.addCard(o1);
        other.addCard(o2);
        other.addCard(o3);
        other.addCard(o4);
        other.addCard(o5);

        test.compareResults(main.compareTo(other), MAIN_HAND_WINS, main, other);
    }

    private void twoPairsScenarioOther(){
        System.out.println("\n===COMPARING TWO PAIRS AND TWO PAIRS===");
        Card m1 = new Card(10, "Ten", "Diamonds");
        Card m2 = new Card(10, "Ten", "Clubs");
        Card m3 = new Card(12, "Queen", "Hearts");
        Card m4 = new Card(12, "Queen", "Spades");
        Card m5 = new Card(2, "Two", "Diamonds");
        Card o1 = new Card(10, "Ten", "Spades");
        Card o2 = new Card(10, "Ten", "Clubs");
        Card o3 = new Card(14, "Ace", "Hearts");
        Card o4 = new Card(12, "Queen", "Clubs");
        Card o5 = new Card(12, "Queen", "Diamonds");

        PokerHand main = new PokerHand();
        PokerHand other = new PokerHand();
        main.addCard(m1);
        main.addCard(m2);
        main.addCard(m3);
        main.addCard(m4);
        main.addCard(m5);
        other.addCard(o1);
        other.addCard(o2);
        other.addCard(o3);
        other.addCard(o4);
        other.addCard(o5);

        test.compareResults(main.compareTo(other), OTHER_HAND_WINS, main, other);
    }

    private void twoPairsScenarioTie(){
        System.out.println("\n===COMPARING TWO PAIRS AND TWO PAIRS===");
        Card m1 = new Card(10, "Ten", "Diamonds");
        Card m2 = new Card(10, "Ten", "Clubs");
        Card m3 = new Card(12, "Queen", "Hearts");
        Card m4 = new Card(12, "Queen", "Spades");
        Card m5 = new Card(4, "Four", "Diamonds");
        Card o1 = new Card(10, "Ten", "Spades");
        Card o2 = new Card(10, "Ten", "Hearts");
        Card o3 = new Card(4, "Four", "Hearts");
        Card o4 = new Card(12, "Queen", "Clubs");
        Card o5 = new Card(12, "Queen", "Diamonds");

        PokerHand main = new PokerHand();
        PokerHand other = new PokerHand();
        main.addCard(m1);
        main.addCard(m2);
        main.addCard(m3);
        main.addCard(m4);
        main.addCard(m5);
        other.addCard(o1);
        other.addCard(o2);
        other.addCard(o3);
        other.addCard(o4);
        other.addCard(o5);

        test.compareResults(main.compareTo(other), TIE, main, other);
    }

    private void testCompareSinglePair(){
        singlePairScenarioMain();
        singlePairScenarioOther();
        singlePairScenarioTie();

    }

    private void singlePairScenarioMain(){
        System.out.println("\n===COMPARING SINGLE PAIR AND SINGLE PAIR===");
        Card m1 = new Card(10, "Ten", "Hearts");
        Card m2 = new Card(3, "Three", "Clubs");
        Card m3 = new Card(6, "Six", "Hearts");
        Card m4 = new Card(10, "Ten", "Spades");
        Card m5 = new Card(5, "Five", "Diamonds");
        Card o1 = new Card(10, "Ten", "Diamonds");
        Card o2 = new Card(6, "Six", "Clubs");
        Card o3 = new Card(4, "Four", "Hearts");
        Card o4 = new Card(6, "Six", "Spades");
        Card o5 = new Card(6, "Six", "Diamonds");

        PokerHand main = new PokerHand();
        PokerHand other = new PokerHand();
        main.addCard(m1);
        main.addCard(m2);
        main.addCard(m3);
        main.addCard(m4);
        main.addCard(m5);
        other.addCard(o1);
        other.addCard(o2);
        other.addCard(o3);
        other.addCard(o4);
        other.addCard(o5);

        test.compareResults(main.compareTo(other), MAIN_HAND_WINS, main, other);
    }

    private void singlePairScenarioOther(){
        System.out.println("\n===COMPARING SINGLE PAIR AND SINGLE PAIR===");
        Card m1 = new Card(10, "Ten", "Hearts");
        Card m2 = new Card(3, "Three", "Clubs");
        Card m3 = new Card(6, "Six", "Hearts");
        Card m4 = new Card(10, "Ten", "Spades");
        Card m5 = new Card(5, "Five", "Diamonds");
        Card o1 = new Card(10, "Ten", "Diamonds");
        Card o2 = new Card(11, "Jack", "Clubs");
        Card o3 = new Card(4, "Four", "Hearts");
        Card o4 = new Card(11, "Jack", "Spades");
        Card o5 = new Card(11, "Jack", "Diamonds");

        PokerHand main = new PokerHand();
        PokerHand other = new PokerHand();
        main.addCard(m1);
        main.addCard(m2);
        main.addCard(m3);
        main.addCard(m4);
        main.addCard(m5);
        other.addCard(o1);
        other.addCard(o2);
        other.addCard(o3);
        other.addCard(o4);
        other.addCard(o5);

        test.compareResults(main.compareTo(other), OTHER_HAND_WINS, main, other);
    }

    private void singlePairScenarioTie(){
        System.out.println("\n===COMPARING SINGLE PAIR AND SINGLE PAIR===");
        Card m1 = new Card(10, "Ten", "Diamonds");
        Card m2 = new Card(3, "Three", "Clubs");
        Card m3 = new Card(6, "Six", "Hearts");
        Card m4 = new Card(10, "Ten", "Spades");
        Card m5 = new Card(5, "Five", "Diamonds");
        Card o1 = new Card(10, "Ten", "Clubs");
        Card o2 = new Card(3, "Three", "Spades");
        Card o3 = new Card(10, "Ten", "Hearts");
        Card o4 = new Card(5, "Five", "Spades");
        Card o5 = new Card(6, "Six", "Diamonds");

        PokerHand main = new PokerHand();
        PokerHand other = new PokerHand();
        main.addCard(m1);
        main.addCard(m2);
        main.addCard(m3);
        main.addCard(m4);
        main.addCard(m5);
        other.addCard(o1);
        other.addCard(o2);
        other.addCard(o3);
        other.addCard(o4);
        other.addCard(o5);

        test.compareResults(main.compareTo(other), TIE, main, other);
    }

    private void testCompareHighCard(){
        highCardScenarioMain();
        highCardScenarioOther();
        highCardScenarioTie();
    }

    private void highCardScenarioMain(){
        System.out.println("\n===COMPARING HIGH CARD AND HIGH CARD===");
        Card m1 = new Card(10, "Ten", "Diamonds");
        Card m2 = new Card(9, "Nine", "Hearts");
        Card m3 = new Card(8, "Eight", "Hearts");
        Card m4 = new Card(14, "Ace", "Spades");
        Card m5 = new Card(6, "Six", "Diamonds");
        Card o1 = new Card(4, "Four", "Diamonds");
        Card o2 = new Card(9, "Nine", "Clubs");
        Card o3 = new Card(8, "Eight", "Hearts");
        Card o4 = new Card(7, "Seven", "Spades");
        Card o5 = new Card(14, "Ace", "Diamonds");

        PokerHand main = new PokerHand();
        PokerHand other = new PokerHand();
        main.addCard(m1);
        main.addCard(m2);
        main.addCard(m3);
        main.addCard(m4);
        main.addCard(m5);
        other.addCard(o1);
        other.addCard(o2);
        other.addCard(o3);
        other.addCard(o4);
        other.addCard(o5);

        test.compareResults(main.compareTo(other), MAIN_HAND_WINS, main, other);
    }

    private void highCardScenarioOther(){
        System.out.println("\n===COMPARING HIGH CARD AND HIGH CARD===");
        Card m1 = new Card(10, "Ten", "Hearts");
        Card m2 = new Card(9, "Nine", "Clubs");
        Card m3 = new Card(8, "Eight", "Diamonds");
        Card m4 = new Card(7, "Seven", "Spades");
        Card m5 = new Card(6, "Six", "Diamonds");
        Card o1 = new Card(10, "Ten", "Diamonds");
        Card o2 = new Card(9, "Nine", "Spades");
        Card o3 = new Card(8, "Eight", "Hearts");
        Card o4 = new Card(7, "Seven", "Clubs");
        Card o5 = new Card(14, "Ace", "Diamonds");

        PokerHand main = new PokerHand();
        PokerHand other = new PokerHand();
        main.addCard(m1);
        main.addCard(m2);
        main.addCard(m3);
        main.addCard(m4);
        main.addCard(m5);
        other.addCard(o1);
        other.addCard(o2);
        other.addCard(o3);
        other.addCard(o4);
        other.addCard(o5);

        test.compareResults(main.compareTo(other), OTHER_HAND_WINS, main, other);
    }

    private void highCardScenarioTie(){
        System.out.println("\n===COMPARING HIGH CARD AND HIGH CARD===");
        Card m1 = new Card(10, "Ten", "Diamonds");
        Card m2 = new Card(9, "Nine", "Clubs");
        Card m3 = new Card(8, "Eight", "Hearts");
        Card m4 = new Card(7, "Seven", "Spades");
        Card m5 = new Card(14, "Ace", "Hearts");
        Card o1 = new Card(10, "Ten", "Clubs");
        Card o2 = new Card(9, "Nine", "Spades");
        Card o3 = new Card(8, "Eight", "Diamonds");
        Card o4 = new Card(7, "Seven", "Hearts");
        Card o5 = new Card(14, "Ace", "Diamonds");

        PokerHand main = new PokerHand();
        PokerHand other = new PokerHand();
        main.addCard(m1);
        main.addCard(m2);
        main.addCard(m3);
        main.addCard(m4);
        main.addCard(m5);
        other.addCard(o1);
        other.addCard(o2);
        other.addCard(o3);
        other.addCard(o4);
        other.addCard(o5);

        test.compareResults(main.compareTo(other), TIE, main, other);
    }


    public static void main(String[] args){
        PokerComparisonTests PokerTest = new PokerComparisonTests();
        PokerTest.testCompareDifferentHandType();
        PokerTest.testCompareFlush();
        PokerTest.compareTwoPairs();
        PokerTest.testCompareSinglePair();
        PokerTest.testCompareHighCard();
        PokerTest.test.end();

    }
}
