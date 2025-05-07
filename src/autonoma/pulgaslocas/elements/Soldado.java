package autonoma.pulgaslocas.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Clase Soldado. Representa un soldado como un elemento gráfico en el juego.
 * Hereda de la clase Sprite y define su representación visual.
 * 
 * @author Juan Sebastian Lopez Guzman
 * @version 2.0
 * @since 2025-05-05
 */
public class Soldado extends Sprite {
    
    public static final int STEP = 10;
    private int maxX;  
    private int maxY;
    private CampoDeBatalla campo;
    
    /**
     * Constructor de la clase Soldado.
     * 
     * @param x Posición en el eje X donde se dibujará el soldado.
     * @param y Posición en el eje Y donde se dibujará el soldado.
     * @param width Ancho del soldado.
     * @param height Alto del soldado.
     * @param color Color del soldado.
     * @param campo Referencia al campo de batalla asociado
     * @since 2.0
     */
    public Soldado(int x, int y, int width, int height, Color color, CampoDeBatalla campo) {
        super(x, y, width, height, color);
        this.campo = campo; // Puede ser null
    }
    
    /**
     * Método para dibujar el soldado en el contexto gráfico proporcionado.
     * @param g Objeto Graphics donde se dibujará el soldado.
     * @since 1.0
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
    
    /**
     * Establece los límites del área de movimiento del soldado
     * @param maxX Límite máximo en el eje X
     * @param maxY Límite máximo en el eje Y
     * @since 2.0
     */
    public void setBounds(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }
    
    /**
     * Maneja el movimiento del soldado con las teclas direccionales
     * @param e Evento de teclado
     * @since 2.0
     */
    public void mover(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                y -= STEP;
                break;
            case KeyEvent.VK_DOWN:
                y += STEP;
                break;
            case KeyEvent.VK_LEFT:
                x -= STEP;
                break;
            case KeyEvent.VK_RIGHT:
                x += STEP;
                break; 
        }
        x = Math.max(0, Math.min(x, maxX - width));
        y = Math.max(0, Math.min(y, maxY - height));
    }
    
    /**
     * Dispara un proyectil tipo pistola en la posición del click del mouse
     * @param e Evento del mouse
     * @since 2.0
     */
    public void dispararPistolaPulguiplum(MouseEvent e) {
        int clickX = e.getX();
        int clickY = e.getY();
        
        // Verificar colisión con pulgas
        for(PulgaNormal pulga : new ArrayList<>(campo.getPulgasNormales())){
            if(pulga.hit(clickX, clickY, 1, 1)){
                pulga.reducirVida(10);
                if(pulga.getVida() <= 0){
                    campo.eliminarPulgaNormal(pulga);
                    campo.aumentarPuntaje(10);
                }
            }
        }
        
        for(PulgaMutante pulga : new ArrayList<>(campo.getPulgasMutantes())){
            if(pulga.hit(clickX, clickY, 1, 1)){
                pulga.reducirVida(10);
                if(pulga.getVida() <= 0){
                    campo.eliminarPulgaMutante(pulga);
                    campo.aumentarPuntaje(20);
                }
            }
        }
    }
    
    /**
     * Dispara un misil que afecta un área alrededor del click del mouse
     * @param e Evento del mouse
     * @since 2.0
     */
    public void dispararMisilPulgoson(MouseEvent e){
        int centroX = e.getX();
        int centroY = e.getY();
        int radio = 30;
        
        for(PulgaNormal pulga : new ArrayList<>(campo.getPulgasNormales())){
            int pulgaX = pulga.getX() + pulga.getWidth()/2;
            int pulgaY = pulga.getY() + pulga.getHeight()/2;
            double distancia = Math.sqrt(Math.pow(pulgaX - centroX, 2) + Math.pow(pulgaY - centroY, 2));
            
            if(distancia <= radio){
                pulga.reducirVida(20);
                if(pulga.getVida() <= 0){
                    campo.eliminarPulgaNormal(pulga);
                    campo.aumentarPuntaje(10);
                }
            }
        }
        
        for(PulgaMutante pulga : new ArrayList<>(campo.getPulgasMutantes())){
            int pulgaX = pulga.getX() + pulga.getWidth()/2;
            int pulgaY = pulga.getY() + pulga.getHeight()/2;
            double distancia = Math.sqrt(Math.pow(pulgaX - centroX, 2) + Math.pow(pulgaY - centroY, 2));
            
            if(distancia <= radio){
                pulga.reducirVida(20);
                if(pulga.getVida() <= 0){
                    campo.eliminarPulgaMutante(pulga);
                    campo.aumentarPuntaje(20);
                }
            }
        }
    }
}