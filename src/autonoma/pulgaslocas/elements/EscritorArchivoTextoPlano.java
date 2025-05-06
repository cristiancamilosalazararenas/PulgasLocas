package autonoma.pulgaslocas.elements;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class EscritorArchivoTextoPlano implements Escritor {

    private Charset charset = Charset.forName("UTF-8");
    private String filePath;

    public EscritorArchivoTextoPlano(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void escribir(ArrayList<String> archivo, String ruta) throws IOException {
        File fichero = new File(ruta);
        FileWriter writer = new FileWriter(fichero, false);
        PrintWriter pw = new PrintWriter(writer);

        for (String linea : archivo) {
            pw.println(linea);
        }

        pw.close();
        writer.close();
    }
}
