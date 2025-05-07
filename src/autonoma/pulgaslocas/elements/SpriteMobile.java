/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.pulgaslocas.elements;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author juanb
 */
public class SpriteMobile extends Sprite {
    protected int velocidadX;
    protected int velocidadY;

    public SpriteMobile(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    public void trayectoria() {
        System.out.println("trayectoria del sprite.");
    }

    @Override
    public void draw(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
