package code;

public class Ball extends Sprite{
	
	static String[] pose = {"UP", "DN", "LT", "RT"};
	

    public Ball(int x, int y) {
        super("fireball", pose, 2, 0, "png", 7, x, y, 50,50);
        scale = 3;
        
        
    }

}
