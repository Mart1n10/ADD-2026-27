import java.io.*; // Importamos el paquete java.io completo para manejar ficheros y directorios

public class Ejercicio1Examen {
    public static void main(String[] args) {
        
        // =========================================================================
        // PASO 1: CREAR LA CARPETA 'PROYECTO/entrada' Y LOS ARCHIVOS FÍSICOS VACÍOS
        // Enunciado: "Crea el directorio PROYECTO/entrada y dentro estos ficheros vacíos:
        // informe.txt, datos.csv, foto.jpg, notas.txt, error.log y LEEME"
        // =========================================================================
        
        // Apuntamos a la carpeta 'PROYECTO/entrada'
        File entrada = new File("PROYECTO/entrada");
        
        // Comprobamos si la carpeta no existe
        if (!entrada.exists()) {
            entrada.mkdirs(); // Crea la carpeta 'PROYECTO/entrada' en el disco duro
        }

        // Definimos los objetos File en memoria asociándolos a la carpeta 'entrada'
        File informe = new File(entrada, "informe.txt");
        File datos = new File(entrada, "datos.csv");
        File foto = new File(entrada, "foto.jpg");
        File notas = new File(entrada, "notas.txt");
        File error = new File(entrada, "error.log");
        File leeme = new File(entrada, "LEEME"); // Fichero sin extensión

        // .createNewFile() exige try-catch para capturar posibles errores de escritura (IOException)
        try {
            informe.createNewFile(); // Crea el archivo físicamente en el disco (vacío)
            datos.createNewFile();
            foto.createNewFile();
            notas.createNewFile();
            error.createNewFile();
            leeme.createNewFile();
        } catch (IOException e) {
            System.out.println("Error al crear los archivos: " + e.getMessage());
        }

        // =========================================================================
        // PASO 2: RECORRER 'entrada' Y MOVER CADA ARCHIVO SEGÚN SU EXTENSIÓN
        // Enunciado: "Recorrer entrada y mover cada fichero a una carpeta dentro de 
        // PROYECTO/salida según su extensión. Las carpetas solo se crean si hacen falta. 
        // Los ficheros sin extensión van a salida/sin_extension."
        // =========================================================================

        // .listFiles() devuelve un array (File[]) con todo el contenido de 'entrada'
        File[] listaArchivos = entrada.listFiles();
        
        // Verificamos que el array no sea nulo antes de recorrerlo
        if (listaArchivos != null) {
            
            // Recorremos los ficheros uno a uno con un bucle for-each
            for (File f : listaArchivos) {
                
                // .isFile() garantiza que procesamos ficheros y no carpetas
                if (f.isFile()) {
                    
                    String nombre = f.getName(); // Obtenemos el nombre del archivo (ej: "informe.txt")

                    // .lastIndexOf(".") busca la posición del último punto.
                    // Si el archivo NO tiene punto (como LEEME), devuelve -1.
                    int posicionPunto = nombre.lastIndexOf(".");
                    String extension;

                    // Determinamos si tiene extensión o no
                    if (posicionPunto == -1) {
                        extension = "sin_extension"; // Si dio -1, va a la carpeta 'sin_extension'
                    } else {
                        // .substring(posicionPunto + 1) extrae el texto después del punto.
                        // .toLowerCase() convierte la extensión a minúsculas para unificar formatos.
                        extension = nombre.substring(posicionPunto + 1).toLowerCase();
                    }

                    // Definimos la carpeta de destino correspondiente (ej: "PROYECTO/salida/txt")
                    File carpetaSalida = new File("PROYECTO/salida/" + extension);
                    
                    // Si la subcarpeta de salida no existe todavía, la creamos
                    if (!carpetaSalida.exists()) {
                        carpetaSalida.mkdirs();
                    }

                    // Definimos la ruta completa de destino para el archivo
                    File destinoFinal = new File(carpetaSalida, nombre);
                    
                    // .renameTo() mueve el fichero físicamente desde 'entrada' hasta su subcarpeta de 'salida'
                    f.renameTo(destinoFinal);
                }
            }
        }

        // =========================================================================
        // PASO 3: MOSTRAR CADA CARPETA DE SALIDA CON SU NÚMERO DE FICHEROS
        // Enunciado: "Al terminar, muestra cada carpeta de salida con el número de 
        // ficheros que contiene."
        // =========================================================================

        // Apuntamos a la carpeta principal de salida
        File salidaBase = new File("PROYECTO/salida");
        
        // Obtenemos un array con las subcarpetas creadas dentro de 'salida'
        File[] subcarpeta = salidaBase.listFiles();

        if (subcarpeta != null) {
            System.out.println("\n--- RESUMEN DE SALIDA ---");

            // Recorremos cada elemento dentro de 'salida'
            for (File carpeta : subcarpeta) {
                
                // .isDirectory() asegura que contamos el contenido de una subcarpeta
                if (carpeta.isDirectory()) {
                    
                    // Obtenemos los ficheros guardados dentro de esta subcarpeta concreta
                    File[] contenido = carpeta.listFiles();
                    
                    // .length nos da la cantidad total de elementos dentro del array
                    int cantidad = (contenido != null) ? contenido.length : 0;

                    // Muestra el resumen (ejemplo: txt -> 2 ficheros)
                    System.out.println(carpeta.getName() + " -> " + cantidad + " ficheros");
                }
            }
        }
    }
}