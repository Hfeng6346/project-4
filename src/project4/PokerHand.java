package project4;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Models a poker hand.
 * Created by Harry on 5/24/2017.
 */
public class PokerHand {

    private ArrayList<Card> hand;

    private static final int FLUSH = 3;
    private static final int TWO_PAIRS = 2;
    private static final int SINGLE_PAIR = 1;
    private static final int HIGH_CARD = 0;
    private static final int FOUR_OF_KIND = 4;
    private static final int THREE_OF_KIND = 3;
    private static final int TWO_OF_KIND = 2;
    private static final int ONE_OF_KIND = 1;
    private static final int MAIN_HAND_WINS = 1;
    private static final int OTHER_HAND_WINS = -1;
    private static final int HAND_TIE = 0;

    public static boolean DEBUG = false;

    /**
     * A PokerHand constructor.
     */
    public PokerHand(){
        this.hand = new ArrayList<Card>();
    }

    /**
     * Adds a card to the poker hand.
     * @param newCard card to be added to hand.
     */
    public void addCard(Card newCard){
        hand.add(newCard);
    }

    /**
     * Adds a card to a specific index in poker hand.
     * @param newCard card to be added to hand
     * @param index where card should be placed
     */
    public void addCardToIndex(Card newCard, int index){
        hand.add(index, newCard);
    }

    /**
     * Determines size of poker hand. Returns the size.
     * @return size of poker hand.
     */
    public int size(){
        return hand.size();
    }

    /**
     * Retrieves a card from the poker hand. Returns the retrieved card.
     * @param index location of card in poker hand.
     * @return Card retrieved from poker hand.
     */
    public Card getCard(int index){
        return hand.get(index);
    }

    /**
     * Creates a shallow copy of poker hand. Returns the copy.
     * @return Shallow copy of poker hand.
     */
    private PokerHand createCopy(){
        PokerHand copy = new PokerHand();
        for(Card card : this.hand){
            copy.addCard(card);
        }
        return copy;
    }

    /**
     * Creates a list containing only the rank (int) of the cards in poker hand. Returns the list.
     * @return list of ranks
     */
    private ArrayList listOfRanks(){
        ArrayList<Integer> rank = new ArrayList<Integer>();
        for(Card card : hand){
            rank.add(card.getRank());
        }
        return rank;
    }

    /**
     *  Determines how this hand compares to another hand, returns
     *  positive, negative, or zero depending on the comparison.
     *
     *  @param other The hand to compare this hand to
     *  @return a negative number if this is worth LESS than other, zero
     *  if they are worth the SAME, and a positive number if this is worth
     *  MORE than other
     */
    public int compareTo(PokerHand other){
        int mainHandWorth = handType();
        int otherHandWorth = other.handType();

        if(mainHandWorth != otherHandWorth){
            return compareDifferentHandType(mainHandWorth, otherHandWorth);
        }
        else{
            if(mainHandWorth == FLUSH){
                debugPrint("Logic FLUSH-MAIN");
                return compareFlush(other);
            }
            else{
                if(mainHandWorth == TWO_PAIRS){
                    debugPrint("Logic TWO_PAIRS-MAIN");
                    return compareTwoPairs(other);
                }
                else{
                    if(mainHandWorth == SINGLE_PAIR){
                        debugPrint("Logic SINGLE_PAIR-MAIN");
                        return compareSinglePair(other);
                    }
                    else{
                        debugPrint("Logic HIGH_CARD-MAIN");
                        return compareHighCard(other, hand.size());
                    }
                }
            }
        }
    }

    /**
     * Determines how this hand compares to another hand, both having different hand types.
     * @param mainHandWorth value of a poker hand
     * @param otherHandWorth value of the other poker hand
     * @return an int. 1 meaning meaning main hand has higher value, -1 meaning other hand has higher value, and tie meaning same value
     */
    private int compareDifferentHandType(int mainHandWorth, int otherHandWorth){
        if(mainHandWorth > otherHandWorth){
            debugPrint("Logic WORTH-1");
            return MAIN_HAND_WINS;
        }
        else{
            debugPrint("Logic WORTH-2");
            return OTHER_HAND_WINS;
            }
    }

