package cardgame7;

import java.util.Scanner;
import java.util.Set;

import cardgame7.Player;
import cardgame7.Hand.Card;


public class TestHandExample {

	public static void main(String[] args) {
	
        int fiveCardPOker=5;
		Scanner input = new Scanner(System.in);
	    Player[] players = new Player[4];
	    Dealer dealer=new Dealer();
	    
		Set<Card> deckOfCards=dealer.getDeckOfCards();
		
		System.out.println("Un-shuffled Cards .");
	    Dealer.showCards(deckOfCards);
	    
	    System.out.println("Shuffled Cards.");
	    Card[] shuffledCards = Dealer.shuffleTheCards(Card.values());
	    Set<Card> shuffledDeckOfCards=dealer.addShuffledCardsToDeck(shuffledCards);
	    Dealer.showCards(shuffledDeckOfCards);
		
	    for(int i = 0; i < players.length; i++) {
	        System.out.println("Enter Player Name: ");
	        players[i] = new Player(input.nextLine());
	      	      
	    }
	    Player[] playersWithCards=dealer.dealCardsToHand(players, deckOfCards,fiveCardPOker);
	    
	    for(Player player : playersWithCards)
	    {
	    	System.out.println(player.getName());
	        player.showPlayerCards();
	    }
	    
	    Hand[] hands= new Hand[playersWithCards.length];
	    for(int i = 0; i < playersWithCards.length; i++) 
	    {
	    Hand hand=new Hand(playersWithCards[i].getCards(),playersWithCards[i].getName());
	    
	    hands[i]=hand;
	    }
	    sort(hands);
	
	}


	  public static void sort(Comparable[] a){ 
	        int n = a.length;
	        Hand highestHand=null;
	        for (int i = 0; i < n; i++) {
	            
	        	for (int j = i; j > 0; j--) {
	        		int k=a[j].compareTo(a[j-1]);
	                if(k > 0)  
	                {
	                	if(highestHand!=null)
	                	{
	                		int l=highestHand.compareTo((Hand)a[j]);
	                		if(l<0)
	                		{
	                			highestHand=(Hand)a[j];
	                		}
	                	}
	                	else {
	                	highestHand=(Hand) a[j];
	                	}
	                }
	                if(k<0)
	                {
	                	if(highestHand!=null)
	                	{
	                		int m=highestHand.compareTo((Hand)a[j]);
	                		if(m<0)
	                		{
	                			highestHand=(Hand)a[j];
	                		}
	                	}
	                	else
	                	{
	                	highestHand=(Hand) a[j-1];
	                	}
	                }
	        	}
	            
	        }
	        
	        System.out.println(highestHand.getName()+"  Won the Bet with  "+highestHand.category.name());
	      
	    }
}
