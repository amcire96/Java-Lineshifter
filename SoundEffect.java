import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffect {
	//private class variable is a Clip which is initialized in the getter methods
	private static Clip clip;
	
	/*initializes the clip variable with the Triumph.wav file in the class
	 *catches 3 exceptions and then returns the clip
	 */
	public static Clip getNextLevelClip(){
	      try {
	    	 File file = new File("DING.WAV");
	    	 // Gets the audio input stream from the file
	         AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
	         // Get a sound clip resource
	         clip = AudioSystem.getClip();
	         // Open audio clip and load samples from the audio input stream
	         clip.open(audioIn);
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	      return clip;
	 }
	 
	/*initializes the clip variable with the Explosion.wav file in the class
	 *catches 3 exceptions and then returns the clip
	 */
	 public static Clip getExplosionClip(){
	      try {
	    	 File file = new File("Explosion.wav");
	    	 // Gets the audio input stream from the file
	         AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
	         // Gets a sound clip resource
	         clip = AudioSystem.getClip();
	         // Opens audio clip and load samples from the audio input stream
	         clip.open(audioIn);
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	      return clip;
	 }
	 
	 public static Clip music(){
	      try {
	    	 File file = new File("DigitalStream.wav");
	    	 // Gets the audio input stream from the file
	         AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
	         // Gets a sound clip resource
	         clip = AudioSystem.getClip();
	         // Opens audio clip and load samples from the audio input stream
	         clip.open(audioIn);
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	      return clip;
	 }
	 
	 /*receives a Clip to play
	  *stops the clip if it is running, rewinds it to the beginning and plays it
	  */
	 public static void play(Clip clip){
		if (clip.isRunning()){
			clip.stop();   // Stop the player if it is still running			 
		}	 
		clip.setFramePosition(0); // rewind to the beginning          
		clip.start();     // Start playing
	}
	 
	 public static Clip getClip(){
		 return clip;
	 }
}


