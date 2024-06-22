package code;

public class Dragon extends Sprite{
	
	
		static String[] pose = {"IDLE"};
		static int[] imgCount = {4};
		public int health = 5;
		
		public Dragon(int x, int y)
		{
			super("dragon", pose, 2, 0, "png", 15, x, y, 133, 160);

		}

}
