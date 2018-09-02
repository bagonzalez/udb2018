package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import math.Vector2D;

public class Laser extends  MovingObject{

	public Laser(Vector2D posicion, Vector2D velocidad, double maxVel, double angulo, BufferedImage textura) {
		super(posicion, velocidad, maxVel, textura);
		this.angulo = angulo;
		this.velocidad = velocidad.escala(maxVel);
	}

	@Override
	public void update() {
		posicion = posicion.add(velocidad);
		
	}

	@Override
	public void draw(Graphics g) {
	Graphics2D g2d = (Graphics2D)g;
		
	at = AffineTransform.getTranslateInstance(posicion.getX() - width/2, posicion.getY());
	
	at.rotate(angulo, width/2, 0);
	
	g2d.drawImage(textura,at,null);
	} 

}
