import java.io.*; // Importamos el paquete io para FileInputStream, ObjectInputStream, IOException, etc.
import java.util.ArrayList; // Importamos ArrayList para manejar la lista de objetos
import java.util.Scanner; // Importamos Scanner para leer la entrada del usuario por consola

public class Ejercicio3_LeerLibros {
    public static void main(String[] args) {

        // ----------------------------------------------------
        // 1. CONFIGURACIÓN Y COMPROBACIÓN DEL FICHERO
        // ----------------------------------------------------
        
        // Creamos la referencia al fichero binario 'salida/libros.dat'
        File archivo = new File("salida/libros.dat");

        // Comprobamos si el fichero realmente existe en el disco duro antes de intentar leerlo
        if (!archivo.exists()) {
            System.out.println("El archivo 'salida/libros.dat' no existe. Ejecuta primero Ejercicio3_GuardarLibros.");
            return; // 'return' interrumpe el método main y finaliza el programa si no existe el archivo
        }

        // Declaramos la variable ArrayList fuera del try para poder usarla más adelante en todo el programa
        ArrayList<Libro> listaLibros = null;

        // ----------------------------------------------------
        // 2. LECTURA Y DESSERIALIZACIÓN DEL ARCHIVO BINARIO
        // ----------------------------------------------------
        
        try {
            // FileInputStream abre el canal de lectura hacia los bytes del archivo binario
            FileInputStream fis = new FileInputStream(archivo);
            
            // ObjectInputStream reconvierte la cadena de bytes de nuevo en objetos Java
            ObjectInputStream ois = new ObjectInputStream(fis);

            // ois.readObject() lee el objeto guardado.
            // Le hacemos un casteo explícito (ArrayList<Libro>) porque readObject() devuelve un 'Object' genérico.
            listaLibros = (ArrayList<Libro>) ois.readObject();

            // Cerramos el flujo de lectura para liberar memoria y recursos del sistema
            ois.close();

        } catch (IOException e) {
            // Captura errores de lectura o si el archivo está corrupto
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return; // Finaliza si hubo error de lectura
        } catch (ClassNotFoundException e) {
            // Captura el error en caso de que Java no encuentre la clase Libro al intentar convertir los bytes
            System.out.println("Error: No se encontró la clase Libro (" + e.getMessage() + ")");
            return; // Finaliza si no encuentra la clase
        }

        // ----------------------------------------------------
        // 3. PROCESAMIENTO Y ESTADÍSTICAS (SI LA LISTA TIENE DATOS)
        // ----------------------------------------------------

        // Verificamos que la lista se haya leído correctamente y que contenga elementos
        if (listaLibros != null && !listaLibros.isEmpty()) {

            // --- REQUISITO 1: MOSTRAR TODOS LOS LIBROS ---
            System.out.println("=== LISTADO COMPLETO DE LIBROS ===");
            
            // Recorremos el ArrayList con un bucle for-each
            for (Libro l : listaLibros) {
                // Al pasar el objeto 'l' a System.out.println(), Java llama automáticamente a su método toString()
                System.out.println(l);
            }

            // --- REQUISITO 2: FILTRAR POR EDITORIAL ---
            Scanner sc = new Scanner(System.in); // Inicializamos el Scanner para leer del teclado
            
            System.out.print("\nIntroduce el nombre de una editorial para buscar: ");
            String editorialBuscada = sc.nextLine(); // Leemos el texto que introduce el usuario

            boolean encontrado = false; // Variable bandera ('flag') para saber si encontramos alguna coincidencia
            
            System.out.println("\n--- Libros de la editorial '" + editorialBuscada + "' ---");

            // Recorremos la lista comprobando la editorial de cada libro
            for (Libro l : listaLibros) {
                // .getEditorial() obtiene el atributo.
                // .trim() quita espacios accidentales y .equalsIgnoreCase() compara sin importar mayúsculas/minúsculas.
                if (l.getEditorial().equalsIgnoreCase(editorialBuscada.trim())) {
                    System.out.println(l); // Imprime el libro que coincide
                    encontrado = true; // Cambiamos la bandera a true para indicar que sí hubo resultados
                }
            }

            // Si la bandera sigue siendo false, significa que el bucle no encontró ninguna coincidencia
            if (!encontrado) {
                System.out.println("No hay libros de esa editorial"); // Mensaje exigido literalmente por el enunciado
            }

            // --- REQUISITO 3: MOSTRAR EL LIBRO CON MÁS PÁGINAS ---
            
            // Asumimos inicialmente que el primer libro de la lista (índice 0) es el que más páginas tiene
            Libro libroMasPaginas = listaLibros.get(0);

            // Recorremos todos los libros de la lista para comparar uno por uno
            for (Libro l : listaLibros) {
                // Si las páginas del libro actual son mayores que las del libro record hasta el momento
                if (l.getPaginas() > libroMasPaginas.getPaginas()) {
                    libroMasPaginas = l; // Actualizamos la referencia al nuevo libro con más páginas
                }
            }

            // Imprimimos el resultado final del libro más largo
            System.out.println("\n=== LIBRO CON MÁS PÁGINAS ===");
            System.out.println(libroMasPaginas);

            // Cerramos el Scanner al finalizar el uso del teclado
            sc.close();
        }
    }
}