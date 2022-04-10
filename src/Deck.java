
import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> cards;

    public Deck(){
    this.cards = new ArrayList<Card>();
    }







    public void CreateFullDeck(){
        //generate cards
        for(Suit cardSuit : Suit.values()){
            for(Value cardValue : Value.values()){
                //Add a new card to the mix
                this.cards.add(new Card(cardSuit,cardValue));

            }
            }
            }












    public void Shuffle(){
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        //use rand
        Random random = new Random();
        int randomCardIndex = 0;
        int originalSize = this.cards.size();
        for (int i = 0; i < originalSize; i ++){
             // generate Random Index
            randomCardIndex = random.nextInt((this.cards.size()-1 -0) +1 ) +0;
            tempDeck.add(this.cards.get(randomCardIndex));
            //remove from original
            this.cards.remove(randomCardIndex);
        }
            this.cards = tempDeck;
    }





    public String toString(){
        String cardListOutput = "";

        for(Card aCard : cards){
            cardListOutput += "\n" + aCard.toString();

        }
        return cardListOutput;
    }



    public void removeCard(int i) {
        this.cards.remove(i);
    }


    public Card getCard(int i){
        return this.cards.get(i);
        }


    public void addCard(Card addCard){
        this.cards.add(addCard);
        }

     public void Draw(Deck comingFrom){
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
     }

public int cardsValue(){
        int totalValue = 0;
        int aces = 0;
         for(Card aCard : this.cards){
             switch (aCard.getValue()){
                 case TWO: totalValue += 2; break;
                 case THREE: totalValue += 3;break;
                 case FOUR: totalValue += 4; break;
                 case FIVE: totalValue += 5;break;
                 case SIX: totalValue += 6; break;
                 case SEV: totalValue += 7;break;
                 case EIGHT: totalValue += 8; break;
                 case NINE: totalValue += 9;break;
                 case TEN: totalValue += 10; break;
                 case JACK: totalValue += 10;break;
                 case QUEEN: totalValue += 10;break;
                 case KING: totalValue += 10; break;
                 case ACE: totalValue += 1;break;

             }
         }
         for(int i = 0; i < aces; i++){
             if(totalValue > 10){
                 totalValue += 1;
             } else {
                 totalValue += 11;
             }
         }

         return totalValue;
}


        public int deckSize(){
         return this.cards.size();
        }



        public void MoveAllToDeck(Deck moveto){
        int thisDeckSize = this.cards.size();
        //puts cards into deck
            for(int i = 0; i < thisDeckSize; i++){
               moveto.addCard(this.getCard(i));
    }


            for(int i = 0; i < thisDeckSize; i++){
               this.removeCard(0);
    }
}
}//end