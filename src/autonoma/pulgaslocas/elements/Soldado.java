package autonoma.pulgaslocas.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Clase Soldado. Representa un soldado como un elemento gráfico en el juego.
 * Hereda de la clase Sprite y define su representación visual.
 * 
 * @author Juan Sebastian Lopez Guzman
 * @version 1.0
 * @since 2025-05-05
 */
public class Soldado extends Sprite {
    
    public static final int STEP = 10;
    private int maxX;  
    private int maxY;  
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
    
    public void setBounds(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }
    
    public void mover(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                y -= STEP;
                break;
            case KeyEvent.VK_DOWN:
                y += STEP;
                break;
            case KeyEvent.VK_LEFT:
                x -= STEP;
                break;
            case KeyEvent.VK_RIGHT:
                x += STEP;
                break; 
        }
        x = Math.max(0, Math.min(x, maxX - width));
        y = Math.max(0, Math.min(y, maxY - height));
    }
    
    public void dispararPistolaPulguiplum(MouseEvent e) {
    
    }
    public void dispararMisilPulgoson(MouseEvent e){
        
    }
}
