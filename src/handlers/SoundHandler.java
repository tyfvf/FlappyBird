package src.handlers;

import java.io.File; 
import java.io.IOException; 
  
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 
  
public class SoundHandler  
{ 
   
    static Clip clip;

    static AudioInputStream audioInputStream;
    static String filePath = "/smw_coin.wav";

    // constructor to initialize streams and clip
    public static void playSound(String path) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        // create AudioInputStream object
        audioInputStream = AudioSystem.getAudioInputStream(new File("./" + path).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();
          
        // open audioInputStream to the clip 
        clip.open(audioInputStream); 
          
        

        clip.start(); 
    }
    
}
