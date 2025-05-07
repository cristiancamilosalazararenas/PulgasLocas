/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.pulgaslocas.elements;

import java.awt.Color;

/**
 * Representa una pulga normal en el juego.
 * <p>
 * Esta clase concreta extiende la clase abstracta {@link Pulga} y define una pulga
 * con comportamiento básico. Se inicializa con una cantidad de vida y velocidades en los ejes X y Y.
 * </p>
 * 
 * @author  Juan José Morales A.
 * @version 20250506
 * @since   1.0
 * @see     autonoma.pulgaslocas.elements.Pulga
 */
public class PulgaNormal extends Pulga {

    public PulgaNormal(int x, int y, int width, int height, Color color, int maxX, int maxY) {
        super(x, y, width, height, color, maxX, maxY);
        this.velocidadX = (Math.random() > 0.5) ? 2 : -2;
        this.velocidadY = (Math.random() > 0.5) ? 2 : -2;
    }

    @Override
    public void run() {
        while(activo){
            mover();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {}
        }
    }

    private void mover() {
        int newX = x + velocidadX;
        int newY = y + velocidadY;
        
        if(newX <= 0 || newX >= maxX - width) velocidadX *= -1;
        if(newY <= 0 || newY >= maxY - height) velocidadY *= -1;
        
        x = Math.max(0, Math.min(newX, maxX - width));
        y = Math.max(0, Math.min(newY, maxY - height));
    }
}

