import java.io.*;

public class CrearCarpetaYArchivos {
    public static void main(String[] args) {
        // CAMBIAR EN EXAMEN: carpeta y nombres de archivos.
        File carpeta = new File("EMPRESA");
        File primero = new File(carpeta, "trabajadores.txt");
        File segundo = new File(carpeta, "salarios.txt");

        // mkdirs(): crea la carpeta (y carpetas intermedias si hicieran falta).
        if (!carpeta.exists() && !carpeta.mkdirs()) {
            System.out.println("No se pudo crear la carpeta");
            return;
        }
        try {
            // DOS if independientes: queremos crear los DOS archivos.
            if (primero.createNewFile()) System.out.println("Creado: " + primero.getName());
            else System.out.println("Ya existia: " + primero.getName());

            if (segundo.createNewFile()) System.out.println("Creado: " + segundo.getName());
            else System.out.println("Ya existia: " + segundo.getName());

            System.out.println("Existe primero: " + primero.exists());
            System.out.println("Existe segundo: " + segundo.exists());
            System.out.println(primero.getName() + ": " + primero.length() + " bytes");
            System.out.println(segundo.getName() + ": " + segundo.length() + " bytes");

            // CAMBIAR EN EXAMEN: nuevo nombre.
            File nuevo = new File(carpeta, "nominas.txt");
            // renameTo() recibe un File, NO una String.
            if (segundo.exists() && !nuevo.exists()) {
                if (segundo.renameTo(nuevo)) System.out.println("Renombrado a " + nuevo.getName());
                else System.out.println("No se pudo renombrar");
            } else System.out.println("No se renombra: origen ausente o destino existente");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
