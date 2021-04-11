package sample;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Song {
	
	public static final String TetrisTheme = "songs/Tetris Official Theme song.wav";

	protected static String path = "";
	
	protected static File sound = new File(path); 
	
	public Clip c;
	
	public int lastFrame = 0;
	
	public Song(String str) {
		path = str;
		sound = new File(path);
		init();
	}
	
	public void init() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
			c = AudioSystem.getClip();
			c.open(ais);}
		    
		catch (Exception e) {
			System.out.println("error in Song.init(): " + e.toString());
		}
	}
	
	public void updatePos() {lastFrame = c.getFramePosition();}
	
	public void setPos() {c.setFramePosition(lastFrame);}
}
