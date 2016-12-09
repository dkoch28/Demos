package model;
/*
 * Author: Andrew Koch
 * PokerHand.java constructs a 5 card poker hand using a list and then compares
 * it to another 5 card poker hand to determine which hand wins.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerHand implements Comparable<PokerHand> {

	List<Card> list = new ArrayList<Card>();

	// Constructs a 5 card poker hand
	public PokerHand(Card c1, Card c2, Card c3, Card c4, Card c5) {

		list.add(c1);
		list.add(c2);
		list.add(c3);
		list.add(c4);
		list.add(c5);

		Collections.sort(list);

	}

	// Determines if a poker hand contains one pair
	public int isPair(PokerHand o) {
		int rank1;
		int rank2;
		int pairCount = 0;
		int i = 0;
		while (i < o.list.size() - 1) {
			rank1 = o.list.get(i).getRank().getValue();
			rank2 = o.list.get(i + 1).getRank().getValue();
			if (rank1 == rank2) {
				pairCount++;
			}
			i++;
		}
		if (pairCount == 1) {
			return 1;
		}
		return 0;
	}

	// Determines if a poker hand has two pairs
	public int isTwoPair(PokerHand o) {
		int rank1;
		int rank2;
		int pairCount = 0;
		int i = 0;
		while (i < o.list.size() - 1) {
			rank1 = o.list.get(i).getRank().getValue();
			rank2 = o.list.get(i + 1).getRank().getValue();
			if (rank1 == rank2) {
				pairCount++;
			}
			i++;
		}
		if (pairCount == 2) {
			return 1;
		}
		return 0;
	}

	// Determines if a poker hand contains three cards of the same value
	public int isThree(PokerHand o) {
		int rank1;
		int rank2;
		int pairCount = 0;
		int i = 0;
		while (i < o.list.size() - 1) {
			rank1 = o.list.get(i).getRank().getValue();
			rank2 = o.list.get(i + 1).getRank().getValue();
			if (rank1 == rank2 && i != o.list.size() - 2 && rank2 == o.list.get(i + 2).getRank().getValue()) {
				pairCount++;
			}
			i++;
		}
		if (pairCount == 1) {
			return 1;
		}
		return 0;
	}

	// Determines if a poker hand contains 5 cards of the same suit
	public int isFlush(PokerHand o) {
		Suit suit = o.list.get(0).getSuit();
		if (suit.equals(o.list.get(1).getSuit()) && suit.equals(o.list.get(2).getSuit())
				&& suit.equals(o.list.get(3).getSuit()) && suit.equals(o.list.get(4).getSuit())) {
			return 1;
		}
		return 0;
	}

	// Determines is a poker hand contains a straight
	public int isStraight(PokerHand o) {
		int rank1 = o.list.get(0).getRank().getValue();
		if ((rank1 == (o.list.get(1).getRank().getValue() - 1)) && (rank1 == (o.list.get(2).getRank().getValue() - 2))
				&& (rank1 == (o.list.get(3).getRank().getValue() - 3))
				&& (rank1 == (o.list.get(4).getRank().getValue() - 4))) {
			return 1;
		}
		return 0;
	}

	// Determines if a poker hand contains a full house
	public int isFull(PokerHand o) {
		int rank1, rank2, rank3, rank4, rank5;
		rank1 = o.list.get(0).getRank().getValue();
		rank2 = o.list.get(1).getRank().getValue();
		rank3 = o.list.get(2).getRank().getValue();
		rank4 = o.list.get(3).getRank().getValue();
		rank5 = o.list.get(4).getRank().getValue();

		if (rank1 == rank2 && rank3 == rank4 && rank3 == rank5) {
			return 1;
		}
		if (rank1 == rank2 && rank1 == rank3 && rank4 == rank5) {
			return 1;
		}
		return 0;
	}

	// Determines is a poker hand contains four cards of the same value
	public int isFour(PokerHand o) {
		int rank1, rank2, rank3, rank4, rank5;
		rank1 = o.list.get(0).getRank().getValue();
		rank2 = o.list.get(1).getRank().getValue();
		rank3 = o.list.get(2).getRank().getValue();
		rank4 = o.list.get(3).getRank().getValue();
		rank5 = o.list.get(4).getRank().getValue();

		if ((rank1 == rank2) && (rank1 == rank3) && (rank1 == rank4)) {
			return 1;
		}
		if ((rank2 == rank3) && (rank2 == rank4) && (rank2 == rank5)) {
			return 1;
		}
		return 0;
	}

	// Compares two poker hands to see which hand contains the higher single
	// value.
	public int highCard(PokerHand t, PokerHand o) {
		int i = 4;
		while (i >= 0) {
			if (t.list.get(i).getRank().getValue() > o.list.get(i).getRank().getValue()) {
				return 1;
			}
			if (t.list.get(i).getRank().getValue() < o.list.get(i).getRank().getValue()) {
				return 2;
			}
			i--;
		}
		return 0;
	}

	// Compares two poker hands with one pair to see which one is higher in
	// value
	public int highCardInPair(PokerHand t, PokerHand o) {
		int rank1;
		int rank2;
		int rank3;
		int rank4;
		int tHigh = 0;
		int oHigh = 0;
		int i = 0;
		while (i < o.list.size() - 1) {
			rank1 = o.list.get(i).getRank().getValue();
			rank2 = o.list.get(i + 1).getRank().getValue();
			if (rank1 == rank2) {
				oHigh = rank1;
				break;
			}
			i++;
		}
		i = 0;
		while (i < t.list.size() - 1) {
			rank3 = t.list.get(i).getRank().getValue();
			rank4 = t.list.get(i + 1).getRank().getValue();
			if (rank3 == rank4) {
				tHigh = rank3;
				break;
			}
			i++;
		}
		if (tHigh > oHigh) {
			return 1;
		}
		if (oHigh > tHigh) {
			return 2;
		}
		return 0;
	}

	// Determines the value of the higher ranked pair in a poker hand with two
	// pairs
	public int getHighCardInTwoPair(PokerHand o) {
		int rank1;
		int rank2;
		int pair = 0;
		int i = 0;
		while (i < o.list.size() - 1) {
			rank1 = o.list.get(i).getRank().getValue();
			rank2 = o.list.get(i + 1).getRank().getValue();
			if (rank1 == rank2) {
				if (rank1 > pair) {
					pair = rank1;
				}
			}
			i++;
		}

		return pair;
	}

	// Determines the value of the lower ranked pair in a poker hand with two
	// pairs
	public int getLowPairInTwoPair(PokerHand o) {
		int rank1;
		int rank2;
		int pair = 0;
		int lowerPair = 0;
		int i = 0;
		while (i < o.list.size() - 1) {
			rank1 = o.list.get(i).getRank().getValue();
			rank2 = o.list.get(i + 1).getRank().getValue();
			if (rank1 == rank2) {
				if (rank1 > pair) {
					lowerPair = pair;
					pair = rank1;
				}
			}
			i++;
		}

		return lowerPair;
	}

	// Determines the value of the three of a kind in a poker hand
	public int highCardInThree(PokerHand o) {
		int rank1;
		int rank2;
		int pair = 0;
		int i = 0;
		while (i < o.list.size() - 1) {
			rank1 = o.list.get(i).getRank().getValue();
			rank2 = o.list.get(i + 1).getRank().getValue();
			if (rank1 == rank2 && i != o.list.size() - 2 && rank2 == o.list.get(i + 2).getRank().getValue()) {
				pair = rank1;
			}
			i++;
		}
		return pair;
	}

	// Determines the value of the four of a kind in a poker hand
	public int highCardInFour(PokerHand o) {
		int rank1, rank2, rank3, rank4, rank5;
		int pair = 0;
		rank1 = o.list.get(0).getRank().getValue();
		rank2 = o.list.get(1).getRank().getValue();
		rank3 = o.list.get(2).getRank().getValue();
		rank4 = o.list.get(3).getRank().getValue();
		rank5 = o.list.get(4).getRank().getValue();

		if ((rank1 == rank2) && (rank1 == rank3) && (rank1 == rank4)) {
			pair = rank1;
		}
		if ((rank2 == rank3) && (rank2 == rank4) && (rank2 == rank5)) {
			pair = rank2;
		}
		return pair;
	}

	// Finds the next highest value cards outside of the pair in a poker hand
	public int getKickerInTwoPair(PokerHand o) {
		int i = 0;
		while (i < o.list.size() - 1) {
			if (o.list.get(i).getRank().getValue() != o.list.get(i + 1).getRank().getValue()) {
				return o.list.get(i).getRank().getValue();
			}
			i++;
			i++;
			if (i == 4) {
				break;
			}
		}
		return o.list.get(4).getRank().getValue();
	}

	public int nameOfHand() {
		int value = 0;
		if (isPair(this) == 1) {
			value = 1;
		}
		if (isTwoPair(this) == 1) {
			value = 2;
		}
		if (isThree(this) == 1) {
			value = 3;
		}
		if (isStraight(this) == 1) {
			value = 4;
		}
		if (isFlush(this) == 1) {
			if (value == 4) {
				value = 8;
			} else {
				value = 5;
			}
		}
		if (isFull(this) == 1) {
			value = 6;
		}
		if (isFour(this) == 1) {
			value = 7;
		}
		return value;
	}

	public int comparePairs(PokerHand t, PokerHand o) {
		int tPair = 0;
		int oPair = 0;

		for (int i = 0; i < 4; i++) {
			if (t.list.get(i).getRank().getValue() == t.list.get(i + 1).getRank().getValue()) {
				tPair = t.list.get(i).getRank().getValue();
			}
			if (o.list.get(i).getRank().getValue() == o.list.get(i + 1).getRank().getValue()) {
				oPair = o.list.get(i).getRank().getValue();
			}
		}
		if (tPair > oPair) {
			return 1;
		}
		if (tPair < oPair) {
			return 2;
		}
		return 0;
	}

	// Compares two poker hands to determine which one is worth a higher amount.
	@Override
	public int compareTo(PokerHand o) {
		// TODO Auto-generated method stub

		int thisValue = 0;
		int otherValue = 0;
		if (isPair(this) == 1) {
			thisValue = 1;
		}
		if (isTwoPair(this) == 1) {
			thisValue = 2;
		}
		if (isThree(this) == 1) {
			thisValue = 3;
		}
		if (isStraight(this) == 1 || this.isStraight(o) == 2) {
			thisValue = 4;
		}
		if (isFlush(this) == 1) {
			if (thisValue == 4) {
				thisValue = 8;
			} else {
				thisValue = 5;
			}
		}
		if (isFull(this) == 1) {
			thisValue = 6;
		}
		if (isFour(this) == 1) {
			thisValue = 7;
		}

		// Other's values
		if (o.isPair(o) == 1) {
			otherValue = 1;
		}
		if (o.isTwoPair(o) == 1) {
			otherValue = 2;
		}
		if (o.isThree(o) == 1) {
			otherValue = 3;
		}
		if (o.isStraight(o) == 1 || o.isStraight(o) == 2) {
			otherValue = 4;
		}
		if (o.isFlush(o) == 1) {
			if (otherValue == 4) {
				otherValue = 8;
			} else {
				otherValue = 5;
			}
		}
		if (o.isFull(o) == 1) {
			otherValue = 6;
		}
		if (o.isFour(o) == 1) {
			otherValue = 7;
		}
		if (thisValue == 0 && otherValue == 0) {
			if (highCard(this, o) == 0) {
				thisValue = 0;
				otherValue = 0;
			}
			if (highCard(this, o) == 1) {
				return 2;
			}
			if (highCard(this, o) == 2) {
				return -2;
			}
		}
		if (thisValue == 1 && otherValue == 1) {

			if (comparePairs(this, o) == 1) {
				return 2;
			}
			if (comparePairs(this, o) == 2) {
				return -2;
			}
			if (highCard(this, o) == 1) {
				return 2;
			}
			if (highCard(this, o) == 2) {
				return -2;
			}
		}
		if (thisValue == 2 && otherValue == 2) {
			thisValue = getHighCardInTwoPair(this);
			otherValue = getHighCardInTwoPair(o);
			if (thisValue > otherValue) {
				return 2;
			}
			if (thisValue < otherValue) {
				return -2;
			}
			if (thisValue == otherValue) {
				thisValue = getLowPairInTwoPair(this);
				otherValue = getLowPairInTwoPair(o);
				if (thisValue > otherValue) {
					return 2;
				}
				if (thisValue < otherValue) {
					return -2;
				}
			}
			if (thisValue == otherValue) {
				thisValue = getKickerInTwoPair(this);
				otherValue = getKickerInTwoPair(o);
				if (thisValue > otherValue) {
					return 2;
				}
				if (thisValue < otherValue) {
					return -2;
				}
			}
			return 0;
		}
		if (thisValue == 3 && otherValue == 3) {
			thisValue = highCardInThree(this);
			otherValue = highCardInThree(o);
			if (thisValue > otherValue) {
				return 2;
			}
			if (thisValue < otherValue) {
				return -2;
			} else {
				return 0;
			}
		}
		if (thisValue == 4 && otherValue == 4) {
			if (highCard(this, o) == 1) {
				return 2;
			}
			if (highCard(this, o) == 2) {
				return -2;
			} else {
				return 0;
			}
		}
		if (thisValue == 5 && otherValue == 5) {
			if (highCard(this, o) == 1) {
				return 2;
			}
			if (highCard(this, o) == 2) {
				return -2;
			} else {
				return 0;
			}
		}
		if (thisValue == 6 && otherValue == 6) {
			thisValue = highCardInThree(this);
			otherValue = highCardInThree(o);
			if (thisValue > otherValue) {
				return 2;
			}
			if (thisValue < otherValue) {
				return -2;
			}
		}
		if (thisValue == 7 && otherValue == 7) {
			thisValue = highCardInFour(this);
			otherValue = highCardInFour(o);
			if (thisValue > otherValue) {
				return 2;
			}
			if (otherValue > thisValue) {
				return -2;
			}
		}
		if (thisValue == 8 && otherValue == 8) {
			if (highCard(this, o) == 1) {
				return 2;
			}
			if (highCard(this, o) == 2) {
				return -2;
			}
			return 0;
		}
		if (thisValue > otherValue) {
			return 2;
		}
		if (thisValue < otherValue) {
			return -2;
		}
		return 0;
	}

}
