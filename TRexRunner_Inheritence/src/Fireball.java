import processing.core.PApplet;

public class Fireball extends GameObject {
	
	public Fireball(PApplet screen, String fileName, int x, int y) {
		this.screen = screen;
		img = screen.loadImage(fileName);
		img.resize(50, 45);
		this.x = x;
		this.y = y; 	
		height = img.height;
		width = img.width;	
	}
	
	public void act() {
		draw();
		x += 5;
	}
	

}
