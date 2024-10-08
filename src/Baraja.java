import java.util.ArrayList;
import java.util.Random;
public class Baraja {
    private ArrayList<Carta> baraja;
    public Baraja() {
        baraja = new ArrayList<Carta>();
    }
    public void construitBaraja() {
        int cont = 0;
        for(int i =0;i<4;i++){
            if(i==0){
                for(int j =0;j<2;j++){
                    if(cont==0){
                        for(int k =0;k<13;k++){
                            Carta carta = new Carta(k,"rojo");
                            baraja.add(carta);
                        }
                        cont = cont +1;
                    }else{
                        for(int k =1;k<13;k++){
                            Carta carta = new Carta(k,"rojo");
                            baraja.add(carta);
                        }
                    }
                }
            }else if(i==1){
                for(int j =0;j<2;j++){
                    if(cont==0){
                        for(int k =0;k<13;k++){
                            Carta carta = new Carta(k,"amarillo");
                            baraja.add(carta);
                        }
                    }else{
                        for(int k =1;k<13;k++){
                            Carta carta = new Carta(k,"amarillo");
                            baraja.add(carta);
                        }
                    }
                }
            }else if(i==2){
                for(int j =0;j<2;j++){
                    if(cont==0){
                        for(int k =0;k<13;k++){
                            Carta carta = new Carta(k,"verde");
                            baraja.add(carta);
                        }
                    }else{
                        for(int k =1;k<13;k++){
                            Carta carta = new Carta(k,"verde");
                            baraja.add(carta);
                        }
                    }
                }
            }else if(i==3){
                for(int j =0;j<2;j++){
                    if(cont==0){
                        for(int k =0;k<13;k++){
                            Carta carta = new Carta(k,"azul");
                            baraja.add(carta);
                        }
                    }else{
                        for(int k =1;k<13;k++){
                            Carta carta = new Carta(k,"azul");
                            baraja.add(carta);
                        }
                    }
                }
            }
            cont = 0;
        }
        for(int i=0;i<8;i++){
            if(i<=3) {
                Carta carta1 = new Carta(13, "SC");
                baraja.add(carta1);
            }else {
                Carta carta2 = new Carta(14, "SC");
                baraja.add(carta2);
            }
        }
    }
    public void mezclarCartas(){
        int cont = 0;
        int g = 0;
        int[] arr = new int[baraja.size()];
        for(int i=0;i<baraja.size();i++){
            arr[i]=0;
        }
        Random rand = new Random();
        while(cont<baraja.size()){
            do {
                g = rand.nextInt(baraja.size());
            }while (arr[g]==1);
            arr[g]=1;
            Carta cartaCont = baraja.get(g);
            baraja.set(g,baraja.get(cont));
            baraja.set(cont,cartaCont);
            cont = cont + 1;
        }
    }
    public void mostrar(){
        for(Carta carta:baraja){
            System.out.println(carta);
        }
    }
    public ArrayList<Carta> entregarCartas(int x) {
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        for(int i=0;i<x;i++){
            cartas.add(baraja.get(i));
            baraja.remove(0);
        }
        return cartas;
    }
}
