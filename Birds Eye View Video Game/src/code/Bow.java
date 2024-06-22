package code;

public class Bow extends Sprite {
	
static String[] pose = {"UP", "DN", "LT", "RT"};
	

    public Bow(int x, int y) {
        super("sling", pose, 2, 0, "png", 7, x, y, 80, 80);
        scale = 3;
    }
}
