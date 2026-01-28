package trabajoT12;

public class Jugador extends Entidad {
    public Jugador(int x, int y) {

        super(x, y, 190, 300);
        
        cargarAnimaciones(
            "/res/azul_walk.png", 133,   
            "/res/azul_punch.png", 205, 
            "/res/azul_ko.png", 195,
            "/res/azul_win.png", 100,
            208 
        );
    }

    public void actualizar() {
        if (vida <= 0) {
            contadorMuerte++; 
        } else if (atacando) {
            contadorAtaque++;
            
            if (contadorAtaque > 20) { 
                atacando = false;
                contadorAtaque = 0;
            }
        }
    }
    
   
    public void mover(int dx) { 
    	if (vida > 0) { 
    		this.x += dx;     		
    		if (dx > 0) direccion = 1;     		
    		if (dx < 0) direccion = -1; 
    		if (vida <= 0 || victoria) return; 
    	    x += dx;
    		}
    	}
    
    public void atacar() { 
    	if (vida > 0 && !atacando) atacando = true; 
    	if (vida <= 0 || victoria) return;
    	}
}