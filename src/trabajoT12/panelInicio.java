package trabajoT12;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class panelInicio extends JPanel{
	
	private ImageIcon escalarIcono(String ruta, int ancho, int alto) {
	    ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
	    Image img = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_FAST);
	    return new ImageIcon(img);
	}
	public panelInicio(Ventana v) {
		setOpaque(false);
		setLayout(null);
		JLabel btnComienzo = new JLabel(escalarIcono("/res/btnComenzar.png", 300, 225)
);
		btnComienzo.setBounds(300, 400, 200, 70);
		btnComienzo.setCursor(new Cursor(Cursor.HAND_CURSOR));

		btnComienzo.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        v.mostrar("historia"); // o "pelea"
		    }
		});
		
		add(btnComienzo);
		
		JLabel portada = new JLabel(escalarIcono("/res/portada.png",500,300));
		
		portada.setBounds(200,00,400,300);
		add(portada);
		
		JLabel btnSalir = new JLabel(escalarIcono("/res/btnSalir.png", 275, 225)
				);
						btnSalir.setBounds(300, 470, 200, 70);
						btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));

						btnSalir.addMouseListener(new MouseAdapter() {
						    @Override
						    public void mouseClicked(MouseEvent e) {
						    	System.exit(0); // 
						    }
						});
						//btnComienzo.addActionListener(e -> v.mostrar("historia"));
						add(btnSalir);
	}
}
