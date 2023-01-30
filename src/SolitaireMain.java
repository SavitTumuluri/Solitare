import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class SolitaireMain extends PApplet{

	public static void main(String[] args) {
		PApplet.main("SolitaireMain");
	}
	
	public void settings() {
		//fullScreen();
		size(1400, 1000);
	}
	
	SolitaireBoard sb;
	PImage CARD_BACK;
	ArrayList<MovableCard> cards;
	MovableCard selected = null;
	long doubleClickTimer = System.nanoTime();
	double DOUBLE_CLICK_THRESHOLD = 0.25;
	int CARDS_TO_FLIP = 3;
	int cardWidth = 125, cardHeight = 190;
	int deckX = 50, deckY = 50;
	int discardX = deckX + (int)(cardWidth * 1.5);
	int discardY = deckY;
	int topPileX = discardX + cardWidth * 3;
	int topPileY = deckY;
	int bottomPileX = deckX;
	int bottomPileY = deckY + (int)(cardHeight * 1.5);
	int startMouseX, startMouseY;
	

	public void setup() {
		noFill();
		strokeWeight(0.3f);
		sb = new SolitaireBoard();
		cards = new ArrayList<MovableCard>();
		for(Card c : sb.getAllCards())
			cards.add(new MovableCard(this, c, cardWidth, cardHeight));
		CARD_BACK = loadImage("cards/green_back.png");
		CARD_BACK.resize(cardWidth, cardHeight);
		setCardPositions();
		drawGameState();		
	}
	
	/**
	 * Restart the game (basically setup() but without having to load all the images again)
	 */
	public void restart()
	{
		sb = new SolitaireBoard();
		//reset movable card array
		for(int i = 0; i < sb.getAllCards().size(); i++)
			cards.get(i).setCard(sb.getAllCards().get(i));		
		selected = null;
		setCardPositions();
		drawGameState();	
	}
	
	/**
	 * Animate the movement of the selected card
	 */
	public void draw() {
		if(selected != null)
		{
			ArrayList<MovableCard> otherCardsToMove = new ArrayList<MovableCard>();
			//get pile of card
			ArrayList<Card> pile = findPile(selected.getCard());
			if(sb.getBottomPiles().contains(pile))
				//move other cards below the selected card
				for(int i = pile.size()-1; i >= 0; i--)
				{
					if(pile.get(i) == selected.getCard())
						break;
					otherCardsToMove.add(cards.get(pile.get(i).getID()));
				}
			
			
			//move cards
			selected.move(mouseX - startMouseX, mouseY - startMouseY);
			for(MovableCard mc : otherCardsToMove)
				mc.move(mouseX - startMouseX, mouseY - startMouseY);
			
			startMouseX = mouseX;
			startMouseY = mouseY;
			drawGameState();
			//layer moving cards on top of rest of board
			selected.drawObject();
			for(int i = otherCardsToMove.size()-1; i >= 0; i--)
				otherCardsToMove.get(i).drawObject();
		}
	}
	
	/**
	 * Draw the entire game state based on the current card positions.
	 */
	public void drawGameState() {
		background(125, 200, 75);
		ArrayList<Card> c = sb.getDeck();
		rect(deckX, deckY, cardWidth, cardHeight);
		for(int i = 0; i < c.size(); i++)
			cards.get(c.get(i).getID()).drawObject();
		
		c = sb.getDiscard();
		for(int i = 0; i < c.size(); i++)
			cards.get(c.get(i).getID()).drawObject();
		
		ArrayList<ArrayList<Card>> topPiles = sb.getTopPiles();
		for(int j = 0; j < topPiles.size(); j++)
		{
			ArrayList<Card> pile = topPiles.get(j);
			for(int i = 0; i < pile.size(); i++)
				cards.get(pile.get(i).getID()).drawObject();
			if(pile.isEmpty())
				rect(topPileX + (int)(j*cardWidth*1.5), topPileY, cardWidth, cardHeight);
		}
		
		ArrayList<ArrayList<Card>> bottomPiles = sb.getBottomPiles();
		for(int j = 0; j < bottomPiles.size(); j++)
		{
			ArrayList<Card> pile = bottomPiles.get(j);
			rect(bottomPileX + (int)(cardWidth * 1.5 * j), bottomPileY, cardWidth, cardHeight);
			for(int i = 0; i < pile.size(); i++)
				cards.get(pile.get(i).getID()).drawObject();
		}
		
		if (sb.checkWinner())
		{
			textSize(24);
			fill(0);
			text("Congratulation, you won!", width/2 - 150, height/2 - 170);
			noFill();
		}
	}
	
	/**
	 * Set the card positions for the board.
	 */
	public void setCardPositions() {
		for(MovableCard card : cards)
		{
			card.setClickable(false);
			card.setCollideHeight(cardHeight);
		}
		ArrayList<Card> c = sb.getDeck();
		for(int i = 0; i < c.size(); i++)
			cards.get(c.get(i).getID()).setPosition(deckX+(int)(i*0.25), deckY+(int)(i*0.25));
		if(!c.isEmpty())
			cards.get(c.get(c.size()-1).getID()).setClickable(true);
		
		c = sb.getDiscard();
		for(int i = 0; i < c.size(); i++)
			cards.get(c.get(i).getID()).setPosition(discardX, discardY);
		if(!c.isEmpty())
			cards.get(c.get(c.size()-1).getID()).setClickable(true);
		//spread last 3 cards
		if(c.size() == 2)
			cards.get(c.get(1).getID()).setPosition(discardX + cardWidth/4, discardY);
		else if(c.size() > 2)
		{
			cards.get(c.get(c.size()-1).getID()).setPosition(discardX + 2* cardWidth/4, discardY);
			cards.get(c.get(c.size()-2).getID()).setPosition(discardX + cardWidth/4, discardY);
		}
		
		ArrayList<ArrayList<Card>> topPiles = sb.getTopPiles();
		for(int j = 0; j < topPiles.size(); j++)
		{
			c = topPiles.get(j);
			for(int i = 0; i < c.size(); i++)
				cards.get(c.get(i).getID()).setPosition(topPileX + (int)(j*cardWidth*1.5), topPileY);	
			if(!c.isEmpty())
				cards.get(c.get(c.size()-1).getID()).setClickable(true);
		}
		
		ArrayList<ArrayList<Card>> bottomPiles = sb.getBottomPiles();
		for(int j = 0; j < bottomPiles.size(); j++)
		{
			c = bottomPiles.get(j);
			for(int i = 0; i < c.size(); i++)
			{
				MovableCard card = cards.get(c.get(i).getID());
				card.setPosition(bottomPileX + (int)(j*cardWidth*1.5), bottomPileY + (int)(i*cardHeight*0.20));			
				card.setClickable(card.getCard().isFaceUp());
				card.setCollideHeight((int)(cardHeight*0.20));
			}
			if(!c.isEmpty())
			{
				cards.get(c.get(c.size()-1).getID()).setClickable(true);
				cards.get(c.get(c.size()-1).getID()).setCollideHeight(cardHeight);					
			}
		
		}
		
	}
	
	/**
	 * Find and return the pile corresponding to the coordinate (x,y)
	 * @param x
	 * @param y
	 * @return
	 */
	public ArrayList<Card> findPile(int x, int y)
	{
		if(x > deckX && x < deckX + cardWidth && y > deckY && y < deckY + cardHeight)
			return sb.getDeck();
		if(x > discardX && x < discardX + cardWidth && y > discardY && y < discardY + cardHeight)
			return sb.getDiscard();
		
		for(int i = 0; i < sb.NUM_TOP_PILES; i++)
		{
			int xOffset = (int)(i*cardWidth*1.5);
			if(x > topPileX + xOffset && x < topPileX + xOffset + cardWidth && y > topPileY && y < topPileY + cardHeight)
				return sb.getTopPiles().get(i);
		}
		
		for(int i = 0; i < sb.NUM_BOTTOM_PILES; i++)
		{
			int xOffset = (int)(i*cardWidth*1.5);
			int pileHeight = (int)(sb.getBottomPiles().get(i).size()*0.25*cardHeight) + cardHeight;
			if(x > bottomPileX + xOffset && x < bottomPileX + xOffset + cardWidth && y > bottomPileY && y < bottomPileY + pileHeight)
				return sb.getBottomPiles().get(i);
		}
		
		return null;
	}
	
	/**
	 * Find and return the pile containing the card
	 * @param card
	 * @return
	 */
	public ArrayList<Card> findPile(Card card)
	{
		ArrayList<Card> pile = null;
		if(sb.getDeck().contains(card))
			pile = sb.getDeck();
		else if(sb.getDiscard().contains(card))
			pile = sb.getDiscard();
		else {
			boolean found = false;				
			for(int i = 0; i < sb.getTopPiles().size(); i++)
				if(sb.getTopPiles().get(i).contains(card))
				{
					pile = sb.getTopPiles().get(i);
					found = true;
					break;
				}
			
			if(!found)
				for(int i = 0; i < sb.getBottomPiles().size(); i++)
					if(sb.getBottomPiles().get(i).contains(card))
					{
						pile = sb.getBottomPiles().get(i);
						break;
					}
		}
		return pile;
	}
	
	@Override
	public void mousePressed() {
		selected = null;
		boolean doubleClick = false;
		for(MovableCard c : cards)
			if(c.clicked(mouseX, mouseY))
			{
				selected = c;
				startMouseX = mouseX;
				startMouseY = mouseY;
				long oldTime = doubleClickTimer;
				doubleClickTimer = System.nanoTime();
				if((doubleClickTimer - oldTime)/(Math.pow(10, 9)) < DOUBLE_CLICK_THRESHOLD)
					doubleClick = true;
			}
		if(selected == null) {
			if(findPile(mouseX, mouseY) == sb.getDeck())
			{
				sb.moveDiscardBackToDeck();
				setCardPositions();
				drawGameState();
			}
			return;
		}
				
		Card card = selected.getCard();
		ArrayList<Card> source = findPile(card);
		if(source == sb.getDeck()) //can't move cards from deck
		{
			selected = null;
			sb.flip(CARDS_TO_FLIP);
			setCardPositions();
			drawGameState();
		}
		else if(doubleClick && (sb.getBottomPiles().contains(source) || sb.getDiscard()==source))
		{
			//send card up if possible
			for(int i = 0; i < sb.NUM_TOP_PILES; i++)
				if(sb.movePiles(card, source, sb.getTopPiles().get(i)))
				{
					selected = null;
					setCardPositions();
					drawGameState();
					return;
				}
		}
			
	}
	
	@Override
	public void mouseReleased() {
		if(selected == null)
			return;
		Card card = selected.getCard();
		ArrayList<Card> source = findPile(card);
		ArrayList<Card> destination = findPile(mouseX, mouseY);
		System.out.println(source);
		System.out.println(destination);
		System.out.println(sb.movePiles(card, source, destination));
//		sb.movePiles(card, source, destination);
		setCardPositions();
		drawGameState();
		selected = null;
	}
	
	@Override
	public void keyPressed() {
		if (key == CODED)
			if(keyCode == ESC)
				exit();
		if (key == 'r') 
			restart();
	}

}




