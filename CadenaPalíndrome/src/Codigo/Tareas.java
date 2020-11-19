package Codigo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tareas {

    public void Is_Cadena_Palindrome(String entrada) throws PilaVacia {
        entrada = entrada.replaceAll("\\s+", "");
        PilaSecuencial cadena_invertida = new PilaSecuencial();
        for (char caracter : entrada.toCharArray()) {
            cadena_invertida.push(caracter);
        }
        for (int i = 0; i < entrada.length(); i++) {
            if (entrada.charAt(i) == (char) cadena_invertida.top()) {
                cadena_invertida.pop();
                if (i == entrada.length() - 1) {
                    System.out.print("La cadena ingresada es palíndrome\n");
                }
            } else {
                System.out.print("La cadena ingresada no es palíndrome\n");
                break;
            }
        }
    }

    public void Inversor(String cadena) throws PilaVacia {
        PilaSecuencial temp = new PilaSecuencial();
        char[] mensaje_nuevo = new char[cadena.length()];
        for (char caracter : cadena.toCharArray()) {
            temp.push(caracter);
        }
        for (int i = 0; i < cadena.length(); i++) {
            mensaje_nuevo[i] = (char) temp.top();
            temp.pop();
        }
        String mensaje_invertido = new String(mensaje_nuevo);
        Decodificador_Primario(mensaje_invertido, mensaje_invertido.length());
    }

    public void Decodificador_Primario(String mensaje, int limite) {
        boolean aux = false;
        if ((int) mensaje.charAt(limite - 1) != 97 || (int) mensaje.charAt(limite - 1) != 101 || (int) mensaje.charAt(limite - 1) != 105 || (int) mensaje.charAt(limite - 1) != 111 || (int) mensaje.charAt(limite - 1) != 117) {
            char[] temp = new char[limite + 1];
            for (int i = 0; i < limite; i++) {
                temp[i] = mensaje.charAt(i);
            }
            temp[limite] = 'a';
            mensaje = new String(temp);
            limite++;
            aux = true;
        }
        int caracteres_pasados = 0;
        for (int i = 0; i < limite; i++) {
            switch ((int) mensaje.charAt(i)) {
                case 97:
                case 101:
                case 105:
                case 111:
                case 117:
                    if (caracteres_pasados > 1) {
                        mensaje = Decodificador_Secundario(mensaje.toCharArray(), i, caracteres_pasados);
                        caracteres_pasados = 0;
                    } else {
                        caracteres_pasados = 0;
                    }
                    break;
                default:
                    caracteres_pasados++;
                    break;
            }
        }
        if (aux) {
            limite--;
            char[] tempo_uno = mensaje.toCharArray();
            char[] tempo_dos = new char[limite];
            for (int i = 0; i < limite; i++) {
                tempo_dos[i] = tempo_uno[i];
            }
            mensaje = new String(tempo_dos);
        }
        System.out.print("\nMensaje decodificado: " + mensaje.toUpperCase() + "\n");
    }

    public String Decodificador_Secundario(char[] mensaje, int posicion_vocal, int caracteres_pasados) {
        int i = posicion_vocal - caracteres_pasados;
        int j = posicion_vocal - 1;
        while (true) {
            char temp = mensaje[i];
            mensaje[i] = mensaje[j];
            mensaje[j] = temp;
            if (caracteres_pasados <= 2 ) {
                break;
            } else {
                caracteres_pasados-=2;
                i++;
                j--;
            }
        }
        return new String(mensaje);
    }

    public static void main(String[] args) throws PilaVacia {
        BufferedReader EntKey = new BufferedReader(new InputStreamReader(System.in));
        Tareas x = new Tareas();
        /*
        
        Esto es para ejecutar el codigo para verificar la palíndromosidad de una cadena, descomente para probar
        
        System.out.print("Ingresar Cadena: ");
        try {
        x.Is_Cadena_Palindrome(EntKey.readLine().toLowerCase());
        } catch (IOException | PilaVacia ex) {
        Logger.getLogger(Tareas.class.getName()).log(Level.SEVERE, null, ex);
        }
         */

        System.out.print("Ingrese mensaje encriptado: ");
        try {
            x.Inversor(EntKey.readLine().toLowerCase());
        } catch (IOException ex) {
            Logger.getLogger(Tareas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
