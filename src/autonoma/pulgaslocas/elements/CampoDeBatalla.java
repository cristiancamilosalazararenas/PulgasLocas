package autonoma.pulgaslocas.elements;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.KeyEvent;

public class CampoDeBatalla {

    private File archivoPuntajes;
    private final Lector lector;
    private final Escritor escritor;
    private ArrayList<PulgaNormal> pulgasNormales;
    private ArrayList<PulgaMutante> pulgasMutantes;
    private Soldado soldado;
    private int maxX;
    private int maxY;
    private int puntaje;

    public CampoDeBatalla(String rutaArchivo) throws IOException {
        this.archivoPuntajes = new File(rutaArchivo);
        if (!this.archivoPuntajes.exists()) {
            this.archivoPuntajes.createNewFile();
        }
        this.lector = new LectorArchivoTextoPlano();
        this.escritor = new EscritorArchivoTextoPlano(rutaArchivo);
        this.pulgasNormales = new ArrayList<>();
        this.pulgasMutantes = new ArrayList<>();
        this.maxX = 700;
        this.maxY = 500;
        this.puntaje = 0;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setSoldado(Soldado soldado) {
        this.soldado = soldado;
        if (soldado != null) {
            soldado.inicializarLimites(maxX, maxY);
        }
    }

    /**
     * Establece los límites del campo de batalla
     *
     * @param maxX Ancho máximo
     * @param maxY Alto máximo
     * @since 2.0
     */
    public void setBounds(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        if (this.soldado != null) {
            this.soldado.inicializarLimites(maxX, maxY);
        }
        for (PulgaNormal pulga : pulgasNormales) {
            pulga.inicializarLimites(maxX, maxY);
        }
        for (PulgaMutante pulga : pulgasMutantes) {
            pulga.inicializarLimites(maxX, maxY);
        }
    }

    /**
     * Agrega una nueva pulga normal al campo con la tecla M
     *
     * @param e Evento de teclado
     * @since 2.0
     */
    public void agregarPulgaNormal(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_M) {
            int width = 20;
            int height = 20;
            int x, y;

            do {
                x = (int) (Math.random() * (maxX - width));
                y = (int) (Math.random() * (maxY - height));
            } while (colisionConSoldado(x, y, width, height) || colisionConPulgas(x, y, width, height));

            PulgaNormal pulga = new PulgaNormal(x, y, width, height, Color.RED, maxX, maxY);
            pulgasNormales.add(pulga);
            new Thread((java.lang.Runnable) pulga).start();
        }
    }

    public void agregarPulgaNormalAutomatica() {
        int width = 20;
        int height = 20;
        int x, y;

        do {
            x = (int) (Math.random() * (maxX - width));
            y = (int) (Math.random() * (maxY - height));
        } while (colisionConSoldado(x, y, width, height) || colisionConPulgas(x, y, width, height));

        PulgaNormal pulga = new PulgaNormal(x, y, width, height, Color.RED, maxX, maxY);
        pulgasNormales.add(pulga);
        new Thread((java.lang.Runnable) pulga).start();
    }

    public void agregarPulgaMutanteAutomatica() {
        int width = 20;
        int height = 20;
        int x, y;

        do {
            x = (int) (Math.random() * (maxX - width));
            y = (int) (Math.random() * (maxY - height));
        } while (colisionConSoldado(x, y, width, height) || colisionConPulgas(x, y, width, height));

        PulgaMutante pulga = new PulgaMutante(x, y, width, height, Color.GREEN, maxX, maxY);
        pulgasMutantes.add(pulga);
        new Thread((java.lang.Runnable) pulga).start();
    }

