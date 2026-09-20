import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LlegirLineaSencera {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./Classe2/test.txt"));
            String linea;
            int n = 0;
            while ((linea = br.readLine()) != null) {
                n++;
                System.out.println("Linea" + n + ": " + linea);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer los archivos: " + e.getMessage());
        }
    }    
}
