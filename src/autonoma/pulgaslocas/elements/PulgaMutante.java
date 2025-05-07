/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.pulgaslocas.elements;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Clase que representa una pulga mutante dentro del juego.
 * <p>
 * Las pulgas mutantes son una subclase de {@link Pulga} con un comportamiento
 * de movimiento particular: se mueven constantemente rebotando en los bordes del
 * campo. Tienen mayor velocidad que una pulga normal y un color distintivo verde.
 * </p>
 *
 * @author Juan José Morales
 * @version 20250506
 * @since 1.0
 */
public class PulgaMutante extends Pulga {

    /**
     * Constructor de la clase PulgaMutante.
     * <p>
     * Inicializa una pulga mutante en la posición, tamaño y color indicados,
     * asignando una velocidad inicial aleatoria en ambos ejes.
     * </p>
     *
     * @param x     Posición inicial en X.
     * @param y     Posición inicial en Y.
     * @param width Ancho de la pulga.
     * @param height Alto de la pulga.
     * @param color Color (no utilizado en este caso, ya que se pinta siempre verde).
     * @param maxX  Límite máximo horizontal.
     * @param maxY  Límite máximo vertical.
     */
    public PulgaMutante(int x, int y, int width, int height, Color color, int maxX, int maxY) {
        super(x, y, width, height, color, maxX, maxY);
        // Velocidades aleatorias iniciales en X y Y
        this.velocidadX = (Math.random() > 0.5) ? 4 : -4;
        this.velocidadY = (Math.random() > 0.5) ? 4 : -4;
    }

    /**
     * Dibuja gráficamente la pulga mutante como un óvalo verde.
     *
     * @param g Objeto Graphics para renderizar en pantalla.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x, y, width, height);
    }

    /**
     * Método que define el comportamiento del hilo asociado a la pulga mutante.
     * <p>
     * Mientras esté activa, la pulga se moverá de forma continua y pausada,
     * ejecutando colisiones con los bordes del campo.
     * </p>
     */
    @Override
    public void run() {
        while (activo) {
            mover();
            try {
                Thread.sleep(30); 
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt(); 
            }
        }
    }

    /**
     * Lógica de movimiento de la pulga mutante.
     * <p>
     * La pulga se desplaza en la dirección actual. Si toca un borde,
     * invierte la dirección correspondiente y permanece dentro de los límites.
     * </p>
     */
    private void mover() {
        int newX = x + velocidadX;
        int newY = y + velocidadY;

        // Rebote en bordes horizontales
        if (newX <= 0 || newX >= maxX - width) velocidadX *= -1;
        // Rebote en bordes verticales
        if (newY <= 0 || newY >= maxY - height) velocidadY *= -1;

        // Actualiza posición dentro de límites
        x = Math.max(0, Math.min(newX, maxX - width));
        y = Math.max(0, Math.min(newY, maxY - height));
    }
}
