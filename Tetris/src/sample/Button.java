package sample;

import java.awt.*;

public class Button {

	private int x, y, length, width;
	
	private int mouseX = 0, mouseY = 0;
	
	private String message;
	
	public Button() {
		setBounds(0, 0, 0, 0);
		message = "";}
	
	public Button(int a, int b, int len, int wid) {
		setBounds(a, b, len, wid);
		message = "";}
	
	public Button(int a, int b, int len, int wid, String m) {
		setBounds(a, b, len, wid);
		message = m;}
	
	public void setBounds(int a, int b, int len, int wid) {
		x = a;
		y = b;
		length = len;
		width = wid;}
	
	
	public void draw(Graphics g) {
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, length, width);
		
		
		if (hoversOver(mouseX, mouseY)) {
			
			g.setColor(Color.GRAY);
			g.fillRect(x, y, length, width);}
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Times Roman", Font.PLAIN, 60));
		
		g.drawString(message, x+length/5, y+width/2);
		
		g.drawRect(x, y, length, width);}
	
	public void setMouseCoordinates(int a, int b) {
		mouseX = a;
		mouseY = b;}
	
	public boolean hoversOver(int a, int b) {
		
		if (a >= x && a <= x+length && b >= y && b<= y+width) {
			return true;}
		
		return false;}
	
	public int getMouseX() {return mouseX;}
	public int getMouseY() {return mouseY;}
	public int getX() {return x;}
	public int getY() {return y;}
	public int getLength() {return length;}
	public int getWidth() {return width;}
	public String getMessage() {return message;}
	
	public void setX(int num) {x = num;}
	public void setY(int num) {y = num;}
	public void setLength(int num) {length = num;}
	public void setWidth(int num) {width = num;}
	public void setMessage(String str) {message = str;}
}
