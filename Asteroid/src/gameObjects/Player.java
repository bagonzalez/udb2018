package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import estados.GameState;
import graficos.Assets;
import input.KeyBoard;
import main.Ventana;
import math.Vector2D;

public class Player extends MovingObject{

	private Vector2D heading; //hacia donde ve la nave
	private Vector2D aceleracion; 
	private final double ACC = 0.2; //cuanto acelera la nave
	private final double DELTAANGLE = 0.1;
	private boolean acelerando = false;
	private GameState gameState;	
	
	private long time, lastTime;
	
	public Player(Vector2D posicion, Vector2D velocidad, double maxVel, BufferedImage textura, GameState gameState) {
		super(posicion, velocidad, maxVel, textura);
		this.gameState = gameState;
		heading = new Vector2D(0, 1);
		aceleracion = new Vector2D();
		time = 0;
		lastTime = System.currentTimeMillis();
	}

	
	@Override
	public void update() 
	{
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(KeyBoard.SHOOT && time > 200)
		{
			gameState.getMovingObjects().add(0,new Laser(
					getCenter().add(heading.escala(width)),
					heading,
					10,
					angulo,
					Assets.redLaser
					));
			time = 0;
		}
		
		
		if(KeyBoard.RIGHT)
			angulo += DELTAANGLE;
		if(KeyBoard.LEFT)
			angulo -= DELTAANGLE;
		
		if(KeyBoard.UP)
		{
			aceleracion = heading.escala(ACC);
			acelerando = true;

		}else
		{
			if(velocidad.getMagnitude() != 0)
				aceleracion = (velocidad.escala(-1).normalize()).escala(ACC/2);
			acelerando = false;
		}
		
		velocidad = velocidad.add(aceleracion);
		
		velocidad = velocidad.limite(maxVel);
		
		heading = heading.setDirection(angulo - Math.PI/2);
	
		posicion = posicion.add(velocidad);
		
		if (posicion.getX() > Ventana.WIDTH)
		posicion.setX(0);
		if (posicion.getY() > Ventana.HEIGHT)
			posicion.setY(0);
		
		if(posicion.getX() < 0)
			posicion.setX(Ventana.WIDTH);
		if(posicion.getY() < 0)
			posicion.setY(Ventana.HEIGHT);
	}

	@Override
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		AffineTransform at1 = AffineTransform.getTranslateInstance(posicion.getX() + width/2 + 5, 
				posicion.getY() + height/2 + 10);
		AffineTransform at2 = AffineTransform.getTranslateInstance(posicion.getX() + 5, 
				posicion.getY() + height/2 + 10);

		at1.rotate(angulo, -5, -10);
		at2.rotate(angulo, width/2 - 5, -10);
		
		if(acelerando)
		{
			
			g2d.drawImage(Assets.speed, at1, null);
			g2d.drawImage(Assets.speed, at2, null);
			
		}
		
		
		at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
		
		at.rotate(angulo, width/2, height/2);
		
		g2d.drawImage(Assets.jugador, at, null);
	}
	
	public Vector2D getCenter(){
		return new Vector2D(posicion.getX() + width/2, posicion.getY() + height/2);

	}
}