import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tablero {
    private static Tablero tableroSingleton;
    private JFrame frame;
    private JPanel panel;
    private ArrayList<JButton> botonesCartas;  // Lista de botones de cartas
    private ArrayList<Carta> j1Cartas;
    private ArrayList<Carta> j2Cartas;
    private int anchoCarta = 100;
    private int altoCarta = 150;
    private int width = 1400;
    private int height = 700;
    private Carta ultimaCarta = null;

    private Tablero() {
        j1Cartas = new ArrayList<>();
        j2Cartas = new ArrayList<>();
        frame = new JFrame("Tablero de Cartas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, 700);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        panel = new JPanel();
        panel.setLayout(null);  // Layout nulo para posicionamiento absoluto
        panel.setPreferredSize(new Dimension(width, 700));  // Tamaño del panel
        panel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        frame.add(scrollPane);
        frame.setVisible(true);

        botonesCartas = new ArrayList<>();
    }

    public static Tablero getTablero() {
        if (tableroSingleton == null) {
            tableroSingleton = new Tablero();
        }
        return tableroSingleton;
    }

    public void addCarta(JButton botonCarta) {
        botonesCartas.add(botonCarta);
        panel.add(botonCarta);
        panel.repaint();
    }

    public void removeCarta(JButton botonCarta) {
        botonesCartas.remove(botonCarta);
        panel.remove(botonCarta);
        panel.repaint();
    }

    private void reajustarPosiciones(ArrayList<Carta> cartas, int posy) {
        int posx = 0;
        for (Carta carta : cartas) {
            carta.makeInvisible();
            carta.move(posx, posy);
            carta.makeVisible();
            posx += anchoCarta + 10;
        }
    }

    public void encimarCarta(Carta carta) {
      int posx = 1400 / 2 - anchoCarta + 4;
      int posy = getHeight() / 2 - altoCarta;

      carta.desplazar(posx, posy);
    if(ultimaCarta != null){
        ultimaCarta.makeInvisible();
    }
      setUltimaCarta(carta);
    };

    public void tirarCarta(Carta carta) {
        int posx = 1400 / 2 - anchoCarta + 4;
        int posy = getHeight() / 2 - altoCarta;
        ArrayList<Carta> jcartas = carta.getY() == 0? j2Cartas : j1Cartas;
        jcartas.remove(carta);
        int posYCarta = carta.getY();
        reajustarPosiciones(jcartas, posYCarta);

        carta.desplazar(posx, posy);
        this.ultimaCarta.makeInvisible();
        setUltimaCarta(carta);
    }

    public void entregarCarta(Jugador jugador, Carta carta) {
        int posx = 0;
        int espacioEntreCartas = 10;
        int posy = jugador.getId() == 2 ? 0 : getHeight() - altoCarta;

        ArrayList<Carta> cartas = jugador.getMano().obtenerMano();
        posx += (cartas.size() - 1) * (anchoCarta + espacioEntreCartas);

        necesitaRedimensionarse(posx, posy);

        carta.desplazar(posx, posy);

        if(jugador.getId() == 2){
            j2Cartas.add(carta);
        }else{
            j1Cartas.add(carta);
        }
    }

    public int getWidth(){
        return panel.getWidth();
    }

    public int getHeight(){
        return panel.getHeight();
    }

    public void necesitaRedimensionarse(int posx, int posy){
        int anchoRequerido = posx + anchoCarta + 10;

        if (anchoRequerido > getWidth()) {
            this.width = anchoRequerido;
        }

        panel.setPreferredSize(new Dimension(this.width, getHeight()));
        panel.revalidate();
    }

    public Carta getUltimaCarta() {
        return ultimaCarta;
    }

    public void setUltimaCarta(Carta carta) {
        ultimaCarta = carta;
    }
}
