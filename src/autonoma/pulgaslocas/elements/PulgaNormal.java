/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.pulgaslocas.elements;

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

    /**
     * Crea una nueva instancia de una pulga normal con los valores especificados.
     * 
     * @param vida  Cantidad de vida inicial de la pulga.
     * @param velX  Velocidad de movimiento horizontal.
     * @param velY  Velocidad de movimiento vertical.
     * @since 1.0
     */
    public PulgaNormal(int vida, int velX, int velY) {
        this.cantidadVida = vida;
        this.velocidadX = velX;
        this.velocidadY = velY;
    }

    /**
     * Método que contiene la lógica de ejecución en un hilo para la pulga normal.
     * <p>
     * Actualmente no implementa comportamiento concreto y lanza una excepción para indicar
     * que aún no está soportado.
     * </p>
     * 
     * @throws UnsupportedOperationException si se intenta ejecutar sin una implementación válida.
     * @since 1.0
     */
    @Override
    public void run() {
        throw new UnsupportedOperationException("Pulga normal corriendo con velocidad (" + velocidadX + ", " + velocidadY + ")");
    }
}

