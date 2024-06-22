package code;

public class HurtDragon extends Sprite {
	
	static String[] pose = {"UP", "DN", "LT", "RT"};
	

    public HurtDragon(int x, int y) {
        super("dragon", pose, 2, 0, "png", 7, x, y, 133, 160);
        scale = 3;
        
        
    }

}
