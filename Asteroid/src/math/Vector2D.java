package math;

public class Vector2D {
	
	private double x,y;
	
	public Vector2D(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector2D()
	{
		x = 0;
		y = 0;
	}
	
	public Vector2D add(Vector2D v)
	{
		return new Vector2D(x + v.getX(), y + v.getY());
	}
	
	public Vector2D escala(double value)
	{
		return new Vector2D(x*value, y*value);
	}
	
	public Vector2D limite(double value)
	{
		if(getMagnitude() > value)
		{
			return this.normalize().escala(value);
		}
		return this;
	}
	
	public Vector2D normalize()
	{
		return  new Vector2D(x/getMagnitude(), y/getMagnitude());
	}

	public double getMagnitude()
	{
		return Math.sqrt(x*x + y*y);
		
	}
	
	public Vector2D setDirection(Double angulo)
	{
		return new Vector2D(Math.cos(angulo)*getMagnitude(), Math.sin(angulo)*getMagnitude());
		
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
