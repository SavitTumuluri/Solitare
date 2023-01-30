import java.util.ArrayList;
import java.util.Random;

/**
 * A class used to represent the state of a solitaire board.
 * @author 
 *
 */
public class SolitaireBoard {

	private ArrayList<Card> allCards;
	private ArrayList<Card> deck;
	private ArrayList<Card> discard;	
	private ArrayList<ArrayList<Card>> topPiles;
	private ArrayList<ArrayList<Card>> bottomPiles;
	
	public final int NUM_BOTTOM_PILES = 7;
	public final int NUM_TOP_PILES = 4;
	
	/**
	 * The constructor initializes a new deck of cards, shuffles them, and deals the board. It
	 * also initializes all the ArrayLists used to represent the piles of cards.
	 */
	public SolitaireBoard()
	{
		//TODO: implement constructor
		this.allCards = new ArrayList<Card>();
		this.deck = new ArrayList<Card>();
		this.discard = new ArrayList<Card>();
		this.topPiles = new ArrayList<ArrayList<Card>>();
		this.bottomPiles = new ArrayList<ArrayList<Card>>();
		this.getNewDeck();
		this.shuffle();
		this.dealBoard();
	}
	
	/**
	 * Get all the cards on the board, regardless of pile.
	 * @return
	 */
	public ArrayList<Card> getAllCards() {
		return allCards;
	}

	/**
	 * Return the deck pile.
	 * @return
	 */
	public ArrayList<Card> getDeck() {
		return deck;
	}

	/**
	 * Return the discard pile.
	 * @return
	 */
	public ArrayList<Card> getDiscard() {
		return discard;
	}

	/**
	 * Return the top four piles.
	 * @return
	 */
	public ArrayList<ArrayList<Card>> getTopPiles() {
		return topPiles;
	}

	/**
	 * Return the bottom seven piles.
	 * @return
	 */
	public ArrayList<ArrayList<Card>> getBottomPiles() {
		return bottomPiles;
	}

	/**
	 * Construct a new deck of cards. The cards in the deck should have a suit of "H", "S", "D"
	 * or "C", for hearts, spades, diamonds and clubs, respectively, and a rank of 1 - 13. A rank
	 * of 1 will correspond to an Ace, 11 to a Jack, 12 to a Queen, and 13 to a King.
	 * @return an ArrayList of Card with all 52 cards. No particular order is necessary.
	 */
	public ArrayList<Card> getNewDeck(){
		Card.NUM_CARDS = 0; //keep this line of code (used for resetting the game)
		//TODO: implement method
		String suit = "";
		Card c = null;
		
		for (int i = 0; i < 4; i++)
		{
			if (i == 0)
			{
				suit = "H";
				
			}
			else if (i == 1)
			{
				suit = "S";
				
			}
			else if (i == 2)
			{
				suit = "D";
				
			}
			else if (i == 3)
			{
				suit = "C";
			}
			for (int j = 0; j < 13; j++)
			{
				c = new Card(suit, j+1);
				this.deck.add(c);
				
			}
		}
		
		return this.deck;
	}
	
	/**
	 * This method should shuffle the deck of cards randomly. You can do this however you like,
	 * but a suggested algorithm is to pick a random card in the deck and move it to the end of
	 * the deck. Then repeat this a number of times until the deck is adequately shuffled.
	 */
	public void shuffle() {
		//TODO: implement method
		Random random = new Random();
		int numCards = 52;
		allCards.addAll(this.deck);

		for (int i = 0; i < 51; i++)
		{
			int randomCard = random.nextInt(numCards);
			Card e = this.deck.get(randomCard);
			this.deck.remove(randomCard);
			this.deck.add(0, e);
			
		}
		for(int i = 0; i < this.deck.size(); i++) {   
		    System.out.print(" "+this.deck.get(i));
		    
		}  
	}
	
