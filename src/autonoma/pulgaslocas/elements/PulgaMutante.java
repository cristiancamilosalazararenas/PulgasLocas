/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.pulgaslocas.elements;

import java.awt.Color;

/**
 * Representa una pulga mutante en el juego.
 * <p>
 * Esta clase extiende {@link Pulga} y define una versión especial de pulga con potencial
 * para comportamientos avanzados o distintos a la pulga normal. Actualmente solo configura
 * los valores iniciales de vida y velocidad.
 * </p>
 * 
 * @author  Juan José Morales A.
 * @version 20250506
 * @since   1.0
 * @see     autonoma.pulgaslocas.elements.Pulga
 */
    /**
     * Crea una nueva instancia de una pulga mutante con la vida y velocidad especificadas.
     * 
     * @param vida  Cantidad de vida inicial de la pulga.
     * @param velX  Velocidad de desplazamiento en el eje X.
     * @param velY  Velocidad de desplazamiento en el eje Y.
     * @since 1.0
     */
    public class PulgaMutante extends Pulga{

    public PulgaMutante(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    /**
     * Ejecuta el comportamiento de la pulga mutante en un hilo.
     * <p>
     * Actualmente, este método lanza una excepción para indicar que aún no se ha
     * implementado la lógica de movimiento o comportamiento especial.
     * </p>
     * 
     * @throws UnsupportedOperationException si se intenta ejecutar sin implementación.
     * @since 1.0
     */
    @Override
    public void run() {
        throw new UnsupportedOperationException("Pulga mutante corriendo con velocidad (" + velocidadX + ", " + velocidadY + ")");
    }
}