    public void handleKey(KeyEvent e) throws IOException {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                if (soldado != null) {
                    soldado.mover(e);
                }
                break;
            case KeyEvent.VK_M:
                agregarPulgaNormal(e);
                break;
            case KeyEvent.VK_N:
                agregarPulgaMutante(e);
                break;
            case KeyEvent.VK_S:
                saltarPulgas(e);
                break;
            case KeyEvent.VK_Q:
                terminarSimulacion(e);
                break;
            case KeyEvent.VK_P:
                agregarPulgaNormalAutomatica(); // Usando 'p' como en el enunciado
                break;
            case KeyEvent.VK_SPACE:
                destruirMitadPulgas();
                break;
        }
    }

    private void destruirMitadPulgas() {
        int totalPulgas = pulgasNormales.size() + pulgasMutantes.size();
        int cantidadADestruir = totalPulgas / 2;
        int destruidas = 0;

        // Destruir pulgas normales primero
        for (int i = pulgasNormales.size() - 1; i >= 0 && destruidas < cantidadADestruir; i--) {
            pulgasNormales.get(i).detener();
            pulgasNormales.remove(i);
            aumentarPuntaje(10);
            destruidas++;
        }

        // Destruir pulgas mutantes si aún faltan
        for (int i = pulgasMutantes.size() - 1; i >= 0 && destruidas < cantidadADestruir; i--) {
            pulgasMutantes.get(i).detener();
            pulgasMutantes.remove(i);
            aumentarPuntaje(20);
            destruidas++;
        }
    }

    /**
     * Agrega una nueva pulga mutante al campo con la tecla N
     *
     * @param e Evento de teclado
     * @since 2.0
     */
    public void agregarPulgaMutante(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_N) {
            int width = 20;
            int height = 20;
            int x, y;

            do {
                x = (int) (Math.random() * (maxX - width));
                y = (int) (Math.random() * (maxY - height));
            } while (colisionConSoldado(x, y, width, height) || colisionConPulgas(x, y, width, height));

            PulgaMutante pulga = new PulgaMutante(x, y, width, height, Color.GREEN, maxX, maxY);
            pulgasMutantes.add(pulga);
            new Thread((java.lang.Runnable) pulga).start();
        }
    }

    private boolean colisionConSoldado(int x, int y, int width, int height) {
        if (soldado == null) {
            return false;
        }
        return x < soldado.getX() + soldado.getWidth()
                && x + width > soldado.getX()
                && y < soldado.getY() + soldado.getHeight()
                && y + height > soldado.getY();
    }

    private boolean colisionConPulgas(int x, int y, int width, int height) {
        for (PulgaNormal p : pulgasNormales) {
            if (x < p.getX() + p.getWidth()
                    && x + width > p.getX()
                    && y < p.getY() + p.getHeight()
                    && y + height > p.getY()) {
                return true;
            }
        }
        for (PulgaMutante p : pulgasMutantes) {
            if (x < p.getX() + p.getWidth()
                    && x + width > p.getX()
                    && y < p.getY() + p.getHeight()
                    && y + height > p.getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Hace saltar todas las pulgas a posiciones aleatorias con la tecla B
     *
     * @param e Evento de teclado
     * @since 2.0
     */
    public void saltarPulgas(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S) { // Corregido a VK_S
            for (PulgaNormal pulga : new ArrayList<>(pulgasNormales)) {
                reposicionarPulga(pulga);
            }
            for (PulgaMutante pulga : new ArrayList<>(pulgasMutantes)) {
                reposicionarPulga(pulga);
            }
        }
    }

    private void reposicionarPulga(Pulga pulga) {
        int width = pulga.getWidth();
        int height = pulga.getHeight();
        int x, y;

        do {
            x = (int) (Math.random() * (maxX - width));
            y = (int) (Math.random() * (maxY - height));
        } while (colisionConSoldado(x, y, width, height) || colisionConPulgas(x, y, width, height));

        pulga.setX(x);
        pulga.setY(y);
    }

    /**
     * Guarda un nuevo puntaje en el archivo de puntuaciones, manteniendo solo
     * los 10 mejores.
     *
     * @param nuevoPuntaje Puntaje a guardar (debe ser un entero positivo).
     * @throws IOException Si hay un error al escribir en el archivo.
     * @since 1.0
     */
    public void guardarPuntaje(int nuevoPuntaje) throws IOException {
        // 1. Leer puntajes existentes
        ArrayList<String> lineas = lector.leer(archivoPuntajes.getPath());
        ArrayList<Integer> puntajes = new ArrayList<>();

        // 2. Convertir líneas a números
        for (String linea : lineas) {
            if (!linea.isBlank()) {
                try {
                    puntajes.add(Integer.parseInt(linea.trim()));
                } catch (NumberFormatException e) {
                    // Ignorar líneas que no son números
                }
            }
        }

        // 3. Agregar nuevo puntaje y ordenar de mayor a menor
        puntajes.add(nuevoPuntaje);
        Collections.sort(puntajes, Collections.reverseOrder());

        // 4. Mantener solo los 10 mejores
        if (puntajes.size() > 10) {
            puntajes = new ArrayList<>(puntajes.subList(0, 10));
        }

        // 5. Escribir de vuelta al archivo
        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (int p : puntajes) {
            nuevasLineas.add(String.valueOf(p));
        }

        escritor.escribir(nuevasLineas, archivoPuntajes.getPath());
    }

    /**
     * Termina la simulación y guarda el puntaje con la tecla Q
     *
     * @param e Evento de teclado
     * @since 2.0
     */
    public void terminarSimulacion(KeyEvent e) throws IOException {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            detenerPulgas();
            guardarPuntaje(puntaje);
            // Aquí podrías indicar que la simulación terminó, quizás cambiando un estado
        }
    }

    private void detenerPulgas() {
        pulgasNormales.forEach(Pulga::detener);
        pulgasMutantes.forEach(Pulga::detener);
    }

    /**
     * Lee los puntajes almacenados en el archivo y los devuelve como una lista
     * de enteros.
     *
     * @return Lista de puntajes ordenados de mayor a menor.
     * @throws IOException Si ocurre un error al leer el archivo.
     * @since 1.0
     */
    public ArrayList<Integer> leerPuntajes() throws IOException {
        ArrayList<String> lineas = lector.leer(archivoPuntajes.getPath()); // Usa el Lector para leer líneas
        ArrayList<Integer> puntajes = new ArrayList<>();

        for (String linea : lineas) {
            if (!linea.isBlank()) {
                try {
                    puntajes.add(Integer.parseInt(linea.trim())); // Convierte cada línea a entero
                } catch (NumberFormatException e) {
                    // Ignorar líneas que no son números
                }
            }
        }

        Collections.sort(puntajes, Collections.reverseOrder()); // Ordena de mayor a menor
        return puntajes;
    }

    public ArrayList<PulgaNormal> getPulgasNormales() {
        return pulgasNormales;
    }

    public ArrayList<PulgaMutante> getPulgasMutantes() {
        return pulgasMutantes;
    }

    public void eliminarPulgaNormal(PulgaNormal p) {
        pulgasNormales.remove(p);
    }

    public void eliminarPulgaMutante(PulgaMutante p) {
        pulgasMutantes.remove(p);
    }

    public void aumentarPuntaje(int puntos) {
        puntaje += puntos;
    }
}