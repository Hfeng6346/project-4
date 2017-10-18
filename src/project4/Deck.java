package project4;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Models a deck of cards.
 * Created by Harry on 5/23/2017.
 */
public class Deck {

    private ArrayList<Card> deck;
    private int deckIndex;

    /**
     * A deck constructor.
     */
    public Deck(){
        this.deck = new ArrayList<Card>();

        int[] listOfRanksInt = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        String[] listOfRanksStr = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
        String[] listOfSuits = {"Diamonds", "Clubs", "Hearts", "Spades"};

        for(int i = 0; i < listOfRanksInt.length; i++){
            for(String suit : listOfSuits){
                Card newCard = new Card(listOfRanksInt[i], listOfRanksStr[i], suit);
                deck.add(newCard);
            }
        }

        this.deckIndex = deck.size() -1;
    }

    /**
     * Shuffles the deck.
     */
    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    /**
     * Draws a card from the deck. Returns the drawn card.
     * @return a card
     */
    public Card drawCard(){
        deckIndex-=1;
        return deck.get(deckIndex + 1);

    }

    /**
     * Determines the size of the deck. Returns the size.
     * @return size of deck
     */
    public int size(){
        return deckIndex+1;
    }

    /**
     * Creates a String representation of a Deck. Returns the String
     * @return a String representing a Deck
     */
    @Override
    public String toString() {
        String deckString = "";
        for(Card card : deck){
            deckString += card;
            if(deck.indexOf(card) != (deck.size() - 1)){
                deckString += ", ";
            }
        }
        return deckString;
    }

    public static void main(String[] args){
        Deck deck1 = new Deck();
        System.out.println("Deck before drawing: " + deck1);
        System.out.println("Deck size before drawing: " + deck1.size());
        System.out.println("Deck index before drawing: " + deck1.deckIndex);
        Card card1 = deck1.drawCard();
        System.out.println("Drew card: " + card1);
        System.out.println("Deck after drawing: " + deck1);
        System.out.println();
        System.out.println("Deck before shuffling: " + deck1);
        deck1.shuffleDeck();
        System.out.println("Deck after shuffling: " + deck1);
        System.out.println("Deck size after drawing: " + deck1.size());
        System.out.println("Deck index after drawing: " + deck1.deckIndex);

    }
}
