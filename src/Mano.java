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

    public ArrayList<Carta> obtenerMano(){
        return this.mano;
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
        return mano.stream().filter(carta1 ->
                carta1.sePuedeColocarEn(carta)
        ).count()>0;
    }
    public void voltearTodasLasCartas() {
        for(Carta carta : mano) {
            carta.voltear();
        }
    }

    public void agregarCarta(Carta carta){
        mano.add(carta);
    }

    public boolean existeLaCarta(Carta otraCarta){
        return mano.stream().filter(
                carta -> otraCarta.getValor() == carta.getValor() &&
                        otraCarta.getColor() == carta.getColor() &&
                        otraCarta.getX() == carta.getX()
        ).count() > 0;
    }

    public void removerCarta(Carta otraCarta){
        mano.remove(otraCarta);
    }

    public void imprimirMano(){
        mano.stream().forEach(System.out::println);
    }
}
