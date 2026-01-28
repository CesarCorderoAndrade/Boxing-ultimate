package trabajoT12;

import javax.swing.*;
import java.awt.*;

public class panelHistoria extends JPanel {

    private Image fondo;

    public panelHistoria(Ventana v) {
        setOpaque(false);
        setLayout(null); // Importante

        // Cargar la imagen
        fondo = new ImageIcon(getClass().getResource("/res/pantallaHistoria.png")).getImage();
        
        
        //Texto boxeador
        JTextArea texto = new JTextArea(
            "Espero ganar, \n es de vida o muerte,  \n" +
            "Lo haré por mi familia \n"
        );
        texto.setEditable(false);
        texto.setFont(new Font("Romana", Font.BOLD, 14));
        texto.setForeground(Color.black);
        texto.setMargin(new Insets(10, 10, 10, 10));
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        texto.setOpaque(false);
        texto.setBounds(90, 50, 200, 90);
        
        //Texto entrenador
        JTextArea texto2 = new JTextArea(
			"Como el dijo el tio Ben de Spiderman \n" +
			"Un gran poder conlleva una gran responsabilidad \n"
		);
        texto2.setEditable(false);
        texto2.setFont(new Font("Romana", Font.BOLD, 14));
        texto2.setForeground(Color.black);
        texto2.setMargin(new Insets(10, 10, 10, 10));
        texto2.setLineWrap(true);
        texto2.setWrapStyleWord(true);
        texto2.setOpaque(false);
        texto2.setBounds(448, 50, 200, 90);
        add(texto2);
        
        // Botón para iniciar la pelea
        ImageIcon original = new ImageIcon(getClass().getResource("/res/btnPelear.png"));
        
        Image img = original.getImage().getScaledInstance(400, 252, Image.SCALE_SMOOTH);
        ImageIcon btnIcon = new ImageIcon(img);

        JButton btn = new JButton(btnIcon);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);

        btn.setBounds(198, 295, 400, 252);

        btn.addActionListener(e -> v.mostrar("pelea")
        		);
        

        add(texto);
        add(btn);

    //Botón salir juego
    	ImageIcon originalSalir = new ImageIcon(getClass().getResource("/res/btnSalir.png"));
		
		Image imgSalir = originalSalir.getImage().getScaledInstance(250, 200, Image.SCALE_SMOOTH);
		ImageIcon btnIconSalir = new ImageIcon(imgSalir);

		JButton btnSalir = new JButton(btnIconSalir);
		btnSalir.setBorderPainted(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setFocusPainted(false);
		btnSalir.setOpaque(false);

		btnSalir.setBounds(525, 400, 250, 200);

		btnSalir.addActionListener(e -> System.exit(0));
		
		add(btnSalir);
    }
    

    // Sobreescribir con paintComponent para dibujar la imagen
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
