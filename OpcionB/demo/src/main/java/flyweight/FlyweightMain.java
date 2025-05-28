package flyweight;

import database.ListaDAO;
import implementacion.FabricaCanciones;
import implementacion.ListaReproduccion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class FlyweightMain {

    private static final String[] NombreCanciones = new String[1000];
    private static final String[] NombresListas = new String[40000];
    private static final String[] NombreArtistas = new String[100];
    private static final List<ListaReproduccion> Listas = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Iniciando creación de listas de reproducción...");
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Memoria máxima disponible (MB): " + (runtime.maxMemory() / 1000000));

        FabricaCanciones.HabilitarFlyweight = true;
        InicializarArreglos();
        CrearListaDinamica();

        System.out.println("Total Listas Creadas: " + Listas.size());

        SimularUsoAleatorio();
        PersistirListasMenosUsadas();

        long memoryUsed = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memoria usada actual (MB): " + (memoryUsed / 1000000));
    }

    private static void CrearListaDinamica() {
        Random random = new Random();
        int p = 0;
        for (int c = 0; c < NombresListas.length; c++) {
            ListaReproduccion playList = new ListaReproduccion(NombresListas[c]);
            for (int i = 0; i < 10; i++) {
                int song = random.nextInt(NombreCanciones.length);
                int artist = random.nextInt(NombreArtistas.length);
                playList.addCancion(NombreCanciones[song], NombreArtistas[artist]);
            }
            Listas.add(playList);
            if (c != 0 && (c + 1) % (NombresListas.length / 10) == 0) {
                p += 10;
                System.out.println("Finalizado " + p + "% - Listas creadas: " + Listas.size());
            }
        }
    }

    private static void InicializarArreglos() {
        for (int c = 0; c < NombreCanciones.length; c++) {
            NombreCanciones[c] = "Song " + (c + 1);
        }
        for (int c = 0; c < NombreArtistas.length; c++) {
            NombreArtistas[c] = "Artista " + (c + 1);
        }
        for (int c = 0; c < NombresListas.length; c++) {
            NombresListas[c] = "PlayList " + (c + 1);
        }
    }

    private static void SimularUsoAleatorio() {
        Random random = new Random();
        for (ListaReproduccion lista : Listas) {
            int veces = random.nextInt(5); // uso aleatorio entre 0 y 4
            for (int i = 0; i < veces; i++) {
                lista.reproducir();
            }
        }
    }

    private static void PersistirListasMenosUsadas() {
        // Ordenar por uso de menor a mayor
        Collections.sort(Listas, Comparator.comparingInt(ListaReproduccion::getUso));

        int total = Listas.size();
        int aPersistir = 500;

        for (int i = 0; i < aPersistir; i++) {
            ListaReproduccion lista = Listas.get(i);
            ListaDAO.guardarLista(lista);
        }

        // Guardar las 500 listas menos usadas en la base de datos
        Listas.subList(0, aPersistir).clear();

        System.out.println("Listas persistidas en la base de datos: " + aPersistir);
        System.out.println("Listas restantes en memoria: " + Listas.size());
    }
}
