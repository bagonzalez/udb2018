package main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Ventana extends JFrame implements Runnable{
	
	public static final int WIDTH = 800, HEIGHT = 600;
	private Canvas canvas;
	private Thread thread;
	private boolean running=false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private final int FPS = 60;
	private double TARGETTIME = 1000000000/FPS; //1 segundo en nano segundo
	private double delta = 0;
	private int AVERAGEFPS = FPS; //mostrara a cuantos FPS corre el juego
	
	public Ventana()
	{
		setTitle("Asteroid");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar la ventana
		setResizable(false); //redimencionar ventana
		setLocationRelativeTo(null); // despliega la ventana al centro de la pantalla
		setVisible(true); //visualizar ventana
	
		canvas  = new Canvas();
		
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setFocusable(true);
		
		add(canvas);
	}

	public static void main(String[] args) {
		new Ventana().start();
	}

	int x = 0;
	private void actualizar() {
		x++;
	}
	
	private void dibujar() {
		bs = canvas.getBufferStrategy();
		
		if(bs == null)
		{
			canvas.createBufferStrategy(3);//3 es el numero de buffers
			return;
		}
		
		g = bs.getDrawGraphics();
		
		//-------iniciaDibujo------------
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.BLACK);
		
		g.drawString(""+AVERAGEFPS,10,10);
		
		//-------terminaDibujo-----------
		
		g.dispose();
		bs.show();
	}
	
	@Override
	public void run() {
		
		long now = 0;
		long lastTime = System.nanoTime();
		int frames = 0;
		long time = 0;
		
		//while se encargara de la posicion de los objetos
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime)/TARGETTIME;
			time += (now - lastTime);
			lastTime = now;
			
			if(delta >= 1)
			{
				actualizar();
				dibujar();
				delta --;
				frames ++;
				//System.out.println(frames); //ver FPS en consola
			}
			
			if(time >= 1000000000)
			{
				AVERAGEFPS = frames;
				frames = 0;
				time = 0;
				
			}
			
		}
		
		stop();
	}
	
	private void start() {
		thread = new Thread(this);
		thread.start(); //llama metodo Run()
		running = true;
	}
	
	private void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
