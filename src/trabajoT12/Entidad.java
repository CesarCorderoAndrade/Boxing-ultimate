package trabajoT12;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public abstract class Entidad {
    protected int x, y, ancho, alto;
    protected int vida = 100;
    protected boolean atacando = false;
    protected boolean muerto = false; 
    protected boolean victoria = false;
    protected int contadorAtaque = 0;
    protected int contadorMuerte = 0; 
    public int direccion = 1; 
    protected float ESCALA = 2.0f;

    
    protected BufferedImage imgIdle, imgAttack, imgKO, imgWin;
    
    protected int anchoIdle, anchoAttack, anchoKO, anchoWin;
   
    protected int altoSprite; 

    public Entidad(int x, int y, int anchoVis, int altoVis) {
        this.x = x;
        this.y = y;
        this.ancho = anchoVis;
        this.alto = altoVis;
    }

    // Cargar las 3 imágenes 
    protected void cargarAnimaciones(String rutaIdle, int wIdle, 
                                     String rutaAtk, int wAtk,
                                     String rutaKO, int wKO, 
                                     String rutaWin,int wWin,
                                     int altoGlobal) {
        this.anchoIdle = wIdle;
        this.anchoAttack = wAtk;
        this.anchoKO = wKO;
        this.anchoWin = wWin;
        this.altoSprite = altoGlobal;
        
        try {
            imgIdle = ImageIO.read(getClass().getResource(rutaIdle));
            imgAttack = ImageIO.read(getClass().getResource(rutaAtk));
            imgKO = ImageIO.read(getClass().getResource(rutaKO));
            imgWin = ImageIO.read(getClass().getResource(rutaWin));
        } catch (Exception e) {
            System.err.println("Error cargando imágenes.");
        }
    }
    
    	// Animaciones
    public BufferedImage getSpriteActual() {    
        
        if (vida <= 0) {
            if (!muerto) { muerto = true; contadorMuerte = 0; }
            return getSpriteAnimado(imgKO, anchoKO, contadorMuerte, 50, false); 
            
        }
        if (victoria) {
           
            int tiempo = (int)(System.currentTimeMillis() / 100);
            return getSpriteAnimado(imgWin, anchoWin, tiempo, 10, true);
        }

        
        if (atacando) {
            return getSpriteAnimado(imgAttack, anchoAttack, contadorAtaque, 5, true);
        }

        
        int tiempo = (int)(System.currentTimeMillis() / 50);
        return getSpriteAnimado(imgIdle, anchoIdle, tiempo, 10, true);
    }

    private BufferedImage getSpriteAnimado(BufferedImage hoja, int pasoCuadricula, int contador, int velocidad, boolean loop) {
        if (hoja == null) return null;

        
        int totalFrames = (hoja.getWidth() + (pasoCuadricula / 2)) / pasoCuadricula;
        if (totalFrames == 0) totalFrames = 1;

        
        int indice = (contador / (velocidad > 0 ? velocidad : 1));
        if (!loop) {
            if (indice >= totalFrames) indice = totalFrames - 1;
        } else {
            indice = indice % totalFrames;
        }

        int desplazamientoInicial = 4; 

       
        int xRecorte = (indice * pasoCuadricula) + desplazamientoInicial;
        
        int anchoRecorte = pasoCuadricula - desplazamientoInicial;

        if (xRecorte + anchoRecorte > hoja.getWidth()) {
            anchoRecorte = hoja.getWidth() - xRecorte; 
        }
        
        if (anchoRecorte <= 0) return hoja.getSubimage(0, 0, 1, 1);

        int alturaReal = Math.min(altoSprite, hoja.getHeight());

        return hoja.getSubimage(xRecorte, 0, anchoRecorte, alturaReal);        
        
    }   
    
    public Rectangle getHurtbox() {
        return new Rectangle(x + 20, y, ancho - 40, alto);
    }

    public Rectangle getHitbox() {
        if (!atacando) return new Rectangle(0, 0, 0, 0);
        int puñoAncho = 60;
        int puñoX = (direccion == 1) ? x + ancho - 20 : x - puñoAncho + 20;
        return new Rectangle(puñoX, y + 40, puñoAncho, 30);
    }

    public void recibirDanio(int cantidad) {
        this.vida -= cantidad;
        if (this.vida < 0) this.vida = 0;
    }
}