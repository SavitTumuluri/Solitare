
/**
 * A Card class to represent a playing card.
 * @author
 *
 */
public class Card {

	private String suit;


	private int rank;


	private boolean faceUp;
	private int color;
	


	public final int BLACK = 0;
	public final int RED = 1;
	
	public static int NUM_CARDS = 0;
	private int id;
	
	/**
	 * Create a new card. Set the suit and the rank based on the parameters. Set the color
	 * to RED or BLACK based on the suit. "H" or "D" (hearts or diamonds) are red, "S" or "C" 
	 * (spades or clubs) are black. All cards should be face down by default (faceUp = false).
	 * @param suit
	 * @param rank
	 */
	public Card(String suit, int rank) {
		id = NUM_CARDS; //keep this line of code (id is required for the processing class)
		NUM_CARDS += 1; //keep this line as well
		//TODO: implement constructor
		this.faceUp = false;
		this.suit = suit;
		this.rank = rank;
		if (suit.equalsIgnoreCase("H") || suit.equalsIgnoreCase("D"))
		{
			this.color = this.RED;
		}
		else if (suit.equalsIgnoreCase("S") || suit.equalsIgnoreCase("C"))
		{
			this.color = this.BLACK;
		}
	}

	/**
	 * Getter for the card id. Used to search for the card in the deck more easily.
	 * @return
	 */
	public int getID() {
		return id;
	}

	/**
	 * Return true if the card is face up, false otherwise.
	 * @return
	 */
	public boolean isFaceUp() {
		return faceUp;
	}
	
	/**
	 * //TODO: Add any other helper methods for implementing the 
	 * rules of solitaire. This can include getters and setters for
	 * the fields, helper methods to compare if cards can be stacked, etc.
	 */
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}
	public void setFaceUp(boolean faceUp) {
		this.faceUp = faceUp;
	}
	public boolean canStack(Card newCard)
	{
		if (this.color != newCard.color && this.rank == newCard.rank + 1)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	/**
	 * Two cards are equal if they have the same suit and same rank.
	 */
	public boolean equals(Object o)
	{
		//TODO: implement method
		
		if (this.suit.equalsIgnoreCase(((Card) o).suit) && this.rank ==((Card) o).rank)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Return the two-character string representing the card. The string is the rank followed
	 * by the suit. If the rank is 1, use "A" for ace, 11, use "J" for jack, 12, use "Q" for
	 * queen, 13, use "K" for king. For 2, 3, ... 10, use "2", "3", ... "10". For example, the 
	 * 10 of spades card would return the string 10S. The Ace of Hearts would return the string
	 * AH.
	 * @return 
	 */
	public String toString()
	{
		//TODO: implement method
		String rank[];
		rank = new String[13];
		rank[0] = "A";
		rank[10] = "J";
		rank[11] = "Q";
		rank[12] = "K";
		for (int i = 1; i < rank.length - 3; i++)
		{
			rank[i] = String.valueOf(i + 1);
		}
		
		return rank[this.rank - 1] + this.suit;
	}
}
