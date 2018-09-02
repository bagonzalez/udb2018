package gameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import input.KeyBoard;
import math.Vector2D;

public class Player extends GameObject{

	public Player(Vector2D posicion, BufferedImage textura) {
		super(posicion, textura);
		
	}

	@Override
	public void update() {
	
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(textura, (int)posicion.getX(), (int)posicion.getY(), null);		
	}
	
	

}
