/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.pulgaslocas.elements;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Representa una pulga normal en el juego.
 * <p>
 * Esta clase concreta extiende la clase abstracta {@link Pulga} y define una pulga
 * con comportamiento básico de movimiento aleatorio. Tiene menor velocidad que la pulga mutante
 * y se representa gráficamente con color rojo.
 * </p>
 * 
 * @author  Juan José Morales A.
 * @version 20250506
 * @since   1.0
 * @see     autonoma.pulgaslocas.elements.Pulga
 */
public class PulgaNormal extends Pulga {

    /**
     * Constructor de la clase PulgaNormal.
     * <p>
     * Inicializa una pulga normal en la posición y dimensiones dadas, con velocidad
     * inicial aleatoria y color rojo. También define los límites máximos de movimiento.
     * </p>
     *
     * @param x     Posición inicial en X.
     * @param y     Posición inicial en Y.
     * @param width Ancho de la pulga.
     * @param height Alto de la pulga.
     * @param color Color asignado (aunque se sobrescribe a rojo al dibujar).
     * @param maxX  Límite máximo horizontal.
     * @param maxY  Límite máximo vertical.
     */
    public PulgaNormal(int x, int y, int width, int height, Color color, int maxX, int maxY) {
        super(x, y, width, height, color, maxX, maxY);
        // Asignación de velocidad inicial aleatoria en X y Y
        this.velocidadX = (Math.random() > 0.5) ? 2 : -2;
        this.velocidadY = (Math.random() > 0.5) ? 2 : -2;
    }

    /**
     * Dibuja gráficamente la pulga normal como un óvalo rojo.
     *
     * @param g Objeto Graphics para renderizar en pantalla.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, width, height);
    }

    /**
     * Método que define el comportamiento del hilo de la pulga normal.
     * <p>
     * Mientras esté activa, se mueve continuamente con pausas temporales entre
     * actualizaciones de posición. Imprime mensajes de depuración si el hilo es interrumpido.
     * </p>
     */
    @Override
    public void run() {
        System.out.println("Hilo PulgaNormal iniciado"); 
        while (activo) {
            mover();
            try {
                Thread.sleep(50); 
            } catch (InterruptedException ex) {
                System.out.println("Hilo interrumpido");
                Thread.currentThread().interrupt(); 
            }
        }
    }

    /**
     * Lógica de movimiento para la pulga normal.
     * <p>
     * Calcula la nueva posición, verifica colisiones con bordes
     * y ajusta la dirección de movimiento si es necesario.
     * </p>
     */
    private void mover() {
        int newX = x + velocidadX;
        int newY = y + velocidadY;

        // Rebote en los bordes del área
        if (newX <= 0 || newX >= maxX - width) velocidadX *= -1;
        if (newY <= 0 || newY >= maxY - height) velocidadY *= -1;

        // Posición restringida dentro de los límites del campo
        x = Math.max(0, Math.min(newX, maxX - width));
        y = Math.max(0, Math.min(newY, maxY - height));
    }
}
