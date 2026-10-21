import java.io.*;

public class RegistroAleatorio {
    public static void main(String[] args) {
        // REGISTRO FIJO: id int (4 bytes) + vida int (4 bytes) = 8 bytes.
        // VARIANTE si piden ir a una posicion sin recorrer todo el fichero.
        final int TAMANO = 8;
        try (RandomAccessFile raf = new RandomAccessFile("vidas.dat", "rw")) {
            raf.setLength(0); // SOLO para la demo: BORRA contenido anterior
            for (int i = 1; i <= 3; i++) { raf.writeInt(i); raf.writeInt(i * 25); }
            // CAMBIAR EN EXAMEN: registro 0, 1, 2... (no id 1, 2, 3)
            int numeroRegistro = 1;
            raf.seek((long) numeroRegistro * TAMANO); // salto directo
            int id = raf.readInt();
            int vida = raf.readInt();
            System.out.println("ID " + id + " / vida " + vida);
            // Modifica la vida de ese registro, sin alterar el id.
            raf.seek((long) numeroRegistro * TAMANO + 4);
            raf.writeInt(99);
            System.out.println("Vida del registro actualizada a 99");
        } catch (IOException e) { System.out.println("Error: " + e.getMessage()); }
    }
}
