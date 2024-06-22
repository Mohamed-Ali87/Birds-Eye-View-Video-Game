package code;

public class SwordRT extends Sprite {
	
	static String[] pose = {"UP", "DN", "LT", "RT"};
	

    public SwordRT(int x, int y) {
        super("swordR", pose, 2, 0, "png", 7, x, y, 80, 20);
        scale = 3;
        
        
    }

}
