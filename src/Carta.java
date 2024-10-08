public class Carta {
    private int valor;
    private String color;
    private String rutaDeImagen;
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private boolean isVisible;
    private boolean estaDibujada;
    private boolean estaVolteada;

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(valor);
        sb.append(" de color: ");
        sb.append(color);
        return sb.toString();
    }

    public Carta(int valor, String color) {
        this.valor = valor;
        this.color = color;
        this.rutaDeImagen = Rutas.getRuta(valor + color);
        this.ancho = 100;
        this.alto = 150;
        this.x = 0;
        this.y = 0;
        this.isVisible = false;
        this.estaDibujada = false;
        this.estaVolteada = true;
    }

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

    public boolean estaVolteada() {
        return estaVolteada;
    }

    public void voltear(){
        this.estaVolteada = !this.estaVolteada;
        if (this.estaDibujada){
            makeInvisible();
            makeVisible();
        }
    }

    public void makeVisible() {
        isVisible = true;
        draw();
    }

    public void makeInvisible() {
        erase();
        isVisible = false;
    }

    // Método para dibujar la carta en el tablero
    private void draw() {
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
