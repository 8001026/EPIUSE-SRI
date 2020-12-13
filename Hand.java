package cardgame7;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Ordering.from;
import static com.google.common.collect.Ordering.natural;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;


import java.util.Comparator;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Function;

import com.google.common.collect.EnumMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multiset.Entry;


import com.google.common.collect.Ordering;

public class Hand implements Comparable<Hand> {
    public Category category;
    private String name;
   	private final LinkedList<Rank> distinctRanks = new LinkedList<>();

      	
    
	public Hand(Set<Card> cards,String name) {
    	this.name=name;
		checkArgument(cards.size() == 5);
        Set<Suit> suits = EnumSet.noneOf(Suit.class);
        Multiset<Rank> ranks = EnumMultiset.create(Rank.class);
        for (Card card : cards) {
            suits.add(card.suit);
            ranks.add(card.rank);
        }
        Set<Entry<Rank>> entries = ranks.entrySet();
        for (Entry<Rank> entry : byCountThenRank.immutableSortedCopy(entries)) {
            distinctRanks.addFirst(entry.getElement());
        }
        Rank first = distinctRanks.getFirst();
        int distinctCount = distinctRanks.size();
        if (distinctCount == 5) {
            boolean flush = suits.size() == 1;
            if (first.ordinal() - distinctRanks.getLast().ordinal() == 4) {
                category = flush ? Category.STRAIGHT_FLUSH : Category.STRAIGHT;
                System.out.println(name+" has a "+category.toString());
            }
            else if (first == Rank.ACE && distinctRanks.get(1) == Rank.FIVE) {
                category = flush ? Category.STRAIGHT_FLUSH : Category.STRAIGHT;
                // ace plays low, move to end
                distinctRanks.addLast(distinctRanks.removeFirst());
                System.out.println(name+" has a "+category.toString());
            }
            else {
                category = flush ? Category.FLUSH : Category.HIGH_CARD;
                System.out.println(name+" has a "+category.toString());
            }
        }
        else if (distinctCount == 4) {
            category = Category.ONE_PAIR;
            System.out.println(name+" has a "+category.toString());
        }
        else if (distinctCount == 3) {
            category = ranks.count(first) == 2 ? Category.TWO_PAIR : Category.THREE_OF_A_KIND;
            System.out.println(name+" has a "+category.toString());
        }
        else {
            category = ranks.count(first) == 3 ? Category.FULL_HOUSE : Category.FOUR_OF_A_KIND;
            System.out.println(name+" has a "+category.toString());
        }
    }


    @Override
	public final int compareTo(Hand that) {
		return byCategoryThenRanks.compare(this, that);
	}
	
	
	private static final Ordering<Entry<Rank>> byCountThenRank;
	
	static {
		Comparator<Entry<Rank>> byCount = comparingInt(Entry::getCount);
		Comparator<Entry<Rank>> byRank = comparing(Entry::getElement);
		byCountThenRank = from(byCount.thenComparing(byRank));
	}
	
	private static final Comparator<Hand> byCategoryThenRanks;
	
	static {
		Comparator<Hand> byCategory = comparing((Hand hand) -> hand.category);
		Function<Hand, Iterable<Rank>> getRanks = (Hand hand) -> hand.distinctRanks;
		Comparator<Hand> byRanks = comparing(getRanks, natural().lexicographical());
		byCategoryThenRanks = byCategory.thenComparing(byRanks);
	}
    
    public enum Category {
        HIGH_CARD,
        ONE_PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        STRAIGHT,
        FLUSH,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        STRAIGHT_FLUSH;
    }

    public enum Rank {
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE;
    }

    public enum Suit {
        DIAMONDS,
        CLUBS,
        HEARTS,
        SPADES;
    }

