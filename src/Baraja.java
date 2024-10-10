import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

public class Baraja {
    private ArrayList<Carta> baraja;
    private ArrayList<Carta> cartasJugadas;
    private Uno juego;

    public Baraja(Uno juego) {
        baraja = new ArrayList<Carta>();
        cartasJugadas = new ArrayList<Carta>();
        this.juego = juego;
    }
    public void construirBaraja() {
        int cont = 0;
        for(int i =0;i<4;i++){
            if(i==0){
                for(int j =0;j<2;j++){
                    if(cont==0){
                        for(int k =0;k<13;k++){
                            Carta carta = new Carta(k,"rojo", juego);
                            baraja.add(carta);
                        }
                        cont = cont +1;
                    }else{
                        for(int k =1;k<13;k++){
                            Carta carta = new Carta(k,"rojo", juego);
                            baraja.add(carta);
                        }
                    }
                }
            }else if(i==1){
                for(int j =0;j<2;j++){
                    if(cont==0){
                        for(int k =0;k<13;k++){
                            Carta carta = new Carta(k,"amarillo", juego);
                            baraja.add(carta);
                        }
                    }else{
                        for(int k =1;k<13;k++){
                            Carta carta = new Carta(k,"amarillo", juego);
                            baraja.add(carta);
                        }
                    }
                }
            }else if(i==2){
                for(int j =0;j<2;j++){
                    if(cont==0){
                        for(int k =0;k<13;k++){
                            Carta carta = new Carta(k,"verde", juego);
                            baraja.add(carta);
                        }
                    }else{
                        for(int k =1;k<13;k++){
                            Carta carta = new Carta(k,"verde", juego);
                            baraja.add(carta);
                        }
                    }
                }
            }else if(i==3){
                for(int j =0;j<2;j++){
                    if(cont==0){
                        for(int k =0;k<13;k++){
                            Carta carta = new Carta(k,"azul", juego);
                            baraja.add(carta);
                        }
                    }else{
                        for(int k =1;k<13;k++){
                            Carta carta = new Carta(k,"azul", juego);
                            baraja.add(carta);
                        }
                    }
                }
            }
            cont = 0;
        }
        for(int i=0;i<8;i++){
            if(i<=3) {
                Carta carta1 = new Carta(13, "SC", juego);
                baraja.add(carta1);
            }else {
                Carta carta2 = new Carta(14, "SC", juego);
                baraja.add(carta2);
            }
        }
    }
    public void mezclarCartas(){
        Collections.shuffle(baraja);
    }
    public void mostrar(){
        for(Carta carta:baraja){
            System.out.println(carta);
        }
    }
    public ArrayList<Carta> entregarCartas(int x) {
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        for(int i=0;i<x;i++){
            if (baraja.isEmpty()) {
                rebarajarCartasJugadas();
            }
            cartas.add(baraja.get(0));
            baraja.remove(0);
        }
        return cartas;
    }

    public void agregarCartaJugada(Carta carta) {
        if(carta.estaVolteada()){
            carta.voltear();
        }
        cartasJugadas.add(carta);
    }

    public void rebarajarCartasJugadas() {
        if (cartasJugadas.size() > 1) {
            Carta ultimaCarta = cartasJugadas.remove(cartasJugadas.size() - 1);
            baraja.addAll(cartasJugadas);
            cartasJugadas.clear();
            cartasJugadas.add(ultimaCarta);
            mezclarCartas();
        }
    }

    public void voltearCartas(){
        for(Carta carta:baraja){
            carta.voltear();
        }
    }

    public void moverBarajaACentro(int centroX, int centroY){
        for(Carta carta:baraja){
            carta.move(centroX, centroY);
            carta.makeVisible();
        }
    }

}
