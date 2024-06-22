package code;

public class Heart extends Sprite {
	
static String[] pose = {"UP", "DN", "LT", "RT"};
	

    public Heart(int x, int y) {
        super("heart", pose, 2, 0, "png", 7, x, y, 100, 100);
        scale = 3;
    }
}
