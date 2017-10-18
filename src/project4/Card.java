package project4;

/**
 * Models a playing card with rank and suit.
 *
 * Created by Harry on 5/23/2017.
 */
public class Card {

    private int rankInt;
    private String rankStr;
    private String suit;

    /**
     * A Card constructor
     * @param rankInt the rank (as an int) of a card
     * @param rankStr the rank (as a String) of a a card
     * @param suit the suit of a card
     */
    public Card(int rankInt, String rankStr, String suit){
        this.rankInt = rankInt;
        this.rankStr = rankStr;
        this.suit = suit;
    }

    /**
     * @return rank (int) of a card
     */
    public int getRank() {
        return rankInt;
    }

    /**
     * @return suit of a card
     */
    public String getSuit() {
        return suit;
    }

    /**
     *Creates a String representation of a Card. Returns the string
     * @return String representation of a Card
     */
    @Override
    public String toString() {
        return rankStr + " of " + suit;
    }

    public static void main(String[] args){
        Card card1 = new Card(14, "Ace","Spades");
        System.out.println(card1);
        System.out.println("Testing getRank(): " + card1.getRank() + ". Testing getSuit(): " + card1.getSuit());
    }

}
