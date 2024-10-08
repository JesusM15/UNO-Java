public class Carta {
    private int valor;
    private String color;
    private String rutaDeImagen;
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private boolean isVisible;

    // Constructor de Carta
    public Carta(int valor, String color) {
        this.valor = valor;
        this.color = color;
        this.rutaDeImagen = Rutas.getRuta(valor + color);
        this.ancho = 100;  // Ancho de la carta
        this.alto = 150;   // Alto de la carta
        this.x = 0;        // Posición X inicial
        this.y = 0;        // Posición Y inicial
        this.isVisible = false;
    }

    // Métodos getter y setter para posición y tamaño
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getAncho() { return ancho; }
    public int getAlto() { return alto; }

    public void setAlto(int alto) { this.alto = alto; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getRutaDeImagen() {
        return rutaDeImagen;
    }
    public void setAncho(int ancho) { this.ancho = ancho; }
    public void setRutaDeImagen(String rutaDeImagen) {
        this.rutaDeImagen = rutaDeImagen;
    }
    public boolean isVisible() { return isVisible; }


    // Método para hacer visible la carta
    public void makeVisible() {
        isVisible = true;
        draw();  // Dibujar la carta
    }

    // Método para hacer invisible la carta
    public void makeInvisible() {
        erase();  // Borrar la carta
        isVisible = false;
    }

    // Método para dibujar la carta en el tablero
    public void draw() {
        if (isVisible) {
            Tablero.getTablero().draw(this);  // Llamar al tablero para que dibuje la carta
        }
    }

    // Método para borrar la carta del tablero
    public void erase() {
        if (isVisible) {
            Tablero.getTablero().erase(this);  // Llamar al tablero para que borre la carta
        }
    }

    // Método para mover la carta
    public void move(int nuevoX, int nuevoY) {
        erase();  // Borrar la carta en su posición actual
        setX(nuevoX);  // Actualizar la nueva posición
        setY(nuevoY);
        draw();  // Dibujar en la nueva posición
    }
}
