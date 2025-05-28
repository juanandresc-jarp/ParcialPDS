package patronbridge;

import factory.EncriptadorFactory;
import implementacion.PuenteMensajeEncriptacion;
import implementacion.InterfaceMensajeEncriptacion;

public class PatronBridgeMain {
    public static void main(String[] args) {
        try {
            InterfaceMensajeEncriptacion bridge = new PuenteMensajeEncriptacion(
                EncriptadorFactory.crearDesdeArchivo("demo\\src\\main\\java\\properties\\config.properties")
            );

            String mensaje = "<Curso><Nombre>Patrones de Diseño</Nombre></Curso>";
            String resultado = bridge.EncryptarMensaje(mensaje, "HG58YZ3CR9123456");
            System.out.println("Resultado > " + resultado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
