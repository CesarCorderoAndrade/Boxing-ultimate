package trabajoT12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class pantallaFinal extends JPanel {
    private static final long serialVersionUID = 1L;
    public static boolean playerWon = false;

    private Image imgAzul;
    private Image imgRojo;
    private Ventana ventana;

    private ImageIcon escalarIcono(String ruta, int ancho, int alto) {
        ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
        Image img = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_FAST);
        return new ImageIcon(img);
    }

    public pantallaFinal(Ventana v) {
        this.ventana = v;
        setOpaque(false);
        setLayout(null);

        imgAzul = escalarIcono("/res/victoriaAzul.png", 800, 600).getImage();
        imgRojo = escalarIcono("/res/victoriaRojo.png", 800, 600).getImage();

        // Image buttons
        ImageIcon iconReiniciar = escalarIcono("/res/btnReiniciar.png", 300, 250);
        ImageIcon iconSalir = escalarIcono("/res/btnSalir.png", 300, 250);

        JButton btnReiniciar = new JButton(iconReiniciar);
        JButton btnSalir = new JButton(iconSalir);

    // Imagenes de los botones
    for (JButton b : new JButton[] { btnReiniciar, btnSalir }) {
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
        b.setOpaque(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    btnReiniciar.setBounds(50, 350, 300, 250);
    btnSalir.setBounds(430, 350, 300, 250);

    btnReiniciar.addActionListener(e -> {
        if (ventana != null) ventana.reiniciarYMostrarJuego();
    });

    btnSalir.addActionListener(e -> {
        System.exit(0);
    });

    add(btnReiniciar);
    add(btnSalir);
}

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Image img = playerWon ? imgAzul : imgRojo;
    if (img != null) {
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    } else {
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.setColor(playerWon ? Color.BLUE : Color.RED);
        g.drawString(
            playerWon ? "VICTORY" : "DEFEAT",
            getWidth() / 2 - 120,
            getHeight() / 2
        );
    }
}
}
