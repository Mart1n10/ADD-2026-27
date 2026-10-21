import java.io.File;

public class ListarDirectorio {
    public static void main(String[] args) {
        // CAMBIAR EN EXAMEN: directorio que hay que explorar.
        File carpeta = new File("EMPRESA");
        if (!carpeta.isDirectory()) {
            System.out.println("La carpeta no existe");
            return;
        }
        File[] elementos = carpeta.listFiles();
        if (elementos == null) { System.out.println("No se puede leer"); return; }
        int archivos = 0;
        long bytes = 0;
        for (File f : elementos) {
            System.out.println(f.getName() + " | " + (f.isFile() ? "ARCHIVO" : "CARPETA")
                    + " | " + f.length() + " bytes");
            if (f.isFile()) { archivos++; bytes += f.length(); }
        }
        System.out.println("Archivos: " + archivos + ", bytes totales: " + bytes);
    }
}