    /**
     * Determines how this hand compares to another hand, both of which are FLUSH.
     * @param other poker hand that is being compared to.
     * @return an int. 1 meaning meaning main hand has higher value, -1 meaning other hand has higher value, and tie meaning same value
     */
    private int compareFlush(PokerHand other){
        return compareHighCard(other, hand.size());
    }

    /**
     * Determines how this hand compares to another hand, both of which contain TWO PAIRS each
     * @param other poker hand being compared to
     * @return an int. 1 meaning meaning main hand has higher value, -1 meaning other hand has higher value, and tie meaning same value
     */
    private int compareTwoPairs(PokerHand other) {
        PokerHand copy = createCopy();
        PokerHand otherCopy = other.createCopy();

        int mainTwoPairVal = twoPairValue();
        int otherTwoPairVal = other.twoPairValue();

        if (mainTwoPairVal > otherTwoPairVal) {
            debugPrint("Logic COMPARE_TWO_PAIRS-1");
            return MAIN_HAND_WINS;
        }
        else{
            if (otherTwoPairVal > mainTwoPairVal) {
                debugPrint("Logic COMPARE_TWO_PAIRS-2");
                return OTHER_HAND_WINS;
            }
            else{
                copy.removeFirstPair(mainTwoPairVal);
                otherCopy.removeFirstPair(otherTwoPairVal);
                debugPrint("Logic COMPARE_TWO_PAIRS-3");
                return copy.compareSinglePair(otherCopy);
            }
        }
    }

    /**
     * Determines how this hand compares to another hand, both of which contain a SINGLE PAIR each
     * @param other poker hand being compared to
     * @return an int. 1 meaning meaning main hand has higher value, -1 meaning other hand has higher value, and tie meaning same value
     */
    private int compareSinglePair(PokerHand other){
        PokerHand copy = createCopy();
        PokerHand otherCopy = other.createCopy();

        int mainSinglePairVal = copy.singlePairValue();
        int otherSinglePairVal = otherCopy.singlePairValue();

        if(mainSinglePairVal > otherSinglePairVal){
            debugPrint("Logic SINGLE_PAIR-1");
            return MAIN_HAND_WINS;
        }
        else{
            if(otherSinglePairVal > mainSinglePairVal){
                debugPrint("Logic SINGLE_PAIR-2");
                return OTHER_HAND_WINS;
            }
            else{
                copy.removeFirstPair(mainSinglePairVal);
                otherCopy.removeFirstPair(otherSinglePairVal);
                debugPrint("Logic SINGLE_PAIR-3");
                return copy.compareHighCard(otherCopy, copy.hand.size());
            }
        }
    }

    /**
     * Determines how this hand compares to another hand, both of which contain only HIGH CARDS
     * @param other poker hand being compared to
     * @param numCardsToCompare number of times Cards in hand should be compared
     * @return an int. 1 meaning meaning main hand has higher value, -1 meaning other hand has higher value, and tie meaning same value
     */
    private int compareHighCard(PokerHand other, int numCardsToCompare){
        PokerHand copy = createCopy();
        PokerHand otherCopy = other.createCopy();

        copy.sort();
        otherCopy.sort();
        for(int i = 0; i < numCardsToCompare; i++){
            if(copy.hand.get(i).getRank() > otherCopy.hand.get(i).getRank()){
                debugPrint("Logic HIGH_CARD-1");
                return MAIN_HAND_WINS;
            }
            if(otherCopy.hand.get(i).getRank() > copy.hand.get(i).getRank()){
                debugPrint("Logic HIGH_CARD-2");
                return OTHER_HAND_WINS;
            }
        }
        debugPrint("Logic HIGH_CARD-3");
        return HAND_TIE;
    }

