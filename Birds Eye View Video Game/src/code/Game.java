package code;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.Timer;

public class Game extends GameBase
{
	
	private long[] lastShootTimes; // To track the last shoot time for each AI rectangle
    private long shootDelay = 2500;
	
	
	private Image backgroundImage;
	private AudioClip backgroundSound;
	private int damage = 7;
	
	
	Link link = new Link(500, 600);
	SwordUP swordUp = new SwordUP((int)link.x+20, (int)link.y-80);
	SwordDN swordDN = new SwordDN((int)link.x+20, (int)link.y+80);
	SwordLT swordLT = new SwordLT((int)link.x-80, (int)link.y+25);
	SwordRT swordRT = new SwordRT((int)link.x+80, (int)link.y+25);
	
	Rect2 health = new Rect2(100, 10, link.health, 50, Color.red );
	Rect2 bar = new Rect2(100, 10, link.health, 50, Color.DARK_GRAY );
	Heart heart = new Heart(2820,-585);
	Potion potion = new Potion(-7580, -600);
	
	
	Portal portal = new Portal(3402,-685);
	Dragon dragon = new Dragon(3231,446);
	HurtDragon hDragon = new HurtDragon(3231, 446);
	
	Sword sword = new Sword(940, -670);
	Shield shield = new Shield(892,-651);
	Bow bow = new Bow(940, -670);
	Key key = new Key(940,-670);
	
	Rect octx = new Rect(1000, 600,1,1);
	Armos armos = new Armos(-4864, 520);
	Armos armos1 = new Armos(-4697, 520);
	//Fly fly = new Fly(300,500);
	Zora zora = new Zora(-3182, -880);
	Blade blade = new Blade(3121,494);
	
	Octorock[] oct = new Octorock[7];
	
	Fly[] flyV = new Fly[5];
	Fly[] flyH = new Fly[4];
	Wall[] wall = new Wall[195];
	Ball[] ball = new Ball[20];
	
	boolean hasPotion = false;
	boolean hasHeart = false;
	boolean hasShield = false;
	boolean hasSword = false;
	boolean hasBow = false;
	boolean hasKey = false;
	boolean attacking = false;
	boolean fire = false;
	boolean hit = false;
	boolean[] octDead = new boolean[oct.length];
	boolean armosDead = false;
	boolean armos1Dead = false;
	boolean dragonDead = false;
	
	boolean facingUP = false;
	boolean facingDN = false;
	boolean facingLT = false;
	boolean facingRT = false;
	
	//for cave
	boolean wall0 = false;
	boolean wall7 = false;
	boolean wall12 = false;
	boolean wall15 = false;
	
	
	//Shooting
	
	//Rect c     		   = new Rect(100, 100, 35, 35);
	double speed 	   = 5;
	
	Rock[]bullet       = new Rock[12];
	int bulletVelocity = 12;
	int i              = 0;
	int bW	 		   = 30;
	int bH			   = 30;
	
	Timer shotCooldown;
	int   cooldownTime;	
	
	
	
	
	

