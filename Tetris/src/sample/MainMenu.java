//package sample;
//
////import java libraries
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;
//
////MainMenu extends the JPanel class
//public class MainMenu extends JPanel{
//
//	private static final long serialVersionUID = 9069015639773292220L;
//
//	//Initialize the JFrame
//	private static JFrame j = new JFrame();
//
//	//Array of games that will appear in the main menu
//	public static final Game[] games = {new Tetris()};
//
//	//Song
//
//
//	//Sets the width and length of the Jframe
//	public static final int frameX = 1200, frameY = 800;
//
//	//tracks the coordinates of the mouse
//	private static int mouseX, mouseY;
//
//	//false when mouse is not clicked, true when the mouse is clicked
//	private static boolean click = false;
//
//
//
//
//	//All graphic components will be drawn in this method
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//
//		//Sets the font and then prints "MENU" at the top of the screen
//		g.setFont(new Font("Times Roman", Font.BOLD, 60));
//		g.drawString("MENU", 450, 100);
//
//		//Loops through every game in the array
//		for (Game i : games) {
//
//			//Feeds the mouse data into the game button
//			i.button.setMouseCoordinates(mouseX, mouseY);
//
//			//Draws the button
//			i.button.draw(g);
//
//			//Executes if the user clicks on a game
//			if (i.button.hoversOver(mouseX, mouseY) && click) {
//
//				//If the user clicks on a game, run the game
//				j.setVisible(false);
//
//				i.run();}
//		}
//
//		click = false;
//	}
//
//	public static void setVisibility(boolean b) {j.setVisible(b);}
//
//	public static void back() {
//		j.setVisible(true);
//
//		}
//
//	public static void main(String[] args) {
//
//		//Initialize an instance of MainMenu
//		MainMenu menu = new MainMenu();
//
//
//		//loop through each game in the array
//		for (int i = 0; i<games.length; i++) {
//
//			//Sets the location and size of the game button
//			games[i].button.setBounds(350, (i*200) + 175, 375, 150);
//
//			//Sets what the button will say
//			games[i].button.setMessage(games[i].getName());}
//
//		//Sets the size, visibility of the frame
//		j.setSize(frameX, frameY);
//		j.setVisible(true);
//
//		//Makes it so that the frame exits when you try to manually close it
//		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		//Add the MainMenu instance as a component of the JFrame
//		j.add(menu);
//
//		//Adds a mouse listener component
//		j.addMouseListener(new MouseListener() {
//
//			//Methods for receiving/processing mouse input
//			public void mousePressed(MouseEvent m) {
//				click = true;}
//
//			public void mouseClicked(MouseEvent m) {}
//
//			public void mouseReleased(MouseEvent m) {
//				click = false;}
//
//			public void mouseEntered(MouseEvent m) {}
//
//			public void mouseExited(MouseEvent m) {}
//
//
//		});
//
//		//Adds a MouseMotionListener component
//		j.addMouseMotionListener(new MouseMotionListener() {
//
//			//Executes when the mouse is moved
//			public void mouseMoved(MouseEvent m) {
//
//				//Sets the mouseX and mouseY variables equal to the coordiates of the mouse
//				mouseX = (int) MouseInfo.getPointerInfo().getLocation().getX();
//				mouseY = (int) MouseInfo.getPointerInfo().getLocation().getY();}
//
//			//Executes when the mouse is clicked and moved at the same time
//			public void mouseDragged(MouseEvent m) {}
//
//		});
//
//		//Runs a Thread
//		new Thread() {
//
//			public void run() {
//
//				//infinite loop
//				while (true) {
//
//					try {
//
//						//Sleeps the Thread for 10 milliseconds
//						//causes the frame to repaint every 10 milliseconds
//						Thread.sleep(10);
//						menu.repaint();}
//
//					catch (Exception e) {}
//				}
//
//			}
//
//		}.start();
//
//	}
//
//}
//
//
