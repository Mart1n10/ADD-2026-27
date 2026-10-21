import java.io.File;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class ModificarEtiquetaXML {
    public static void main(String[] args) {
        File fichero = new File("personajes.xml"); // CAMBIAR EN EXAMEN
        try {
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            Document doc = fabrica.newDocumentBuilder().parse(fichero);
            NodeList lista = doc.getElementsByTagName("npc");
            for (int i = 0; i < lista.getLength(); i++) {
                Element nodo = (Element) lista.item(i);
                String nombre = nodo.getElementsByTagName("nombre").item(0).getTextContent();
                if (nombre.equals("Guardian")) { // CAMBIAR nombre a buscar
                    nodo.getElementsByTagName("vida").item(0).setTextContent("150"); // CAMBIAR dato
                }
            }
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(fichero)); // guarda cambios
            System.out.println("XML modificado");
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }
}
