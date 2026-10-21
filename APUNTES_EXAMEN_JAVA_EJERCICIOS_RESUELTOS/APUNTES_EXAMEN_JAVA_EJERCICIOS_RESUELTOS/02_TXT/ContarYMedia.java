import java.io.*;

public class ContarYMedia {
    public static void main(String[] args) {
        String ruta = "personas.txt"; // CAMBIAR EN EXAMEN
        int contador = 0;
        double suma = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] c = linea.split(";");
                if (c.length != 3) continue;
                try {
                    double saldo = Double.parseDouble(c[2].trim().replace(',', '.'));
                    // CAMBIAR EN EXAMEN: filtro opcional, por ejemplo saldo >= 1000
                    if (saldo >= 1000) {
                        System.out.println(c[0].trim() + " : " + saldo);
                        contador++;
                        suma += saldo;
                    }
                } catch (NumberFormatException e) { System.out.println("Registro no valido: " + linea); }
            }
            System.out.println("Personas que cumplen el filtro: " + contador);
            System.out.println("Media de sus saldos: " + (contador == 0 ? 0 : suma / contador));
        } catch (IOException e) { System.out.println("Error: " + e.getMessage()); }
    }
}
