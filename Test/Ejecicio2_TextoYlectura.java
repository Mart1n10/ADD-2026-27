import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class Ejecicio2_TextoYlectura {
     static Scanner sc = new Scanner(System.in);
    public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] linea = new String[3];
        for (int i = 0; i < 3; i++) {
            System.out.println("Dame una frase");
            linea[i] = sc.nextLine();
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Writer.txt"));
            for(int i = 0; i < 3; i ++){
                bw.write(linea[i]);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
