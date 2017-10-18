package project4;

import java.util.ArrayList;

/**
 * Models a stud poker hand.
 * Created by Harry on 5/25/2017.
 */
public class StudPokerHand {

    private ArrayList<Card> holeCardList;
    private CommunityCard communityCard;

    public static boolean DEBUG = false;

    /**
     * A Stud Poker Hand constructor
     * @param communityCard community cards
     */
    public StudPokerHand(CommunityCard communityCard){
        this.holeCardList = new ArrayList<Card>();
        this.communityCard = communityCard;
    }

    /**
     * Adds a hole card to stud poker hand
     * @param card card to add
     */
    public void addHoleCard(Card card){
        holeCardList.add(card);
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
    public int compareTo(StudPokerHand other){
        PokerHand bestMainHand = this.getBestFiveCardHand();
        PokerHand bestOtherHand = other.getBestFiveCardHand();

        return bestMainHand.compareTo(bestOtherHand);
    }

    private PokerHand getBestFiveCardHand(){
        PokerHand allCards = new PokerHand();
        for(Card card : this.holeCardList){
            allCards.addCard(card);
        }
        debugPrint("HOLE CARDS: " + allCards);

        for(Card card : this.communityCard.getCcList()){
            allCards.addCard(card);
        }
        debugPrint("HOLE + CC CARDS: " + allCards);
        ArrayList<PokerHand> listOfHands = getAllFiveCardHands(allCards, 5);
        debugPrint("SIZE: " + listOfHands.size());
        PokerHand bestHand = listOfHands.get(0);

        for(int i = 1; i < listOfHands.size(); i++){
            debugPrint("==Comparing Next Hand==\nHAND 1: " + bestHand + "nHAND 2: " + listOfHands.get(i));
            if(listOfHands.get(i).compareTo(bestHand) > 0){
                bestHand = listOfHands.get(i);
            }
        }
        return bestHand;
    }

    private ArrayList<PokerHand> getAllFiveCardHands(PokerHand chooseFrom, int targetLength){
        if(chooseFrom.size() == targetLength){
            return listWithOneListInside(chooseFrom);
        }
        else{
            if(chooseFrom.size() == 0){
                return new ArrayList<PokerHand>();
            }
            else{
                PokerHand firstElement = new PokerHand();
                firstElement.addCard(chooseFrom.getCard(0));

                PokerHand otherElement = new PokerHand();
                for(int i = 1; i < chooseFrom.size(); i++){
                    otherElement.addCard(chooseFrom.getCard(i));
                }

                ArrayList<PokerHand> restCombo = getAllFiveCardHands(otherElement, targetLength - 1);
                ArrayList<PokerHand> firstList = prependToAllLists(firstElement, restCombo);
                ArrayList<PokerHand> restList = getAllFiveCardHands(otherElement, targetLength);
                ArrayList<PokerHand> returnList = new ArrayList<PokerHand>();
                returnList.addAll(firstList);
                returnList.addAll(restList);
                return returnList;
            }
        }
    }

    private ArrayList<PokerHand> listWithOneListInside(PokerHand chooseFrom){
        ArrayList<PokerHand> returnList = new ArrayList<PokerHand>();
        returnList.add(chooseFrom);
        return returnList;
    }

    private ArrayList<PokerHand> prependToAllLists(PokerHand prefix, ArrayList<PokerHand> listOfListsToPrepend){
        ArrayList<PokerHand> listOfPokerHands = new ArrayList<PokerHand>();
        for(PokerHand pokerHand : listOfListsToPrepend){
            pokerHand.addCardToIndex(prefix.getCard(0), 0);
            listOfPokerHands.add(pokerHand);
        }
        return listOfPokerHands;
    }

    /**
     * A String representation of a Stud Poker Hand
     * @return a String representing a stud poker hand
     */
    @Override
    public String toString() {
        String handString = "";
        for(Card card : holeCardList){
            handString += card;
            if(holeCardList.indexOf(card) != (holeCardList.size() - 1)){
                handString += ", ";
            }
        }
        return handString;
    }

    public static void main(String[] args){
        PokerHand.DEBUG = true;
        DEBUG = true;
        Deck d1 = new Deck();
        d1.shuffleDeck();

        CommunityCard cc = new CommunityCard();
        for(int i = 0; i < 5; i++){
            cc.addCard(d1.drawCard());
        }
        StudPokerHand sph = new StudPokerHand(cc);
        for(int i = 0; i < 2; i++){
            sph.addHoleCard(d1.drawCard());
        }

        System.out.println(sph.getBestFiveCardHand());
    }

    private void debugPrint(String statement){
        if(DEBUG){
            System.out.println(statement);
        }
    }
}
