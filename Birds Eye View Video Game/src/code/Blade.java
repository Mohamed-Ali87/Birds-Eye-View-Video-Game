package code;

public class Blade extends Sprite{
	
	static String[] pose = {"UP", "DN"};
	

    public Blade(int x, int y) {
        super("blade", pose, 2, 0, "png", 0, x, y, 80, 80);
        scale = 3;
        
        
    }

}