    /**
     * Determines the highest value between the TWO PAIRS in the hand. Returns the value
     * @return value of highest card
     */
    private int twoPairValue(){
        int doubleType = typeOfDoublePair();
        if(doubleType == FOUR_OF_KIND){
            return fourOfKindValue();
        }
        if(doubleType == THREE_OF_KIND){
            return threeAndTwoOfKindValue();
        }
        if(doubleType == TWO_OF_KIND){
            return twoAndTwoOfKindValue();
        }
        return -1;
    }

    /**
     * Determines the value of a FOUR OF A KIND (TWO PAIRS). Returns the value.
     * @return value of a FOUR OF A KIND
     */
    private int fourOfKindValue(){
        PokerHand copy = createCopy();
        copy.removeXCard(ONE_OF_KIND);
        return copy.hand.get(0).getRank();
    }

    /**
     * Determines the highest value between a THREE OF A KIND and a TWO OF A KIND. Returns the highest value.
     * @return value of either a THREE OF A KIND or a TWO OF A KIND
     */
    private int threeAndTwoOfKindValue(){
        return getHighestRank();
    }

    /**
     * Determines the highest value between two TWO OF A KINDSs. Returns the highest value.
     * @return value of either of the TWO OF A KINDs
     */
    private int twoAndTwoOfKindValue(){
        PokerHand copy = createCopy();
        copy.removeXCard(ONE_OF_KIND);
        return copy.getHighestRank();
    }

    /**
     * Determines the value of the pair of cards in the hand. Returns the value.
     * @return value of the single pair of cards.
     */
    private int singlePairValue(){
        PokerHand copy = createCopy();
        if(copy.isXOfKind(THREE_OF_KIND) && !copy.isXOfKind(TWO_OF_KIND)){
            copy.repeatRemoveXCard(ONE_OF_KIND, hand.size() - THREE_OF_KIND);
            return copy.hand.get(0).getRank();
        }
        if(copy.isXOfKind(TWO_OF_KIND)){
            copy.repeatRemoveXCard(ONE_OF_KIND, hand.size() - TWO_OF_KIND);
            return copy.hand.get(0).getRank();
        }
        return -1;
    }

    /**
     * Determines what type of hand the poker hand is. Returns an int representing the type.
     * @return 3: FLUSH, 2: TWO PAIRS, 1: SINGLE PAIR, 0: HIGH CARDS
     */
    private int handType(){
        if(isFlush()){
            return FLUSH;
        }
        if(isTwoPairs()){
            return TWO_PAIRS;
        }
        if(isOnePair()){
            return SINGLE_PAIR;
        }
        else{
            return HIGH_CARD;
        }
    }

    /**
     * Determines if hand is a FLUSH. Returns true if it is a FLUSH.
     * @return true or false, depending on whether hand is a FLUSH or not.
     */
    private boolean isFlush(){
        String suit = hand.get(0).getSuit();
        for(int i = 1; i < hand.size(); i++){
            if(!hand.get(i).getSuit().equals(suit)){
                return false;
            }
        }
        return true;
    }

    /**
     * Determines is hand contains TWO PAIRS. Returns true if it does.
     * @return true or false, depending if hand contains TWO PAIRS
     */
    private boolean isTwoPairs(){
        int type = typeOfDoublePair();
        return(type == FOUR_OF_KIND || type == THREE_OF_KIND || type == TWO_OF_KIND);
    }

    /**
     * Determines what type of pairs are in a TWO PAIRS hand. Returns an int representing the type.
     * @return 4: FOUR OF A KIND, 3: THREE OF A KIND & TWO OF A KIND, 2: TWO OF A KIND & TWO OF A KIND
     */
    private int typeOfDoublePair(){
        PokerHand copy = createCopy();

        if(isXOfKind(FOUR_OF_KIND)){
            return FOUR_OF_KIND;
        }
        if(isXOfKind(THREE_OF_KIND) && isXOfKind(TWO_OF_KIND)){
            return THREE_OF_KIND;
        }
        if(copy.isXOfKind(TWO_OF_KIND)){
            copy.removeXCard(TWO_OF_KIND);
            if(copy.isXOfKind(TWO_OF_KIND)){
                return TWO_OF_KIND;
            }
        }
        return -1;
    }

