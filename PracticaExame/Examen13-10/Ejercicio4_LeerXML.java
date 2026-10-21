import java.io.File; // Para hacer referencia al archivo fisico colegio.xml
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Ejercicio4_LeerXML {
    public static void main(String[] args) {

        // ----------------------------------------------------
        // 1. CARGA Y PARSEO DEL ARCHIVO XML (DOM)
        // ----------------------------------------------------

        // Apuntamos al archivo XML
        File archivoXML = new File("colegio.xml");

        // Comprobamos si el archivo existe antes de intentar leerlo
        if (!archivoXML.exists()) {
            System.out.println("El archivo 'colegio.xml' no existe.");
            return; // Finaliza la ejecución si no se encuentra el fichero
        }

        try {
            // Creamos la fábrica de parsers DOM
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
            // Creamos el lector DOM a partir de la fábrica
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            // Parseamos (leemos) el archivo XML y lo cargamos en memoria como un objeto Document
            Document doc = builder.parse(archivoXML);

            // Reorganiza y limpia la estructura del documento XML para evitar saltos de línea molestos
            doc.getDocumentElement().normalize();

            // ----------------------------------------------------
            // REQUISITO 1: MOSTRAR NOMBRE DEL COLEGIO Y DIRECTOR/A
            // ----------------------------------------------------

            // Recuperamos el primer nodo <nombre> del XML (que corresponde al nombre del colegio)
            String nombreColegio = doc.getElementsByTagName("nombre").item(0).getTextContent();
            
            // Recuperamos la etiqueta <director>
            String director = doc.getElementsByTagName("director").item(0).getTextContent();

            System.out.println("=== DATOS DEL CENTRO ===");
            System.out.println("Colegio: " + nombreColegio);
            System.out.println("Director/a: " + director);

            // ----------------------------------------------------
            // REQUISITO 2 Y 3: LISTAR ALUMNOS Y CALCULAR ESTADÍSTICAS
            // ----------------------------------------------------

            // Obtenemos una lista (NodeList) con todos los nodos <alumno>
            NodeList listaAlumnos = doc.getElementsByTagName("alumno");

            // Variables acumuladoras para las estadísticas
            double sumaNotas = 0; // Para calcular la media
            double notaMaxima = -1.0; // Inicializamos con un valor menor a cualquier nota
            String alumnoMejorNota = ""; // Guardará el nombre del alumno con mejor nota
            int aprobados = 0; // Contador de aprobados (nota >= 5.0)
            int suspendidos = 0; // Contador de suspendidos (nota < 5.0)

            System.out.println("\n=== LISTADO DE ALUMNOS ===");

            // Recorremos la lista de alumnos elemento por elemento
            for (int i = 0; i < listaAlumnos.getLength(); i++) {
                
                Node nodo = listaAlumnos.item(i); // Obtenemos el nodo i-ésimo

                // Comprobamos que el nodo sea de tipo ELEMENT_NODE (para ignorar comentarios o espacios)
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    
                    // Convertimos el nodo genérico a un Element para poder usar getAttribute() y getElementsByTagName()
                    Element elemAlumno = (Element) nodo;

                    // Extraemos el atributo 'curso' de la etiqueta <alumno curso="...">
                    String curso = elemAlumno.getAttribute("curso");

                    // Extraemos el texto dentro de <nombre> de ese alumno concreto
                    String nombreAlumno = elemAlumno.getElementsByTagName("nombre").item(0).getTextContent();

                    // Extraemos el texto dentro de <nota> y lo convertimos de String a double
                    double nota = Double.parseDouble(elemAlumno.getElementsByTagName("nota").item(0).getTextContent());

                    // Imprimimos los datos del alumno formateados
                    System.out.println("Alumno: " + nombreAlumno + " | Curso: " + curso + " | Nota: " + nota);

                    // --- CÁLCULO DE ESTADÍSTICAS ---
                    
                    // Sumamos la nota al acumulador total
                    sumaNotas += nota;

                    // Contabilizamos si ha aprobado o suspendido
                    if (nota >= 5.0) {
                        aprobados++;
                    } else {
                        suspendidos++;
                    }

                    // Comprobamos si esta nota supera a la máxima guardada hasta el momento
                    if (nota > notaMaxima) {
                        notaMaxima = nota;
                        alumnoMejorNota = nombreAlumno; // Guardamos el nombre del mejor alumno
                    }
                }
            }

            // ----------------------------------------------------
            // IMPRESIÓN FINAL DE ESTADÍSTICAS
            // ----------------------------------------------------
            
            // Calculamos la nota media dividiendo la suma entre el número total de alumnos
            double notaMedia = sumaNotas / listaAlumnos.getLength();

            System.out.println("\n=== ESTADÍSTICAS DEL ALUMNADO ===");
            // String.format("%.2f", notaMedia) redondea la nota media a 2 decimales
            System.out.println("Nota media del centro: " + String.format("%.2f", notaMedia));
            System.out.println("Alumno con mejor nota: " + alumnoMejorNota + " (" + notaMaxima + ")");
            System.out.println("Aprobados: " + aprobados);
            System.out.println("Suspendidos: " + suspendidos);

        } catch (Exception e) {
            // Capturamos cualquier excepción de parseo XML o lectura de archivo
            System.out.println("Error al procesar el archivo XML: " + e.getMessage());
        }
    }
}