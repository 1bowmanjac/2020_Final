package sample;



import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public abstract class Game extends JPanel implements SoundHandling{
	
	private static final long serialVersionUID = -8478510610929097850L;

	public static final int frameX = 1200, frameY = 800;
	
	protected int score = 0, high_score;
	
	protected int mouseX, mouseY;
	
	protected int show;
	
	protected boolean click;
	
	public boolean exitStatus;
	
	protected String name;

	
	public Song song =  new Song("songs/Tetris Official Theme song.wav");
	
	public int getScore() {
		return score;}

	
	public Font regFont(int style, int num) {
		
		return new Font("Times Roman", style, num);}
	
	public void playSound() {
		try {song.c.start();}
		
		catch(Exception error) {
			System.out.println("error in Game.playSound:" + error.toString());}
	}
	
	public void pauseSound() {
		song.updatePos();
		song.c.stop();}
	
	public void resumeSound() {
		song.setPos();
		
		try {song.c.start();}
		
		catch(Exception error) {
			System.out.println("error in Game.resumeSound: " + error.toString());}
	}
	
	public void stopSound() {song.c.stop();}
	
	public abstract void run();
	
	public String getName() {return name;}
	
}
