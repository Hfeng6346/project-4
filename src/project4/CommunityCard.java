package project4;

import java.util.ArrayList;

/**
 * Models a community card.
 * Created by Harry on 5/25/2017.
 */
public class CommunityCard
{
    private ArrayList<Card> ccList;

    /**
     * A Community Card constructor
     */
    public CommunityCard(){
        this.ccList = new ArrayList<Card>();
    }

    /**
     * Adds a card to the Community Card
     * @param card a card to add to Community Card
     */
    public void addCard(Card card){
        ccList.add(card);
    }

    /**
     * Removes a card from the Community Card
     * @param card a card to remove from Community Card
     */
    public void removeCard(Card card){
        ccList.remove(card);
    }

    /**
     * Retrieves a card from Community Card
     * @param index location of card
     * @return the card
     */
    public Card getCard(int index){
        return ccList.get(index);
    }

    /**
     * Gets a list of all cards in Community Card
     * @return list of cards in Community Card
     */
    public ArrayList<Card> getCcList(){
        return ccList;
    }

    /**
     * @return how many cards are in the Community Cards
     */
    public int getCcListSize(){
        return ccList.size();
    }

    /**
     * A String representation of Community Cards.
     * @return a String representing Community Cards
     */
    @Override
    public String toString() {
        String handString = "";
        for(Card card : ccList){
            handString += card;
            if(ccList.indexOf(card) != (ccList.size() - 1)){
                handString += ", ";
            }
        }
        return handString;
    }
}
