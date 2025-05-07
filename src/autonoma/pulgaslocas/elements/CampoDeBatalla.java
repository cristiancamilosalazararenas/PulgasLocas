package autonoma.pulgaslocas.elements;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.KeyEvent;

/**
 * Clase que representa el campo de batalla donde interactúan pulgas normales, pulgas mutantes y un soldado.
 * Maneja eventos de teclado, colisiones, generación de pulgas y almacenamiento de puntajes.
 *
 *@author Juan José Morales
 * @version 20250506
 * @since 1.0
 */
public class CampoDeBatalla {

    // Archivo donde se guardan los puntajes
    private File archivoPuntajes;
    
    // Objetos para lectura y escritura de archivos
    private final Lector lector;
    private final Escritor escritor;

    // Listas de pulgas normales y mutantes activas en el campo
    private ArrayList<PulgaNormal> pulgasNormales;
    private ArrayList<PulgaMutante> pulgasMutantes;

    // Soldado que se mueve por el campo
    private Soldado soldado;

    // Dimensiones máximas del campo
    private int maxX;
    private int maxY;

    // Puntaje actual del jugador
    private int puntaje;

    /**
     * Constructor que inicializa el campo de batalla y prepara el archivo de puntajes.
     *
     * @param rutaArchivo Ruta del archivo donde se almacenan los puntajes.
     * @throws IOException Si ocurre un error al crear o acceder al archivo.
     */
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

    // Métodos getters
    public int getMaxX() { return maxX; }

    public int getMaxY() { return maxY; }

    public int getPuntaje() { return puntaje; }

    /**
     * Establece el soldado dentro del campo y ajusta sus límites.
     *
     * @param soldado Objeto Soldado.
     */
    public void setSoldado(Soldado soldado) {
        this.soldado = soldado;
        if (soldado != null) {
            soldado.inicializarLimites(maxX, maxY);
        }
    }

