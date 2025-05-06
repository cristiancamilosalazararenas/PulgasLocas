package autonoma.pulgaslocas.elements;

import java.io.IOException;
import java.util.ArrayList;

public interface Lector {
    public abstract ArrayList<String> leer(String localizacionArchivo) throws IOException;
}
