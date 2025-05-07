package autonoma.pulgaslocas.elements;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Clase base para elementos gráficos. Maneja posición, tamaño y color.
 * @author Juan Sebastian Lopez Guzman
 * @version 1.0
 * @since 2025-05-05
 */
public abstract class Sprite {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Color color;

    /**
     * Crea un Sprite con posición, tamaño y color
     */
    public Sprite(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
    
    /**
     * Detecta colisión con otro sprite
     * @return true si hay colisión
     */
    public boolean hit(int x, int y, int width, int height) {
        return this.x < x + width &&
               this.x + this.width > x &&
               this.y < y + height &&
               this.y + this.height > y;
    }
    // Getters y setters 
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color=color;
    }
}