package factory;

import encriptacion.InterfaceEncriptar;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EncriptadorFactory {

    public static InterfaceEncriptar crearDesdeArchivo(String rutaArchivo) throws Exception {
        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream(rutaArchivo)) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo el archivo de configuración: " + e.getMessage());
        }

        String claseEncriptadora = props.getProperty("encriptador");
        if (claseEncriptadora == null) {
            throw new RuntimeException("No se encontró la clave 'encriptador' en el archivo.");
        }

        try {
            Class<?> clase = Class.forName(claseEncriptadora);
            Object instancia = clase.getDeclaredConstructor().newInstance();

            if (!(instancia instanceof InterfaceEncriptar)) {
                throw new RuntimeException("La clase especificada no implementa InterfaceEncriptar");
            }

            return (InterfaceEncriptar) instancia;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Clase no encontrada: " + claseEncriptadora);
        }
    }
}
