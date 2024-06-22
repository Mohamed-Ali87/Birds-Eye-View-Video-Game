package code;

public class SwordLT extends Sprite{
	
	static String[] pose = {"UP", "DN", "LT", "RT"};
	

    public SwordLT(int x, int y) {
        super("swordL", pose, 2, 0, "png", 7, x, y, 80, 20);
        scale = 3;
        
        
    }

}
