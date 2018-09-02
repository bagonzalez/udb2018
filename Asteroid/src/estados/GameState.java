package estados;

import java.awt.Graphics;
import java.util.ArrayList;

import gameObjects.MovingObject;
import gameObjects.Player;
import graficos.Assets;
import math.Vector2D;

public class GameState {
	
	private Player player;
	private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();

	
	public GameState()
	{
		player = new Player(new Vector2D(400, 300), new Vector2D(), 7, Assets.jugador,this);
		movingObjects.add(player);
		
	}
	
	public void update()
	{
		for(int i = 0; i < movingObjects.size(); i++)
			movingObjects.get(i).update();
	}
	
	public void draw(Graphics g)
	{
		for(int i = 0; i < movingObjects.size(); i++)
			movingObjects.get(i).draw(g);
	}

	public ArrayList<MovingObject> getMovingObjects() {
		return movingObjects;
	}
	
	
	
}