package code;
import java.awt.*;

public class Sprite extends Rect
{
	Animation[] animation;
	
	final static int UP = 0;
	final static int DN = 1;
	final static int LT = 2;
	final static int RT = 3;
	
	int pose = DN;
	
	static double scale = 1;
	
	boolean moving = false;
	
	public Sprite(String name, String[] pose, int imagecount, int start, String filetype, int ms, int x, int y, int w, int h)
	{
		super(x, y, w, h);
		
		animation = new Animation[pose.length];
		
		for(int i = 0; i < pose.length; i ++)
		{
			animation[i] = new Animation(name + "_" + pose[i], imagecount, start,  ms, filetype);
		}
	}
	
	
	
	public void goLT(int dx)
	{
		pose = LT;
		
		moving = true;
		
		vx = -dx;
	}
	
	public void goRT(int dx)
	{
		pose = RT;
		
		moving = true;
		
		vx = dx;
	}
	
	public void goUP(int dy)
	{
		pose = UP;
		
		moving = true;
		
		vy = -dy;
	}
	
	public void goDN(int dy)
	{
		pose = DN;
		
		moving = true;
		
		vy = dy;
	}
	
	
	
	
	public void draw(Graphics pen)
	{
		if (!moving)

			pen.drawImage(animation[pose].getStaticImage(), (int)x - Camera.x, (int)y - Camera.y, (int)w, (int)h, null);
		
		else
		
			pen.drawImage(animation[pose].getCurrentImage(), (int)x - Camera.x, (int)y - Camera.y, (int)w, (int)h, null);
	}

	public void update() {
		// TODO Auto-generated method stub
		animation[pose].getCurrentImage();
	}

	
	
}