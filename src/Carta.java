public class Carta {
    private int valor;
    private String color;
    private boolean estaVolteada = false;

    public Carta(int valor, String color) {
        this.valor = valor;
    }

    public int getValor() {
        return this.valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isEstaVolteada() {
        return this.estaVolteada;
    }

    public void setEstaVolteada(boolean estaVolteada) {
        this.estaVolteada = estaVolteada;
    }

    public boolean puedeColocarse(Carta cartaArriba){
        return (
                this.valor == cartaArriba.getValor() ||
                this.color == cartaArriba.getColor() || this.valor == -1
        );
    }


}

