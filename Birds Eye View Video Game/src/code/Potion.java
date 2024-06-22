package code;

public class Potion extends Sprite {
	
	static String[] pose = {"UP", "DN", "LT", "RT"};
	
	public int health = 3;

    public Potion(int x, int y) {
        super("potion", pose, 2, 0, "png", 7, x, y, 100, 100);
        scale = 3;
        
        
    }

}
