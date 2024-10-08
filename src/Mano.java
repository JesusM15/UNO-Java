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
        return mano.stream()
                .limit(x)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
