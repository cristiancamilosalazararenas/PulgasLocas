package autonoma.pulgaslocas.main;

import autonoma.pulgaslocas.elements.CampoDeBatalla;
import autonoma.pulgaslocas.elements.Soldado;
import autonoma.pulgaslocas.gui.VentanaPrincipalPulgasLocas;
import java.awt.Color;
import java.io.IOException;

public class PulgasLocas {

    public static void main(String[] args) throws IOException {
        try {
            String ruta = "puntajes.txt";

            // 1. Crear campo de batalla
            CampoDeBatalla campo = new CampoDeBatalla(ruta);

            // 2. Crear soldado y asociarlo con el campo
            Soldado soldado = new Soldado(0, 0, 30, 30, Color.BLUE, campo);
            campo.setSoldado(soldado);
            campo.setBounds(700, 500); // Establecer límites del campo

            // 3. Crear y mostrar la ventana principal
            java.awt.EventQueue.invokeLater(() -> {
                try {
                    VentanaPrincipalPulgasLocas ventana = new VentanaPrincipalPulgasLocas(campo, soldado);
                    ventana.setLocationRelativeTo(null);
                    ventana.setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}