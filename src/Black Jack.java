import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;
class Main {
    public static void main(String[] args) {
        System.out.println("welcome to blackjack ");

        //Create playing deck
        Deck playingDeck = new Deck();
        playingDeck.CreateFullDeck();
        playingDeck.Shuffle();
        //Create a Hand
        Deck playerHand = new Deck();
        Deck dealerHand = new Deck();

        double playerMoney = 10000.00;
        Scanner sc = new Scanner(System.in);

        //Game loop
        while (playerMoney > 0) {
            //play on!
            System.out.println("you have: $" + playerMoney +"\nHow much will you bet:");
            double playerBet = sc.nextDouble();
            if (playerBet > playerMoney) {
                System.out.println("please stop playing ;) ");
                break;
            }
            boolean endRound = false;
            //Start Dealing
            //Player will get 2 cards
            playerHand.Draw(playingDeck);
            playerHand.Draw(playingDeck);

             //Dealers
            dealerHand.Draw(playingDeck);
            dealerHand.Draw(playingDeck);

            while (true) {

                System.out.println("your hand");
                System.out.println(playerHand.toString());

                System.out.println("your deck is valued at: "+playerHand.cardsValue());
                System.out.println("---------------------------------------");
                System.out.println("Dealer Hand: "+ dealerHand.getCard(0).toString()+" and hidden card");
                System.out.println("--------------------------------------");
                // player choice

                System.out.println("would you like to hit or stand\n (1) for hit(2) for stay");
                int responce = sc.nextInt();

                if(responce == 1){
                    playerHand.Draw(playingDeck);
                    System.out.println("--------------------------------------");
                    System.out.println("you drew a:"+ playerHand.getCard(playerHand.deckSize()-1).toString());
                    //bust if > 21
                    if(playerHand.cardsValue() > 21){
                        System.out.println("bust: your card value is:"+playerHand.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;

                    }
                }

                if(responce == 2){
                    break;
                }
            }
//reveal dealers cards

            System.out.println("Dealer Cards"+ dealerHand.toString());
            System.out.println("--------------------------------------");
            //see is dealer beat you
            if((dealerHand.cardsValue()>playerHand.cardsValue()) && endRound == false){
                System.out.println("dealer one");
                playerMoney -= playerBet;
                endRound = true;
            }

            //Dealer draws 16, stand at 17

            while((dealerHand.cardsValue() < 17)&& endRound == false){
                dealerHand.Draw(playerHand);
                System.out.println("Dealer Drew: "+ dealerHand.getCard(dealerHand.deckSize()-1).toString());
            }
            //Display Total Value for Dealer
            System.out.println("Dealers hand is valued at:" + dealerHand.cardsValue());
            //Determine if dealer busted
            if ((dealerHand.cardsValue() > 21)&& endRound == false){
                System.out.println("Dealer Broke: YOU WIN");
                playerMoney = playerMoney +(playerBet + (playerMoney / 5));
                endRound = true;
            }

            //determine if push
            if((playerHand.cardsValue() == dealerHand.cardsValue())&& endRound == false){
                System.out.println("push");
                endRound = true;
            }

            if(playerHand.cardsValue() > dealerHand.cardsValue()&& endRound == false){
                System.out.println("PLAYER 1 WON ROUND");
                playerMoney = playerBet + playerBet / 5 ;
                endRound = true;
            }
            playerHand.MoveAllToDeck(playingDeck);
            dealerHand.MoveAllToDeck(playingDeck);
            System.out.println("--------------------------------------");
            System.out.println("end of hand");
            System.out.println("--------------------------------------");
        }
            System.out.println("______________________");
            System.out.println("you lost");
    }
}
