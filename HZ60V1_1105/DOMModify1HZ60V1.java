import java.io.File;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class DOMModify1HZ60V1 {
    public static void main(String[] args) {
        try {
            File inputFile = new File("HZ60V1_orarend.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Element newOraado = doc.createElement("oraado");
            newOraado.setTextContent("Dr. Kovács Béla");

            Node firstOra = doc.getElementsByTagName("ora").item(0);
            if (firstOra != null) {
                firstOra.appendChild(newOraado);
            }

            NodeList orak = doc.getElementsByTagName("ora");
            for (int i = 0; i < orak.getLength(); i++) {
                Node oraNode = orak.item(i);
                if (oraNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element oraElem = (Element) oraNode;
                    if (oraElem.hasAttribute("tipus")) {
                        String tipus = oraElem.getAttribute("tipus");
                        if ("gyakorlat".equalsIgnoreCase(tipus.trim())) {
                            oraElem.setAttribute("tipus", "előadás");
                        }
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            DOMSource source = new DOMSource(doc);

            StreamResult consoleResult =
                new StreamResult(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
            System.out.println("--- Módosított XML fájl tartalma ---");
            transformer.transform(source, consoleResult);

            StreamResult fileResult = new StreamResult(new File("orarendModify1HZ60V1.xml"));
            transformer.transform(source, fileResult);

            System.out.println("\n--- A fájl mentve: orarendModify1HZ60V1.xml ---");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
