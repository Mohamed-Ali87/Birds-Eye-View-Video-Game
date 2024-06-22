package code;

import java.awt.Graphics;

public class Camera {
	
	public static int x = 0;
	public static int y = 0;
//	public static int w = 800;
//	static int h = 500;
  
	
	
	
	public static void setup(int x, int y) {
		
		setLocation(x, y);
		//setSize(w, h);
	}
	
	public static void setLocation(int x, int y) {
		
		Camera.x = x;
		Camera.y = y;
	}
	
	public static void setSize(int w, int h) {
		
		Camera.w = w;
		Camera.h = h;
	}
	
	static public void moveUp(int dy) {
		y -= dy;
	}
	
	static public void moveDown(int dy) {
		y += dy;
	}
	
	static public void moveRight(int dx) {
		x += dx;
	}
	
	static public void moveLeft(int dx) {
		x -= dx;
	}
	
	public static void draw(Graphics g) {
		
	  g.drawRect(x, y, w, h);
	}
	
	
	
	
}