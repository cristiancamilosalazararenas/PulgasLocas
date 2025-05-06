package autonoma.pulgaslocas.elements;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase SpriteContainer. Implementa la interfaz GraphicContainer para manejar una lista de objetos Sprite.
 * tiene funcionalidades basicas para agregar, remover y acceder a los sprites almacenados.
 * 
 * @author Juan Sebastian Lopez Guzman
 * @version 1.0
 * @since 2025-05-05
 */
public class SpriteContainer implements GraphicContainer {
    
    /**
     * Lista que almacena los sprites contenidos en este contenedor.
     */
    private List<Sprite> sprites;
    
    /**
     * Constructor de la clase SpriteContainer.
     * Inicializa una lista vacía para almacenar sprites.
     * 
     * @since 1.0
     */
    public SpriteContainer() {
        this.sprites = new ArrayList<>();
    }
    
    /**
     * Agrega un sprite al contenedor si no es nulo.
     * Implementación del método de la interfaz GraphicContainer.
     * 
     * @param sprite El sprite a agregar al contenedor.
     * @since 1.0
     */
    @Override
    public void agregarSprite(Sprite sprite) {
        if (sprite != null) {
            sprites.add(sprite);
        }
    }
    
    /**
     * Remueve un sprite específico del contenedor.
     * 
     * @param sprite El sprite a remover.
     * @return true si el sprite estaba presente y fue removido, false en caso contrario.
     * @since 1.0
     */
    public boolean removerSprite(Sprite sprite) {
        return sprites.remove(sprite);
    }
    
    /**
     * Obtiene la lista de todos los sprites contenidos.
     * 
     * @return Una lista con todos los sprites almacenados en el contenedor.
     * @since 1.0
     */
    public List<Sprite> getSprites() {
        return sprites;
    }
    
}