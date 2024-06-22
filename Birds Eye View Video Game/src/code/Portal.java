package code;

public class Portal extends Sprite{
	
static String[] pose = {"UP", "DN", "LT", "RT"};
	
	public int health = 3;

    public Portal(int x, int y) {
        super("portal1", pose, 2, 0, "png", 7, x, y, 150, 300);
        scale = 3;
        
        
    }

}
