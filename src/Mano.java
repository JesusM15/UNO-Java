import java.util.ArrayList;
public class Mano {
    private ArrayList<Carta> mano;
    public Mano() {
        mano = new ArrayList();
    }
    public void guardarCartas(ArrayList<Carta> cartas) {
        mano.addAll(cartas);
    }
    public ArrayList<Carta> obtenerCartas(int x) {
        ArrayList<Carta> aux = new ArrayList();
        for(int i = 0; i < x; i++) {
            aux.add(mano.get(i));
        }
        return aux;
    }
}
