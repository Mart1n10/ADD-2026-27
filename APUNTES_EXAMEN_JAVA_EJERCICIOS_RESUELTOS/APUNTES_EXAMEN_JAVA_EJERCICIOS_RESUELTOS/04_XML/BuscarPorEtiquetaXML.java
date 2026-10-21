import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class BuscarPorEtiquetaXML {
    public static void main(String[] args) {
        String buscar = "Guardian"; // CAMBIAR EN EXAMEN: nombre a buscar
        try {
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            Document doc = fabrica.newDocumentBuilder().parse(new File("personajes.xml"));
            NodeList lista = doc.getElementsByTagName("npc");
            boolean encontrado = false;
            for (int i = 0; i < lista.getLength(); i++) {
                Element npc = (Element) lista.item(i);
                String nombre = npc.getElementsByTagName("nombre").item(0).getTextContent();
                // CAMBIAR EN EXAMEN: equalsIgnoreCase para buscar nombre, o usar numero y >.
                if (nombre.equalsIgnoreCase(buscar)) {
                    String vida = npc.getElementsByTagName("vida").item(0).getTextContent();
                    System.out.println("Encontrado: " + nombre + ", vida " + vida);
                    encontrado = true;
                }
            }
            if (!encontrado) System.out.println("No encontrado");
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }
}
