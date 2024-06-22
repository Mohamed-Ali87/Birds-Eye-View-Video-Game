package code;

public class SwordUP extends Sprite {
	
	static String[] pose = {"UP", "DN", "LT", "RT"};
	

    public SwordUP(int x, int y) {
        super("swordU", pose, 2, 0, "png", 7, x, y, 20, 80);
        scale = 3;
        
        
    }

}
