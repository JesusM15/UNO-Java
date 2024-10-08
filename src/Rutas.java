import java.util.HashMap;

public class Rutas {
    // Mapa para almacenar las rutas de las imágenes de las cartas
    private static HashMap<String, String> rutasCartas;

    // Inicializar el mapa con las rutas
    static {
        rutasCartas = new HashMap<>();
        rutasCartas.put("0rojo", "./media/cerorojo.png");
        rutasCartas.put("1rojo", "./media/unorojo.png");
        rutasCartas.put("2rojo", "./media/dosrojo.png");
        rutasCartas.put("3rojo", "./media/tresrojo.png");
        rutasCartas.put("4rojo", "./media/cuatrorojo.png");
        rutasCartas.put("5rojo", "./media/cincorojo.png");
        rutasCartas.put("6rojo", "./media/seisrojo.png");
        rutasCartas.put("7rojo", "./media/sieterojo.png");
        rutasCartas.put("8rojo", "./media/ochorojo.png");
        rutasCartas.put("9rojo", "./media/nueverojo.png");

        rutasCartas.put("0azul", "./media/ceroazul.png");
        rutasCartas.put("1azul", "./media/unoazul.png");
        rutasCartas.put("2azul", "./media/dosazul.png");
        rutasCartas.put("3azul", "./media/tresazul.png");
        rutasCartas.put("4azul", "./media/cuatroazul.png");
        rutasCartas.put("5azul", "./media/cincoazul.png");
        rutasCartas.put("6azul", "./media/seisazul.png");
        rutasCartas.put("7azul", "./media/sieteazul.png");
        rutasCartas.put("8azul", "./media/ochoazul.png");
        rutasCartas.put("9azul", "./media/nueveazul.png");

        rutasCartas.put("0verde", "./media/ceroverde.png");
        rutasCartas.put("1verde", "./media/unoverde.png");
        rutasCartas.put("2verde", "./media/dosverde.png");
        rutasCartas.put("3verde", "./media/tresverde.png");
        rutasCartas.put("4verde", "./media/cuatroverde.png");
        rutasCartas.put("5verde", "./media/cincoverde.png");
        rutasCartas.put("6verde", "./media/seisverde.png");
        rutasCartas.put("7verde", "./media/sieteverde.png");
        rutasCartas.put("8verde", "./media/ochoverde.png");
        rutasCartas.put("9verde", "./media/nueveverde.png");

        rutasCartas.put("0amarillo", "./media/ceroamarillo.png");
        rutasCartas.put("1amarillo", "./media/unoamarillo.png");
        rutasCartas.put("2amarillo", "./media/dosamarillo.png");
        rutasCartas.put("3amarillo", "./media/tresamarillo.png");
        rutasCartas.put("4amarillo", "./media/cuatroamarillo.png");
        rutasCartas.put("5amarillo", "./media/cincoamarillo.png");
        rutasCartas.put("6amarillo", "./media/seisamarillo.png");
        rutasCartas.put("7amarillo", "./media/sieteamarillo.png");
        rutasCartas.put("8amarillo", "./media/ochoamarillo.png");
        rutasCartas.put("9amarillo", "./media/nueveamarillo.png");

        rutasCartas.put("10rojo", "./media/saltarrojo.png");
        rutasCartas.put("10azul", "./media/saltarazul.png");
        rutasCartas.put("10amarillo", "./media/saltaramarillo.png");
        rutasCartas.put("10verde", "./media/saltarverde.png");

        rutasCartas.put("11rojo", "./media/masdosrojo.png");
        rutasCartas.put("11azul", "./media/masdosazul.png");
        rutasCartas.put("11amarillo", "./media/masdosamarillo.png");
        rutasCartas.put("11verde", "./media/masdosverde.png");

        rutasCartas.put("12rojo", "./media/reversarojo.png");
        rutasCartas.put("12azul", "./media/reversaazul.png");
        rutasCartas.put("12amarillo", "./media/reversaamarillo.png");
        rutasCartas.put("12verde", "./media/reversaverde.png");

        rutasCartas.put("13SC", "./media/mascuatro.png");
        rutasCartas.put("14SC", "./media/cambiarcolor.png");
    }

    public static String getRuta(String valorColor) {
        return rutasCartas.get(valorColor);
    }

}