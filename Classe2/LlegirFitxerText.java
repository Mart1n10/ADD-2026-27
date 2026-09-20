import java.io.File;
import java.io.FileReader;

public class LlegirFitxerText {
    public static void main(String[] args) {
        File dir = new File("./Classe2/test.txt");

        try {
            FileReader fr = new FileReader(dir);
            int i;
            while((i=fr.read())!=-1){
                System.out.print((char)i);
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("Error al leer los archivos: " + e.getMessage());
        }
    }    
}