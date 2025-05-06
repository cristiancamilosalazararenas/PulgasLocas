package autonoma.pulgaslocas.elements;

import java.io.IOException;
import java.util.ArrayList;

public interface Escritor {

    public abstract void escribir(ArrayList<String> archivo, String ruta) throws IOException;
}
