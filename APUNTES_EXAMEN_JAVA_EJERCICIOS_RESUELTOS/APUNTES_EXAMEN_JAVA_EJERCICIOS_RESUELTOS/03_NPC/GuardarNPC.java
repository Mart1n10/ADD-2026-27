import java.io.*;

public class GuardarNPC {
    public static void main(String[] args) {
        // CAMBIAR EN EXAMEN: nombre del .dat y datos de los NPC.
        String ruta = "npc.dat";
        NPC[] personajes = {
            new NPC("Guardian", 100, 10, 2, 5),
            new NPC("Mercader", 80, 4, 0, 9),
            new NPC("Enemigo", 40, 8, 3, 1)
        };
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ruta))) {
            // Guarda cada NPC como objeto completo en el DAT.
            for (NPC npc : personajes) out.writeObject(npc);
            System.out.println("NPC guardados: " + personajes.length);
        } catch (IOException e) { System.out.println("Error: " + e.getMessage()); }
    }
}
