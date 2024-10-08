public class Main {
    public static void main(String[] args) {
        // Crear el tablero (no es necesario hacer más de una vez, es un singleton)
        Tablero tablero = Tablero.getTablero();

        // Crear algunas cartas
        Carta cartaRoja = new Carta(5, "rojo");
        Carta cartaAzul = new Carta(7, "azul");
        Carta comodin = new Carta(12, "SC");

        // Hacer visibles las cartas
        cartaRoja.makeVisible();
        cartaAzul.makeVisible();
        comodin.makeVisible();

        cartaAzul.move(400, 300);
        // Mover la carta roja a una nueva posición
        cartaRoja.move(200, 250);
    }
}
