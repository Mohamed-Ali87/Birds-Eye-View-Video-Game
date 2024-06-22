package code;

public class Zora extends Sprite {
	
	static String[] pose = {"UP", "DN", "LT", "RT"};
	
	public Zora(int x, int y) {
		super("zora", pose, 2, 0, "png", 7, x, y, 80, 80);
        scale = 3;
        
        
    }

}
