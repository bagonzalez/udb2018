package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import graficos.Assets;
import input.KeyBoard;
import math.Vector2D;

public class Player extends MovingObject{

	private Vector2D heading; //hacia donde ve la nave
	private Vector2D aceleracion; 
	private final double ACC = 0.2; //cuanto acelera la nave
	private final double DELTAANGLE = 0.1;
	
	public Player(Vector2D posicion, Vector2D velocidad, double maxVel, BufferedImage textura) {
		super(posicion, velocidad, maxVel, textura);
		heading = new Vector2D(0, 1);
		aceleracion = new Vector2D();
	}

	
	@Override
	public void update() 
	{
		
		if(KeyBoard.RIGHT)
			angulo += DELTAANGLE;
		if(KeyBoard.LEFT)
			angulo -= DELTAANGLE;
		
		if(KeyBoard.UP)
		{
			aceleracion = heading.escala(ACC);

		}else
		{
			if(velocidad.getMagnitude() != 0)
				aceleracion = (velocidad.escala(-1).normalize()).escala(ACC/2);
		}
		
		velocidad = velocidad.add(aceleracion);
		
		velocidad.limite(maxVel);
		
		heading = heading.setDirection(angulo - Math.PI/2);
	
		posicion = posicion.add(velocidad);
		
	}

	@Override
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
		
		at.rotate(angulo, Assets.jugador.getWidth()/2, Assets.jugador.getHeight()/2);
		
		g2d.drawImage(Assets.jugador, at, null);
	}
	
	

}
