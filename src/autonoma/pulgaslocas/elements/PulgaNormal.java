/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.pulgaslocas.elements;

/**
 *
 * @author juanb
 */
public class PulgaNormal extends Pulga{
    public PulgaNormal(int vida, int velX, int velY) {
        this.cantidadVida = vida;
        this.velocidadX = velX;
        this.velocidadY = velY;
    }
    @Override
    public void run() {
        throw new UnsupportedOperationException("Pulga normal corriendo con velocidad (" + velocidadX + ", " + velocidadY + ")"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
