package code;

public class Sword extends Sprite {
	
	static String[] pose = {"UP", "DN", "LT", "RT"};
	

    public Sword(int x, int y) {
        super("msword", pose, 2, 0, "png", 7, x, y, 70, 80);
        scale = 3;
        
        
    }

}
