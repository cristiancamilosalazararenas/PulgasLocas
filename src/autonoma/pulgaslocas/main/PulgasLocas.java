package autonoma.pulgaslocas.main;

import autonoma.pulgaslocas.elements.CampoDeBatalla;
import java.io.IOException;
import java.util.ArrayList;

public class PulgasLocas {

    public static void main(String[] args) {
        try {
            String ruta = "puntajes.txt";
            CampoDeBatalla campo = new CampoDeBatalla(ruta);
            campo.guardarPuntaje(250);
            campo.guardarPuntaje(320);
            campo.guardarPuntaje(150);
            campo.guardarPuntaje(480);
            campo.guardarPuntaje(300);
            campo.guardarPuntaje(420);
            campo.guardarPuntaje(500);
            campo.guardarPuntaje(375);
            campo.guardarPuntaje(460);
            campo.guardarPuntaje(280);
            campo.guardarPuntaje(290);

            ArrayList<Integer> puntajes = campo.leerPuntajes();
            System.out.println("PUNTAJES GUARDADOS:");
            for (int p : puntajes) {
                System.out.println(p);
            }

        } catch (IOException e) {
            System.out.println("Error al manejar el archivo de puntajes: " + e.getMessage());
        }
    }
}
