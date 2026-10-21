import java.io.*;

public class GuardarLeerDatosDAT {
    public static void main(String[] args) {
        // VARIANTE de .dat: guarda valores sueltos, NO objetos Serializable.
        // CAMBIAR EN EXAMEN: ruta, valores y tipos (mismo orden al leer).
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("datos.dat"))) {
            out.writeUTF("Ana");
            out.writeInt(23);
            out.writeDouble(1500.50);
        } catch (IOException e) { System.out.println("Error escritura: " + e.getMessage()); }

        try (DataInputStream in = new DataInputStream(new FileInputStream("datos.dat"))) {
            String nombre = in.readUTF(); // MISMO ORDEN que al escribir
            int edad = in.readInt();
            double saldo = in.readDouble();
            System.out.println(nombre + " " + edad + " " + saldo);
        } catch (IOException e) { System.out.println("Error lectura: " + e.getMessage()); }
    }
}
