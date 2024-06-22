package code;

public class SwordDN extends Sprite{
	
	static String[] pose = {"UP", "DN", "LT", "RT"};
	

    public SwordDN(int x, int y) {
        super("sword", pose, 2, 0, "png", 7, x, y, 20, 80);
        scale = 3;
        
        
    }

}
