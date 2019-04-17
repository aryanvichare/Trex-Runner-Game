import processing.core.PApplet;
import processing.core.PImage;

public class TRex extends GameObject {
	
	private int frameToDisplay, counter; 
	private float ySpeed; 
	private float gravity; 
	private PImage running1, running2, crouch1, crouch2; 
	private PImage dead; 
	private boolean isAlive; 
	
	private static String file_running1 = "/Users/aryan/Documents/JrJava/JrJavaMacNeon/eclipseWS/TRexRunnerTemplate/assets/t_rex_running1.png";
	private static String file_running2 = "/Users/aryan/Documents/JrJava/JrJavaMacNeon/eclipseWS/TRexRunnerTemplate/assets/t_rex_running2.png";	
	private static String file_deadTrex = "/Users/aryan/Documents/JrJava/JrJavaMacNeon/eclipseWS/TRexRunnerTemplate/assets/t_rex_end_game.png";
	
	// Constructor
	public TRex(PApplet screen, String fileName, int x, int y) {
		super(screen, fileName, x, y);	
		this.screen = screen;

		running1 = screen.loadImage(file_running1);
		running2 = screen.loadImage(file_running2);
		dead = screen.loadImage(file_deadTrex);
		
		frameToDisplay = 1;
		gravity = 0.4f;
		isAlive = true; 
	}
	
	public void act() {
		if(isAlive) {
			counter++;
			if(frameToDisplay == 1) screen.image(running2, x, y); 
			if(frameToDisplay == 2) screen.image(running1, x, y);
			
			if(counter == 10) {
				if(frameToDisplay == 1) frameToDisplay = 2;
				else if(frameToDisplay == 2) frameToDisplay = 1;
				counter = 0; 
			}
		} else {
			screen.image(dead, x, y);
		}
		applyGravity();
	}
	
	
	public void startJump() { 
		if(isOnGround()) {
			ySpeed = 11;
			
		}
	}
	
	public void applyGravity() {
	
		ySpeed -= gravity;
		y -= ySpeed; 
		
		if(y >= 108) { y = 108; }
		
	}
	
	public void crouch() { }
		
	public void stop() {
		screen.image(dead, x, y);
		frameToDisplay = -1; 
	}
	
	public void resume() { frameToDisplay = 1; }
	public boolean isAlive() { return isAlive; }
	public void setDead(boolean isDead) { this.isAlive = false; }
	public boolean isOnGround() { return y == 108; }
		
}