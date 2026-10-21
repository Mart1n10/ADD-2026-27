import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*; // con la estrella coge todo tipo de Document
public class LecturaXML {
    public static void main(String[] args) {
        File file = new File("car.xml");
        try {
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document doc = constructor.parse(file);
            doc.getDocumentElement().normalize();

            NodeList llista = doc.getElementsByTagName("car");
            System.out.println("Numero de coches: " + llista.getLength());
            for (int i = 0; i < llista.getLength(); i++) {
                Node node = llista.item(i);
                if (node.getNodeType() == node.ELEMENT_NODE){
                    Element e = (Element)node;
                    System.out.println("Modelo = " + e.getElementsByTagName("brand").item(0).getTextContent() + "\nAño = " + e.getElementsByTagName("year").item(0).getTextContent());
                }
            }    
        } catch (Exception e) {
        }
    }
}
