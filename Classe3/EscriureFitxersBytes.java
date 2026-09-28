import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EscriureFitxersBytes{
    public static void main(String[] args) {
        File fitxer = new File("FitxerBytes.dat");
        try (FileOutputStream out = new FileOutputStream(fitxer)) {
            for(int i = 0; i < 100; i++) {
                out.write(i);
            }
            out.close();
        } catch (IOException e) {
            System.err.println("Error!!" + e.getMessage());
        }
        try (FileInputStream in = new FileInputStream(fitxer)){
            int b;
            while((b = in.read()) != -1) {
                System.out.println(b);
            }
        } catch (IOException e) {
            System.err.println("Error al leer!!!" + e.getMessage());
        }
    }
}
