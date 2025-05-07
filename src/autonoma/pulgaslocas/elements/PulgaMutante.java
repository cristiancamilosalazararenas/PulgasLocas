/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.pulgaslocas.elements;

import java.awt.Color;

/**
 *
 * @author juanb
 */
public class PulgaMutante extends Pulga{

    public PulgaMutante(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Pulga mutante corriendo con velocidad (" + velocidadX + ", " + velocidadY + ")"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
