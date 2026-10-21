import java.io.File;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class BuilderXML{
    public static void main(String[] args) {
        try {
            DocumentBuilder dbuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dbuilder.newDocument();
            Element arrel = doc.createElement("Pokedex");
            doc.appendChild(arrel);

            Element pokemon = doc.createElement("Pokemon");
            pokemon.setAttribute("Pokemon", "Pikachu");
            arrel.appendChild(pokemon);

            Element hp = doc.createElement("hp");
            hp.appendChild(doc.createTextNode("100"));
            pokemon.appendChild(hp);

            //Usuario
            System.out.println("\nIntroduce el nombre: ");
            Scanner leerP = new Scanner(System.in);
            String palabra = leerP.next();
            Element pokemon1 = doc.createElement("Pokemon");
            pokemon1.setAttribute("Pokemon", palabra);
            arrel.appendChild(pokemon1);

            System.out.println("\nIntroduce la vida: ");
            Scanner leerV = new Scanner(System.in);
            String vida = leerV.next();
            Element hp1 = doc.createElement("hp");
            hp1.appendChild(doc.createTextNode(vida));
            pokemon1.appendChild(hp1);
            
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(new File("sortida.xml")));
        } catch (Exception e) {
        }
    }
}