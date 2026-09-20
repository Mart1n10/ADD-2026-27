import java.io.BufferedWriter;
import java.io.FileWriter;

public class EscriureFitxerText {
    public static void main(String[] args) {
        String[] lineas = {"Hola, primera línea.", "Segunda línea.", "Tercera línea."};
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Writer.txt"));
            for(String linea : lineas){
                bw.write(linea);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }    
}