package model;

/*
 * Author: Andrew Koch
 * Card.java constructs a card that holds a number value for its rank and a string for 
 * a suit
 */
public class Card implements Comparable<Card> {

	private Rank rank;
	private Suit suit;
	private char pic;
	char suitClub = '\u2663';
	char suitSpade = '\u2660';
	char suitHeart = '\u2665';
	char suitDiamond = '\u2666';

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
		if (suit.toString().equals("Spades")) {
			this.pic = suitSpade;
		}
		if (suit.toString().equals("Diamonds")) {
			this.pic = suitDiamond;
		}
		if (suit.toString().equals("Hearts")) {
			this.pic = suitHeart;
		}
		if (suit.toString().equals("Clubs")) {
			this.pic = suitClub;
		}
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public char getChar() {
		return pic;
	}

	@Override
	public int compareTo(Card o) {
		// TODO Auto-generated method stub
		if (this.getRank().getValue() < o.getRank().getValue()) {
			return -2;
		}
		if (this.getRank().getValue() > o.getRank().getValue()) {
			return 2;
		}
		return 0;
	}

}