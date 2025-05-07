/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package autonoma.pulgaslocas.elements;

/**
 * Interfaz que define un contrato para ejecutar tareas en un hilo.
 * <p>
 * Las clases que implementan esta interfaz deben proporcionar una implementación
 * concreta del método {@link #run()} que contenga la lógica a ejecutar.
 * </p>
 * 
 * <p><b>Nota:</b> Esta interfaz puede coincidir con {@link java.lang.Runnable}, pero está 
 * definida localmente en el paquete {@code autonoma.pulgaslocas.elements} y no debe confundirse con la estándar de Java.</p>
 * 
 * @author  Juan José Morales A.
 * @version 20250506
 * @since   1.0
 */
public interface Runnable {

    /**
     * Ejecuta la acción definida por la clase que implementa esta interfaz.
     * <p>
     * Este método debe contener la lógica que se desea ejecutar de forma concurrente
     * o secuencial, según el uso del programa.
     * </p>
     * 
     * @since 1.0
     */
    void run();
}