    /**
     * Determines if a hand contains only a SINGLE PAIR. Returns true if it does.
     * @return true or false, depending if hand contains a SINGLE PAIR.
     */
    private boolean isOnePair(){
        int type = typeOfSinglePair();
        return(type == THREE_OF_KIND || type == TWO_OF_KIND);
    }

    /**
     * Determines type of SINGLE PAIR in hand. Returns an int representing the type.
     * @return 3: THREE OF A KIND, 2: TWO OF A KIND
     */
    private int typeOfSinglePair(){
        PokerHand copy = createCopy();

        if(isXOfKind(THREE_OF_KIND) && !isXOfKind(TWO_OF_KIND)){
            return THREE_OF_KIND;
        }
        if(isXOfKind(TWO_OF_KIND)){
           copy.removeXCard(TWO_OF_KIND);
           if(!copy.isXOfKind(TWO_OF_KIND) && !copy.isXOfKind(THREE_OF_KIND)){
               return TWO_OF_KIND;
           }
        }
        return -1;
    }

    /**
     * Determines how many times a card with a certain value appears in a hand. Returns the number of occurrence.
     * @param rank rank of a card
     * @return number of occurrence
     */
    private int numberOfOccurrence(int rank){
        ArrayList handRanks = listOfRanks();
        int counter = 0;
        Integer cardRank = rank;
        for(int i = 0; i < handRanks.size(); i++){
            if(cardRank.equals(handRanks.get(i))){
                counter++;
            }
        }
        return counter;
    }

    /**
     * Determines if a hand contains cards with a value that appears X times. Returns true if it does.
     * @param x number of time rank appears in hand
     * @return true or false.
     */
    private boolean isXOfKind(int x){
        ArrayList rank = listOfRanks();
        for(int i = 0; i < hand.size() - (x - 1); i++){
            if(Collections.frequency(rank, rank.get(i)) == x){
                return true;
            }
        }
        return false;
    }

    /**
     * Removes cards from a hand based on how often its value appears.
     * @param x Type of card: 4- FOUR OF A KIND, 3- THREE OF A KIND, 2-TWO OF A KIND,1- HIGH CARD
     */
    private void removeXCard(int x){
        ArrayList rank = listOfRanks();
        for(int i = 0; i < rank.size(); i++){
            if(Collections.frequency(rank, hand.get(i).getRank()) == x){
                int desiredRank = hand.get(i).getRank();
                for(int j = 0; j < x; j++){
                    int index = rank.lastIndexOf(desiredRank);
                    hand.remove(index);
                    rank.remove(index);
                }
                break;
            }
        }
    }

    /**
     * Repeats the removeXCard() function.
     * @param x Type of card: 4- FOUR OF A KIND, 3- THREE OF A KIND, 2-TWO OF A KIND,1- HIGH CARD
     * @param repeats how many times to repeat removeXCard()
     */
    private void repeatRemoveXCard(int x, int repeats){
        for(int i = 0; i < repeats; i++){
            removeXCard(x);
        }
    }

    private void removeFirstPair(int pairValue){
        ArrayList handRanks = listOfRanks();
        if(Collections.frequency(handRanks, pairValue) == numberOfOccurrence(pairValue)){
            int counter = 0;
            for(int i = hand.size() - 1; i >= 0; i--){
                if(counter >= TWO_OF_KIND){
                    break;
                }
                if(hand.get(i).getRank() == pairValue){
                    hand.remove(i);
                    counter++;
                }
            }
        }
//        if(Collections.frequency(handRanks, pairValue) == THREE_OF_KIND){
//            int counter = 0;
//            for(int i = hand.size() - 1; i >= 0; i--){
//                if(counter >= TWO_OF_KIND){
//                    break;
//                }
//                if(hand.get(i).getRank() == pairValue){
//                    hand.remove(i);
//                    counter++;
//                }
//            }
//        }
//        if(Collections.frequency(handRanks, pairValue) == TWO_OF_KIND){
//            for(int i = hand.size() - 1; i >= 0; i--){
//                if(hand.get(i).getRank() == pairValue){
//                    hand.remove(i);
//                }
//            }
//        }
    }


