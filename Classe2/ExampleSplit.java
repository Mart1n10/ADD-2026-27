import java.io.BufferedReader;
import java.io.FileReader;

public class ExampleSplit {
    public static void main(String[] args) {
        try{
            BufferedReader br = new BufferedReader(new FileReader("Writter.txt"));
            String linea;
            while((linea = br.readLine()) != null){
                String[] parts = linea.split(";");
                if (Integer.parseInt(palabras[1].trim()) >= 5) {
                    System.out.println("Alumno aprobado: " + palabras[0] + ", Nota: " + palabras[1] + " - Minutos");
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al leer los archivos: " + e.getMessage());
        }
    }
}