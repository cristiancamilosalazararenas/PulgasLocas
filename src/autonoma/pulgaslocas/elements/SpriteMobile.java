/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.pulgaslocas.elements;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Representa un objeto móvil dentro del entorno del juego.
 * <p>
 * Esta clase contiene las propiedades básicas de movimiento en los ejes X e Y,
 * así como un método para representar su trayectoria. Sirve como clase base para
 * elementos móviles como las pulgas.
 * </p>
 * 
 * @author  Juan José Morales A.
 * @version 20250506
 * @since   1.0
 */
    /**
     * Velocidad del objeto en el eje X.
     * 
     * @since 1.0
     */

public class SpriteMobile extends Sprite {
    protected int velocidadX;

    /**
     * Velocidad del objeto en el eje Y.
     * 
     * @since 1.0
     */
    protected int velocidadY;
    
    
    public SpriteMobile(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }


    /**
     * Imprime la trayectoria del sprite en la salida estándar.
     * <p>
     * Este método puede ser sobrescrito por clases hijas para definir un comportamiento
     * más específico de movimiento o trayecto.
     * </p>
     * 
     * @since 1.0
     */


    public void trayectoria() {
        System.out.println("trayectoria del sprite.");
    }

    @Override
    public void draw(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

