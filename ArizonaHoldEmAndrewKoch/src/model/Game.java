/*
 * Author: Andrew Koch
 * Game.java starts a game with the specified amount of players from standard in and determines 
 * which player has the best hand.
 */

package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {

	private static float startBalance = 100;
	private static int continuePlaying = 0;
	private static int gameCount = 0;

	public static void main(String args[]) {

		List<Player> players = new ArrayList<Player>();

		System.out.println("How many Players?");
		Scanner input = new Scanner(System.in);
		int numOfPlayers = input.nextInt();

		while (continuePlaying == 0) {
			List<PokerHand> bestHands = new ArrayList<PokerHand>();
			List<Card> community = new ArrayList<Card>();
			float pot = 0;
			Deck deck = new Deck();

			// Creates wanted number of players
			if (gameCount == 0) {
				for (int i = 0; i < numOfPlayers; i++) {
					players.add(new Player(deck.getCard(), deck.getCard(), startBalance));
				}
			} else {
				for (int d = 0; d < numOfPlayers; d++) {
					float balance = players.get(d).getBalance();
					players.add(d, new Player(deck.getCard(), deck.getCard(), balance));
					players.remove(d + 1);
				}
			}

			// Collect entry fee
			for (int j = 0; j < players.size(); j++) {
				players.get(j).subtractEntry();
				pot += 2.0;
			}

			// Add cards to communtiy
			for (int k = 0; k < 5; k++) {
				community.add(deck.getCard());
			}

			// Print community cards
			System.out.print("Community Cards: ");
			for (int l = 0; l < 5; l++) {
				System.out.print(community.get(l).getRank().valForPrint());
				System.out.print(community.get(l).getChar() + " ");
			}
			System.out.println("\n++++++++++++++++++++++++++++++++++++");

			// Add community cards to player's card list
			for (int h = 0; h < numOfPlayers; h++) {
				for (int a = 0; a < 5; a++) {
					players.get(h).makeCardList(community.get(a));
				}
			}
			// Make all hands
			for (int p = 0; p < numOfPlayers; p++) {
				players.get(p).makeListOfHand();
			}

			// Print Players Hands
			for (int m = 0; m < numOfPlayers; m++) {
				int value;
				String nameOfHand = null;
				PokerHand curr;
				curr = players.get(m).getBestHand();
				value = curr.nameOfHand();
				if (value == 0) {
					nameOfHand = "High Card";
				}
				if (value == 1) {
					nameOfHand = "Pair";
				}
				if (value == 2) {
					nameOfHand = "Two Pair";
				}
				if (value == 3) {
					nameOfHand = "Three Of A Kind";
				}
				if (value == 4) {
					nameOfHand = "Straight";
				}
				if (value == 5) {
					nameOfHand = "Flush";
				}
				if (value == 6) {
					nameOfHand = "Full House";
				}
				if (value == 7) {
					nameOfHand = "Four Of A Kind";
				}
				if (value == 8) {
					nameOfHand = "Straight Flush";
				}
				bestHands.add(curr);
				System.out.print("Player " + (m + 1) + ": $" + players.get(m).getBalance() + " - "
						+ players.get(m).getCard(0).getRank().valForPrint());
				System.out.print(players.get(m).getCard(0).getChar() + " ");
				System.out.print(players.get(m).getCard(1).getRank().valForPrint());
				System.out.print(players.get(m).getCard(1).getChar() + "\n");
				System.out.println("\t Best hand: " + curr.list.get(0).getRank().valForPrint()
						+ curr.list.get(0).getChar() + " " + curr.list.get(1).getRank().valForPrint()
						+ curr.list.get(1).getChar() + " " + curr.list.get(2).getRank().valForPrint()
						+ curr.list.get(2).getChar() + " " + curr.list.get(3).getRank().valForPrint()
						+ curr.list.get(3).getChar() + " " + curr.list.get(4).getRank().valForPrint()
						+ curr.list.get(4).getChar() + " - " + nameOfHand + "\n");
			}
			Collections.sort(bestHands);
			String nameOfWinningHand = null;
			if (bestHands.get(numOfPlayers - 1).nameOfHand() == 0) {
				nameOfWinningHand = "High Card";
			}
			if (bestHands.get(numOfPlayers - 1).nameOfHand() == 1) {
				nameOfWinningHand = "Pair";
			}
			if (bestHands.get(numOfPlayers - 1).nameOfHand() == 2) {
				nameOfWinningHand = "Two Pair";
			}
			if (bestHands.get(numOfPlayers - 1).nameOfHand() == 3) {
				nameOfWinningHand = "Three Of A Kind";
			}
			if (bestHands.get(numOfPlayers - 1).nameOfHand() == 4) {
				nameOfWinningHand = "Straight";
			}
			if (bestHands.get(numOfPlayers - 1).nameOfHand() == 5) {
				nameOfWinningHand = "Flush";
			}
			if (bestHands.get(numOfPlayers - 1).nameOfHand() == 6) {
				nameOfWinningHand = "Full House";
			}
			if (bestHands.get(numOfPlayers - 1).nameOfHand() == 7) {
				nameOfWinningHand = "Four Of A Kind";
			}
			if (bestHands.get(numOfPlayers - 1).nameOfHand() == 8) {
				nameOfWinningHand = "Straight Flush";
			}
			if (bestHands.get(numOfPlayers - 1).compareTo(bestHands.get(numOfPlayers - 2)) != 0) {
				int winningPlayer = 0;
				for (int g = 0; g < numOfPlayers; g++) {
					if (players.get(g).getBestHand().compareTo(bestHands.get(numOfPlayers - 1)) == 0) {
						players.get(g).currBalance += pot;
						winningPlayer = g + 1;
					}
				}
				System.out.println(
						"Winner: Player " + winningPlayer + " $" + players.get(winningPlayer - 1).getBalance());
				System.out.println("++++++++++++++++++++++++++++++++++++");
				System.out.println("\t" + nameOfWinningHand + " "
						+ bestHands.get(numOfPlayers - 1).list.get(0).getRank().valForPrint()
						+ bestHands.get(numOfPlayers - 1).list.get(0).getChar() + " "
						+ bestHands.get(numOfPlayers - 1).list.get(1).getRank().valForPrint()
						+ bestHands.get(numOfPlayers - 1).list.get(1).getChar() + " "
						+ bestHands.get(numOfPlayers - 1).list.get(2).getRank().valForPrint()
						+ bestHands.get(numOfPlayers - 1).list.get(2).getChar() + " "
						+ bestHands.get(numOfPlayers - 1).list.get(3).getRank().valForPrint()
						+ bestHands.get(numOfPlayers - 1).list.get(3).getChar() + " "
						+ bestHands.get(numOfPlayers - 1).list.get(4).getRank().valForPrint()
						+ bestHands.get(numOfPlayers - 1).list.get(4).getChar() + " ");
			} else {
				int tieCount = 1;
				int b = 0;
				while (b < players.size() - 1) {
					if (bestHands.get(numOfPlayers - 1).compareTo(players.get(b).getBestHand()) == 0) {
						tieCount++;
						b++;
					}
					b++;
				}
				pot = pot / tieCount;
				System.out.println("Winning hands (tie)");
				System.out.println("++++++++++++++++++++++++++++++++++++");
				int printCount = 0;
				int counter = numOfPlayers - 1;
				while (printCount != tieCount) {
					for (int v = 0; v < 5; v++) {
						System.out.print(bestHands.get(counter).list.get(v).getRank().valForPrint());
						System.out.print(bestHands.get(counter).list.get(v).getChar() + " ");
					}
					for (int z = 0; z < players.size(); z++) {
						if (bestHands.get(counter).equals(players.get(z).getBestHand())) {
							players.get(z).currBalance += pot;
							System.out.print(" - " + nameOfWinningHand + " - Player " + (z + 1) + " $"
									+ players.get(z).getBalance());
						}
					}
					printCount++;
					counter--;
					System.out.print("\n");
				}
			}
			System.out.println("\n \n Play another round? y or n: ");
			Scanner answer = new Scanner(System.in);
			char yOrN = answer.next().charAt(0);
			if (yOrN == 'n') {
				continuePlaying = 1;
			}
			gameCount++;
		}

	}
}