	public void initialize()
	{		
		backgroundImage = getImage(getDocumentBase(), "Overworld3.png");
		lastShootTimes = new long[ball.length];
		
		//setting location of sprites
		
		oct[0] = new Octorock(-1543, -846);
		oct[1] = new Octorock(-1188, -660);
		oct[2] = new Octorock(-1547, -469);
		oct[3] = new Octorock(-1182, -275);
		oct[4] = new Octorock(-702, -754);
		oct[5] = new Octorock(-941, -572);
		oct[6] = new Octorock(-714, -357);
		
		for(int i = 0; i < flyV.length; i++)
		{
		   flyV[i] = new Fly(-7680+ 400*i,  -500);
        }
		
		for(int i = 0; i < flyH.length; i++)
		{
		   flyH[i] = new Fly(-7655,  -900 + (i* 200));
        }
		flyH[1] = new Fly(-5864,-689);
		flyH[3] = new Fly(-5875,-285);
		
		for(int i = 0; i < ball.length; i++)
		{
		   ball[i] = new Ball(3228 + i*100,  443);
		   lastShootTimes[i] = System.currentTimeMillis();
        }
		
		
		for(int i = 22; i < wall.length; i++)
		{
		   wall[i] = new Wall(2300+15*i,  600, 20, 120);
        }
		
		//Camera
		
		  //first map
		wall[0] = new Wall(490, 43, 124, 121); //cave
		wall[1] = new Wall(2, 495, 28, 91);
		
		//right1
		wall[4] = new Wall(-1909, 203, 14, 682);
		wall[5] = new Wall(-473, 8, 236, 31);
		wall[6] = new Wall(-28, 485, 20, 120);
		
		//left1 up1
		wall[7] = new Wall(-1082, -1061, 133, 123); //cave
		wall[8] = new Wall(-1914, -894, 45, 726);
		wall[9] = new Wall(-495, -180, 262, 47);
		
		//left2
		wall[10] = new Wall(-3368, 9, 131, 29);
		wall[11] = new Wall(-1956, 196, 23, 708);
		wall[12] = new Wall(-3609, 33, 141, 117);//cave
		
		//left2 up1
		wall[13] = new Wall(-3830, -883, 594, 757);
		wall[14] = new Wall(-1948, -887, 21, 709);
		
		//left3 up1
		wall[15] = new Wall(-4957, -1058, 187, 120);//cave
		wall[16] = new Wall(-5741, -879, 1882, 723);
		
		//left4 up1
		wall[17] = new Wall(-6884, -968, 1110, 839);
		
		//left4
		wall[18] = new Wall(-6875, 7, 1100, 781);
		
		
		//left3
		wall[19] = new Wall(-5754, 387, 29, 316);
		wall[20] = new Wall(-4810, 380, 140, 46); //Boss entrance
		
		//boss room
		wall[2] = new Wall(3714, 467, 17, 145);
		
		//end room
		wall[3] = new Wall(2028, -701, 7, 270);
		
		//wall when exiting out of cave
		wall[21] = new Wall(859, 1, 211, 9); 
		
	//	*****************************************************************************************************************************************************
		
		//Collision Walls
		
		//first room
		wall[22] = new Wall(-3, 372, 124, 121);
		wall[23] = new Wall(-10, 591, 177, 74);
		wall[24] = new Wall(118, 364, 78, 119);
		wall[25] = new Wall(624, 100, 136, 98);
		wall[26] = new Wall(1686, 587, 236, 311);
		wall[27] = new Wall(828, -6, 20, 124);
		wall[28] = new Wall(228, 633, 20, 256);
		wall[29] = new Wall(244, 883, 1456, 69);
		wall[30] = new Wall(1184, 372, 739, 120);
		wall[31] = new Wall(1087, 2, 79, 435);
		wall[32] = new Wall(377, 165, 48, 105);
		wall[33] = new Wall(292, 284, 63, 42);
		wall[34] = new Wall(469, 84, 157, 16);
		wall[35] = new Wall(413, 75, 68, 119);
		wall[36] = new Wall(226, 303, 73, 68);
		
		//left1
		wall[37] = new Wall(-1927, 91, 1361, 107);
		wall[38] = new Wall(-573, 2, 90, 131);
		wall[39] = new Wall(-237, 3, 100, 333);
		wall[40] = new Wall(-229, 754, 136, 168);
		wall[41] = new Wall(-1931, 888, 1716, 43);
		wall[42] = new Wall(-708, 402, 91, 88);
		wall[43] = new Wall(-952, 302, 98, 94);
		wall[44] = new Wall(-1313, 509, 107, 79);
		wall[45] = new Wall(-1671, 504, 105, 81);
		wall[46] = new Wall(-954, 703, 106, 74);
		wall[47] = new Wall(-714, 605, 98, 73);
		wall[48] = new Wall(-106, 648, 89, 45);
		wall[49] = new Wall(-952, 511, 99, 71);
		wall[50] = new Wall(-117, 322, 133, 118);
		wall[51] = new Wall(-175, 699, 104, 34);
		
		
		//left2
		wall[52] = new Wall(-3475, 2, 111, 134);
		wall[53] = new Wall(-3726, 181, 102, 90);
		wall[54] = new Wall(-3473, 144, 61, 37);
		wall[55] = new Wall(-2890, 79, 974, 119);
		wall[56] = new Wall(-2947, 893, 1045, 34);
		wall[57] = new Wall(-2391, 312, 94, 71);
		wall[58] = new Wall(-2635, 414, 87, 60);
		wall[59] = new Wall(-2878, 316, 111, 79);
		wall[60] = new Wall(-2875, 512, 106, 71);
		wall[61] = new Wall(-2878, 709, 107, 63);
		wall[62] = new Wall(-2636, 606, 107, 71);
		wall[63] = new Wall(-2396, 506, 104, 75);
		wall[64] = new Wall(-2398, 710, 111, 72);
		wall[65] = new Wall(-3235, 1, 237, 948);
		wall[66] = new Wall(-2997, 24, 96, 141);
		wall[67] = new Wall(-3662, 33, 57, 144);
		wall[68] = new Wall(-3473, 504, 96, 85);
		wall[69] = new Wall(-2989, 918, 36, 28);
		wall[70] = new Wall(-3568, 898, 316, 61);
		wall[71] = new Wall(-3716, 806, 116, 113);
		wall[72] = new Wall(-3759, 280, 44, 521);
		
		//left1 up1
		wall[73] = new Wall(-950, -680, 335, 86);
		wall[74] = new Wall(-1549, -582, 450, 87);
		wall[75] = new Wall(-1539, -384, 449, 84);
		wall[76] = new Wall(-943, -485, 326, 91);
		wall[77] = new Wall(-952, -980, 723, 94);
		wall[78] = new Wall(-1547, -774, 461, 83);
		wall[79] = new Wall(-1913, -197, 1430, 71);
		wall[80] = new Wall(-12, -783, 12, 490);
		wall[81] = new Wall(-234, -232, 116, 104);
		wall[82] = new Wall(-229, -894, 109, 73);
		wall[83] = new Wall(-113, -786, 74, 48);
		wall[84] = new Wall(-129, -338, 105, 115);
		wall[85] = new Wall(-1917, -983, 841, 98);
		
		//left2 up1
		wall[86] = new Wall(-2146, -1073, 239, 182);
		wall[87] = new Wall(-2400, -1074, 119, 191);
		wall[88] = new Wall(-2638, -1073, 121, 186);
		wall[89] = new Wall(-2634, -777, 96, 90);
		wall[90] = new Wall(-2394, -676, 99, 87);
		wall[91] = new Wall(-2633, -579, 100, 82);
		wall[92] = new Wall(-2397, -478, 103, 81);
		wall[93] = new Wall(-2634, -383, 99, 81);
		wall[94] = new Wall(-2991, -197, 1069, 68);
		wall[95] = new Wall(-3847, -182, 468, 70);
		wall[96] = new Wall(-3238, -492, 239, 362);
		wall[97] = new Wall(-3235, -1079, 235, 481);
		wall[98] = new Wall(-3596, -1079, 1680, 4);
		wall[99] = new Wall(-3870, -1101, 256, 200);
		
		//left3 up1
		wall[100] = new Wall(-5406, -197, 1567, 73);
		wall[101] = new Wall(-5031, -667, 86, 70);
		wall[102] = new Wall(-5026, -480, 83, 78);
		wall[103] = new Wall(-4800, -1075, 120, 174);
		wall[104] = new Wall(-5160, -1079, 240, 192);
		wall[105] = new Wall(-4443, -1081, 239, 197);
		wall[106] = new Wall(-4062, -1086, 242, 195);
		wall[107] = new Wall(-5546, -1079, 1564, 3);
		wall[108] = new Wall(-5742, -372, 201, 210);
		wall[109] = new Wall(-5693, -747, 108, 38);
		wall[110] = new Wall(-5514, -271, 76, 90);
		wall[111] = new Wall(-5550, -900, 167, -83);
		wall[112] = new Wall(-5419, -1071, 20, 210);
		wall[113] = new Wall(-5598, -860, 151, 51);
		wall[114] = new Wall(-5738, -777, 208, 25);
		
		//left4 up1
		wall[115] = new Wall(-6231, -576, 90, 68);
		wall[116] = new Wall(-5886, -785, 123, 96);
		wall[117] = new Wall(-6237, -1076, 99, 186);
		wall[118] = new Wall(-6483, -190, 111, 87);
		wall[119] = new Wall(-6240, -189, 107, 81);
		wall[120] = new Wall(-6481, -1079, 104, 193);
		wall[121] = new Wall(-6477, -773, 94, 84);
		wall[122] = new Wall(-7213, -1080, 1228, 5);
		wall[123] = new Wall(-5999, -289, 90, 179);
		wall[124] = new Wall(-5886, -389, 119, 281);
		wall[125] = new Wall(-7313, -380, 84, 71);
		wall[126] = new Wall(-7317, -581, 104, 82);
		wall[127] = new Wall(-6962, -1075, 108, 191);
		wall[128] = new Wall(-6003, -1079, 162, 293);
		wall[129] = new Wall(-6474, -377, 89, 71);
		wall[130] = new Wall(-6478, -572, 96, 71);
		wall[131] = new Wall(-6954, -479, 92, 68);
		wall[132] = new Wall(-6948, -678, 79, 80);
		wall[133] = new Wall(-7309, -780, 87, 83);
		wall[134] = new Wall(-7443, -1079, 232, 190);
		wall[135] = new Wall(-7681, -784, 6, 495);
		wall[136] = new Wall(-7692, -878, 245, 95);
		wall[137] = new Wall(-7431, -186, 578, 79);
		wall[138] = new Wall(-7679, -288, 225, 85);
		
		//left4
		wall[139] = new Wall(-6483, 3, 114, 197);
		wall[140] = new Wall(-6241, 2, 107, 198);
		wall[141] = new Wall(-6478, 504, 99, 79);
		wall[142] = new Wall(-7679, 302, 827, 483);
		wall[143] = new Wall(-5879, 696, 109, 94);
		wall[144] = new Wall(-6002, -5, 127, 299);
		wall[145] = new Wall(-5875, 2, 108, 392);
		wall[146] = new Wall(-7675, 1, 823, 196);
		wall[147] = new Wall(-7680, 2, 5, 929);
		wall[148] = new Wall(-6000, 795, 231, 94);
		wall[149] = new Wall(-7680, 888, 1714, 64);
		
		//boss entrance
		wall[150] = new Wall(-4075, 168, 116, 100);
		wall[151] = new Wall(-4846, 303, 210, 88);
		wall[152] = new Wall(-4619, 338, 53, 56);
		wall[153] = new Wall(-4923, 342, 60, 44);
		wall[154] = new Wall(-4680, 393, 232, 95);
		wall[155] = new Wall(-5040, 391, 237, 102);
		wall[156] = new Wall(-4322, 587, 113, 100);
		wall[157] = new Wall(-4322, 394, 113, 99);
		wall[158] = new Wall(-4560, 586, 112, 95);
		wall[159] = new Wall(-5040, 586, 115, 104);
		wall[160] = new Wall(-5280, 589, 114, 93);
		wall[161] = new Wall(-5281, 395, 115, 94);
		wall[162] = new Wall(-3963, 253, 97, 544);
		wall[163] = new Wall(-5772, 259, 83, 120);
		wall[164] = new Wall(-4051, 796, 99, 79);
		wall[165] = new Wall(-5455, 885, 1392, 89);
		wall[166] = new Wall(-5635, 822, 107, 131);
		wall[167] = new Wall(-5704, 747, 51, 131);
		wall[168] = new Wall(-5775, 698, 70, 138);
		wall[169] = new Wall(-5562, 107, 36, 122);
		wall[170] = new Wall(-5674, 171, 99, 108);
		
		//cave
		wall[171] = new Wall(213, -1004, 1585, 119);
		wall[172] = new Wall(1076, -198, 586, 51);
		wall[173] = new Wall(211, -204, 621, 80);
		wall[174] = new Wall(1677, -921, 130, 776);
		wall[175] = new Wall(221, -899, 20, 729);
		
		//boss room
		wall[176] = new Wall(2157, 881, 657, 63);
		wall[177] = new Wall(3227, 683, 362, 101);
		wall[178] = new Wall(2934, 884, 358, 75);
		wall[179] = new Wall(2136, 193, 21, 696);
		wall[180] = new Wall(2157, 132, 875, 64);
		wall[181] = new Wall(2994, 196, 274, 99);
		wall[182] = new Wall(3233, 297, 358, 100);
		wall[183] = new Wall(3353, 395, 368, 99);
		wall[184] = new Wall(3348, 583, 376, 113);
		wall[185] = new Wall(2989, 783, 417, 101);
		
		//last room
		wall[186] = new Wall(2061, -492, 95, 360);
		wall[187] = new Wall(2157, -200, 1620, 61);
		wall[188] = new Wall(3592, -885, 98, 714);
		wall[189] = new Wall(2157, -922, 1467, 38);
		wall[190] = new Wall(2061, -885, 97, 295);
		
		//fire wall
	//	wall[191] = new Wall(3844, -177, 86, 1356);
	//	wall[192] = new Wall(1877, 1083, 2121, 78);
	//	wall[193] = new Wall(1278, -208, 2752, 103);
	//	wall[194] = new Wall(1805, -605, 117, 2279);
		

   // adding background music
		 try {
	            URL soundUrl = new URL(getCodeBase(), "Overworld.wav");
	            backgroundSound = getAudioClip(soundUrl);
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        }
		 
		 
		 cooldownTime = 400;
			
			shotCooldown = new Timer(cooldownTime, new ActionListener() 
			{
	            @Override
	            public void actionPerformed(ActionEvent e) 
	            {
	            	shotCooldown.stop(); //cooldown ends after the above specified time
	            }
	        });
		 

	}
	
