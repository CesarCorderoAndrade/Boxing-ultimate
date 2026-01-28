package trabajoT12;

import java.awt.CardLayout;
import javax.swing.JFrame;

public class Ventana extends JFrame {

    CardLayout cardLayout;
    panelFondo contenedorFondo;
    PanelJuego panelJuego;

    public Ventana() {
        setTitle("Boxing");
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        contenedorFondo = new panelFondo("/res/ringOficial.png");
        cardLayout = (CardLayout) contenedorFondo.getLayout();

        panelInicio inicio = new panelInicio(this);
        panelHistoria historia = new panelHistoria(this);
        panelJuego = new PanelJuego(this);
        panelPausa resultado = new panelPausa(this);
        // Create pantallaFinal passing this Ventana so it can call reiniciarYMostrarJuego()
        pantallaFinal finalPanel = new pantallaFinal(this);

        contenedorFondo.add(inicio,"Inicio");
        contenedorFondo.add(historia,"historia");
        contenedorFondo.add(panelJuego,"pelea");
        contenedorFondo.add(resultado,"resultado");
        contenedorFondo.add(finalPanel,"pantallaFinal");

        add(contenedorFondo);
        setVisible(true);
    }

    public void mostrar(String nombre) {
        cardLayout.show(contenedorFondo, nombre);
        if (nombre.equals("pelea")) {
            panelJuego.requestFocusInWindow();
            panelJuego.timer.start();
        } else {
            // ensure the game timer is stopped when leaving the fight screen
            if (panelJuego != null) {
                panelJuego.timer.stop();
            }
        }
    }

    public void volverAlInicio() {
        panelJuego.reset();
        mostrar("Inicio");
    }

    // Called from pantallaFinal's Reiniciar button
    public void reiniciarYMostrarJuego() {
        if (panelJuego != null) {
            panelJuego.reset();
        }
        mostrar("pelea");
    }

}
