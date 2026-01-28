package trabajoT12;

import javax.swing.*;
import java.awt.*;

public class panelFondo extends JPanel {
	
	private Image imagen;
	
	public panelFondo(String ruta) {
		imagen = new ImageIcon(getClass().getResource(ruta)).getImage();
		setLayout(new CardLayout());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imagen,0, 0,getWidth(),getHeight(),this);
	}
}
