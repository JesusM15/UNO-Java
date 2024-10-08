public class Main {
    public static void main(String[] args) {
        Tablero tablero = Tablero.getTablero();
        Baraja baraja = new Baraja();
         baraja.construitBaraja();
         baraja.mezclarCartas();

        int posx = 0;
        int posy = 0;
//        for(Carta carta : baraja.entregarCartas(30)){
//            carta.move(posx, posy);
//            carta.makeVisible();
//            posx = carta.getAncho() + posx;
//
//            if(posx == tablero.getWidth()){
//                posx = 0;
//                posy = carta.getAlto() + posy;
//            }
//
//
//        }

    }
}
