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
public abstract class Pulga extends SpriteMobile implements Runnable{
    protected int cantidadVida;

    public Pulga(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }
}