    public enum Card {
    	  TWO_DIAMONDS(Rank.TWO, Suit.DIAMONDS),
          THREE_DIAMONDS(Rank.THREE, Suit.DIAMONDS),
          FOUR_DIAMONDS(Rank.FOUR, Suit.DIAMONDS),
          FIVE_DIAMONDS(Rank.FIVE, Suit.DIAMONDS),
          SIX_DIAMONDS(Rank.SIX, Suit.DIAMONDS),
          SEVEN_DIAMONDS(Rank.SEVEN, Suit.DIAMONDS),
          EIGHT_DIAMONDS(Rank.EIGHT, Suit.DIAMONDS),
          NINE_DIAMONDS(Rank.NINE, Suit.DIAMONDS),
          TEN_DIAMONDS(Rank.TEN, Suit.DIAMONDS),
          JACK_DIAMONDS(Rank.JACK, Suit.DIAMONDS),
          QUEEN_DIAMONDS(Rank.QUEEN, Suit.DIAMONDS),
          KING_DIAMONDS(Rank.KING, Suit.DIAMONDS),
          ACE_DIAMONDS(Rank.ACE, Suit.DIAMONDS),

          TWO_CLUBS(Rank.TWO, Suit.CLUBS),
          THREE_CLUBS(Rank.THREE, Suit.CLUBS),
          FOUR_CLUBS(Rank.FOUR, Suit.CLUBS),
          FIVE_CLUBS(Rank.FIVE, Suit.CLUBS),
          SIX_CLUBS(Rank.SIX, Suit.CLUBS),
          SEVEN_CLUBS(Rank.SEVEN, Suit.CLUBS),
          EIGHT_CLUBS(Rank.EIGHT, Suit.CLUBS),
          NINE_CLUBS(Rank.NINE, Suit.CLUBS),
          TEN_CLUBS(Rank.TEN, Suit.CLUBS),
          JACK_CLUBS(Rank.JACK, Suit.CLUBS),
          QUEEN_CLUBS(Rank.QUEEN, Suit.CLUBS),
          KING_CLUBS(Rank.KING, Suit.CLUBS),
          ACE_CLUBS(Rank.ACE, Suit.CLUBS),

          TWO_HEARTS(Rank.TWO, Suit.HEARTS),
          THREE_HEARTS(Rank.THREE, Suit.HEARTS),
          FOUR_HEARTS(Rank.FOUR, Suit.HEARTS),
          FIVE_HEARTS(Rank.FIVE, Suit.HEARTS),
          SIX_HEARTS(Rank.SIX, Suit.HEARTS),
          SEVEN_HEARTS(Rank.SEVEN, Suit.HEARTS),
          EIGHT_HEARTS(Rank.EIGHT, Suit.HEARTS),
          NINE_HEARTS(Rank.NINE, Suit.HEARTS),
          TEN_HEARTS(Rank.TEN, Suit.HEARTS),
          JACK_HEARTS(Rank.JACK, Suit.HEARTS),
          QUEEN_HEARTS(Rank.QUEEN, Suit.HEARTS),
          KING_HEARTS(Rank.KING, Suit.HEARTS),
          ACE_HEARTS(Rank.ACE, Suit.HEARTS),

          TWO_SPADES(Rank.TWO, Suit.SPADES),
          THREE_SPADES(Rank.THREE, Suit.SPADES),
          FOUR_SPADES(Rank.FOUR, Suit.SPADES),
          FIVE_SPADES(Rank.FIVE, Suit.SPADES),
          SIX_SPADES(Rank.SIX, Suit.SPADES),
          SEVEN_SPADES(Rank.SEVEN, Suit.SPADES),
          EIGHT_SPADES(Rank.EIGHT, Suit.SPADES),
          NINE_SPADES(Rank.NINE, Suit.SPADES),
          TEN_SPADES(Rank.TEN, Suit.SPADES),
          JACK_SPADES(Rank.JACK, Suit.SPADES),
          QUEEN_SPADES(Rank.QUEEN, Suit.SPADES),
          KING_SPADES(Rank.KING, Suit.SPADES),
          ACE_SPADES(Rank.ACE, Suit.SPADES);

        public final Rank rank;

        public final Suit suit;

        Card(Rank rank, Suit suit) {
            this.rank = rank;
            this.suit = suit;
        }
    }
 	
	public String getName() {
		return name;
	}
}