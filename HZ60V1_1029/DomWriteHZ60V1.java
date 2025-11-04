// Source code modified by Losonczi Dominik (based on FernFlower decompiled version)
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomWriteHZ60V1 {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        // Bemeneti XML fájl
        File inputFile = new File("HZ60V1hallgato.xml");

        // Parser létrehozása
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        // XML betöltése és normalizálása
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("hallgato");

        // Új XML tartalom létrehozása kiíráshoz
        StringBuilder xmlOutput = new StringBuilder();
        xmlOutput.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xmlOutput.append("<hallgatok>\n");

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            System.out.println("\nAktuális elem: " + node.getNodeName());

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                String id = elem.getAttribute("id");
                String keresztnev = elem.getElementsByTagName("keresztnev").item(0).getTextContent();
                String vezeteknev = elem.getElementsByTagName("vezeteknev").item(0).getTextContent();
                String foglalkozas = elem.getElementsByTagName("foglalkozas").item(0).getTextContent();

                // Konzolra írás
                System.out.println("Hallgató id: " + id);
                System.out.println("Keresztnév: " + keresztnev);
                System.out.println("Vezetéknév: " + vezeteknev);
                System.out.println("Foglalkozás: " + foglalkozas);

                // XML-be írás
                xmlOutput.append("  <hallgato id=\"").append(id).append("\">\n");
                xmlOutput.append("    <keresztnev>").append(keresztnev).append("</keresztnev>\n");
                xmlOutput.append("    <vezeteknev>").append(vezeteknev).append("</vezeteknev>\n");
                xmlOutput.append("    <foglalkozas>").append(foglalkozas).append("</foglalkozas>\n");
                xmlOutput.append("  </hallgato>\n");
            }
        }

        xmlOutput.append("</hallgatok>\n");

        // Fájlba mentés
        FileWriter writer = new FileWriter("hallgató1HZ60V1.xml");
        writer.write(xmlOutput.toString());
        writer.close();

        System.out.println("\n✅ Az adatok sikeresen kiírva a hallgato.xml fájlba!");
    }
}
