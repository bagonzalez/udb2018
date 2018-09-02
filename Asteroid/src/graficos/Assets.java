package graficos;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage jugador;
	
	//efectos
	
	public static BufferedImage speed;
	
	//lasers
	
	public static BufferedImage blueLaser, greenLaser, redLaser;
	
	public static void init()
	{
		jugador = Loader.ImageLoader("/ships/player.png");
		
		speed = Loader.ImageLoader("/effects/fire08.png");
		
		blueLaser = Loader.ImageLoader("/lasers/laserBlue01.png");

		greenLaser = Loader.ImageLoader("/lasers/laserGreen11.png");
		
		redLaser = Loader.ImageLoader("/lasers/laserRed01.png");

	}
}
