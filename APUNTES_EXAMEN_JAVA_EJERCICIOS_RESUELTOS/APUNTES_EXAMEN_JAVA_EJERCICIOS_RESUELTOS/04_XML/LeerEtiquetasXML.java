import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class LeerEtiquetasXML {
    public static void main(String[] args) {
        try {
            // DOM carga el XML como arbol de nodos.
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            DocumentBuilder builder = fabrica.newDocumentBuilder();
            Document doc = builder.parse(new File("personajes.xml")); // CAMBIAR nombre
            // CAMBIAR EN EXAMEN: etiqueta que se repite (<npc>).
            NodeList lista = doc.getElementsByTagName("npc");
            for (int i = 0; i < lista.getLength(); i++) {
                Element npc = (Element) lista.item(i);
                // CAMBIAR EN EXAMEN: etiquetas internas <nombre>, <vida>.
                String nombre = npc.getElementsByTagName("nombre").item(0).getTextContent();
                String vida = npc.getElementsByTagName("vida").item(0).getTextContent();
                System.out.println("NPC: " + nombre + " | vida: " + vida);
            }
        } catch (Exception e) { System.out.println("Error XML: " + e.getMessage()); }
    }
}
