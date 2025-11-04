//package domkprlnb;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomReadHZ60V11
{
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException
    {
        // XML fájl megnyitása
        File xmlFile = new File("HZ60V1_orarend.xml");

        // DocumentBuilderFactory példányosítása
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // DocumentBuilder létrehozása
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        // DOM fa előállítása
        Document doc = dBuilder.parse(xmlFile);

        // Dokumentum normalizálása
        doc.getDocumentElement().normalize();

        // Gyökérelem neve
        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());

        // Az összes <ora> elem listája
        NodeList nList = doc.getElementsByTagName("ora");

        System.out.println("\nAz órarend tartalma:\n----------------------------------");

        for (int i = 0; i < nList.getLength(); i++)
        {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element elem = (Element) nNode;

                // Attribútumok lekérése
                String id = elem.getAttribute("id");
                String tipus = elem.getAttribute("tipus");

                // Al-elemek lekérése
                String targy = elem.getElementsByTagName("targy").item(0).getTextContent();

                Element idopontElem = (Element) elem.getElementsByTagName("idopont").item(0);
                String nap = idopontElem.getElementsByTagName("nap").item(0).getTextContent();
                String tol = idopontElem.getElementsByTagName("tol").item(0).getTextContent();
                String ig = idopontElem.getElementsByTagName("ig").item(0).getTextContent();

                String helyszin = elem.getElementsByTagName("helyszin").item(0).getTextContent();
                String oktato = elem.getElementsByTagName("oktato").item(0).getTextContent();
                String szak = elem.getElementsByTagName("szak").item(0).getTextContent();

                // Eredmények kiírása
                System.out.println("Óra ID: " + id);
                System.out.println("Típus: " + tipus);
                System.out.println("Tantárgy: " + targy);
                System.out.println("Időpont: " + nap + " " + tol + " - " + ig);
                System.out.println("Helyszín: " + helyszin);
                System.out.println("Oktató: " + oktato);
                System.out.println("Szak: " + szak);
                System.out.println("----------------------------------");
            }
        }
    }
}
