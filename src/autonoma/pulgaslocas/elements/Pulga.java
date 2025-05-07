/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.pulgaslocas.elements;

import java.awt.Color;

/**
 * Clase abstracta que representa una Pulga dentro del juego.
 * <p>
 * Esta clase define el comportamiento básico de una pulga móvil con capacidad
 * de ejecución en un hilo independiente. Hereda de {@link SpriteMobile} y puede
 * ser utilizada como base para diferentes tipos de pulgas con comportamientos
 * específicos.
 * </p>
 * 
 * <p>
 * Las instancias de esta clase deben implementar el método {@link #run()} para
 * definir el comportamiento en ejecución del hilo asociado a cada pulga.
 * </p>
 * 
 * @author Juan José Morales A.
 * @version 20250506
 * @since 1.0
 * @see autonoma.pulgaslocas.elements.SpriteMobile
 */
public abstract class Pulga extends SpriteMobile implements Runnable {

    /**
     * Cantidad de vida que posee la pulga.
     * <p>
     * Este atributo representa la cantidad de puntos de vida actuales de la
     * pulga. Puede ser modificado por eventos del juego como colisiones o
     * ataques.
     * </p>
     *
     * @since 1.0
     */
    protected int cantidadVida;

    /**
     * Bandera que indica si la pulga está activa.
     * <p>
     * Se utiliza para controlar la ejecución del hilo correspondiente a la pulga.
     * Declarado como {@code volatile} para garantizar visibilidad entre hilos.
     * </p>
     */
    protected volatile boolean activo;

    /**
     * Límite máximo de desplazamiento en el eje X.
     */
    protected int maxX;

    /**
     * Límite máximo de desplazamiento en el eje Y.
     */
    protected int maxY;

    /**
     * Constructor que inicializa los atributos de la pulga.
     *
     * @param x     Posición inicial en X.
     * @param y     Posición inicial en Y.
     * @param width Ancho del sprite.
     * @param height Alto del sprite.
     * @param color Color de la pulga.
     * @param maxX  Límite máximo horizontal del campo.
     * @param maxY  Límite máximo vertical del campo.
     */
    public Pulga(int x, int y, int width, int height, Color color, int maxX, int maxY) {
        super(x, y, width, height, color);
        this.maxX = maxX;
        this.maxY = maxY;
        this.activo = true;
        this.cantidadVida = 100;
    }

    /**
     * Detiene la ejecución de la pulga.
     * <p>
     * Este método cambia el estado activo a falso, lo que permite detener
     * el bucle de ejecución del hilo asociado a la pulga.
     * </p>
     */
    public void detener() {
        activo = false;
    }

    /**
     * Devuelve la cantidad de vida actual de la pulga.
     *
     * @return Entero que representa los puntos de vida restantes.
     */
    public int getVida() {
        return cantidadVida;
    }

    /**
     * Reduce la vida de la pulga según el daño recibido.
     *
     * @param danio Valor entero que se restará a la vida de la pulga.
     */
    public void reducirVida(int danio) {
        cantidadVida -= danio;
    }

    /**
     * Inicializa los límites del movimiento de la pulga.
     * <p>
     * Define el espacio máximo en el que la pulga puede moverse
     * dentro del campo de batalla.
     * </p>
     *
     * @param maxX Límite máximo en el eje X.
     * @param maxY Límite máximo en el eje Y.
     * @since 2.0
     */
    public void inicializarLimites(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    /**
     * Método que debe ser implementado por las subclases para definir el
     * comportamiento de la pulga cuando se ejecuta en un hilo.
     */
    @Override
    public abstract void run();

}
