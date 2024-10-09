import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class Carta {
    private int valor;
    private String color;
    private String rutaDeImagen;
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private boolean isVisible;
    private boolean estaDibujada;
    private boolean estaVolteada;
    private JButton botonCarta;
    private boolean entregada;
    private Uno juego;

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(valor);
        sb.append(" de color: ");
        sb.append(color);
        return sb.toString();
    }

    public Carta(int valor, String color, Uno juego) {
        this.valor = valor;
        this.color = color;
        this.rutaDeImagen = Rutas.getRuta(valor + color); // Ruta de la imagen
        this.ancho = 100;  // Tamaño de la carta
        this.alto = 150;
        this.x = 0;
        this.y = 0;
        this.isVisible = false;
        this.estaDibujada = false;
        this.estaVolteada = false; // Por defecto, no volteada
        this.juego = juego;
        this.entregada = false;

        botonCarta = new JButton();
        actualizarImagen();

        botonCarta.setBounds(x, y, ancho, alto);
        botonCarta.setFocusPainted(false);
        botonCarta.setBorderPainted(false);
        botonCarta.setContentAreaFilled(false);

        botonCarta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                juego.jugarTurno(Carta.this);
            }
        });
    }

    private void actualizarImagen() {
        ImageIcon icon = new ImageIcon(rutaDeImagen);
        Image img = icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH); // Ajustar imagen al tamaño del botón
        botonCarta.setIcon(new ImageIcon(img));
    }
    public boolean estaEntregada(){
        return this.entregada;
    }

    public void setEntregada(boolean entregada){
        this.entregada = true;
    }
    public int getValor() {return valor;}
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getAncho() { return ancho; }
    public int getAlto() { return alto; }

    public void setAlto(int alto) { this.alto = alto; actualizarImagen(); }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getRutaDeImagen() {
        return rutaDeImagen;
    }
    public void setAncho(int ancho) { this.ancho = ancho; actualizarImagen(); }
    public void setRutaDeImagen(String rutaDeImagen) {
        this.rutaDeImagen = rutaDeImagen;
        actualizarImagen(); // Actualizar la imagen si se cambia la ruta
    }

    public boolean isVisible() { return isVisible; }

    public boolean estaVolteada() {
        return estaVolteada;
    }

    public void voltear() {
        this.estaVolteada = !this.estaVolteada;
        if (estaVolteada) {
            setRutaDeImagen("./media/volteada.png");
//            botonCarta.setIcon(new ImageIcon("./media/volteada.png"));
        } else {
            setRutaDeImagen(Rutas.getRuta(valor + color));
        }
        //actualizarImagen();
    }

    public void makeVisible() {
        isVisible = true;
        Tablero.getTablero().addCarta(botonCarta);
    }

    public void makeInvisible() {
        isVisible = false;
        Tablero.getTablero().removeCarta(botonCarta);
    }

    public void move(int nuevoX, int nuevoY) {
        setX(nuevoX);
        setY(nuevoY);
        botonCarta.setBounds(x, y, ancho, alto);  // Actualizar la posición del botón
    }

    public void desplazar(int nuevoX, int nuevoY) {
        int delay = 5;
        int steps = 10;

        double stepX = (nuevoX - x) / (double) steps;
        double stepY = (nuevoY - y) / (double) steps;

        Timer timer = new Timer(delay, new ActionListener() {
            int currentStep = 0;
            double actualX = x;
            double actualY = y;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStep < steps) {
                    actualX += stepX;
                    actualY += stepY;

                    botonCarta.setBounds((int) actualX, (int) actualY, ancho, alto);
                    currentStep++;
                } else {
                    botonCarta.setBounds(nuevoX, nuevoY, ancho, alto);
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        timer.start(); // Iniciar el movimiento suave
    }

    public boolean sePuedeColocarEn(Carta otraCarta){
        return (
                otraCarta.getColor() == this.color || otraCarta.getValor() == this.valor ||
                        this.valor == 13 || this.valor == 14
                );
    }
}
