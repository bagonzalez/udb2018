package gameObjects;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import math.Vector2D;

public abstract class MovingObject extends GameObject{

	
	protected Vector2D velocidad;
	protected AffineTransform at;//rotacion
	protected double angulo;
	protected double maxVel;
	protected int width;
	protected int height;
		
	public MovingObject(Vector2D posicion,Vector2D velocidad,double maxVel, BufferedImage textura) {
		super(posicion, textura);
		this.velocidad = velocidad;
		this.maxVel = maxVel;
		width = textura.getWidth();
		height = textura.getHeight();
		angulo = 0;
	}

}
