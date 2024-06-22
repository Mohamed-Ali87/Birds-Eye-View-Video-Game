package code;

public class Key extends Sprite {
	
	static String[] pose = {"UP", "DN", "LT", "RT"};
	

    public Key(int x, int y) {
        super("key", pose, 2, 0, "png", 7, x, y, 80, 80);
        scale = 3;
        
        
    }

}
