import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class archivoendirectorio {
    static Scanner sc = new Scanner(System.in);
    public void ImprimirArchivo(File f1) {
        try {
            f1.createNewFile();
        } catch (IOException e) {
        System.out.println("Error " + e.getMessage());
        }
    }
    public void main(String[] args) {
        System.out.println("Dime la ruta donde quieras guardar el archivo");
        String ruta = sc.nextLine();
        File dir = new File(ruta);
        System.out.println("Dime de que formato quieres el archivo");
        String ruta2 = sc.nextLine();
        File dir2 = new File(ruta, ruta2);
        ImprimirArchivo(dir2);
    }
}
