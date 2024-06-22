package code;

public class Armos extends Sprite {

	static String[] pose = {"UP", "DN", "LT", "RT"};
	
	public int health = 3;

    public Armos(int x, int y) {
        super("armos", pose, 2, 0, "png", 7, x, y, 100, 100);
        scale = 3;
        
        
    }
	
}
