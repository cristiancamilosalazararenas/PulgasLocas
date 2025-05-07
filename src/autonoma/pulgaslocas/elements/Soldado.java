package autonoma.pulgaslocas.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Representa un soldado que puede moverse dentro del campo de batalla y atacar
 * a pulgas normales y mutantes mediante distintas armas.
 * 
 * @author  
 * @version 20250507
 * @since 1.0
 */
public class Soldado extends Sprite {

    /** Paso de movimiento del soldado. */
    public static final int STEP = 10;

    /** Límite máximo en X dentro del campo. */
    private int maxX;

    /** Límite máximo en Y dentro del campo. */
    private int maxY;

    /** Referencia al campo de batalla donde actúa el soldado. */
    private CampoDeBatalla campo;

    /**
     * Crea un nuevo soldado con posición, tamaño, color y campo de batalla.
     * 
     * @param x Posición X inicial
     * @param y Posición Y inicial
     * @param width Ancho del soldado
     * @param height Alto del soldado
     * @param color Color del soldado
     * @param campo Campo de batalla donde se encuentra
     */
    public Soldado(int x, int y, int width, int height, Color color, CampoDeBatalla campo) {
        super(x, y, width, height, color);
        this.campo = campo;
    }

    /**
     * Inicializa los límites máximos del movimiento del soldado
     * y lo centra dentro del campo.
     * 
     * @param maxX Límite máximo horizontal
     * @param maxY Límite máximo vertical
     */
    public void inicializarLimites(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.x = (maxX - width) / 2;
        this.y = (maxY - height) / 2;
    }

    /**
     * Dibuja al soldado en la pantalla.
     * 
     * @param g Contexto gráfico sobre el que se dibuja
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    /**
     * Mueve al soldado según la tecla presionada.
     * El movimiento se limita a los bordes del campo.
     * 
     * @param e Evento de teclado que representa la tecla presionada
     */
    public void mover(java.awt.event.KeyEvent e) {
        switch (e.getKeyCode()) {
            case java.awt.event.KeyEvent.VK_UP:
                y -= STEP;
                break;
            case java.awt.event.KeyEvent.VK_DOWN:
                y += STEP;
                break;
            case java.awt.event.KeyEvent.VK_LEFT:
                x -= STEP;
                break;
            case java.awt.event.KeyEvent.VK_RIGHT:
                x += STEP;
                break;
        }
        x = Math.max(0, Math.min(x, maxX - width));
        y = Math.max(0, Math.min(y, maxY - height));
    }

    /**
     * Dispara la Pistola Pulguiplum hacia una posición del campo,
     * eliminando una sola pulga si es impactada.
     * 
     * @param e Evento de ratón con la coordenada del clic
     */
    public void dispararPistolaPulguiplum(MouseEvent e) {
        int clickX = e.getX();
        int clickY = e.getY();

        ArrayList<PulgaNormal> normalesAChequear = new ArrayList<>(campo.getPulgasNormales());
        ArrayList<PulgaMutante> mutantesAChequear = new ArrayList<>(campo.getPulgasMutantes());

        for (PulgaNormal pulga : normalesAChequear) {
            if (pulga.hit(clickX, clickY, 1, 1)) {
                pulga.reducirVida(100);
                if (pulga.getVida() <= 0) {
                    pulga.detener();
                    campo.eliminarPulgaNormal(pulga);
                    campo.aumentarPuntaje(10);
                }
                break;
            }
        }

        for (PulgaMutante pulga : mutantesAChequear) {
            if (pulga.hit(clickX, clickY, 1, 1)) {
                pulga.reducirVida(50);
                if (pulga.getVida() <= 0) {
                    pulga.detener();
                    campo.eliminarPulgaMutante(pulga);
                    campo.aumentarPuntaje(20);
                } else if (pulga.getVida() == 50) {
                    pulga.detener();
                    campo.eliminarPulgaMutante(pulga);
                    PulgaNormal nuevaNormal = new PulgaNormal(pulga.getX(), pulga.getY(), pulga.getWidth(), pulga.getHeight(), Color.RED, campo.getMaxX(), campo.getMaxY());
                    campo.getPulgasNormales().add(nuevaNormal);
                    new Thread((java.lang.Runnable) nuevaNormal).start();
                }
                break;
            }
        }
    }

    /**
     * Dispara el Misil Pulgosón en un área circular de efecto, dañando
     * todas las pulgas dentro del radio especificado.
     * 
     * @param e Evento de ratón con la posición del centro de la explosión
     */
    public void dispararMisilPulgoson(MouseEvent e) {
        int centroX = e.getX();
        int centroY = e.getY();
        int radio = 30;

        ArrayList<PulgaNormal> normalesAChequear = new ArrayList<>(campo.getPulgasNormales());
        ArrayList<PulgaMutante> mutantesAChequear = new ArrayList<>(campo.getPulgasMutantes());

        for (PulgaNormal pulga : normalesAChequear) {
            int pulgaX = pulga.getX() + pulga.getWidth() / 2;
            int pulgaY = pulga.getY() + pulga.getHeight() / 2;
            double distancia = Math.sqrt(Math.pow(pulgaX - centroX, 2) + Math.pow(pulgaY - centroY, 2));

            if (distancia <= radio) {
                pulga.reducirVida(100);
                if (pulga.getVida() <= 0) {
                    pulga.detener();
                    campo.eliminarPulgaNormal(pulga);
                    campo.aumentarPuntaje(10);
                }
            }
        }

        for (PulgaMutante pulga : mutantesAChequear) {
            int pulgaX = pulga.getX() + pulga.getWidth() / 2;
            int pulgaY = pulga.getY() + pulga.getHeight() / 2;
            double distancia = Math.sqrt(Math.pow(pulgaX - centroX, 2) + Math.pow(pulgaY - centroY, 2));

            if (distancia <= radio) {
                pulga.reducirVida(50);
                if (pulga.getVida() <= 0) {
                    pulga.detener();
                    campo.eliminarPulgaMutante(pulga);
                    campo.aumentarPuntaje(20);
                } else if (pulga.getVida() == 50) {
                    pulga.detener();
                    campo.eliminarPulgaMutante(pulga);
                    PulgaNormal nuevaNormal = new PulgaNormal(pulga.getX(), pulga.getY(), pulga.getWidth(), pulga.getHeight(), Color.RED, campo.getMaxX(), campo.getMaxY());
                    campo.getPulgasNormales().add(nuevaNormal);
                    new Thread((java.lang.Runnable) (Runnable) nuevaNormal).start();
                }
            }
        }
    }
}
