package main;
import javax.swing.JFrame;

public class Ventana extends JFrame{
	
	public static final int WIDTH = 800, HEIGHT = 600;
	
	public Ventana()
	{
		setTitle("Asteroid");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar la ventana
		setResizable(false); //redimencionar ventana
		setLocationRelativeTo(null); // despliega la ventana al centro de la pantalla
		setVisible(true); //visualizar ventana
	}

	public static void main(String[] args) {
		new Ventana();
	}

}
