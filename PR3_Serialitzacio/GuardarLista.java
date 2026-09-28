import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GuardarLista {

    public static void main(String[] args) {

        ArrayList<Jugador> jugadores = new ArrayList<>();
        
        jugadores.add(new Jugador("Marc", "Barcelona", 22, 10, "Delantero"));
        jugadores.add(new Jugador("Pau", "Girona", 24, 7, "Centrocampista"));
        jugadores.add(new Jugador("Alex", "Terrassa", 21, 9, "Delantero"));
        jugadores.add(new Jugador("David", "Sabadell", 25, 4, "Defensa"));
        jugadores.add(new Jugador("Eric", "Manresa", 23, 11, "Extremo"));
        jugadores.add(new Jugador("Joan", "Badalona", 26, 8, "Centrocampista"));

        try {

            FileOutputStream fichero =
                    new FileOutputStream("jugadores_lista.dat");

            ObjectOutputStream salida =
                    new ObjectOutputStream(fichero);

            salida.writeObject(jugadores);

            salida.close();

            System.out.println("Lista guardada correctamente.");

        } catch (IOException e) {

            System.out.println("Error al guardar la lista.");
            e.printStackTrace();

        }
    }
}