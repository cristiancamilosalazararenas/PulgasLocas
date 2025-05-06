package autonoma.pulgaslocas.elements;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Clase Soldado. Representa un soldado como un elemento gráfico en el juego.
 * Hereda de la clase Sprite y define su representación visual.
 * 
 * @author Juan Sebastian Lopez Guzman
 * @version 1.0
 * @since 2025-05-05
 */
public class Soldado extends Sprite {
    
    /**
     * Constructor de la clase Soldado.
     * 
     * @param x Posición en el eje X donde se dibujará el soldado.
     * @param y Posición en el eje Y donde se dibujará el soldado.
     * @param width Ancho del soldado.
     * @param height Alto del soldado.
     * @param color Color del soldado.
     * @since 1.0
     */
    public Soldado(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }
    
    /**
     * Método para dibujar el soldado en el contexto gráfico proporcionado.
     * Implementa el método abstracto de la clase Sprite.
     * 
     * @param g Objeto Graphics donde se dibujará el soldado.
     * @since 1.0
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
    
}
