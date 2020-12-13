package cardgame7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import cardgame7.Hand.Card;
import cardgame7.Hand.Rank;
import cardgame7.Hand.Suit;


public class Dealer {

	private  Set<Card> deckOfCards = new HashSet<Card>();

	
	public Dealer() {
		
			
		Card[] cardArray= shuffleTheCards(Card.values());
		
		for (Card card : cardArray) {
			deckOfCards.add(card);
			
		}
				
		
	}
	
	public  Set<Card>  addShuffledCardsToDeck(Card[] shuffledCards)
	{
		Set<Card>  deckOfShuffledCards = new HashSet<Card>();
		 Rank[] rankArray=	Rank.values();
		
		
			for (Rank rank : rankArray) {				
				for (Card card : shuffledCards) {
		     		
		     		deckOfShuffledCards.add(card);
				}
			}
		

     	return deckOfShuffledCards;
	}

	
	
	public void setDeckOfCards(Set<Card> deckOfCards) {
		this.deckOfCards = deckOfCards;
	}

	static void showCards(Set<Card> deckOfCards) {
		System.out.println("---------------------------------------------");
		int count = 0;
		for (Card card : deckOfCards) {
			System.out.printf("%s of %s\t", card.rank, card.suit); // use print f with \t (tab character)
			count++;
			if (count % 4 == 0)
				System.out.println("" + "\n");
		}
		System.out.println("---------------------------------------------");
	}


	
	public Player[] dealCardsToHand(Player[] players, Set<Card> deck, int numOfCards) 
	{
		for (int i = 0; i < players.length; i++) {			
			
			Player player= players[i];  
		Set<Card> setOfCards = null;
		int j=0;
		for (Iterator<Card> iterator = deck.iterator(); iterator.hasNext();) {
		    Card card = iterator.next();
		   
		    if (setOfCards == null) {
				setOfCards = new HashSet<Card>();
			}
		    setOfCards.add(card);
		    iterator.remove();
		    j++;
		    if(j==numOfCards)
		    {
		       	player.setCards(setOfCards);
		    	break;
		    }
		  }
		}
		return players;
    }
	
	
	public static Card[] shuffleTheCards(Card[] cardArray)
	{
		for (int shuffleNumber = 0; shuffleNumber < 5; shuffleNumber++) 
		{
			
			 Random rand = new Random();
		for (int i = 0; i < cardArray.length; i++) {
			int randomIndexToSwap = rand.nextInt(cardArray.length);
			Card temp = cardArray[randomIndexToSwap];
			cardArray[randomIndexToSwap] = cardArray[i];
			cardArray[i] = temp;
			
		 }
    	}
		return cardArray;
	}

	
	
	public Set<Card> getDeckOfCards() {

		return deckOfCards;
	}
	
	
}
