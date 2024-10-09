import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private int id;
    private Mano mano;

    public Jugador(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
        mano = new Mano();
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public Mano getMano() {return mano;}

    public void tomarCartas(ArrayList<Carta> cartas) {
        mano.recibirCartas(cartas);
    }
}
