package estados;

import java.awt.Graphics;

import gameObjects.Player;
import graficos.Assets;
import math.Vector2D;

public class GameState {
	
	private Player player;
	
	public GameState()
	{
		player = new Player(new Vector2D(100, 500), new Vector2D(),7, Assets.jugador);
	}

	public void update()
	{
		player.update();
	}
	
	public void draw(Graphics g)
	{
		player.draw(g);
	}
}
