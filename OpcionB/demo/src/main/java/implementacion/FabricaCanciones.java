package implementacion;

import java.util.HashMap;
import java.util.Map;

public class FabricaCanciones {
    public static boolean HabilitarFlyweight = true;
    private static final Map<String,Cancion> PLAY_CANCION = new HashMap<>();
    private static final Map<String, Artista> ARTISTAS = new HashMap<>();
    private static Long Secuencia = 0L;
    public static Artista obtenerArtista(String nombreArtista) {
        if (!ARTISTAS.containsKey(nombreArtista)) {
            ARTISTAS.put(nombreArtista, new Artista(nombreArtista));
        }
        return ARTISTAS.get(nombreArtista);
    }
    public static Cancion CrearItem(String NombreTema, String nombreArtista) {
        if (HabilitarFlyweight && PLAY_CANCION.containsKey(NombreTema)) {
            return PLAY_CANCION.get(NombreTema);
        } else {
            Artista artista = obtenerArtista(nombreArtista);
            Cancion playItem = new Cancion(++Secuencia, NombreTema, artista);
            PLAY_CANCION.put(NombreTema, playItem);
            return playItem;
        }
    }    
}
