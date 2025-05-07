/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.pulgaslocas.elements;

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
public class SpriteMobile {

    /**
     * Velocidad del objeto en el eje X.
     * 
     * @since 1.0
     */
    protected int velocidadX;

    /**
     * Velocidad del objeto en el eje Y.
     * 
     * @since 1.0
     */
    protected int velocidadY;

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
}

