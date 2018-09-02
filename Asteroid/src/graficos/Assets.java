package graficos;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage jugador;
	
	//efectos
	
	public static BufferedImage speed;
	
	public static void init()
	{
		jugador = Loader.ImageLoader("/ships/player.png");
		
		speed = Loader.ImageLoader("/effects/fire08.png");
		
	}
}
