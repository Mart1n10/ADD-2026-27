import java.io.*;

public class LeerNPC {
    public static void main(String[] args) {
        String ruta = "npc.dat"; // CAMBIAR EN EXAMEN
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ruta))) {
            while (true) {
                NPC npc = (NPC) in.readObject(); // cast obligatorio
                System.out.println(npc);
                // CAMBIAR EN EXAMEN: ejemplo de condicion: NPC con vida superior a 50
                if (npc.getVida() > 50) System.out.println("  NPC con mucha vida");
            }
        } catch (EOFException e) {
            // EOF es normal cuando ya no quedan objetos.
            System.out.println("Fin del archivo de NPC");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
