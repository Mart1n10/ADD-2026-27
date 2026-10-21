import java.io.*;

public class EscribirYAnadirTXT {
    public static void main(String[] args) {
        String ruta = "nuevo.txt"; // CAMBIAR EN EXAMEN
        // false: sobrescribe / true: anade SIN borrar.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, false))) {
            bw.write("Ana;23;1500.50");
            bw.newLine();
            bw.write("Luis;31;2200.00");
            bw.newLine();
        } catch (IOException e) { System.out.println("Error escritura: " + e.getMessage()); }

        // Segunda apertura en modo append para anadir.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            bw.write("Marta;28;3100.25");
            bw.newLine();
        } catch (IOException e) { System.out.println("Error append: " + e.getMessage()); }
        System.out.println("Revisa " + ruta);
    }
}