    /**
     * Establece los límites del campo de batalla.
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
     * Agrega una pulga normal al presionar la tecla 'M'.
     *
     * @param e Evento de teclado
     * @since 2.0
     */
    public void agregarPulgaNormal(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_M) {
            int width = 20;
            int height = 20;
            int x, y;

            // Posicionamiento aleatorio sin colisiones
            do {
                x = (int) (Math.random() * (maxX - width));
                y = (int) (Math.random() * (maxY - height));
            } while (colisionConSoldado(x, y, width, height) || colisionConPulgas(x, y, width, height));

            PulgaNormal pulga = new PulgaNormal(x, y, width, height, Color.RED, maxX, maxY);
            pulgasNormales.add(pulga);
            new Thread((java.lang.Runnable) pulga).start();
        }
    }

    /**
     * Agrega una pulga normal automáticamente sin evento de teclado.
     */
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
        new Thread((java.lang.Runnable) (Runnable) pulga).start(); // Inicia en un nuevo hilo
    }

    /**
     * Agrega una pulga mutante automáticamente.
     */
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
        new Thread((java.lang.Runnable) pulga).start(); // Inicia en nuevo hilo
    }

    /**
     * Maneja eventos de teclado para controlar la simulación.
     *
     * @param e Evento de teclado
     * @throws IOException En caso de error al guardar puntaje
     */
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
                agregarPulgaNormalAutomatica();
                break;
            case KeyEvent.VK_SPACE:
                destruirMitadPulgas();
                break;
        }
    }

    /**
     * Elimina la mitad de las pulgas activas en el campo (priorizando normales).
     */
    private void destruirMitadPulgas() {
        ArrayList<PulgaNormal> copiaNormales = new ArrayList<>(pulgasNormales);
        ArrayList<PulgaMutante> copiaMutantes = new ArrayList<>(pulgasMutantes);

        int totalPulgas = copiaNormales.size() + copiaMutantes.size();
        if (totalPulgas == 0) return;

        int cantidadADestruir = (int) Math.ceil(totalPulgas / 2.0);
        int destruidas = 0;

        int aDestruirNormales = Math.min(copiaNormales.size(), cantidadADestruir);
        for (int i = 0; i < aDestruirNormales; i++) {
            PulgaNormal p = copiaNormales.get(i);
            p.detener();
            pulgasNormales.remove(p);
            aumentarPuntaje(10);
            destruidas++;
        }

        if (destruidas < cantidadADestruir) {
            int aDestruirMutantes = cantidadADestruir - destruidas;
            for (int i = 0; i < aDestruirMutantes && i < copiaMutantes.size(); i++) {
                PulgaMutante p = copiaMutantes.get(i);
                p.detener();
                pulgasMutantes.remove(p);
                aumentarPuntaje(20);
            }
        }
    }

    /**
     * Agrega una pulga mutante al presionar 'N'.
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

    // Métodos privados de colisión
    private boolean colisionConSoldado(int x, int y, int width, int height) {
        if (soldado == null) return false;
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
                    && y + height > p.getY()) return true;
        }
        for (PulgaMutante p : pulgasMutantes) {
            if (x < p.getX() + p.getWidth()
                    && x + width > p.getX()
                    && y < p.getY() + p.getHeight()
                    && y + height > p.getY()) return true;
        }
        return false;
    }

    /**
     * Hace que todas las pulgas salten a nuevas posiciones aleatorias al presionar 'S'.
     *
     * @param e Evento de teclado
     * @since 2.0
     */
    public void saltarPulgas(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S) {
            for (PulgaNormal pulga : new ArrayList<>(pulgasNormales)) {
                reposicionarPulga(pulga);
            }
            for (PulgaMutante pulga : new ArrayList<>(pulgasMutantes)) {
                reposicionarPulga(pulga);
            }
        }
    }

    // Reposiciona una pulga en coordenadas válidas
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

    // Guarda el puntaje en el archivo
    public void guardarPuntaje(int nuevoPuntaje) throws IOException {
        ArrayList<String> lineas = lector.leer(archivoPuntajes.getPath());
        ArrayList<Integer> puntajes = new ArrayList<>();

        for (String linea : lineas) {
            if (!linea.isBlank()) {
                try {
                    puntajes.add(Integer.parseInt(linea.trim()));
                } catch (NumberFormatException e) {
                    // Ignorar
                }
            }
        }

        puntajes.add(nuevoPuntaje);

        while (puntajes.size() > 10) {
            puntajes.remove(0);
        }

        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (int p : puntajes) {
            nuevasLineas.add(String.valueOf(p));
        }

        escritor.escribir(nuevasLineas, archivoPuntajes.getPath());
    }

    /**
     * Termina la simulación al presionar 'Q' y guarda el puntaje.
     *
     * @param e Evento de teclado
     * @throws IOException Si hay error de escritura
     * @since 2.0
     */
    public void terminarSimulacion(KeyEvent e) throws IOException {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            detenerPulgas();
            guardarPuntaje(puntaje);
            this.puntaje = 0;
        }
    }

    private void detenerPulgas() {
        pulgasNormales.forEach(Pulga::detener);
        pulgasMutantes.forEach(Pulga::detener);
    }

    /**
     * Devuelve la lista de puntajes ordenados de mayor a menor.
     *
     * @return Lista de puntajes
     * @throws IOException Si ocurre un error al leer el archivo
     * @since 1.0
     */
    public ArrayList<Integer> leerPuntajes() throws IOException {
        ArrayList<String> lineas = lector.leer(archivoPuntajes.getPath());
        ArrayList<Integer> puntajes = new ArrayList<>();

        for (String linea : lineas) {
            if (!linea.isBlank()) {
                try {
                    puntajes.add(Integer.parseInt(linea.trim()));
                } catch (NumberFormatException e) {
                    // Ignorar
                }
            }
        }

        Collections.sort(puntajes, Collections.reverseOrder());
        return puntajes;
    }

    // Métodos para obtener y eliminar pulgas
    public ArrayList<PulgaNormal> getPulgasNormales() { return pulgasNormales; }

    public ArrayList<PulgaMutante> getPulgasMutantes() { return pulgasMutantes; }

    public void eliminarPulgaNormal(PulgaNormal p) { pulgasNormales.remove(p); }

    public void eliminarPulgaMutante(PulgaMutante p) { pulgasMutantes.remove(p); }

    // Aumenta el puntaje del jugador
    public void aumentarPuntaje(int puntos) { puntaje += puntos; }
}
