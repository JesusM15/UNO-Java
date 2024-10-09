import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tablero {
    private static Tablero tableroSingleton;
    private JFrame frame;
    private JPanel panel;
    private ArrayList<JButton> botonesCartas;  // Lista de botones de cartas

    private Tablero() {
        frame = new JFrame("Tablero de Cartas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 700);

        panel = new JPanel();
        panel.setLayout(null);  // Layout nulo para posicionamiento absoluto
        panel.setPreferredSize(new Dimension(1400, 700));  // Tamaño del panel

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

    public int getWidth(){
        return panel.getWidth();
    }
}
