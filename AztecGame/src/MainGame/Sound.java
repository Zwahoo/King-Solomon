package MainGame;
//import sun.audio.*;
//import javax.sound.sampled.*;
//import java.io.*;
//public class Sound {

//public void music(String soundFile){
//AudioPlayer AP = AudioPlayer.player;
//AudioStream AS;
//AudioData AD;
//ContinuousAudioDataStream loop = null;

//try{
//FileInputStream stream = new FileInputStream(soundFile);
//AS = new AudioStream(stream);
//AD = AS.getData();
//loop = new ContinuousAudioDataStream(AD);		
//AP.start(loop);
//}catch(IOException error){
//System.out.println("La La La");
//}


//}


//}

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class Sound extends JFrame {
	Clip clip;
	public boolean isPlaying = false;

	// Constructor
	public Sound(String filename, boolean loop) {
		//      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//      this.setTitle("Test Sound Clip");
		//      this.setSize(300, 200);
		//      this.setVisible(true);

		try {
			// Open an audio input stream.
			//URL url = this.getClass().getClassLoader().getResource("assets/sounds/KingSolomonsOverworldTheme.wav");
			File soundFile = new File(filename);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			// Get a sound clip resource.
			clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			playSound(loop);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

	}

	public void playSound(boolean loop){
		if (loop){
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.getLevel();
		}
		else {
			clip.start();
		}
		isPlaying = true;
	}

	public void stopSound(){
		clip.stop();
		isPlaying = false;
	}
}


