/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.pulgaslocas.elements;

import java.awt.Color;

public class PulgaMutante extends Pulga {

    public PulgaMutante(int x, int y, int width, int height, Color color, int maxX, int maxY) {
        super(x, y, width, height, color, maxX, maxY);
        this.velocidadX = (Math.random() > 0.5) ? 4 : -4;
        this.velocidadY = (Math.random() > 0.5) ? 4 : -4;
    }

    @Override
    public void run() {
        while(activo){
            mover();
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {}
        }
    }

    private void mover() {
        int newX = x + velocidadX;
        int newY = y + velocidadY;
        
        if(newX <= 0 || newX >= maxX - width) velocidadX *= -1;
        if(newY <= 0 || newY >= maxY - height) velocidadY *= -1;
        
        x = Math.max(0, Math.min(newX, maxX - width));
        y = Math.max(0, Math.min(newY, maxY - height));
    }
}
