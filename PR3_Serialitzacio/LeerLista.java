import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LeerLista {

    public static void main(String[] args) {

        try {

            FileInputStream fichero =
                    new FileInputStream("jugadores_lista.dat");

            ObjectInputStream entrada =
                    new ObjectInputStream(fichero);

            ArrayList<Jugador> jugadores =
                    (ArrayList<Jugador>) entrada.readObject();

            entrada.close();

            System.out.println("Jugadores recuperados:");

            for (Jugador jugador : jugadores) {
                System.out.println(jugador);
            }

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error al leer la lista.");
            e.printStackTrace();

        }
    }
}