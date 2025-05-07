package autonoma.pulgaslocas.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Soldado extends Sprite {

    public static final int STEP = 10;
    private int maxX;
    private int maxY;
    private CampoDeBatalla campo;

    public Soldado(int x, int y, int width, int height, Color color, CampoDeBatalla campo) {
        super(x, y, width, height, color);
        this.campo = campo;
    }

    public void inicializarLimites(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.x = (maxX - width) / 2;
        this.y = (maxY - height) / 2;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

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

    public void dispararPistolaPulguiplum(MouseEvent e) {
        int clickX = e.getX();
        int clickY = e.getY();

        // Crear copias de las listas para evitar ConcurrentModificationException
        ArrayList<PulgaNormal> normalesAChequear = new ArrayList<>(campo.getPulgasNormales());
        ArrayList<PulgaMutante> mutantesAChequear = new ArrayList<>(campo.getPulgasMutantes());

        for (PulgaNormal pulga : normalesAChequear) {
            if (pulga.hit(clickX, clickY, 1, 1)) {
                pulga.reducirVida(10);
                if (pulga.getVida() <= 0) {
                    campo.eliminarPulgaNormal(pulga);
                    campo.aumentarPuntaje(10);
                }
                break; // La pistola impacta solo una pulga por clic
            }
        }

        for (PulgaMutante pulga : mutantesAChequear) {
            if (pulga.hit(clickX, clickY, 1, 1)) {
                pulga.reducirVida(10);
                if (pulga.getVida() <= 0) {
                    campo.eliminarPulgaMutante(pulga);
                    campo.aumentarPuntaje(20);
                } else if (pulga.getVida() == 50) { // Si es impactada una vez, se convierte en normal
                    campo.eliminarPulgaMutante(pulga);
                    PulgaNormal nuevaNormal = new PulgaNormal(pulga.getX(), pulga.getY(), pulga.getWidth(), pulga.getHeight(), Color.RED, campo.getMaxX(), campo.getMaxY());
                    campo.getPulgasNormales().add(nuevaNormal);
                    new Thread((java.lang.Runnable) nuevaNormal).start();
                }
                break; // La pistola impacta solo una pulga por clic
            }
        }
    }

    public void dispararMisilPulgoson(MouseEvent e) {
        int centroX = e.getX();
        int centroY = e.getY();
        int radio = 30;

        // Crear copias de las listas para evitar ConcurrentModificationException
        ArrayList<PulgaNormal> normalesAChequear = new ArrayList<>(campo.getPulgasNormales());
        ArrayList<PulgaMutante> mutantesAChequear = new ArrayList<>(campo.getPulgasMutantes());

        for (PulgaNormal pulga : normalesAChequear) {
            int pulgaX = pulga.getX() + pulga.getWidth() / 2;
            int pulgaY = pulga.getY() + pulga.getHeight() / 2;
            double distancia = Math.sqrt(Math.pow(pulgaX - centroX, 2) + Math.pow(pulgaY - centroY, 2));

            if (distancia <= radio) {
                pulga.reducirVida(20);
                if (pulga.getVida() <= 0) {
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
                pulga.reducirVida(20);
                if (pulga.getVida() <= 0) {
                    campo.eliminarPulgaMutante(pulga);
                    campo.aumentarPuntaje(20);
                } else if (pulga.getVida() <= 50 && pulga.getVida() > 30) { // Si recibe daño y aún le queda para convertirse en normal
                    campo.eliminarPulgaMutante(pulga);
                    PulgaNormal nuevaNormal = new PulgaNormal(pulga.getX(), pulga.getY(), pulga.getWidth(), pulga.getHeight(), Color.RED, campo.getMaxX(), campo.getMaxY());
                    campo.getPulgasNormales().add(nuevaNormal);
                    new Thread((java.lang.Runnable) (Runnable) nuevaNormal).start();
                }
            }
        }
    }
}