package cardgame7;

import cardgame7.Hand.Rank;
import cardgame7.Hand.Suit;

public class RankSuitChecker {
	
	public static String findMatchingRank(Rank rank)
	{
		String ranking="14";
		switch(rank)
		{
		case TWO: ranking="2"; break;
		
		case THREE: ranking="3"; break;
		
		case FOUR: ranking="4"; break;
		
		case FIVE: ranking="5"; break;
		
		case SIX: ranking="6"; break;
		
		case SEVEN: ranking="7"; break;
		
		case EIGHT: ranking="8"; break;
		
		case NINE: ranking="9"; break;
		
		case TEN: ranking="10"; break;
		
		case JACK: ranking="JACK"; break;
		
		case QUEEN: ranking="QUEEN"; break;
		
		case KING: ranking="KING"; break;
		
		case ACE: ranking="ACE";
		
		
		}
		
		return ranking;
	}
	
	public static SuitEnum findMatchingSuit(Suit suit)
	{
		
		
		SuitEnum suits=SuitEnum.EMPTY;
		switch(suit)
		{
		case DIAMONDS: suits=SuitEnum.DIAMONDS; break;
		
		case CLUBS: suits=SuitEnum.CLUBS; break;
		
		case HEARTS: suits=SuitEnum.HEARTS; break;
		
		case SPADES: suits=SuitEnum.SPADES; break;
		}
		return suits;
	}
	
	
	public enum SuitEnum {
		 SPADES("\u2660\uFE0F"), HEARTS("\u2665\uFE0F"), DIAMONDS("\u2666\uFE0F"), CLUBS("\u2663\uFE0F"),EMPTY("");

	    private final String icon;

	    SuitEnum(String icon) {
	        this.icon = icon;
	    }

	    public String getIcon() {
	        return icon;
	    }
    }

}
