package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Link extends Sprite //implements KeyListener
{
	static String[] pose = {"UP", "DN", "LT", "RT"};
	
	public int health = 350;
	public int attack = 1;
	
	public Link(int x, int y)
	{
		super("link", pose, 2, 0, "png", 7, x, y, 80, 80);

	}
}