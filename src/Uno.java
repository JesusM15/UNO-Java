import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Uno {
    private ArrayList<Jugador> jugadores;
    private Baraja baraja;
    private int turnoActual = 0;
    private Tablero tablero = Tablero.getTablero();
    private Carta ultimaCarta;
    private boolean turnoCancelado = false;
    private boolean juegoTerminado = false;
    private Jugador ganador = null;
    private boolean prevTurnoCancelado = false;
  
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
        // checar si ya no hay cartas y ver si cualquier jugador puede colocar
        // si no puede colocar ni uno gameover, de lo contrario, el que pueda colocar
        // le damos el turno
        return jugadores.stream().filter(jugador ->
                jugador.getMano().obtenerMano().isEmpty()
        ).count() > 0;
    }

    public void jugarTurno(Carta carta){
        Jugador jugador = obtenerJugador();
        if(!jugador.getMano().existeLaCarta(carta) || !carta.sePuedeColocarEn(this.ultimaCarta) || ganador != null){
            return;
        }

        jugador.getMano().removerCarta(carta);
        colocarCarta(carta);

        if(manejarComodin(carta)){
            this.turnoCancelado = false;
        }

        jugador.getMano().voltearTodasLasCartas();
        obtenerTurno();
    }

    public void jugar(){
        prepararBaraja();
        repartirCartas(7);
        colocarPrimerCarta();
        while (ultimaCarta.getValor() == 13 || ultimaCarta.getValor() == 14){
            colocarPrimerCarta();
        }
        obtenerTurno();

    }

    public void colocarCarta(Carta carta){
        this.ultimaCarta = carta;
        tablero.tirarCarta(carta);
        baraja.agregarCartaJugada(carta);
    }

    public void colocarPrimerCarta(){
        Carta carta = baraja.entregarCartas(1).getFirst();
        this.ultimaCarta = carta;
        tablero.encimarCarta(carta);
        carta.voltear();
        baraja.agregarCartaJugada(carta);
    }
    public  boolean manejarComodin(Carta carta){
        String color;
        int valor = carta.getValor();
        if(carta.getValor()<10){
            return false;
        }
        switch(valor){
            case 10: // saltar carta
                this.turnoCancelado = true;
                this.prevTurnoCancelado = true;
                obtenerTurno();
                break;
            case 11: // mas dos
                this.turnoCancelado = true;
                this.prevTurnoCancelado = true;
                obtenerTurno();
                comerCartas(2);
                obtenerJugador().getMano().obtenerMano().stream().filter(
                        carta1 -> !carta1.estaVolteada()
                ).forEach(carta1 -> {
                    carta1.voltear();
                });
                break;
            case 12: // reversa
                break;
            case 13:  // mas cuatro
                this.turnoCancelado = true;
                this.prevTurnoCancelado = true;
                obtenerTurno();
                comerCartas(4);
                obtenerJugador().getMano().obtenerMano().stream().filter(
                        carta1 -> !carta1.estaVolteada()
                ).forEach(carta1 -> {
                    carta1.voltear();
                });
                do {
                    color= JOptionPane.showInputDialog(null, "elige el color (azul,rojo,verde,amarillo):");
                }while(!color.equals("azul") && !color.equals("rojo") && !color.equals("verde") && !color.equals("amarillo"));
                this.ultimaCarta.setColor(color);
                break;
            case 14:
                do {
                    color= JOptionPane.showInputDialog(null, "elige el color (azul,rojo,verde,amarillo):");
                }while(!color.equals("azul") && !color.equals("rojo") && !color.equals("verde") && !color.equals("amarillo"));
                ultimaCarta.setColor(color);
                break;
        }

        return true;
    }
    public void comerCartas(int x){
        for(int i=0;i<x;i++){
            comer();
        }
    }
    public boolean checarSiEsComidin(Carta carta){
        if(carta.getValor()==13 || carta.getValor()==14){
            return true;
        }
        return false;
    }
    public void repartirCartas(int cantidadDeCartas){
        int n = cantidadDeCartas;
        ArrayList<Carta> cartas;
        for(Jugador jugador : jugadores){
            cartas = baraja.entregarCartas(n);
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

    public void reproducirSonido() {
        Toolkit.getDefaultToolkit().beep();
    }

    public void obtenerTurno(){
        if(turnoActual == this.jugadores.size() || turnoActual == 0){
            this.turnoActual = 1;
        } else {
            this.turnoActual++;
        }

        if(!turnoCancelado) {
            if(!prevTurnoCancelado){
                reproducirSonido();
                JOptionPane.showMessageDialog(null, "Iniciar turno");
            }else{
                this.prevTurnoCancelado = false;
            }
            obtenerJugador().getMano().voltearTodasLasCartas();
        }
//        System.out.println("Mano de " + turnoActual);
//        obtenerJugador().getMano().imprimirMano();
        while (!puedeColocar() && !turnoCancelado && ganador == null){
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
