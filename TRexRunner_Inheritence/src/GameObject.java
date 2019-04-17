import processing.core.PApplet;
import processing.core.PImage;

public class GameObject {
	
	protected int x, y;
	protected PImage img; 
	protected PApplet screen; 
	protected int height, width;
	
	public GameObject(PApplet screen, String fileName, int x, int y) {
		this.screen = screen;
		img = screen.loadImage(fileName);
		this.x = x;
		this.y = y; 	
		height = img.height;
		width = img.width;
	}
	
	public GameObject() { }
	
	public GameObject(String fileName, int x, int y) { }
	
	public void draw() {
		screen.image(img, x, y);
	}
	
	public void act() {
		screen.image(img, x, y);
	}
	
	public void act(int speed) { screen.image(img, x, y); }
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getHeight() { return height; }
	public int getWidth() { return width; }
	public boolean isOffScreenToLeft() { return x < 0; }
	public boolean isOffScreenToRight() { return x > 1000; }	
	public void resetPosition() { x = 1110; }

	public void moveRight(int pixels) {
		x += pixels; 
		if(isOffScreenToRight()) { x = 0; }
	}
	
	public void moveLeft(int pixels) {
		x -= pixels; 
		if(isOffScreenToLeft()) { x = 1000; }
	}
	
	public boolean isCollidingWithCactus(Cactus c) {
		boolean xOverlap = isOverlapping(x, x + width, c.getX(), c.getX() + c.getWidth());
		boolean yOverlap = isOverlapping(y, y + height, c.getY(), c.getY() + c.getHeight());
		
		if(xOverlap && yOverlap) {
			return true;
		}
		else {
			return false; 
		}
	}
	
	public boolean isOverlapping(float s1, float e1, float s2, float e2) {
		float maxStart = Math.max(s1, s2);	
		float minEnd = Math.min(e1, e2); 
		
		if(minEnd > maxStart) return true;
		else return false; 
	}


}
