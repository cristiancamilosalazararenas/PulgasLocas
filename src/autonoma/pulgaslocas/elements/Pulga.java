/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.pulgaslocas.elements;

import java.awt.Color;

/**
 * Clase abstracta que representa una Pulga dentro del juego.
 * <p>
 * Esta clase define el comportamiento básico de una pulga móvil con capacidad de ejecución
 * en un hilo independiente. Hereda de {@link SpriteMobile} y puede ser utilizada como base
 * para diferentes tipos de pulgas con comportamientos específicos.
 * </p>
 * 
 * @author  Juan José Morales A.
 * @version 20250506
 * @since   1.0
 * @see     autonoma.pulgaslocas.elements.SpriteMobile
 */
public abstract class Pulga extends SpriteMobile implements Runnable {
    
    /**
     * Cantidad de vida que posee la pulga.
     * <p>
     * Este atributo representa la cantidad de puntos de vida actuales de la pulga. 
     * Puede ser modificado por eventos del juego como colisiones o ataques.
     * </p>
     * 
     * @since 1.0
     */
    protected int cantidadVida;

    public Pulga(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }
}
