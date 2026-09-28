import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LeerObjetos {

    public static void main(String[] args) {

        try {

            FileInputStream fichero =
                    new FileInputStream("jugadores_objetos.dat");

            ObjectInputStream entrada =
                    new ObjectInputStream(fichero);

            System.out.println("Jugadores recuperados:");

            while (true) {
                Jugador jugador = (Jugador) entrada.readObject();
                System.out.println(jugador);
            }

        } catch (EOFException e) {

            System.out.println("Fin del fichero.");

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error al leer los objetos.");
            e.printStackTrace();

        }
    }
}