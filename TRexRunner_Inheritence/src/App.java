import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class App extends PApplet {
	ArrayList<Dot> dots;
	ArrayList<Cactus> cacti;
	ArrayList<Cloud> clouds;
	ArrayList<Fireball> fireballs; 
	TRex dino;
	Cactus c1;
	Cactus c2;
	Ground g1, g2;
	PImage gameOver; 
	
	private int retryX, retryY, retrySizeX, retrySizeY; 
	private int gameSpeed = 5; 
	private int score;
	
	private static String file_TRex = "/Users/aryan/Documents/JrJava/JrJavaMacNeon/eclipseWS/TRexRunnerTemplate/assets/t_rex_running1.png";
	private static String file_cactus = "/Users/aryan/Documents/JrJava/JrJavaMacNeon/eclipseWS/TRexRunnerTemplate/assets/cactus1.png";
	private static String file_clouds = "/Users/aryan/Documents/JrJava/JrJavaMacNeon/eclipseWS/TRexRunnerTemplate/assets/cloud.png";
	private static String file_ground = "/Users/aryan/Documents/JrJava/JrJavaMacNeon/eclipseWS/TRexRunnerTemplate/assets/ground.png";
	private static String file_gameOver = "/Users/aryan/Documents/JrJava/JrJavaMacNeon/eclipseWS/TRexRunnerTemplate/assets/gameOver.png";
	private static String file_noodle = "/Users/aryan/Documents/JrJava/JrJavaMacNeon/eclipseWS/TRexRunner_Inheritence/assets/noodle.jpg";
	private static String file_fireball = "/Users/aryan/Documents/JrJava/JrJavaMacNeon/eclipseWS/TRexRunner_Inheritence/assets/fireball.png";
	
	private static int timeMillis;
	private static int darkTimer; 
	
	// ** Decorations **
	PImage noodle; 
	

	public void setup() {
		size(1000, 400);
		score = 0;
		gameOver = loadImage(file_gameOver);
		dino = new TRex(this, file_TRex, 40, 108);
		c1 = new Cactus(this, file_cactus, 400, 200);
		c2 = new Cactus(this, file_cactus, 200, 200);
		
		// Powerups (Noodle, Mushroooms) // 
		//noodle = this.loadImage(file_noodle);
		//noodle.resize(200, 200);
		
		// Clouds // 
		clouds = new ArrayList<Cloud>();
		for(int i = 0; i < 10; i++) {
			clouds.add(new Cloud(this, file_clouds, (int)(Math.random()*1000), (int)(Math.random()*90)));
		}
		// Cacti //
		cacti = new ArrayList<Cactus>();
		for(int i = 0; i < 100; i++) {
			int randCacti = (int)(Math.random()*3) + 1;
			cacti.add(new Cactus(this, "/Users/aryan/Documents/JrJava/JrJavaMacNeon/eclipseWS/TRexRunnerTemplate/assets/shortCactus" + (randCacti) + ".png", 1100 + 2600*i, 130));
		}
		
		// Ground //
		
		g1 = new Ground(this, file_ground, 0, 192);
		g2 = new Ground(this, file_ground, -1100, 192);
		
		// Dots //
		
		dots = new ArrayList<Dot>();
		for(int i = 0; i < 100; i++) {
			Dot newDot = new Dot(this,(int)(Math.random()*1001),(int)(Math.random()*401),2,2);
			dots.add(newDot);
		}
		
		// Fireballs //
		
		fireballs = new ArrayList<Fireball>();
		
		retrySizeX = 70;
		retrySizeY = 30;
		retryX = 1000/2 - retrySizeX/2;
		retryY = 50;
			
		timeMillis = 1000; 
		darkTimer = timeMillis; 

	}
	
	public void draw() {
		// Color Changer //
		if(darkTimer < 2000) background(200);
		else background(0, 0, 0);
		line(0, 200, 1000, 200);
		
		darkTimer++; 
		timeMillis--; 
		
		// Score //
		
		fill(color(33, 34, 137));
		textSize(28);
		text("Score = " + score, 800, 43, 100);
		
		// Draw Ground //
		
		g1.draw(); 
		g2.draw();
		
		// Dino acts //
		
		dino.act();
		
		if(!dino.isAlive()) {
			for(int i = 0; i < cacti.size(); i++) cacti.get(i).act(0);
			dino.stop();
			drawClouds(false);
			drawDots(false);
		}
		else {
			if(timeMillis == 0) {
				gameSpeed += 3; 
				timeMillis = 1000; 
			}
		
			score++;
			for(int i = 0; i < cacti.size(); i++) cacti.get(i).act(gameSpeed);
			drawClouds(true);
			g1.moveLeft(gameSpeed);
			g2.moveLeft(gameSpeed);
			drawDots(true);
		}
		
		// Draw Cacti decoration // 
		
		c1.draw();
		c2.draw();
		
		// Collision detection // 
		
		for(Cactus c : cacti) {
			if(dino.isCollidingWithCactus(c)) {
				image(gameOver, 500 - (gameOver.width)/2, 20, gameOver.width, gameOver.height);
				fill(color(0, 0, 0));
				rect(retryX, retryY, retrySizeX, retrySizeY);
				fill(color(196, 229, 11));
				textSize(28);
				text("Retry", retryX, retryY + 23); 
				if(overRect(retryX, retryY, retrySizeX, retrySizeY)) {					
					dino.setDead(false);
					draw();
				}
				dino.setDead(true);
			}
		}
		
		// Fireball Collision // 
		
		for(int i = 0; i < fireballs.size(); i++) {
			Fireball fb = fireballs.get(i);
			fb.act();
			if(fb.isOffScreenToRight()) fireballs.remove(fb);
			
			for(int j = 0; j < cacti.size(); j++) {
				if(fb.isCollidingWithCactus(cacti.get(j))) {
					fireballs.remove(i);
					cacti.remove(j);
				}
			}
			
		}
		
		// Keystroke detection // 
		
		if (keyPressed) {
			if(key == CODED) {
				//dino.crouch();
			}
		}
	}
	
	public void keyReleased() {
		if(dino.isAlive()) {
			if(key == ' ') {
				dino.startJump();
			}
			if(key == 'f') {
				Fireball fb = new Fireball(this, file_fireball, dino.x + 30, dino.y + 10);
				fireballs.add(fb);
			}
		}
	}
	
	boolean overRect(int x, int y, int width, int height)  {
		  if (mouseX >= x && mouseX <= x+width && 
		      mouseY >= y && mouseY <= y+height) {
		    return true;
		  } else {
		    return false;
		  }
	}
	
	public void drawClouds(boolean show) {
		Cloud c;
		int randSpeed; 
		for(int i = 0; i < clouds.size(); i++) {
			c = clouds.get(i);
			c.draw();
			randSpeed = (int)(Math.random()*4) + 1;
			if(show) c.moveLeft(randSpeed);
		}
	}
	
	public void drawDots(boolean show) {
		for(int loc = 0; loc < dots.size(); loc++) {
			Dot dot = dots.get(loc);
			if(show) {
				int rand = (int)(Math.random()*2); 
				if(rand == 0) { dot.moveRight(2); } 
				else if(rand == 1) dot.moveRight(10); 
			}
			dot.draw();
		}
	}
}
