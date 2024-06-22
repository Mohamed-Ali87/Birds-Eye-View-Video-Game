package code;

public class Shield extends Sprite{
	
	static String[] pose = {"UP", "DN", "LT", "RT"};
	
	public int health = 3;

    public Shield(int x, int y) {
        super("shield", pose, 2, 0, "png", 7, x, y, 70, 70);
        scale = 3;
        
        
    }

}
