import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Uno {
    private ArrayList<Jugador> jugadores;
    private Baraja baraja;
    private int turnoActual = 0;
    private Tablero tablero = Tablero.getTablero();
    private Carta ultimaCarta;
    private boolean juegoTerminado = false;
    private Jugador ganador = null;

    public Uno(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.baraja = new Baraja(Uno.this);
    }

    public void obtenerGanador(){
        jugadores.stream().filter(
                jugador1 -> jugador1.getMano().obtenerMano().isEmpty()
        ).forEach(jugador -> ganador = jugador);
    }

    public boolean checarSiElJuegoTermino(){
        return jugadores.stream().filter(jugador ->
                jugador.getMano().obtenerMano().isEmpty()
        ).count() > 0;
    }

    public void jugarTurno(Carta carta){
        Jugador jugador = obtenerJugador();
        if(!jugador.getMano().existeLaCarta(carta) || !carta.sePuedeColocarEn(this.ultimaCarta) || ganador != null){
            return;
        }
        System.out.println(carta);
        colocarCarta(carta);
        jugador.getMano().removerCarta(carta);
        jugador.getMano().voltearTodasLasCartas();
        obtenerTurno();
    }

    public void jugar(){
        prepararBaraja();
        repartirCartas();
        colocarPrimerCarta();
        while (ultimaCarta.getValor() == 13 || ultimaCarta.getValor() == 14){
            colocarPrimerCarta();
        }
        obtenerTurno();
    }

    public void colocarCarta(Carta carta){
        this.ultimaCarta = carta;
        tablero.tirarCarta(carta);
    }

    public void colocarPrimerCarta(){
        Carta carta = baraja.entregarCartas(1).getFirst();
        this.ultimaCarta = carta;
        tablero.encimarCarta(carta);
        carta.voltear();
    }

    public void repartirCartas(){
        int n = 7;
        ArrayList<Carta> cartas;
        for(Jugador jugador : jugadores){
            cartas = baraja.entregarCartas(n);
            System.out.println(jugador.getNombre());
            for(Carta carta : cartas){
                jugador.getMano().agregarCarta(carta);
                tablero.entregarCarta(jugador, carta);
                try {
                    Thread.sleep(100);  // Añadir un retraso de 200ms entre cada carta
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public Jugador obtenerJugador(){
        return this.jugadores.get(this.turnoActual - 1);
    }

    public void obtenerTurno(){
        if(turnoActual == this.jugadores.size() || turnoActual == 0){
            this.turnoActual = 1;
        } else {
            this.turnoActual++;
        }

        obtenerJugador().getMano().voltearTodasLasCartas();

        while (!puedeColocar()){
            comer();
        }

        if(checarSiElJuegoTermino()){
            obtenerGanador();
            JOptionPane.showMessageDialog(null, "Juego terminado ganador: " + ganador.getNombre());
        }
    }

    public void comer(){
        int n = 1;
        Jugador jugador = obtenerJugador();
        ArrayList<Carta> cartas;
        cartas = baraja.entregarCartas(n);

        for(Carta carta : cartas){
            carta.voltear();
            jugador.getMano().agregarCarta(carta);
            tablero.entregarCarta(jugador, carta);
//            try {
//                Thread.sleep(100);  // Añadir un retraso de 200ms entre cada carta
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

    }

    public boolean puedeColocar(){
        return obtenerJugador().getMano().sePuedePonerAlgunaCarta(ultimaCarta);
    }

    public void prepararBaraja(){
        int cartaAncho = 100;
        int cartaAlto = 150;

        baraja.construirBaraja();
        baraja.mezclarCartas();
        baraja.voltearCartas();
        baraja.moverBarajaACentro((1400 / 2) - cartaAncho*2,
                tablero.getHeight() / 2 - cartaAlto);
    }

}
