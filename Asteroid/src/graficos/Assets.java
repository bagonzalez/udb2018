package graficos;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage jugador;
	
	public static void init()
	{
		jugador = Loader.ImageLoader("/ships/player.png");
		
	}
}
