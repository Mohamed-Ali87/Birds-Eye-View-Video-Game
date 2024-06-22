package code;

public class Rock extends Sprite {
	
	static String[] pose = {"UP", "DN", "LT", "RT"};
	

    public Rock(int x, int y, int w, int h) {
        super("rock", pose, 2, 0, "png", 7, x, y, w, h);
        scale = 3;
        
        
    }

}
