import java.io.File;

public class VerDir {

    static int totalElementos = 0;
    static long sumaTamanos = 0;

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Uso: java VerDir <ruta>");
            return;
        }
        String path = args[0];
        
        File dir = new File(path);

        if (!dir.isDirectory()) {
            System.out.println("No es un directorio");
            return;
        }

        System.out.println("Es un directorio y su ruta absoluta es: " + dir.getAbsolutePath());

        imprimirContenidoDentroCarpeta(dir);

        System.out.println("\nNúmero total de elementos: " + totalElementos);
        System.out.println("Suma de tamaños de los archivos: " + sumaTamanos + " bytes");
    }

    public static void imprimirContenidoDentroCarpeta(File dir) {

        File[] files = dir.listFiles();

        for (File f : files) {

            totalElementos++;

            System.out.println(f.getName());

            System.out.printf("Es un archivo? %b, Tamaño: %d bytes, Se puede escribir? %b\n", f.isFile(), f.length(), f.canWrite());

            if (f.isFile()) {
                sumaTamanos += f.length();
            }

            if (f.isDirectory()) {
                imprimirContenidoDentroCarpeta(f);
            }
        }
    }
}