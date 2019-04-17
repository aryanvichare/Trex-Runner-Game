import processing.core.PApplet;
import processing.core.PImage;

public class Ground extends GameObject  {
	
	// Constructor
	public Ground(PApplet screen, String fileName, int x, int y) {
		super(screen, fileName, x, y);	
	}
	
	public boolean isOffScreenToLeft() { return x < -img.width; }

}