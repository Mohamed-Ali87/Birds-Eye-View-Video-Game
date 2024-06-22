package code;

public class Fly extends Sprite {
	
static String[] pose = {"UP", "DN", "LT", "RT"};
	

    public Fly(int x, int y) {
        super("fly", pose, 2, 0, "png", 7, x, y, 80, 80);
        scale = 3;
        
        
    }

}
