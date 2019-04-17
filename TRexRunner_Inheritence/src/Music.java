import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.TargetDataLine;

public class Music {
	
	public static void playMusic(String filePath) {
		InputStream music; 
		try {
			
			music = new FileInputStream(new File(filePath));
			
			
		} catch (Exception e) { e.printStackTrace(); };
		
	}

}
