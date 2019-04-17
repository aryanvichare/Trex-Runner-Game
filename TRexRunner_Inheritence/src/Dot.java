import processing.core.PApplet;
import processing.core.PImage;

public class Dot extends GameObject {
	
	// Constructor
	public Dot(PApplet screen, int x, int y, int width, int height) {
		this.screen = screen; 
		this.x = x;
		this.y = y; 	
		this.width = width;
		this.height = height;
	}
	
	public void draw() {
		screen.fill(200,200,200);
		screen.ellipse(x, y, width, height);
	}
}