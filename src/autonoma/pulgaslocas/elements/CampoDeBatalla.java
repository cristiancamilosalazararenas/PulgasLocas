package autonoma.pulgaslocas.elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CampoDeBatalla {

    private File archivoPuntajes;
    private final Lector lector;
    private final Escritor escritor;

    public CampoDeBatalla(String rutaArchivo) throws IOException {
        this.archivoPuntajes = new File(rutaArchivo);
        if (!archivoPuntajes.exists()) {
            archivoPuntajes.createNewFile(); // Crea el archivo si no existe
        }
        this.lector = new LectorArchivoTextoPlano();
        this.escritor = new EscritorArchivoTextoPlano(rutaArchivo);
    }

    public void guardarPuntaje(int nuevoPuntaje) throws IOException {
        ArrayList<String> lineas = lector.leer(archivoPuntajes.getPath());
        ArrayList<Integer> puntajes = new ArrayList<>();

        for (String linea : lineas) {
            if (!linea.isBlank()) {
                puntajes.add(Integer.parseInt(linea.trim()));
            }
        }

        puntajes.add(nuevoPuntaje);
        Collections.sort(puntajes, Collections.reverseOrder()); // Mayor a menor

        if (puntajes.size() > 10) {
            puntajes = new ArrayList<>(puntajes.subList(0, 10));
        }

        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (int p : puntajes) {
            nuevasLineas.add(String.valueOf(p));
        }

        escritor.escribir(nuevasLineas, archivoPuntajes.getPath());
    }

    public ArrayList<Integer> leerPuntajes() throws IOException {
        ArrayList<String> lineas = lector.leer(archivoPuntajes.getPath());
        ArrayList<Integer> puntajes = new ArrayList<>();

        for (String linea : lineas) {
            if (!linea.isBlank()) {
                puntajes.add(Integer.parseInt(linea.trim()));
            }
        }

        return puntajes;
    }
}
