import java.io.*;

public class MayorSaldo {
    public static void main(String[] args) {
        // CAMBIAR EN EXAMEN: ruta del TXT proporcionado por el profesor.
        String ruta = "personas.txt";
        String nombreMayor = "";
        int edadMayor = 0;
        double saldoMayor = Double.NEGATIVE_INFINITY; // funciona incluso con saldos negativos
        boolean hayDatos = false;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue; // saltar lineas vacias
                // FORMATO: nombre;edad;saldo
                String[] campos = linea.split(";"); // CAMBIAR separador si fuera ','
                if (campos.length != 3) { System.out.println("Linea invalida: " + linea); continue; }
                try {
                    String nombre = campos[0].trim();
                    int edad = Integer.parseInt(campos[1].trim());
                    double saldo = Double.parseDouble(campos[2].trim().replace(',', '.'));

                    // CAMBIAR EN EXAMEN: '>' busca el MAYOR; '<' busca el MENOR.
                    if (!hayDatos || saldo > saldoMayor) {
                        saldoMayor = saldo;
                        nombreMayor = nombre;
                        edadMayor = edad;
                        hayDatos = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Numero no valido: " + linea);
                }
            }
            if (hayDatos) {
                System.out.println("Persona con MAYOR saldo:");
                System.out.println("Nombre: " + nombreMayor);
                System.out.println("Edad: " + edadMayor);
                System.out.println("Saldo: " + saldoMayor);
            } else System.out.println("Sin registros validos");
        } catch (IOException e) {
            System.out.println("Error leyendo TXT: " + e.getMessage());
        }
    }
}
