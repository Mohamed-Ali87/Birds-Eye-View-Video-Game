package code;

import java.awt.Graphics;

public class Octorock extends Sprite
{
	public int health = 1;
	static String[] pose = {"UP", "DN", "LT", "RT"};
	

    public Octorock(int x, int y) {
        super("Octorock", pose, 2, 0, "png", 7, x, y, 80, 80);
        scale = 3;
        ix = x;
        
    }

    


}