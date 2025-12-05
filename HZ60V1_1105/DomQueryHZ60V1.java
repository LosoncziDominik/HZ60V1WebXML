import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.w3c.dom.*;

public class DomQueryHZ60V1{
    public static void main(String[] argv) throws SAXException, IOException, ParserConfigurationException
    {
        File xmlFile = new File("HZ60V1hallgato.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
        System.out.println("------------------------------------\n");

        System.out.println("Aktuális elem:");
        NodeList hallgatok = doc.getElementsByTagName("hallgato");

        for(int i = 0; i < hallgatok.getLength(); i++){
            Element hallgato = (Element) hallgatok.item(i);
            System.out.println(hallgato.getElementsByTagName("hallgato").item(0).getTextContent());
            System.out.println("vezeteknev: " +  hallgato.getElementsByTagName("vezeteknev").item(0).getTextContent());
        }
    }
}