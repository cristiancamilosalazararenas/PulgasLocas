package autonoma.pulgaslocas.elements;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class LectorArchivoTextoPlano implements Lector {

    private ArrayList<String> archivo;
    private Charset charset = Charset.forName("UTF-8");

    public LectorArchivoTextoPlano() {
        this.archivo = new ArrayList<>();
    }

    @Override
    public ArrayList<String> leer(String localizacionArchivo) throws IOException {
        archivo.clear();
        File file = new File(localizacionArchivo);
        FileReader reader = new FileReader(file, charset);
        BufferedReader buffer = new BufferedReader(reader);

        String linea;
        while ((linea = buffer.readLine()) != null) {
            archivo.add(linea);
        }

        reader.close();
        return archivo;
    }
}
