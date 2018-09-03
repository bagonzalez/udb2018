package graficos;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage player;
	
	// efectos
	
	public static BufferedImage speed;
	
	// explosion
	
	public static BufferedImage[] exp = new BufferedImage[9];
	
	// lasers
	
	public static BufferedImage blueLaser, greenLaser, redLaser;
	
	// Meteoritos
	
	public static BufferedImage[] bigs = new BufferedImage[4];
	public static BufferedImage[] meds = new BufferedImage[2];
	public static BufferedImage[] smalls = new BufferedImage[2];
	public static BufferedImage[] tinies = new BufferedImage[2];
	
	// ufo
	
	public static BufferedImage ufo;
	
	// numeros
	
	public static BufferedImage[] numbers = new BufferedImage[11];
	
	public static BufferedImage life;
	
	public static void init()
	{
		player = Loader.ImageLoader("/ships/player.png");
		
		speed = Loader.ImageLoader("/effects/fire08.png");
		
		blueLaser = Loader.ImageLoader("/lasers/laserBlue01.png");
		
		greenLaser = Loader.ImageLoader("/lasers/laserGreen11.png");
		
		redLaser = Loader.ImageLoader("/lasers/laserRed01.png");
		
		ufo = Loader.ImageLoader("/ships/ufo.png");
		
		life = Loader.ImageLoader("/others/life.png");
		
		for(int i = 0; i < bigs.length; i++)
			bigs[i] = Loader.ImageLoader("/meteors/big"+(i+1)+".png");
		
		for(int i = 0; i < meds.length; i++)
			meds[i] = Loader.ImageLoader("/meteors/med"+(i+1)+".png");
		
		for(int i = 0; i < smalls.length; i++)
			smalls[i] = Loader.ImageLoader("/meteors/small"+(i+1)+".png");
		
		for(int i = 0; i < tinies.length; i++)
			tinies[i] = Loader.ImageLoader("/meteors/tiny"+(i+1)+".png");
		
		for(int i = 0; i < exp.length; i++)
			exp[i] = Loader.ImageLoader("/explosion/"+i+".png");
		
		for(int i = 0; i < numbers.length; i++)
			numbers[i] = Loader.ImageLoader("/numbers/"+i+".png");
		
	}
	
}
