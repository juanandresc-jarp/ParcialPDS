/*
 * Asignatura: Patrones de Diseño de Software 
 * Patrón Estructural - > Bridge
 * Tipo de Clase: Java
 * Clase que hereda de InterfaceEncriptar y encripta el mensaje mediante el algoritmo ROT13.
 */
package encriptacion;


public class ProcesoEncriptarROT13 implements InterfaceEncriptar {

    @Override
    public String encryptar(String message, String password) throws Exception {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            result.append(c);
        }
        return result.toString();
    }
}