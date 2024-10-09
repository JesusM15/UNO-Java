import java.util.ArrayList;
import java.util.stream.Collectors;
public class Mano {
    private ArrayList<Carta> mano;
    public Mano() {
        mano = new ArrayList();
    }

    public void guardarCartas(ArrayList<Carta> cartas) {
        mano.addAll(cartas);
    }

    public ArrayList<Carta> obtenerCartas(int x) {
        ArrayList<Carta> cartas = new ArrayList();
        for(int i=0;i<x;i++) {
            cartas.add(mano.get(i));
        }
        return cartas;
    }
    public void recibirCartas(ArrayList<Carta> cartas) {
        for(Carta carta : cartas) {
            mano.add(carta);
        }
    }
    public boolean sePuedePonerAlgunaCarta(Carta carta) {
        return mano.stream().anyMatch(carta1 -> carta1.getValor() == carta.getValor() ||
                carta1.getColor() == carta.getColor() ||
                carta1.getValor() == 13 ||
                carta1.getValor() == 14);
    }
    public void voltearTodasLasCartas() {
        for(Carta carta : mano) {
            carta.voltear();
        }
    }

}
