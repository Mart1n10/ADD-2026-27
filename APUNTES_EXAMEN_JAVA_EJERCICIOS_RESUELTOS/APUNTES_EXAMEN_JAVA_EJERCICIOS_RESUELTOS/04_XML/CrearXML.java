import java.io.File;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class CrearXML {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            Document doc = fabrica.newDocumentBuilder().newDocument();
            // CAMBIAR EN EXAMEN: raiz y etiquetas que pide el enunciado.
            Element raiz = doc.createElement("biblioteca");
            doc.appendChild(raiz);
            String[] titulos = {"Java facil", "POO", "XML"};
            int[] precios = {20, 25, 18};
            for (int i = 0; i < titulos.length; i++) {
                Element libro = doc.createElement("libro");
                Element titulo = doc.createElement("titulo");
                titulo.setTextContent(titulos[i]);
                Element precio = doc.createElement("precio");
                precio.setTextContent(String.valueOf(precios[i]));
                libro.appendChild(titulo);
                libro.appendChild(precio);
                raiz.appendChild(libro);
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(new File("biblioteca.xml")));
            System.out.println("XML creado");
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }
}
