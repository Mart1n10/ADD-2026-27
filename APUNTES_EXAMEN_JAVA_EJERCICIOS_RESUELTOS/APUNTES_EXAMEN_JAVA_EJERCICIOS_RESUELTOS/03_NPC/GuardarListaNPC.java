import java.io.*;
import java.util.ArrayList;

public class GuardarListaNPC {
    public static void main(String[] args) {
        ArrayList<NPC> lista = new ArrayList<>();
        lista.add(new NPC("Aldeano", 60, 1, 2, 3));
        lista.add(new NPC("Jefe", 200, 8, 9, 10));
        // VARIANTE: guardar una lista entera con UN writeObject() en vez de objeto por objeto.
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("lista_npc.dat"))) {
            out.writeObject(lista);
        } catch (IOException e) { System.out.println("Error guardando: " + e.getMessage()); }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("lista_npc.dat"))) {
            // El cast a ArrayList genera aviso de tipo no comprobado (normal en este ejemplo).
            @SuppressWarnings("unchecked")
            ArrayList<NPC> recuperada = (ArrayList<NPC>) in.readObject();
            for (NPC npc : recuperada) System.out.println(npc);
        } catch (IOException | ClassNotFoundException e) { System.out.println("Error leyendo: " + e.getMessage()); }
    }
}
