import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Tablero {
    private static Tablero tableroSingleton;  // Singleton
    private JFrame frame;
    private JPanel panel;
    private Graphics2D graphic;
    private Image tableroImage;
    private ArrayList<Object> objetos;  // Lista de cartas y objetos a dibujar
    private HashMap<Object, Image> cartas;  // Mapa de cartas y sus imágenes

    // Constructor privado
    private Tablero() {
        frame = new JFrame();
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(tableroImage, 0, 0, null);
            }
        };

        frame.setTitle("Tablero de Cartas");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);

        panel.setPreferredSize(new Dimension(800, 600));
        tableroImage = panel.createImage(800, 600);
        graphic = (Graphics2D) tableroImage.getGraphics();
        objetos = new ArrayList<>();
        cartas = new HashMap<>();
    }

    // Método para obtener el singleton del tablero
    public static Tablero getTablero() {
        if (tableroSingleton == null) {
            tableroSingleton = new Tablero();
        }
        return tableroSingleton;
    }

    // Método para dibujar una carta en el tablero
    public void draw(Carta carta) {
        try {
            Image img = new ImageIcon(carta.getRutaDeImagen()).getImage();
            cartas.put(carta, img);  // Guardar la carta y su imagen en el mapa
            objetos.add(carta);  // Agregar la carta a la lista de objetos
            redraw();
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen: " + e.getMessage());
        }
    }

    // Método para borrar una carta del tablero
    public void erase(Carta carta) {
        objetos.remove(carta);  // Quitar la carta de la lista
        cartas.remove(carta);  // Quitar la carta del mapa de imágenes
        redraw();
    }

    // Método para redibujar todos los objetos
    private void redraw() {
        clear();  // Limpiar el tablero
        for (Object obj : objetos) {
            if (obj instanceof Carta) {
                Carta carta = (Carta) obj;
                Image img = cartas.get(carta);
                if (img != null) {
                    graphic.drawImage(img, carta.getX(), carta.getY(), carta.getAncho(), carta.getAlto(), null);
                }
            }
        }
        panel.repaint();
    }

    // Método para limpiar el tablero
    private void clear() {
        graphic.clearRect(0, 0, panel.getWidth(), panel.getHeight());
        panel.repaint();
    }
}
