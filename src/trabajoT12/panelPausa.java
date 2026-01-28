package trabajoT12;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class panelPausa extends JPanel {
	private Image fondo;
	private PanelJuego panelJuego;
	
	private ImageIcon escalarIcono(String ruta, int ancho, int alto) {
	    ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
	    Image img = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_FAST);
	    return new ImageIcon(img);
	}
	
    public panelPausa(Ventana v) {
    	setOpaque(false);
    	setLayout(null);

    	fondo = new ImageIcon(getClass().getResource("/res/fondoPausa.png")).getImage();

    	JLabel btnContinuar = new JLabel(escalarIcono("/res/btnContinuar.png",300, 250));
        btnContinuar.setBounds(150, 400, 200, 70);
        btnContinuar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnContinuar.addMouseListener(new MouseAdapter() {
        		@Override
        		public void mouseClicked(MouseEvent e) {
        			v.mostrar("pelea"); 
        				    }
        				});
        
        JLabel btnReinicio = new JLabel(escalarIcono("/res/btnReiniciar.png",300, 250));
        btnReinicio.setBounds(150, 480, 200, 70);
        btnReinicio.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnReinicio.addMouseListener(new MouseAdapter() {
        		@Override
        		public void mouseClicked(MouseEvent e) {
        			v.volverAlInicio();
        			v.mostrar("pelea");
        				    }
        				});
        JLabel btnInicio = new JLabel(escalarIcono("/res/btnInicio.png",300, 250));
        btnInicio.setBounds(435, 400, 200, 70);
        btnInicio.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnInicio.addMouseListener(new MouseAdapter() {
        		@Override
        		public void mouseClicked(MouseEvent e) {
        			v.volverAlInicio();
        			v.mostrar("Inicio");
        				    }
        				});
        JLabel btnSalir = new JLabel(escalarIcono("/res/btnSalir.png", 300, 250)
				);
						btnSalir.setBounds(435, 480, 200, 70);
						btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));

						btnSalir.addMouseListener(new MouseAdapter() {
						    @Override
						    public void mouseClicked(MouseEvent e) {
						    	System.exit(0); // 
						    }
						});
						//btnComienzo.addActionListener(e -> v.mostrar("historia"));
						
       add(btnInicio);
       add(btnContinuar);
       add(btnSalir);
       add(btnReinicio);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    
}
