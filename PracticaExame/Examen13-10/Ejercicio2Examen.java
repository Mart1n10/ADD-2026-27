import java.io.*;
import java.util.Scanner;

public class Ejercicio2Examen {
    public static void main(String[] args) {
        
        // ----------------------------------------------------
        // 1. INICIALIZACIÓN Y CONFIGURACIÓN DE FICHEROS
        // ----------------------------------------------------
        Scanner sc = new Scanner(System.in);
        
        // Apuntamos a la carpeta "salida"
        File carpeta = new File("salida");
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // Crea el directorio "salida" en el disco si no existe
        }

        // Apuntamos al fichero "diario.txt" dentro de la carpeta "salida"
        File archivo = new File(carpeta, "diario.txt");

        // ----------------------------------------------------
        // 2. ESCRITURA EN EL FICHERO
        // ----------------------------------------------------
        System.out.println("Escribe las lineas de texto que quieras, cuando escribas 'FIN', se acabara");
        
        try {
            // FileWriter con 'true' abre el archivo en modo APPEND (añadir al final)
            // PrintWriter nos da el método .println() para escribir frases cómodamente
            PrintWriter pw = new PrintWriter(new FileWriter(archivo, true));
            
            while (true) {
                String linea = sc.nextLine(); // Leemos del teclado
                
                // Comprobamos si el usuario quiere terminar (sin diferenciar mayúsculas/minúsculas)
                if (linea.equalsIgnoreCase("FIN")) {
                    break; // Sale del bucle sin guardar la palabra "FIN"
                } else {
                    pw.println(linea); // Guarda la línea introducida en el archivo
                }
            }
            
            pw.close(); // Cierra el canal de escritura para guardar los cambios en disco
            
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }

        // ----------------------------------------------------
        // 3. LECTURA Y CÁLCULO DE ESTADÍSTICAS
        // ----------------------------------------------------
        // Variables acumuladoras
        int numLinea = 0;
        int totalPalabras = 0;
        String lineaMasLarga = "";

        try {
            // BufferedReader permite leer el archivo de texto línea por línea con .readLine()
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            
            String linea; // Declaramos la variable donde guardaremos cada línea leída
            
            // Leemos y asignamos la línea dentro de la condición del bucle.
            // Mientras no sea null, significa que todavía quedan líneas por procesar.
            while ((linea = br.readLine()) != null) {
                numLinea++; // Incrementamos el contador de líneas
                
                int palabrasLinea = 0;
                
                // .trim() quita espacios sobrantes al inicio y al final.
                // .isEmpty() comprueba si la línea está vacía para no contar palabras de más.
                if (!linea.trim().isEmpty()) {
                    // .split("\\s+") divide la frase por cualquier grupo de espacios en blanco
                    palabrasLinea = linea.trim().split("\\s+").length;
                    totalPalabras += palabrasLinea; // Sumamos al acumulador total de palabras
                    
                    // Comprobamos si esta línea supera en longitud a la guardada anteriormente
                    if (linea.length() > lineaMasLarga.length()) {
                        lineaMasLarga = linea;
                    }
                }
                
                // Muestra la línea formateada: "1: Texto de la línea (X palabras)"
                System.out.println(numLinea + ": " + linea + " (" + palabrasLinea + " palabras)");  
            }
            
            br.close(); // Cierra el canal de lectura
            
            // Muestra el resumen estadístico solicitado
            System.out.println("\n--- ESTADÍSTICAS ---");
            System.out.println("Total de líneas: " + numLinea);
            System.out.println("Total de palabras: " + totalPalabras);
            System.out.println("Línea más larga: " + lineaMasLarga);
            
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
        sc.close();
    }
}