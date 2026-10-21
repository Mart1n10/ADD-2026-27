import java.io.*;
import java.util.ArrayList;

public class Ejercicio3_GuardarLibros {
    public static void main(String[] args) {

        // 1. Preparar la carpeta de salida
        File carpeta = new File("salida");
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // Crea la carpeta 'salida' si no existe
        }

        // 2. Definir el fichero binario de destino
        File archivo = new File(carpeta, "libros.dat");

        // 3. Crear el ArrayList con 5 libros (al menos dos con la misma editorial: "Planeta")
        ArrayList<Libro> listaLibros = new ArrayList<>();
        listaLibros.add(new Libro("El Quijote", 863, "978-8424115531", "Planeta"));
        listaLibros.add(new Libro("Cien años de soledad", 471, "978-0307474728", "Debolsillo"));
        listaLibros.add(new Libro("La sombra del viento", 565, "978-8408043644", "Planeta"));
        listaLibros.add(new Libro("Fahrenheit 451", 249, "978-8445074213", "Minotauro"));
        listaLibros.add(new Libro("1984", 326, "978-8499890944", "Debolsillo"));

        // 4. Guardar la lista de objetos en el fichero binario
        try {
            // FileOutputStream abre el flujo hacia el archivo.
            // ObjectOutputStream permite escribir objetos complejos (como un ArrayList entero).
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
            
            // Escribimos la lista completa de una sola vez
            oos.writeObject(listaLibros);
            
            oos.close(); // Cierra el flujo de escritura
            System.out.println("Lista de libros guardada correctamente en 'salida/libros.dat'.");

        } catch (IOException e) {
            System.out.println("Error al guardar el archivo binario: " + e.getMessage());
        }
    }
}