    private void sort(){
        for(int i = hand.size() - 1; i > -1; i--){
            for(int j = 0; j <= i - 1; j++){
                if(hand.get(j).getRank() < hand.get(j+1).getRank()){
                    Card tmp = hand.get(j);
                    hand.set(j, hand.get(j+1));
                    hand.set(j+1, tmp);
                }
            }
        }
    }

    private int getHighestRank(){
        int rank = 0;
        for(Card card : hand){
            if(card.getRank() > rank){
                rank = card.getRank();
            }
        }
        return rank;
    }

    /**
     * A String representation of a Poker Hand. Returns the String
     * @return A String representing a Poker Hand
     */
    @Override
    public String toString() {
        String handString = "";
        for(Card card : hand){
            handString += card;
            if(hand.indexOf(card) != (hand.size() - 1)){
                handString += ", ";
            }
        }
        return handString;
    }

    public static void main(String[] args){
//        See PokerComparisonTest.java for more comprehensive testing
        DEBUG = false;
        testHandTypes();
        testCompareTO();
    }

    private static void testHandTypes(){
        System.out.println("===TESTING FLUSH===");
        Card c1 = new Card(14, "Ace","Diamonds");
        Card c2 = new Card(13, "King","Diamonds");
        Card c3 = new Card(12, "Queen", "Diamonds");
        Card c4 = new Card(11, "Jack", "Diamonds");
        Card c5 = new Card(10, "Ten","Diamonds");
        PokerHand hand1 = new PokerHand();
        hand1.addCard(c1);
        hand1.addCard(c2);
        hand1.addCard(c3);
        hand1.addCard(c4);
        hand1.addCard(c5);
        System.out.println("Hand: " + hand1);
        System.out.println("Hand type value: " + hand1.handType());
        System.out.println("Hand: " + hand1);

        System.out.println("\n===TESTING TWO PAIRS [FOUR OF KIND]===");
        Card c6 = new Card(14, "Ace", "Diamonds");
        Card c7 = new Card(14, "Ace", "Clubs");
        Card c8 = new Card(14, "Ace", "Hearts");
        Card c9 = new Card(14, "Ace", "Spades");
        Card c10 = new Card(13, "King", "Diamonds");
        PokerHand hand2 = new PokerHand();
        hand2.addCard(c6);
        hand2.addCard(c7);
        hand2.addCard(c8);
        hand2.addCard(c9);
        hand2.addCard(c10);
        System.out.println("Hand: " + hand2);
        System.out.println("Hand type value: " + hand2.handType());
        System.out.println("Hand: " + hand2);


        System.out.println("\n===TESTING TWO PAIRS [THREE OF KIND && TWO OF KIND]===");
        Card c11 = new Card(14, "Ace", "Diamonds");
        Card c12 = new Card(14, "Ace", "Clubs");
        Card c13 = new Card(14, "Ace", "Hearts");
        Card c14 = new Card(13, "King", "Spades");
        Card c15 = new Card(13, "King", "Diamonds");
        PokerHand hand3 = new PokerHand();
        hand3.addCard(c11);
        hand3.addCard(c12);
        hand3.addCard(c13);
        hand3.addCard(c14);
        hand3.addCard(c15);
        System.out.println("Hand: " + hand3);
        System.out.println("Hand type value: " + hand3.handType());
        System.out.println("Hand: " + hand3);

        System.out.println("\n===TESTING TWO PAIRS [TWO OF KIND && TWO OF KIND]===");
        Card c16 = new Card(14, "Ace", "Diamonds");
        Card c17 = new Card(13, "King", "Clubs");
        Card c18 = new Card(13, "King", "Hearts");
        Card c19 = new Card(12, "Queen", "Spades");
        Card c20 = new Card(14, "Ace", "Diamonds");
        PokerHand hand4 = new PokerHand();
        hand4.addCard(c16);
        hand4.addCard(c17);
        hand4.addCard(c18);
        hand4.addCard(c19);
        hand4.addCard(c20);
        System.out.println("Hand: " + hand4);
        System.out.println("Hand type value: " + hand4.handType());
        System.out.println("Hand: " + hand4);

        System.out.println("\n===TESTING SINGLE PAIR [THREE OF KIND]===");
        Card c21 = new Card(14, "Ace", "Diamonds");
        Card c22 = new Card(10, "Ten", "Clubs");
        Card c23 = new Card(14, "Ace", "Hearts");
        Card c24 = new Card(12, "Queen", "Spades");
        Card c25 = new Card(14, "Ace", "Diamonds");
        PokerHand hand5 = new PokerHand();
        hand5.addCard(c21);
        hand5.addCard(c22);
        hand5.addCard(c23);
        hand5.addCard(c24);
        hand5.addCard(c25);
        System.out.println("Hand: " + hand5);
        System.out.println("Hand type value: " + hand5.handType());
        System.out.println("Hand: " + hand5);

        System.out.println("\n===TESTING SINGLE PAIR [TWO OF KIND]===");
        Card c26 = new Card(10, "Ten", "Diamonds");
        Card c27 = new Card(10, "Ten", "Clubs");
        Card c28 = new Card(2, "Two", "Hearts");
        Card c29 = new Card(12, "Queen", "Spades");
        Card c30 = new Card(14, "Ace", "Diamonds");
        PokerHand hand6 = new PokerHand();
        hand6.addCard(c26);
        hand6.addCard(c27);
        hand6.addCard(c28);
        hand6.addCard(c29);
        hand6.addCard(c30);
        System.out.println("Hand: " + hand6);
        System.out.println("Hand type value: " + hand6.handType());
        System.out.println("Hand: " + hand6);
    }