	/**
	 * Deal the initial board. A solitaire board has 7 piles from left to right. The leftmost 
	 * pile has 1 card, the next pile to the right has 2 cards, the next 3 cards, and so on.
	 * The 7th pile has 7 cards. The top card in each pile is dealt face up. For our ArrayList
	 * representing a pile, the bottom card in the pile will be the first card in the ArrayList
	 * and the top card will be the last card in the ArrayList. This method should use the
	 * dealTopCard() method to get the top card from the deck and add it to the appropriate pile.
	 */
	public void dealBoard() {
		//TODO: implement method
		for (int i = 1; i < NUM_BOTTOM_PILES +1; i++)
		{
			ArrayList<Card> aBottomPile = new ArrayList<Card>();
			for (int j = 0; j < i; j++)
			{

				Card c = dealTopCard();
//				System.out.println();
//				System.out.println("card="+c+"i=="+i+"j=="+j);
				if (j==i - 1){				
					c.setFaceUp(true);
				}
				aBottomPile.add(c);
			}
			this.bottomPiles.add(aBottomPile);
		}
		for (int i = 1; i < NUM_TOP_PILES+1; i++)
		{
			ArrayList<Card> aTopPile = new ArrayList<Card>();
			this.topPiles.add(aTopPile);
		}
//		System.out.println();
//		for(int r = 0; r < this.bottomPiles.size(); r++) { 
//			//System.out.print(" "+this.bottomPiles.get(r));
//			ArrayList<Card> bpa= this.bottomPiles.get(r);
//			System.out.print("[");
//			for(int t = 0; t < bpa.size(); t++){
//				System.out.print(" "+bpa.get(t)+":"+bpa.get(t).isFaceUp());
//			}
//			System.out.print("]");
//		    
//		} 
//		System.out.println();
//		for(int i = 0; i < this.deck.size(); i++) {   
//		    System.out.print(" "+this.deck.get(i));
//		} 
//		
//		
		
	}
	
	
	/**
	 * Remove and return the top (last) card from the deck. If the deck is empty, return null.
	 * @return the top Card, or null if the deck is empty.
	 */
	public Card dealTopCard() {
		//TODO: implement method
		if (this.deck.isEmpty())
		{
			return null;
		}
		else
		{
			Card l = this.deck.get(this.deck.size()-1);
			this.deck.remove(this.deck.size()-1);
			return l;
			
		}
	}
	
	public Card dealTopCardInDiscard() {
		if (this.discard.isEmpty())
		{
			return null;
		}
		else
		{
			Card l = this.discard.get(this.discard.size()-1);
			this.discard.remove(this.discard.size()-1);
			return l;
			
		}
	}
	
	/**
	 * Flip 'numToFlip' cards from deck to discard. Use the dealTopCard() method. The cards 
	 * that flip should be added to the discard pile and turned face up. The cards should flip
	 * one at a time so that the order in the piles is reversed. For example, if the deck is
	 * [1C, 2C, 3C] and the discard is empty, then flip(2) will result in the deck being [1C] and
	 * the discard being [3C, 2C].
	 * @param numToFlip
	 */
	public void flip(int numToFlip) {
		//TODO: implement method
		System.out.println("numToFlip:" + numToFlip);
		for (int i = 0; i < numToFlip; i++)
		{
			Card q = dealTopCard();
			if(q != null){
				q.setFaceUp(true);
				this.discard.add(q);
			}
			
		}
		
	}
	
	/**
	 * Called when the deck runs out (deck is empty), move all the cards from the discard pile
	 * back into the deck. When this method finishes, the discard pile should be empty. The cards
	 * that were face up in the discard pile should now be face down in the deck. The top card
	 * of the discard pile should be the bottom card of the deck, and the bottom card of the
	 * discard should become the top card of the deck.
	 */
	public void moveDiscardBackToDeck()
	{
		//TODO: implement method
		
//		for(int o = 0; o < this.deck.size(); o++) {   
//		    System.out.print(" "+this.deck.get(o)+":"+this.deck.get(o).isFaceUp());
//		} 
//		System.out.println();
//		//this.flip(24);
//		for(int u = 0; u < this.discard.size(); u++) {   
//		    System.out.print(" "+this.discard.get(u)+":"+this.discard.get(u).isFaceUp());
//		} 
//		System.out.println();

		
		while (this.discard.size() > 0)
		{
			Card w = dealTopCardInDiscard();
			w.setFaceUp(false);
			this.deck.add(w);
		}
//		for(int o = 0; o < this.deck.size(); o++) {   
//		    System.out.print(" "+this.deck.get(o)+":"+this.deck.get(o).isFaceUp());
//		} 
//		System.out.println();
//
//		for(int u = 0; u < this.discard.size(); u++) {   
//		    System.out.print(" "+this.discard.get(u)+":"+this.discard.get(u).isFaceUp());
//		} 

	}
	
