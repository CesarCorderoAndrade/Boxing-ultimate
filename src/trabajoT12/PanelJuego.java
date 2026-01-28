package trabajoT12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class PanelJuego extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    Jugador jugador = new Jugador(100, 200);
    EnemigoIA enemigo = new EnemigoIA(500, 200);
    Timer timer;
    private Ventana ventana;
    private boolean gameOverTriggered = false;

    private ImageIcon escalarIcono(String ruta, int ancho, int alto) {
        ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
        Image img = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_FAST);
        return new ImageIcon(img);
    }

    public PanelJuego(Ventana v) {
        this.ventana = v;
        setOpaque(false);
        setLayout(null);
        timer = new Timer(20, this);

        setFocusable(true);
        configurarTeclado();

        JLabel btnPausa = new JLabel(escalarIcono("/res/btnPausa.png", 200, 150));
        btnPausa.setBounds(295, 10, 200, 50);
        btnPausa.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnPausa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                v.mostrar("resultado");
            }
        });
        add(btnPausa);
    }

    private void configurarTeclado() {
        InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        im.put(KeyStroke.getKeyStroke("LEFT"), "izquierda");
        im.put(KeyStroke.getKeyStroke("RIGHT"), "derecha");
        im.put(KeyStroke.getKeyStroke("SPACE"), "atacar");

        am.put("izquierda", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                jugador.mover(-10);
                limitarDentroPantalla(jugador);
            }
        });
        am.put("derecha", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                jugador.mover(10);
                limitarDentroPantalla(jugador);
            }
        });
        am.put("atacar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                jugador.atacar();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jugador.actualizar();
        enemigo.pensar(jugador);

        // Limitar posiciones después de actualizar/pensar
        limitarDentroPantalla(jugador);
        limitarDentroPantalla(enemigo);

        verificarColisiones();

        if (!gameOverTriggered && (enemigo.vida <= 0 || jugador.vida <= 0)) {
            gameOverTriggered = true;

            pantallaFinal.playerWon = (enemigo.vida <= 0);

            if (enemigo.vida <= 0) {
                jugador.victoria = true;
                enemigo.atacando = false;
            } else {
                enemigo.victoria = true;
                jugador.atacando = false;
            }

            // ⏱️ Esperar 3 segundos y cambiar de pantalla
            Timer t = new Timer(2500, ev -> {
                ventana.mostrar("pantallaFinal");
            });
            t.setRepeats(false);
            t.start();
        }

        repaint();
    }

    private void verificarColisiones() {
        int empujon = 50;

        if (jugador.atacando && jugador.contadorAtaque == 5) {
            if (jugador.getHitbox().intersects(enemigo.getHurtbox())) {
                enemigo.recibirDanio(10);
            }
        }

        if (enemigo.atacando && enemigo.contadorAtaque == 5) {
            if (enemigo.getHitbox().intersects(jugador.getHurtbox())) {
                jugador.recibirDanio(15);
            }
        }

        if (jugador.atacando && jugador.contadorAtaque == 5) {
            if (jugador.getHitbox().intersects(enemigo.getHurtbox())) {
                enemigo.recibirDanio(5);
                if (enemigo.vida <= 0) {
                    if (jugador.x < enemigo.x) {
                        jugador.x -= empujon;
                    } else {
                        jugador.x += empujon;
                    }
                    enemigo.x += 50;
                    jugador.x += 50;
                }
            }
        }

        // Asegurar que tras empujones o movimientos forzados no salgan de la pantalla
        limitarDentroPantalla(jugador);
        limitarDentroPantalla(enemigo);
    }

    /**
     * Limita la entidad dentro de los bordes del panel.
     * Usa getWidth() / getHeight() para calcular el máximo permitido.
     */
    private void limitarDentroPantalla(Entidad e) {
        if (e == null) return;
        int minX = 0;
        int maxX = Math.max(0, getWidth() - e.ancho);
        e.x = Math.max(minX, Math.min(e.x, maxX));

        int minY = 0;
        int maxY = Math.max(0, getHeight() - e.alto);
        e.y = Math.max(minY, Math.min(e.y, maxY));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        dibujarEntidad(g, jugador);
        dibujarEntidad(g, enemigo);
        dibujarBarrasVida(g);

        if (enemigo.vida <= 0 || jugador.vida <= 0) {
            g.setFont(new Font("Arial", Font.BOLD, 48));
            if (enemigo.vida <= 0) {
                g.setColor(Color.BLACK);
                g.drawString("¡KO!", 348, 203);
                g.setColor(Color.GREEN);
                g.drawString("¡KO!", 345, 200);

                
            } else {
                g.setColor(Color.BLACK);
                g.drawString("¡KO!", 348, 203);
                g.setColor(Color.RED);
                g.drawString("¡KO!", 345, 200);

            }
        }
    }

    private void dibujarEntidad(Graphics g, Entidad e) {
        BufferedImage sprite = e.getSpriteActual();
        if (sprite != null) {
            if (e.direccion == -1) {
                g.drawImage(sprite, e.x + e.ancho, e.y, -e.ancho, e.alto, null);
            } else {
                g.drawImage(sprite, e.x, e.y, e.ancho, e.alto, null);
            }
        } else {
            g.setColor(Color.PINK);
            g.fillRect(e.x, e.y, e.ancho, e.alto);
        }
    }

    private void dibujarBarrasVida(Graphics g) {
        g.setColor(Color.WHITE); g.fillRect(18, 18, 204, 24);
        g.setColor(Color.GRAY);  g.fillRect(20, 20, 200, 20);
        g.setColor(Color.GREEN); g.fillRect(20, 20, jugador.vida * 2, 20);
        g.setColor(Color.BLACK); g.drawString("JUGADOR", 20, 15);

        g.setColor(Color.WHITE); g.fillRect(558, 18, 204, 24);
        g.setColor(Color.GRAY);  g.fillRect(560, 20, 200, 20);
        g.setColor(Color.RED);   g.fillRect(560, 20, enemigo.vida * 2, 20);
        g.setColor(Color.BLACK); g.drawString("CPU", 450, 15);
    }

    public void reset() {
        timer.stop();
        jugador = new Jugador(100, 200);
        enemigo = new EnemigoIA(500, 200);
        gameOverTriggered = false;
        // Asegurar posiciones iniciales dentro del panel
        limitarDentroPantalla(jugador);
        limitarDentroPantalla(enemigo);
        timer.start();
        repaint();
    }
}