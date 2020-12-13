package cardgame7;

import java.util.HashSet;
import java.util.Set;

import cardgame7.Hand.Card;
import cardgame7.RankSuitChecker.SuitEnum;

public class Player{
	
	 private  Set<Card> cards = new HashSet<Card>();
	 
	private String name;
	 
	 Player(String name){
	        this.name = name;
	    }
	 
	 void showPlayerCards(){
	        System.out.println("---------------------------------------------");
	        for (Card card : cards){
	           
	        	 String rank=RankSuitChecker.findMatchingRank(card.rank);
		         SuitEnum suit=RankSuitChecker.findMatchingSuit(card.suit);
	        	System.out.printf("%s  of %s  ", card.rank, card.suit+"  "+rank+ " of "+ suit.getIcon()+"\n");
           
	        }
	       
	    }

	public String getName() {
		return name;
	}
	
	
	public Set<Card> getCards() {
		return cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}


}
