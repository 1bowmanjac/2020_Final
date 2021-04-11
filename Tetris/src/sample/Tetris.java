package sample;

import ServerSide.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class Tetris extends Game { 
	
	private static final long serialVersionUID = -7977995506901453930L;

	private static JFrame j = new JFrame();
	
	private static Random rand = new Random();
	
	//All possible tetris pieces and rotations
	private static final Point pieces[][][] = {
			//O-Piece
			{
				{new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 0)}
			},
			
			//I-Piece
			{
				{new Point(0, -1), new Point(0, 0), new Point(0, 1), new Point(0, 2)},
				{new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(2, 0)}
			},
			
			//S-Piece
			{
				{new Point(-1, 0), new Point(0, 0), new Point(0, -1), new Point(1, -1)},
				{new Point(-1, -1), new Point(-1, 0), new Point(0, 0), new Point(0, 1)},
			},
			
			//Z-Piece
			{
				{new Point(-1, 0), new Point(0, 0), new Point(0, 1), new Point(1, 1)},
				{new Point(1, -1), new Point(1, 0), new Point(0, 0), new Point(0, 1)}
			},
			
			//L-Piece
			{
				{new Point(0, -1), new Point(0, 0), new Point(0, 1), new Point(1, 1)},
				{new Point(-1, 1), new Point(-1, 0), new Point(0, 0), new Point(1, 0)},
				{new Point(-1, -1), new Point(0, -1), new Point(0, 0), new Point(0, 1)},
				{new Point(1, -1), new Point(1, 0), new Point(0, 0), new Point(-1, 0)}
			},
			
			//J-Piece
			{
				{new Point(0, -1), new Point(0, 0), new Point(0, 1), new Point(-1 ,1)},
				{new Point(-1, -1), new Point(-1, 0), new Point(0, 0), new Point(1, 0)},
				{new Point(1, -1), new Point(0, -1), new Point(0, 0), new Point(0, 1)},
				{new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(1, 1)}
			},
			
			//T-Piece
			{
				{new Point(-1, 0), new Point(0, 0), new Point(0, -1), new Point(1, 0)},
				{new Point(0, -1), new Point(0, 0), new Point(0, 1), new Point(1, 0)},
				{new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(0, 1)},
				{new Point(-1, 0), new Point(0, 0), new Point(0, 1), new Point(0, -1)}
			}	
	};
	
	private static final Color[] colours = {Color.RED, Color.ORANGE, Color.YELLOW, Color.BLUE, Color.GREEN};
	
	//Piece that the user is controlling
	private Point[][] controlPiece;
	
	private Color pieceColour;
	
	//Rotation of the piece
	private int changeX = 6, changeY = 1, rotation = 0;
	
	private Color[][] grid = new Color[12][24];
	
	public Tetris() {
		super();
		this.name = "Tetris";
		//initialised the song. hopefully fixes nullpointer error
		this.song = new Song("songs/Tetris Official Theme song.wav");
		super.song = new Song("songs/Tetris Official Theme song.wav");
		System.out.println("initialized tetris game and song");
	}
	
	public void init() {
		
		click = false;
		exitStatus = false;
		show = 0;
		controlPiece = pieces[rand.nextInt(pieces.length)];
		pieceColour = colours[rand.nextInt(colours.length)];
		initGrid();
	}
	
	public void initGrid() {
		for (int i = 0; i<grid.length; i++) {
			for (int j = 0; j<grid[i].length; j++) {
				
				grid[i][j] = Color.GRAY;
				
				if (i == 0 || i == 11 || j == 23) {
					grid[i][j] = Color.DARK_GRAY;}
				
			}
		}
	}
	
	//Checks to see if the piece collides with anythings
	public boolean collides(int x, int y, int r) {
		
		int newRot = rotation + r;
		newRot = newRot < 0? newRot+controlPiece.length-1 : newRot;
		newRot %= controlPiece.length;
		
		for (Point p : controlPiece[(newRot) % controlPiece.length]) {
			
			try {	
				if (grid[p.x + x + changeX][p.y + y + changeY] != Color.GRAY) {
					return true;}
			}
			
			catch (ArrayIndexOutOfBoundsException e) {return false;}
		}
		
			return false;}
	
	public void glue() {
		
		for (Point p : controlPiece[rotation]) {
			grid[(int) p.getX() + changeX][(int) p.getY() + changeY] = pieceColour;}
		
		delRows();
		
		resetPiece();}
	
	public void delRows() {
		
		boolean del;
		
		for (int i = 1; i < grid[0].length-1; i++) {
			del = true;
			
			for (int j = 1; j < grid.length-1; j++) {
				if (grid[j][i] == Color.GRAY) {
					del = false;}
			}
			if (del) {
				score+=200;
				clear(i);}
		}
	}
	
	public void clear(int row) {
		for (int j = row-1; j > 0; j--) {
			for (int i = 1; i < grid.length-1; i++) {
				grid[i][j+1] = grid[i][j];}
		}
	}
	
	public void resetPiece() {
		
		changeX = 6;
		changeY = 1;
		rotation = 0;
		
		controlPiece = pieces[rand.nextInt(pieces.length)];
		pieceColour = colours[rand.nextInt(colours.length)];}
	
	public void drawPiece(Graphics g) {
		
		for (Point p : controlPiece[rotation]) {
			
			g.setColor(pieceColour);
			g.fillRect((int) ( (p.getX() + changeX) * 30) + 420, (int) ( (p.getY() + changeY) * 30) + 5, 30, 30);
			
			g.setColor(Color.BLACK);
			g.drawRect((int) ( (p.getX() + changeX) * 30) + 420, (int) ( (p.getY() + changeY) * 30) + 5, 30, 30);	}
	}
	
	public void drawGrid(Graphics g) {
		
		for (int i = 0; i<grid.length; i++) {
			
			for (int j = 0; j<grid[i].length; j++) {
				
				g.setColor(grid[i][j]);
				g.fillRect((i*30) + 420, (j*30) + 5, 30, 30);
				
				g.setColor(Color.BLACK);
				g.drawRect((i*30) + 420, (j*30) + 5, 30, 30);}
		}
		
	}
	
	public boolean isDead() {
		
		for (int i = 0; i<grid.length; i++) {
			if (grid[i][0] != Color.GRAY && grid[i][0] != Color.DARK_GRAY) {
				return true;
			}
		}
		
		return false;}
	
	public void gameOver(Graphics g) throws IOException {
		j.setVisible(false);
	}
	
	public  void displayScore(Graphics g) {
		g.setFont(regFont(Font.PLAIN, 25));
		g.drawString("Score: "+score, 50, 50);}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (show == 1) {
			drawGrid(g);
			
			drawPiece(g);
			
			if (isDead()) {
				// when you die add the current score to the shared score list
				try {
					Socket socket = new Socket("localhost", 25505);
					Client client = new Client(socket);
					client.ADDHIGHSCORE(String.valueOf(getScore()));

					System.out.println(client.GETHIGHSCORES());
				} catch (IOException e) {
					e.printStackTrace();
				}

				stopSound();
				show = 2;}
		}
		
		else if (show == 2) {
			try {
				gameOver(g);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		displayScore(g);
		
	}

	public void run() {
		
		Tetris game = new Tetris();
		game.init();
		
		j.setSize(frameX, frameY);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
		
		j.add(game);
		
		
		j.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {}
			
			public void keyPressed(KeyEvent e) {
				
				switch(e.getKeyCode()) {
				
				case KeyEvent.VK_UP:
				case KeyEvent.VK_W:
					
					if (!game.collides(0, 0, -1)) {
						game.rotation--;
						
						if (game.rotation < 0) {
							game.rotation = game.controlPiece.length - 1;}
						
						game.repaint();}
					break;
					
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_S:
					
					if (!game.collides(0, 0, 1)) {
						game.rotation++;
						
						if (game.rotation >= game.controlPiece.length) {
							game.rotation -= game.controlPiece.length;}
						
						game.repaint();}
					
					break;
					
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_A:
					
					if (!game.collides(-1, 0, 0)) {
						game.changeX--;}
						
						game.repaint();
						break;
					
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_D:
					
					if (!game.collides(1, 0, 0)) {
						game.changeX++;}
					
						game.repaint();
						break;
					
				case KeyEvent.VK_SPACE:
					
					if (game.show == 0) {
						game.show = 1;}
					
					if (!game.collides(0, 1, 0)) {
						game.changeY++;
						game.score++;}
					
					else {

						game.glue();}
					
						
					
						game.repaint();
						break;
				}
				
			}
			
			public void keyReleased(KeyEvent e) {
				
			}
			
		});
		
		j.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent m) {
				
			}
			
			public void mousePressed(MouseEvent m) {
				game.click = true;}
			
			public void mouseReleased(MouseEvent m) {
				game.click = false;}
			
			public void mouseEntered(MouseEvent m) {
				
			}
			
			public void mouseExited(MouseEvent m) {
				
			}
			
		});
		
		j.addMouseMotionListener(new MouseMotionListener() {
			
			public void mouseMoved(MouseEvent m) {
				game.mouseX = (int) MouseInfo.getPointerInfo().getLocation().getX();
				game.mouseY = (int) MouseInfo.getPointerInfo().getLocation().getY();}
			
			public void mouseDragged(MouseEvent m) {}
			
		});
		
		new Thread() {
			
			public void run() {
				while (true) {
					try {
						/**
						 * runs the same code as when you hit space
						 */
						if (game.show == 0) {
							game.show = 1;}

						if (!game.collides(0, 1, 0)) {
							game.changeY++;
							game.score++;}

						game.repaint();

						Thread.sleep(500);
						game.repaint();

						if (game.show == 1) {

							if (game.song.lastFrame == 0) {

								System.out.println(game.song.lastFrame);

								game.playSound();
								game.song.lastFrame = 20_000;}

							else if (game.song.lastFrame == 4606976) {
								game.song.lastFrame = 0;
								game.song.setPos();}

							if (!game.collides(0, 1, 0)) {
							game.changeY++;}

							else {
								game.glue();}
						}

						else if (game.show == 2) {
							stopSound();
						}

					}

					catch (Exception e) {System.out.println("error in second thread: " + e.toString());}
				}
			}
			
		}.start();
	}
}