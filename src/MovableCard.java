import processing.core.PImage;

/**
 * Wraps a Card object in a movable frame.
 * @author SharickE
 *
 */
public class MovableCard {

	private PImage img;
	private SolitaireMain papplet;
	private int x, y;
	private int width, height;
	private int collideWidth, collideHeight;
	private Card c;
	private boolean clickable;
	
	public MovableCard(SolitaireMain papplet, Card c, int width, int height) {
		this.papplet = papplet;
		this.c = c;
		this.img = papplet.loadImage("cards/" + c.toString() + ".png");
		this.width = width;
		this.height = height;
		collideWidth = width;
		collideHeight = height;
		img.resize(width, height);
	}
		
	public Card getCard() {
		return c;
	}
	
	public void setCard(Card c) {
		this.c = c;
	}
	
	public boolean isClickable() {
		return clickable;
	}
	
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}

	public void setCollideHeight(int collideHeight)
	{
		this.collideHeight = collideHeight;
	}
	
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}
	
	public void drawObject() {
		if(c.isFaceUp())
			papplet.image(img, x, y);
		else
			papplet.image(papplet.CARD_BACK, x, y);
		papplet.rect(x, y, width, height);
	}

	public boolean clicked(int mouseX, int mouseY) {
		return clickable && mouseX > x && mouseX < x + collideWidth && mouseY > y && mouseY < y + collideHeight;
	}

}