	/**
	 * This method takes a Card and two ArrayList of Cards representing the source and destination
	 * piles. The method attempts to move the card from the source pile to the destination pile.
	 * If a move is valid, it is made and the method returns true. If a move is not valid, the
	 * method returns false. 
	 * 
	 * If any of the arguments are null, the method should return false.
	 * 
	 * There are a few valid moves in solitaire and I will list the valid moves here:
	 * 
	 * 1. Move the top card of the discard to a top pile. 
	 * If the top destination pile is empty, then the only valid move is moving an Ace. If the
	 * top destination pile is not empty, then you must place a card that is matching in 
	 * suit (hearts, spades, diamonds or clubs) and is one rank higher. For example, a 5 of 
	 * hearts could be placed onto a 4 of hearts, or an Ace of diamonds could be placed onto an
	 * empty pile.
	 * 
	 * 2. Move the top card of the discard to a bottom pile. 
	 * If the bottom destination pile is empty, then the only valid move is moving a King. If the
	 * bottom destination pile is not empty, then you muse place a card that is opposite in color
	 * (black on red or red on black) and is one rank lower. For example, a 5 of hearts could be
	 * placed onto a 6 of clubs or a 6 of spades. A King of Spades could be placed onto an empty
	 * pile.
	 * 
	 * 3. Move the top card of a bottom pile to a top pile.
	 * The same rules apply as case 1. The difference here is that if the new top card of the 
	 * bottom pile is face down, it should flip to face up.

	 * 4. Move some number of face up cards from one bottom pile to another bottom pile.
	 * The same rules apply as case 2. The difference here is that the card you are moving does
	 * not have to be the top card of a bottom pile. But if the card you are moving is not the
	 * top card, it still must be face up and all the cards on top of it should also move to the
	 * destination pile. If the new top card of the source pile is face down, it should flip to
	 * face up. 
	 * 
	 * 5. You do not need to consider the case of moving from the deck to the discard, as this 
	 * move is handled in the flip() method in this class. 
	 * 
	 * If you want to call helper methods to deal with the different cases, then you may do so.
	 * I suggest writing a method in the Card class that sees if one Card can move onto another
	 * Card and calling that method from this method, but you may do this however you want.
	 * 
	 * @param c - card to move
	 * @param sourcePile
	 * @param destinationPile
	 * @return true if the card successfully moved from the source pile to the destination pile
	 */
	public boolean movePiles(Card c, ArrayList<Card> sourcePile, ArrayList<Card> destinationPile) {
		// TODO: implement method

		/*
		 * works but it works wrong
		 */

		// if (destinationPile.isEmpty())
		// {
		// if (c.getRank() == 1 || c.getRank() == 13)
		// {
		// sourcePile.remove(c);
		// destinationPile.add(c);
		// sourcePile.get(sourcePile.size()-1).setFaceUp(true);
		// return true;
		// }
		// else
		// {
		// return false;
		// }
		// }
		// else
		// {
		// if
		// (c.getSuit().equals((destinationPile.get(destinationPile.size()-1).getSuit()))
		// && c.getRank() ==
		// destinationPile.get(destinationPile.size()-1).getRank() + 1)
		// {
		// sourcePile.remove(c);
		// destinationPile.add(c);
		// sourcePile.get(sourcePile.size()-1).setFaceUp(true);
		// return true;
		// }
		// else if (c.getColor() !=
		// destinationPile.get(destinationPile.size()-1).getColor() &&
		// c.getRank() ==
		// destinationPile.get(destinationPile.size()-1).getRank() -1)
		// {
		// sourcePile.remove(c);
		// destinationPile.add(c);
		// sourcePile.get(sourcePile.size()-1).setFaceUp(true);
		// return true;
		// }
		// else
		// {
		// return false;
		// }
		// }
		/*
		 * Code that does not work
		 */
		// if (this.topPiles.isEmpty())
		// {
		// if (c.getRank() == 1)
		// {
		// sourcePile.remove(c);
		// destinationPile.add(c);
		// sourcePile.get(sourcePile.size()-1).setFaceUp(true);
		// return true;
		// }
		// else
		// {
		// return false;
		// }
		// }
		// else if (this.topPiles.size() > 0)
		// {
		// if
		// (c.getSuit().equals((destinationPile.get(destinationPile.size()-1).getSuit()))
		// && c.getRank() ==
		// destinationPile.get(destinationPile.size()-1).getRank() + 1)
		// {
		// sourcePile.remove(c);
		// destinationPile.add(c);
		// sourcePile.get(sourcePile.size()-1).setFaceUp(true);
		// return true;
		// }
		// else
		// {
		// return false;
		// }
		// }
		// else if (this.bottomPiles.isEmpty())
		// {
		// if (c.getRank() == 13)
		// {
		// sourcePile.remove(c);
		// destinationPile.add(c);
		// sourcePile.get(sourcePile.size()-1).setFaceUp(true);
		// return true;
		// }
		// else
		// {
		// return false;
		// }
		// }
		// else if (this.bottomPiles.size() > 0)
		// {
		// if (c.getColor() !=
		// destinationPile.get(destinationPile.size()-1).getColor() &&
		// c.getRank() ==
		// destinationPile.get(destinationPile.size()-1).getRank() -1)
		// {
		// sourcePile.remove(c);
		// destinationPile.add(c);
		// sourcePile.get(sourcePile.size()-1).setFaceUp(true);
		// return true;
		// }
		// else
		// {
		// return false;
		// }
		// }
		// else
		// {
		// return false;
		// }
		/*
		 * attempt #2
		 */
		if (this.topPiles.contains(destinationPile)) {
			System.out.println("this.topPiles.contains(destinationPile)");
			int ind = sourcePile.indexOf(c);
			System.out.println("463 index of c =" + ind);
			if (destinationPile.isEmpty()) {
				if (c.getRank() == 13) {
//					sourcePile.remove(c);
//					destinationPile.add(c);
					if (this.topPiles.contains(destinationPile) && c.getRank() == 13) {
						int sizeOfSource2 = sourcePile.size();
						for (int r = ind; r < sizeOfSource2; r++){
							Card movingCard = sourcePile.get(ind);
							sourcePile.remove(movingCard);
							destinationPile.add(movingCard);
						}
					}

					
					
//						destinationPile.remove(c);
//						sourcePile.add(ind, c);
					
					if (!sourcePile.isEmpty()) {
						sourcePile.get(sourcePile.size() - 1).setFaceUp(true);
					}
					return true;
				}
				else if (c.getRank() == 1)
				{
					sourcePile.remove(c);
					destinationPile.add(c);
					
				}
				if (!sourcePile.isEmpty()) {
					sourcePile.get(sourcePile.size() - 1).setFaceUp(true);
				}
				return true;
			} else if (destinationPile.size() > 0) {
				if (c.getSuit().equals((destinationPile.get(destinationPile.size() - 1).getSuit()))
						&& c.getRank() == destinationPile.get(destinationPile.size() - 1).getRank() + 1) {

					sourcePile.remove(c);
					destinationPile.add(c);
					if (!sourcePile.isEmpty()) {
						sourcePile.get(sourcePile.size() - 1).setFaceUp(true);
					}
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}

		}

		else if (this.bottomPiles.contains(destinationPile)) {
			int indexOfC2 = sourcePile.indexOf(c);

			System.out.println("this.bottomPiles.contains(destinationPile) || destinationPile.isEmpty()");

			if (destinationPile.isEmpty()) {
				System.out.print(c.getRank());
				if (c.getRank() == 13) {
					System.out.print(c.getRank());

					// for (int j = 0; j < sourcePile.size(); j++)
					// {
					// if (sourcePile.get(j).isFaceUp())
					// {
					// Card sourceCard = sourcePile.get(j);
					// sourcePile.remove(sourceCard);
					// destinationPile.add(sourceCard);
					// sourcePile.get(sourcePile.size()-1).setFaceUp(true);
					// return true;
					// }
					// else
					// {
					// return false;
					// }
					// }
					for (int e = indexOfC2; e < sourcePile.size(); e++) {
						destinationPile.add(sourcePile.get(e));
						sourcePile.remove(sourcePile.get(e));

					}
					// if (sourcePile.size() > 0){
					// sourcePile.get(sourcePile.size()-1).setFaceUp(true);
					// }
					return true;
				}
			} else if (destinationPile.size() > 0) {
				int indexOfC = sourcePile.indexOf(c);
				System.out.println("index of c at bottom:" + indexOfC);
				if (c.getColor() != destinationPile.get(destinationPile.size() - 1).getColor()
						&& c.getRank() == destinationPile.get(destinationPile.size() - 1).getRank() - 1) {
					System.out.println("line 546");
					// for (int j = 0; j < sourcePile.size(); j++)
					// {
					// if (sourcePile.get(j).isFaceUp())
					// {
					// Card sourceCard = sourcePile.get(j);
					// sourcePile.remove(sourceCard);
					// destinationPile.add(sourceCard);
					// sourcePile.get(sourcePile.size()-1).setFaceUp(true);
					// return true;
					// }
					// else
					// {
					// return false;
					// }
					// }
					int sourcePileSize = sourcePile.size();
					for (int e = indexOfC; e < sourcePileSize; e++) {
						System.out.println("sourcepile get index of c" + sourcePile.get(indexOfC));
						Card movingCard = sourcePile.get(indexOfC);
						sourcePile.remove(movingCard);
						destinationPile.add(movingCard);

					}


					if (sourcePile.size() > 0) {
						sourcePile.get(sourcePile.size() - 1).setFaceUp(true);
					}
					return true;
				}
			}

		}
		System.out.println("END");
		return false;

	}

	//
	//
	
	
	/**
	 * Check if the board is a winning position. A winning position is when all the cards
	 * are in the top 4 piles. 
	 * OPTIONAL: A game can be considered winning if all the cards are in the top or bottom piles
	 * (none in the discard or deck) and they are all revealed (face up).
	 * @return
	 */
	public boolean checkWinner()
	{
		//TODO: implement method
		/*
		 * default check
		 */
//		for (int i = 0; i < this.topPiles.size(); i++)
//		{
//			int arraySize = this.topPiles.get(i).size();
//			if (arraySize < 13)
//			{
//				return false;
//			}
//			
//		}
//		return true;
		/*
		 * optional
		 */
		if (this.discard.isEmpty() && this.deck.isEmpty())
		{
			for (int i = 0; i < this.bottomPiles.size(); i++)
			{
				for (int j = 0; j < this.bottomPiles.get(i).size(); j++)
				{

					if (this.bottomPiles.get(i).get(j).isFaceUp() == false)
					{
						return false;
					}
					
				}
			}
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	public static void main(String[] args) {
		SolitaireBoard a = new SolitaireBoard();
	//	a.flip(10);
	//	a.moveDiscardBackToDeck();
		/*
		 * rule #1 test
		 */
//		ArrayList<Card> source = new ArrayList<Card>();
//		Card cardA = new Card("D" , 1);
//		
//		source.add(cardA);
//		ArrayList<Card> destination = new ArrayList<Card>();
//		System.out.println();
//		for(int i = 0; i < source.size(); i++) {   
//		    System.out.print(" "+source.get(i));
//		} 
//		System.out.println();
//		for(int i = 0; i < destination.size(); i++) {   
//		    System.out.print(" "+destination.get(i));
//		} 
//		System.out.println();
//		System.out.println(a.movePiles(cardA, source, destination));
//		for(int i = 0; i < source.size(); i++) {   
//		    System.out.print(" "+source.get(i));
//		} 
//		System.out.println();
//		for(int i = 0; i < destination.size(); i++) {   
//		    System.out.print(" "+destination.get(i));
//		} 
		
		/*
		 * rule 1 test 2
		 */
//		ArrayList<Card> source = new ArrayList<Card>();
//		Card cardA = new Card("D" , 2);
//		
//		source.add(cardA);
//		Card cardB = new Card("D", 1);
//		ArrayList<Card> destination = new ArrayList<Card>();
//		destination.add(cardB);
//		System.out.println();
//		for(int i = 0; i < source.size(); i++) {   
//		    System.out.print(" "+source.get(i));
//		} 
//		System.out.println();
//		for(int i = 0; i < destination.size(); i++) {   
//		    System.out.print(" "+destination.get(i));
//		} 
//		System.out.println();
//		System.out.println(a.movePiles(cardA, source, destination));
//		for(int i = 0; i < source.size(); i++) {   
//		    System.out.print(" "+source.get(i));
//		} 
//		System.out.println();
//		for(int i = 0; i < destination.size(); i++) {   
//		    System.out.print(" "+destination.get(i));
//		} 
//
//	
		/*
		 * rule 1 false test
		 */
//		ArrayList<Card> source = new ArrayList<Card>();
//		Card cardA = new Card("D" , 3);
//		
//		source.add(cardA);
//		Card cardB = new Card("D", 1);
//		ArrayList<Card> destination = new ArrayList<Card>();
//		destination.add(cardB);
//		System.out.println();
//		for(int i = 0; i < source.size(); i++) {   
//		    System.out.print(" "+source.get(i));
//		} 
//		System.out.println();
//		for(int i = 0; i < destination.size(); i++) {   
//		    System.out.print(" "+destination.get(i));
//		} 
//		System.out.println();
//		System.out.println(a.movePiles(cardA, source, destination));
//		for(int i = 0; i < source.size(); i++) {   
//		    System.out.print(" "+source.get(i));
//		} 
//		System.out.println();
//		for(int i = 0; i < destination.size(); i++) {   
//		    System.out.print(" "+destination.get(i));
//		} 
	}
}
