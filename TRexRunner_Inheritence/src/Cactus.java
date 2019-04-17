import processing.core.PApplet;
import processing.core.PImage;

public class Cactus extends GameObject {
	
	// Constructor
	public Cactus(PApplet screen, String fileName, int x, int y) {
		super(screen, fileName, x, y);	
	}
	
	public void act(int speed) {
		screen.image(img, x, y);
		moveLeft(speed);
		if(isOffScreenToLeft()) resetPosition();
	}	
}