	public void start() {
        // Start the background sound when the applet starts
        backgroundSound.loop();
    }

    public void stop() {
        // Stop the background sound when the applet stops
        backgroundSound.stop();
    }
	
	public void inGameLoop()
	{
		dragon.moving = false;
		
		//setting location of sword
		swordUp.x = link.x+20; 
		swordUp.y = link.y-80;
		
		swordDN.x = link.x+20; 
		swordDN.y = link.y+80;
		
		swordLT.x = link.x+-77; 
		swordLT.y = link.y+25;
		
		swordRT.x = link.x+77; 
		swordRT.y = link.y+25;
		
		
		
		link.physicsOFF();
		link.moving = false;
		
		swordUp.physicsOFF();
		swordDN.physicsOFF();
		swordLT.physicsOFF();
		swordRT.physicsOFF();
					
		// Respond to user input
	
		if(pressing[_W]) {
			link.goUP(10); 
		
			
			
			
			facingUP = true;
			facingDN = false;
			facingLT = false;
			facingRT = false;
		}
		if(pressing[_S]) {
			link.goDN(10); 
		
			
			facingDN = true;
			facingUP = false;
			facingLT = false;
			facingRT = false;
		}
		if(pressing[_A]) {
			link.goLT(10);
			
			
			facingLT = true;
			facingUP = false;
			facingDN = false;
			facingRT = false;
		}
		if(pressing[_D]) {
			link.goRT(10); 
			
			
			facingRT = true;
			facingUP = false;
			facingLT = false;
			facingDN = false;
		}
		
		// collision detection
		for(int i = 22; i < wall.length; i++)
		{
			if(link.overlaps(wall[i]))
			{
				link.pushedOutOf(wall[i]);
				
				swordUp.vx = 0;
				swordUp.vy = 0;
				
			//	if(facingUP && pressing[_W]) swordUp.vy = -10;
				
			}
			if(armos.overlaps(wall[i]))
			{
			//	armos.pushedOutOf(wall[i]);
			}
		}
		
		for(int i = 191; i < wall.length; i++) {
			for(int j = 0; j < ball.length; j++) {
				if(ball[j].overlaps(wall[i])) ball[j].pushedOutOf(wall[i]);
			}
		}
		
		
		//Changing rooms
		if(link.overlaps(wall[1]) ) Camera.setLocation(0,0);
		
		if(link.overlaps(wall[5]) || link.overlaps(wall[4])  || link.overlaps(wall[6]) ) Camera.setLocation(-1920,0);
		
		if(link.overlaps(wall[7]) || link.overlaps(wall[8]) || link.overlaps(wall[9])) Camera.setLocation(-1920, -1080);
		
		if(link.overlaps(wall[10]) || link.overlaps(wall[11])) Camera.setLocation(-3840, 0);
			
		if(link.overlaps(wall[13]) || link.overlaps(wall[14])) Camera.setLocation(-3840, -1080);
		
		if(link.overlaps(wall[16])) Camera.setLocation(-5760, -1080);
		
		if(link.overlaps(wall[17])) Camera.setLocation(-7680, -1080);
		
		if(link.overlaps(wall[18])) Camera.setLocation(-7680, 0);
		
		if(link.overlaps(wall[19])) Camera.setLocation(-5760, 0);
		
		
		
		//Camera to handle which cave to exit out from
		
		if(link.overlaps(wall[0])) wall0 = true;
		if(link.overlaps(wall[7])) wall7 = true;
		if(link.overlaps(wall[12])) wall12 = true;
		if(link.overlaps(wall[15])) wall15 = true;
		
		
		
		
		if(link.overlaps(wall[0]) || link.overlaps(wall[7]) || link.overlaps(wall[12]) || link.overlaps(wall[15]) ) {
			
			Camera.setLocation(0,-1080);
			
			link.x = 950;
			link.y = -165;
		}
		
		if(link.overlaps(wall[21]) && wall0 == true) {
			
			Camera.setLocation(0, 0);
			
			link.x = 514;
			link.y = 201;
			
			wall0 = false;
		}
		
        if(link.overlaps(wall[21]) && wall7 == true) {
			
			Camera.setLocation(-1920, -1080);
			
			link.x = -1057;
			link.y = -890;
			
			wall7 = false;
		}
        
        if(link.overlaps(wall[21]) && wall12 == true) {
			
			Camera.setLocation(-3840, 0);
			
			link.x = -3578;
			link.y = 195;
			
			wall12 = false;
		}
        
       if(link.overlaps(wall[21]) && wall15 == true) {
			
			Camera.setLocation(-7680, -1080);
			
			link.x = -4897;
			link.y = -896;
			
			wall15 = false;
		}


        
	  // Camera to handle boss and end room
      
		if(link.overlaps(wall[20]) && hasKey) { 
			
			Camera.setLocation(1920, 0);
			
			link.x = 2875;
			link.y = 885;
			fire = true;
		}
		
		if(link.overlaps(wall[2]) ) {
			
			Camera.setLocation(1920, -1080);
			
			link.x = 2109;
			link.y = -579;
		}
		
       if(link.overlaps(wall[3])) {
			
			Camera.setLocation(1920, 0);
			
			link.x = 3639;
			link.y = 497;
		}
		
		
       //Chasing method
       
       boolean chasing = false;
       int range = 400; // Adjust the range as needed

       if (link.x + link.w >= armos.x - range &&
           link.x <= armos.x + armos.w + range &&
           link.y + link.h >= armos.y - range &&
           link.y <= armos.y + armos.h + range) {
           chasing = true;
       } else {
           chasing = false;
       }

       if (chasing) armos.chase(link, 3);
       if(!chasing) {
    	   armos.x = -4864;
    	   armos.y = 520;
       }
       
       
       boolean chasing1 = false;
       int range1 = 400; // Adjust the range as needed

       if (link.x + link.w >= armos1.x - range1 &&
           link.x <= armos1.x + armos1.w + range1 &&
           link.y + link.h >= armos1.y - range1 &&
           link.y <= armos1.y + armos1.h + range1) {
           chasing1 = true;
       } else {
           chasing1 = false;
       }

       if (chasing1) armos1.chase(link,3);
       if(!chasing1) {
    	   armos1.x = -4697;
    	   armos1.y = 520;
       }
    	   
    	// calling methods for ai
       
    	oct[0].moveLeftRight(0, 400,4);
    	oct[1].moveLeftRight(400, 0,4);
    	oct[2].moveLeftRight(0, 400,4);
    	oct[3].moveLeftRight(400, 0,4);
    	oct[4].moveLeftRight(300, 0,4);
    	oct[5].moveLeftRight(0, 300,4);
    	oct[6].moveLeftRight(300, 0,4);
    	
    	
    	flyH[0].moveLeftRight(0, 1820,11);
		flyH[1].moveLeftRight(1820, 0,11);
		flyH[2].moveLeftRight(0, 1820,11);
		flyH[3].moveLeftRight(1820, 0,11);
		
		blade.moveUpDown(190, 4);
		
		for(int i = 0; i < flyV.length; i++ )
		{
			flyV[i].moveUpDown(445, 6 );
		//	if(link.overlaps(flyV[i])) link.health--;
		}
		
	//	zora.moveTowardsPlayer(link, 50);
		
		
		if(fire) {
		for (int i = 0; i < ball.length; i++) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastShootTimes[i] >= shootDelay) {
                ball[i].shootPastPlayer(link, 20);
                lastShootTimes[i] = currentTime;
            }
		}
		}
    	
	//	fly.moveUpDown(500,6);
		//armos.chase(link, 2);
		// Apply Physics
		
		link.move();
		swordUp.move();
		swordDN.move();
		swordLT.move();
		swordRT.move();
		//fly.move();
		armos.move();
		armos1.move();
		zora.move();
		
		blade.move();
		for(int i = 0; i < oct.length; i++ )
		{
		   oct[i].move();
		   
		}
		
		for(int i = 0; i < flyH.length; i++ )
		{
		   flyH[i].move();
		   if(link.overlaps(flyH[i])) link.health = link.health - damage;
		}
		
		
		for(int i = 0; i < flyV.length; i++ )
		{
		   flyV[i].move();
		   if(link.overlaps(flyV[i])) link.health = link.health - damage;
		}
		
		for(int i = 0; i < ball.length; i++ )
		{
		   ball[i].move();
		}
		
		
		if (pressing[RT] || pressing[LT] || pressing[DN] || pressing[UP]) //shoot keys
        {
        	if (!shotCooldown.isRunning()) 				//if you aren't on cooldown
        	{
                fireBullet(link); 							//shoots a bullet instantly
                shotCooldown.start(); 					//cooldown activates before next bullet can shoot
        	}
        }
		
        for (int k = 0; k < bullet.length; k++) if (bullet[k] != null) bullet[k].move();
        
        
        //changing items when walking into different cave entrances
        
        if(link.overlaps(sword) && wall0) hasSword = true;
        if(link.overlaps(bow) && wall7)   hasBow = true;
        if(link.overlaps(key) && wall12)  hasKey = true;
        if(link.overlaps(shield) && wall15) {
        	hasShield = true;
        	damage = 5;
        	health.setColor(Color.blue);
        }
        
        if(link.overlaps(heart)) {
        	hasHeart = true; 
        	link.health = 500;
        }
        
        if(link.overlaps(potion)) {
        	hasPotion = true; 
        	link.health = 350;
        }
        
		
		dragon.goUP(0);
		dragon.move();
		
		
		// know when attacking
		if(pressing[SPACE]) {
			attacking = true;
			
		}
		else attacking = false;
		
		
		// code for when sword collides with ai
		 for(int i = 0; i < oct.length; i++ )
			{
			   if((((swordUp.overlaps(oct[i]) && facingUP) || (swordDN.overlaps(oct[i]) && facingDN) ||
				(swordLT.overlaps(oct[i]) && facingLT) || (swordRT.overlaps(oct[i]) && facingRT))) && hit == false && attacking) {
				   
				   oct[i].health = oct[i].health - link.attack;
				   link.attack = 0;
				   //hit = true;
				   System.out.print(oct[i].health);
			   }
			   
			   
			
			   if(oct[i].health == 0) octDead[i] = true;
			 
			   
			  if(link.overlaps(oct[i]) && !octDead[i] )link.health = link.health - damage;
				  
				  

			}
		 
		
		 
		 if(((swordUp.overlaps(dragon)  || swordDN.overlaps(dragon) || swordLT.overlaps(dragon) || swordRT.overlaps(dragon))) && attacking) {
			 dragon.health = dragon.health - link.attack;
			link.attack = 0;
			 System.out.println(dragon.health);
		 }
		 
		 else if((((swordUp.overlaps(armos) && facingUP)  || (swordDN.overlaps(armos) && facingDN) ||
			       (swordLT.overlaps(armos) && facingLT) || (swordRT.overlaps(armos) && facingRT)) && attacking)) {
			 
			 	armos.health = armos.health - link.attack;
			 	link.attack = 0;
			 	System.out.println(armos.health);
		 }
		 
		 else if((((swordUp.overlaps(armos1) && facingUP)  || (swordDN.overlaps(armos1) && facingDN) ||
				   (swordLT.overlaps(armos1) && facingLT) || (swordRT.overlaps(armos1) && facingRT)) && attacking)) {
					 
				armos1.health = armos1.health - link.attack;
				link.attack = 0;
				System.out.println(armos1.health);
		}
		 
		 else link.attack = 1;
		 
		 // when ai dies
		 if(dragon.health <= 0) dragonDead = true;
		 if(armos.health <= 0) armosDead = true;
		 if(armos1.health <= 0) armos1Dead = true;
		 
		 if(link.overlaps(armos) && !armosDead) link.health = link.health - damage;
		 if(link.overlaps(armos) && !armosDead) link.health = link.health - damage;
		 
		 if(link.overlaps(blade) && dragon.health > 0) link.health = link.health - damage;
		 if(link.overlaps(dragon) && dragon.health > 0) link.health = link.health - damage;
		 
		 for(int i = 0; i < ball.length; i++) {
			 if(link.overlaps(ball[i]) && dragon.health > 0) link.health = link.health - damage;
		 }
		 
		 if(link.overlaps(dragon)  && dragon.health > 0) link.pushedOutOf(dragon);
		 if(link.overlaps(blade) && dragon.health > 0) link.pushedOutOf(blade);
		 
		 health.w = link.health;
		//System.out.println(link.health);
       
	}
	
	public void paint(Graphics pen)
	{
	   
		pen.drawImage(backgroundImage, -7680 - Camera.x, -1080 - Camera.y, 11520, 2160, this);
		
		for(int i = 22; i < wall.length; i++ )
		{
		  // wall[i].draw(pen);
		   
		}
		
		
		for(int i = 0; i < oct.length; i++ )
		{
			if(oct[i].health > 0)
		   oct[i].draw(pen);
		   
		}
		
		for(int i = 0; i < flyH.length; i++ )
		{
		   flyH[i].draw(pen);
		}
		
		
		
		for(int i = 0; i < flyV.length; i++ )
		{
		   flyV[i].draw(pen);
		}
		
		for(int i = 0; i < ball.length; i++ )
		{
		   if(dragon.health > 0)ball[i].draw(pen);
		}
		
		if(wall0 == true && hasSword == false) sword.draw(pen);
		if(wall7 == true && hasBow == false) bow.draw(pen);
		if(wall12 == true && hasKey == false) key.draw(pen);
		if(wall15 == true && hasShield == false) shield.draw(pen);
		
		if(link.health > 0)link.draw(pen);
		
		
	if(hasSword) {
	
	    if(facingUP && pressing[SPACE]) {
	    	swordUp.draw(pen);
	    }
	    if(facingDN && pressing[SPACE]) {
	    	swordDN.draw(pen);
	    }
	    if(facingLT && pressing[SPACE]) {
	    	swordLT.draw(pen);
	    }
	    if(facingRT && pressing[SPACE]) {
	    	swordRT.draw(pen);
	    }
	}
	    
		if(dragon.health > 0)blade.draw(pen);
	    if(armos.health > 0) armos.draw(pen);
	    if(armos1.health > 0) armos1.draw(pen);
	    
	  //  fly.draw(pen);
	    zora.draw(pen);
	    for (int j = 0; j < bullet.length; j++) if (bullet[j] != null) bullet[j].draw(pen);
	    
	    if(dragon.health > 0) dragon.draw(pen);
	    
	    
	    // change the animation of the dragon when it collides with sword
	    if(((swordUp.overlaps(dragon) || swordDN.overlaps(dragon) || swordLT.overlaps(dragon) || swordRT.overlaps(dragon))) && hasSword&& attacking && dragon.health >0)
	    hDragon.draw(pen);
	    
	    
	    bar.draw(pen);
	    health.draw(pen);
	    if(!hasHeart) heart.draw(pen);
	    if(!hasPotion) potion.draw(pen);
	    
	    portal.draw(pen);
	    
	   
		  
	}
	
	
	//code from group project to shoot a bullet
	private void fireBullet(Rect r) 
	{
		if(hasBow) {
		
		if (i >= bullet.length) i = 0;
		
		if (pressing[RT]) 
        {
            bullet[i] = new Rock((int) r.x + (int)r.w, (int) r.y + (int)(r.h * .5 - bH * .5), bW, bH);
            bullet[i].setVelocity(bulletVelocity, (int) (r.vy * 0.8));
        }

        if (pressing[LT]) 
        {
            bullet[i] = new Rock((int) r.x - bW, (int) r.y + (int)(r.h * .5 - bH * .5), bW, bH);
            bullet[i].setVelocity(-bulletVelocity, (int) (r.vy * 0.8));
        }

        if (pressing[DN]) 
        {
            bullet[i] = new Rock((int) r.x + (int)(r.w * .5 - bW * .5), (int) r.y + (int)r.h, bW, bH);
            bullet[i].setVelocity((int) (r.vx * 0.8), bulletVelocity);
        }

        if (pressing[UP]) 
        {
            bullet[i] = new Rock((int) r.x + (int)(r.w * .5 - bW * .5), (int) r.y - bH, bW, bH);
            bullet[i].setVelocity((int) (r.vx * 0.8), -bulletVelocity);
        }
        i++;
    }
	}
	
	// help move camera around when editing the map
	public void keyPressed(KeyEvent e)
	{		
		pressing[e.getKeyCode()] = true;
		
		int code = e.getExtendedKeyCode();
	/*
		if(code == UP) Camera.moveUp(1080); 
		if(code == DN) Camera.moveDown(1080);
		if(code == RT) Camera.moveRight(1920);
		if(code == LT) Camera.moveLeft(1920);
		repaint();
*/
	}
	
	
	//for moving around walls 
	public void mouseDragged(MouseEvent e)
	{
		int nx = e.getX()+ Camera.x;
		int ny = e.getY() + Camera.y;
		
		int dx = nx - mx;
		int dy = ny - my;
		
		
		for(int i = 0; i < wall.length; i++)
		{
			if(wall[i].resizer.held)
			{
				wall[i].resizeBy(dx, dy);
			}
			else
			if(wall[i].held)
			{
				wall[i].moveBy(dx, dy);
			}
		}
		
		mx = nx;
		my = ny;	
		
		
	}
	
	
	public void mousePressed(MouseEvent e)
	{
		mx = e.getX() + Camera.x;
		my = e.getY() + Camera.y;
		
		for(int i = 0; i < wall.length; i++)
		{
			if(wall[i].contains(mx, my))
			{
				wall[i].grab();
			}
			
			if(wall[i].resizer.contains(mx,  my))
			{
				wall[i].resizer.grab();
			}
		}
		
		System.out.println(mx + "," + my);
		
	}
	
	public void mouseReleased(MouseEvent e)
	{
		
		for(int i = 0; i < wall.length; i++)
		{
			wall[i].drop();
			wall[i].resizer.drop();
		}	
	}
	
	
	// for saving location of walls
	public void keyTyped(KeyEvent e)
	{
		for(int i = 0; i < wall.length; i++)
		{
			//System.out.println(wall[i].toString());
		}	
	}

}