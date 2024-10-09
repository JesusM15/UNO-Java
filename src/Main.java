import javax.swing.*;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
//        Baraja baraja = new Baraja();
//        baraja.construirBaraja();
//        baraja.mezclarCartas();

        ArrayList<Jugador> jugadores = new ArrayList<>();
        int nPlayers = 2;
        for(int i = 0; i<nPlayers; i++){
            String name = JOptionPane.showInputDialog(null, "Nombre jugador #" + (i+1));
            jugadores.add(new Jugador(name, (i+1)));
        }

        Uno game = new Uno(jugadores);
        game.jugar();

    }
}
