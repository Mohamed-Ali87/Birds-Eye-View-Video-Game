package code;
import java.awt.*;

public class Rect
{
	double x;
	double y;
	double w;
	double h;
	
	double vx = 0;
	double vy = 0;
	
	boolean hasSetV = false;
	boolean isMovingUp = false;
	boolean isMovingLeft = false;
	boolean isJumping = false;
	double ix;
	double iy;
	double jumpStartY;
	double jumpStartX;
	//double gravity = G;
	
	//double ay = G;
	
	static final double G = 0.4;
	
	Color c = Color.BLACK;
	
	boolean held = false;
	
	public Rect(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;
		ix = this.x;
		iy = this.y;
	}
	
	public Rect(int x, int y, int w, int h, Color c)
	{
		this(x, y, w, h);
		
		setColor(c);
	}
	
	public void grab()
	{
		held = true;
	}
	
	public void drop()
	{
		held = false;
	}
	
	public void resizeBy(int dw, int dh)
	{
		w += dw;
		h += dh;
	}
	
	public void move()
	{
		x += vx;
		y += vy;
		
	//	vy += ay;
	}
	
	public void jump(int dy)
	{
		vy -= dy;
	}
	
	public void bounceOff(Rect r)
	{
		if(wasAbove(r) || wasBelow(r))     bounceV();
		
		if(wasLeftOf(r) || wasRightOf(r))  bounceH();
	}
	
	public void bounceV()
	{
		vy = -vy *.6;
		
		if(Math.abs(vy) < 3)  vy = 0;
	}
	
	public void bounceH()
	{
		vx = -vx * .6;

		if(Math.abs(vx) < 3)  vx = 0;
	}
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	public void setVelocity(int vx, int vy)
	{
		this.vx = vx;
		this.vy = vy;
	}
	
	//-------------------------------------------------------------------------//
	// Setup the Rect to go Left when the move function is called in GameLoop  //
	//-------------------------------------------------------------------------//
	
	public void goLT(int dx)
	{
		vx = -dx;
	}
	
	public void goRT(int dx)
	{
		vx = dx;
	}
	
	public void goUP(int dy)
	{
		vy = -dy;
	}
	
	public void goDN(int dy)
	{
		vy = dy;
	}
	
	public void physicsOFF()
	{
		setVelocity (0, 0);
	}
	
	public void setColor(Color c)
	{
		this.c = c;
	}
	
	public boolean contains(int mx, int my)
	{
		return (mx >= x  )   && 
			   (mx <= x+w)   && 			   
			   (my >= y  )   && 
			   (my <= y+h);
	}
	
	public void moveBy(int dx, int dy)
	{
		x += dx;
		y += dy;
	}
	
	
	public boolean isLeftOf(Rect r)
	{
		return x < r.x - w + 1;
	}
	public boolean isRightOf(Rect r)
	{
		return x > r.x + r.w - 1;
	}
	
	public boolean isAbove(Rect r)
	{
		return y < r.y - h + 1;
	}
	
	public boolean isBelow(Rect r)
	{
		return y > r.y + r.h - 1;
	}
	
	public void chase(Rect r, int speed)
	{
		if(r.isAbove  (this))   goUP(speed);
		if(r.isBelow  (this))   goDN(speed);
		if(r.isLeftOf (this) )   goLT(speed);
		if(r.isRightOf(this))   goRT(speed);
		
	/*	if (overlaps(r)) {
	        vx = 0;
	        vy = 0;
	    } */
	}
	
	   public void moveLeftRight(int leftDistance, int rightDistance, int s) {
		   
	        if (isMovingLeft) {
	            goLT(s);
	        } else {
	            goRT(s);
	        }

	        // Change direction when reaching the left or right boundary
	        if (x <= ix - leftDistance) {
	            isMovingLeft = false;
	        } else if (x >= ix + rightDistance) {
	            isMovingLeft = true;
	        }
	    }
	   
	   public void moveUpDown(int oscillationDistance, int speed) {
	        if (isMovingUp) {
	            goUP(speed);
	            if (y <= iy - oscillationDistance) {
	                isMovingUp = false;
	            }
	        } else {
	            goDN(speed);
	            if (y >= iy + oscillationDistance) {
	                isMovingUp = true;
	            }
	        }
	    }
	   
	   
	   public void shootPastPlayer(Rect playerRect, double speed) {
	        // Calculate the direction from the AI rect to the player rect
	        double deltaX = playerRect.x - this.x;
	        double deltaY = playerRect.y - this.y;

	        // Calculate the magnitude of the direction vector
	        double magnitude = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

	        // Normalize the direction vector
	        double normalizedDeltaX = deltaX / magnitude;
	        double normalizedDeltaY = deltaY / magnitude;

	        // Set the velocity based on the normalized direction vector and speed
	        this.vx = normalizedDeltaX * speed;
	        this.vy = normalizedDeltaY * speed;
	        
	        hasSetV = true;
	    }



	
	
	

	public boolean wasLeftOf(Rect r)
	{
		return x - vx < r.x - w + 1;
	}
	
	public boolean wasRightOf(Rect r)
	{
		return x - vx > r.x + r.w - 1;
	}
	
	public boolean wasAbove(Rect r)
	{
		return y - vy < r.y - h + 1;
	}
	
	public boolean wasBelow(Rect r)
	{
		return y - vy > r.y + r.h - 1;
	}
	
	public void pushedOutOf(Rect r)
	{
		if(wasLeftOf(r))    pushLeftOf(r);
		if(wasRightOf(r))   pushRightOf(r);
		if(wasAbove(r))     pushAbove(r);
		if(wasBelow(r))     pushBelow(r);
	}
	
	public void pushLeftOf(Rect r)
	{
		x = r.x - w - 1;
	}
	
	public void pushRightOf(Rect r)
	{
		x = r.x + r.w + 1;
	}
	
	public void pushAbove(Rect r)
	{
		y = r.y - h - 1;
	}
	
	public void pushBelow(Rect r)
	{
		y = r.y +  r.h + 1;
	}
	
	public void applyFrictionWithFloor()
	{
		vx = vx *.6;
	}
	
	public void stopsFalling()
	{
		vy = 0;
	}
	
	public boolean standingOnAny(Rect[] r)
	{
		for(int i = 0; i < r.length; i++)
		{
			if (y+h == r[i].y - 1)  return true;
		}
		
		return false;		
	}
	
	
	public boolean overlaps(Rect r)
	{
		return (x     <= r.x + r.w) &&
			   (x + w >= r.x      ) &&
			   (y     <= r.y + r.h) &&
			   (y + h >= r.y      );
    }
	
	public void draw(Graphics pen)
	{
		pen.setColor(c);
		pen.drawRect((int)x - Camera.x, (int)y -Camera.y, (int)w, (int)h);
	}
	
	
	

}