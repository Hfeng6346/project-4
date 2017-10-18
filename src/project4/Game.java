package project4;

import java.util.Scanner;
/**
 * Creates a game.
 * Created by Harry on 5/30/2017.
 */
public class Game {

    private int score;
    private int cardsUsedPerTurn = 9;

    /**
     * A Game constructor
     */
    public Game(){
        this.score = 0;
    }

    /**
     * Initiates a game.
     */
    public void startGame(){
        Deck deck = new Deck();
        deck.shuffleDeck();

        while(playerTurn(deck) && checkDeckSize(deck));
        if(deck.size() < cardsUsedPerTurn){
            System.out.println("Uh oh! Not enough cards to continue playing. Game Over!");
        }
    }

    /**
     * Simulates a player turn
     * @param deck deck of cards
     * @return true if player guesses correctly. False otherwise.
     */
    public boolean playerTurn(Deck deck){
        CommunityCard cc = new CommunityCard();

        for(int i = 0; i < 5; i++){
            cc.addCard(deck.drawCard());
        }

        StudPokerHand player = new StudPokerHand(cc);
        StudPokerHand cpu = new StudPokerHand(cc);

        for(int i = 0; i < 2; i++){
            player.addHoleCard(deck.drawCard());
            cpu.addHoleCard(deck.drawCard());
        }

        System.out.println("The community cards are:" + "\n   " + cc +
                "\nWhich of the following hands is worth more?" + "\n   Hand A: " + player + "\n   Hand B: " + cpu +
                "\nEnter 'a' for Hand A, 'b' for Hand B, or 'tie' if both hands are equal in value.");

        int userAnswer = convertUserInputToInt(input());
        int correctAnswer = player.compareTo(cpu);

        return checkAnswer(userAnswer, correctAnswer);
    }

    private String input(){
        Scanner reader = new Scanner(System.in);
        return reader.nextLine();
    }

    private int convertUserInputToInt(String input){
        if(input.equals("a")){
            return 1;
        }
        else{
            if(input.equals("b")){
                return -1;
            }
            else{
                if(input.equals("tie")){
                    return 0;
                }
                else{
                    return 404;
                }
            }
        }
    }

    private boolean checkAnswer(int userAnswer, int correctAnswer){
        if(userAnswer == 404){
            System.out.println("Error. Please enter 'a' for Hand A, 'b' for Hand B, or 'tie' if both hands are equal in value.");
            return checkAnswer(convertUserInputToInt(input()), correctAnswer);
        }
        else{
            if(userAnswer == correctAnswer){
                score++;
                System.out.println("Correct!\nYour score is: " + score + "\n");
                return true;
            }
            else{
                System.out.println("Wrong!\nYour score is: " + score + "\nGame Over!");
                return false;
            }
        }
    }

    private boolean checkDeckSize(Deck deck){
        return deck.size() >=  cardsUsedPerTurn;
    }
}
