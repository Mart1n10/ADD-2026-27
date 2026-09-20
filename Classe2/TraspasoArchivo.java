import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class TraspasoArchivo {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./Classe2/test.txt"));
            String linea1;
            int n = 0;
            while ((linea1 = br.readLine()) != null) {
                n++;
                System.out.println("Linea" + n + ": " + linea1);
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter("Writer1.txt"));
            String linea2;
            bw.write(linea2);
            bw.newLine();
            br.close();
            bw.close();
        } catch (Exception e) {
        }
    }
}
