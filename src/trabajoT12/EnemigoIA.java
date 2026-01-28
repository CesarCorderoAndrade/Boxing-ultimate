package trabajoT12;

public class EnemigoIA extends Entidad {
    public EnemigoIA(int x, int y) {
        super(x, y, 200, 300);
        
        cargarAnimaciones(
            "/res/rojo_walk.png", 150,   
            "/res/rojo_punch.png", 230, 
            "/res/rojo_ko.png", 187,  
            "/res/rojo_win.png", 120,
            208
        );
    }

    public void pensar(Jugador objetivo) {
        if (vida <= 0) {
            contadorMuerte++; 
            return; 
        }
        if (vida <= 0 || victoria)
        	return;
        
        int distanciaDeRespeto = 105;
        
        if (Math.abs(objetivo.x - this.x) > distanciaDeRespeto) {
             if (objetivo.x > this.x) { 
            	 this.x += 2; direccion = 1; 
            	 }
             else { 
            	 this.x -= 2; direccion = -1; 
            	 }
        } else {
             if (Math.random() < 0.1) atacando = true;
        }

        if (atacando) {
            contadorAtaque++;
            if (contadorAtaque > 20) { atacando = false; contadorAtaque = 0; }
        }
    }
}