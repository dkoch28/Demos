package model;

/*
 * Author: Andrew Koch
 * Rank.java constructs a enum that defines the 13 possible values of a card in a
 * deck of cards.
 */
public enum Rank {
	Deuce(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(10), Jack(11), Queen(12), King(
			13), Ace(14);

	private int value;

	Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public String valForPrint() {
		if (value == 2) {
			return "2";
		}
		if (value == 3) {
			return "3";
		}
		if (value == 4) {
			return "4";
		}
		if (value == 5) {
			return "5";
		}
		if (value == 6) {
			return "6";
		}
		if (value == 7) {
			return "7";
		}
		if (value == 8) {
			return "8";
		}
		if (value == 9) {
			return "9";
		}
		if (value == 10) {
			return "10";
		}
		if (value == 11) {
			return "J";
		}
		if (value == 12) {
			return "Q";
		}
		if (value == 13) {
			return "K";
		}
		if (value == 14) {
			return "A";
		}
		return null;

	}
}