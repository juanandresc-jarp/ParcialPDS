package database;

import implementacion.Cancion;
import implementacion.ListaReproduccion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ListaDAO {

    private static final String URL = "jdbc:postgresql://dpg-d0ri4cqli9vc738l1vmg-a.oregon-postgres.render.com:5432/pds_empleados";
    private static final String USER = "user";
    private static final String PASS = "n5tRrgGJP12mM9AGV7923S24oyRS1I4R";

    public static void guardarLista(ListaReproduccion lista) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO listas (nombre, canciones) VALUES (?, ?)"
            );

            stmt.setString(1, lista.getNombreLista());

            StringBuilder cancionesSerializadas = new StringBuilder();
            for (Cancion c : lista.getCanciones()) {
                cancionesSerializadas.append(c.getNombreCancion()).append(" - ")
                        .append(c.getArtista()).append(";");
            }

            stmt.setString(2, cancionesSerializadas.toString());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