    private static void testCompareTO(){
        System.out.println("\n===COMPARING FLUSH AND FLUSH===");
        Card c1 = new Card(10, "Ten", "Diamonds");
        Card c2 = new Card(11, "Jack", "Diamonds");
        Card c3 = new Card(12, "Queen", "Diamonds");
        Card c4 = new Card(13, "King", "Diamonds");
        Card c5 = new Card(14, "Ace", "Diamonds");
        Card c6 = new Card(10, "Ten", "Clubs");
        Card c7 = new Card(11, "Jack", "Clubs");
        Card c8 = new Card(12, "Queen", "Clubs");
        Card c9 = new Card(13, "King", "Clubs");
        Card c10 = new Card(14, "Ace", "Clubs");
        PokerHand hand1 = new PokerHand();
        PokerHand hand2 = new PokerHand();
        hand1.addCard(c1);
        hand1.addCard(c2);
        hand1.addCard(c3);
        hand1.addCard(c4);
        hand1.addCard(c5);
        hand2.addCard(c6);
        hand2.addCard(c7);
        hand2.addCard(c8);
        hand2.addCard(c9);
        hand2.addCard(c10);
        System.out.println("Hand 1: " + hand1);
        System.out.println("Hand 2: " + hand2);
        System.out.println("Hand 1 type: " + hand1.handType());
        System.out.println("Hand 2 type: " + hand2.handType());
        System.out.println(hand1.compareTo(hand2));
        System.out.println("Hand 1: " + hand1);
        System.out.println("Hand 2: " + hand2);

        System.out.println("\n===COMPARING TWO PAIRS AND TWO PAIRS===");
        c1 = new Card(10, "Ten", "Diamonds");
        c2 = new Card(10, "Ten", "Clubs");
        c3 = new Card(12, "Queen", "Hearts");
        c4 = new Card(12, "Queen", "Spades");
        c5 = new Card(14, "Ace", "Diamonds");
        c6 = new Card(10, "Ten", "Diamonds");
        c7 = new Card(10, "Ten", "Clubs");
        c8 = new Card(10, "Ten", "Hearts");
        c9 = new Card(12, "Queen", "Spades");
        c10 = new Card(12, "Queen", "Diamonds");
        hand1 = new PokerHand();
        hand2 = new PokerHand();
        hand1.addCard(c1);
        hand1.addCard(c2);
        hand1.addCard(c3);
        hand1.addCard(c4);
        hand1.addCard(c5);
        hand2.addCard(c6);
        hand2.addCard(c7);
        hand2.addCard(c8);
        hand2.addCard(c9);
        hand2.addCard(c10);
        System.out.println("Hand 1: " + hand1);
        System.out.println("Hand 2: " + hand2);
        System.out.println("Hand 1 type: " + hand1.handType());
        System.out.println("Hand 2 type: " + hand2.handType());
        System.out.println(hand1.compareTo(hand2));
        System.out.println("Hand 1: " + hand1);
        System.out.println("Hand 2: " + hand2);

        System.out.println("\n===COMPARING SINGLE PAIR AND SINGLE PAIR===");
        c1 = new Card(10, "Ten", "Diamonds");
        c2 = new Card(3, "Three", "Clubs");
        c3 = new Card(6, "Six", "Hearts");
        c4 = new Card(6, "Six", "Spades");
        c5 = new Card(5, "Five", "Diamonds");
        c6 = new Card(10, "Ten", "Diamonds");
        c7 = new Card(5, "Five", "Clubs");
        c8 = new Card(4, "Four", "Hearts");
        c9 = new Card(6, "Six", "Spades");
        c10 = new Card(6, "Six", "Diamonds");
        hand1 = new PokerHand();
        hand2 = new PokerHand();
        hand1.addCard(c1);
        hand1.addCard(c2);
        hand1.addCard(c3);
        hand1.addCard(c4);
        hand1.addCard(c5);
        hand2.addCard(c6);
        hand2.addCard(c7);
        hand2.addCard(c8);
        hand2.addCard(c9);
        hand2.addCard(c10);
        System.out.println("Hand 1: " + hand1);
        System.out.println("Hand 2: " + hand2);
        System.out.println("Hand 1 type: " + hand1.handType());
        System.out.println("Hand 2 type: " + hand2.handType());
        System.out.println(hand1.compareTo(hand2));
        System.out.println("Hand 1: " + hand1);
        System.out.println("Hand 2: " + hand2);

        System.out.println("\n===COMPARING HIGH CARD AND HIGH CARD===");
        c1 = new Card(10, "Ten", "Diamonds");
        c2 = new Card(9, "Nine", "Clubs");
        c3 = new Card(8, "Eight", "Hearts");
        c4 = new Card(7, "Seven", "Spades");
        c5 = new Card(6, "Six", "Diamonds");
        c6 = new Card(10, "Ten", "Diamonds");
        c7 = new Card(9, "Nine", "Clubs");
        c8 = new Card(8, "Eight", "Hearts");
        c9 = new Card(7, "Seven", "Spades");
        c10 = new Card(14, "Ace", "Diamonds");
        hand1 = new PokerHand();
        hand2 = new PokerHand();
        hand1.addCard(c1);
        hand1.addCard(c2);
        hand1.addCard(c3);
        hand1.addCard(c4);
        hand1.addCard(c5);
        hand2.addCard(c6);
        hand2.addCard(c7);
        hand2.addCard(c8);
        hand2.addCard(c9);
        hand2.addCard(c10);
        System.out.println("Hand 1: " + hand1);
        System.out.println("Hand 2: " + hand2);
        System.out.println("Hand 1 type: " + hand1.handType());
        System.out.println("Hand 2 type: " + hand2.handType());
        System.out.println(hand1.compareTo(hand2));
        System.out.println("Hand 1: " + hand1);
        System.out.println("Hand 2: " + hand2);
    }

    private void debugPrint(String statement){
        if(DEBUG){
            System.out.println(statement);
        }
    }
}
