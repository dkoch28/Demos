/*
 * Author: Andrew Koch
 * Player.java constructs a new player for the game that hold its two cards, the player's 
 * money, and their best possible hand.
 */


package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

	List<Card> initHand = new ArrayList<Card>();
	List<Card> sevenCards = new ArrayList<Card>();
	List<PokerHand> twentyOneHands = new ArrayList<PokerHand>();
	float currBalance = 0;

	public Player(Card c1, Card c2, float balance) {

		currBalance = balance;
		initHand.add(c1);
		initHand.add(c2);

		sevenCards.add(c1);
		sevenCards.add(c2);

		Collections.sort(initHand);
	}

	public void subtractEntry() {
		currBalance = currBalance - 2;
	}

	public float getBalance() {
		return currBalance;
	}

	public Card getCard(int index) {
		return initHand.get(index);
	}

	public void makeCardList(Card c3) {
		sevenCards.add(c3);
	}

	public void makeListOfHand() {
		Collections.sort(sevenCards);
		List<Card> temp = new ArrayList<Card>();
		PokerHand tempHand;
		for (int i = 0; i < 7; i++) {
			for (int j = i + 1; j < 7; j++) {
				for (int w = 0; w < 7; w++) {
					if (w != i && w != j) {
						temp.add(sevenCards.get(w));
					}
				}
				tempHand = new PokerHand(temp.get(0), temp.get(1), temp.get(2), temp.get(3), temp.get(4));
				while (temp.size() != 0) {
					temp.remove(0);
				}
				twentyOneHands.add(tempHand);
			}

		}
	}

	public PokerHand getBestHand() {
		PokerHand currBestHand = twentyOneHands.get(0);
		Collections.sort(twentyOneHands.get(0).list);
		for (int i = 1; i < 21; i++) {
			Collections.sort(twentyOneHands.get(i).list);
			if (currBestHand.compareTo(twentyOneHands.get(i)) < 0) {
				currBestHand = twentyOneHands.get(i);
			}
		}
		return currBestHand;

	}

}
