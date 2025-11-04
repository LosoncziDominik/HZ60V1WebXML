//package domkprlnb;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomWriteHZ60V11
{
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException
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

        // --- Új DOM dokumentum létrehozása a kiíráshoz ---
        Document newDoc = dBuilder.newDocument();
        Element root = newDoc.createElement("orarend_kimentes");
        newDoc.appendChild(root);

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

                // --- Konzolra írás ---
                System.out.println("Óra ID: " + id);
                System.out.println("Típus: " + tipus);
                System.out.println("Tantárgy: " + targy);
                System.out.println("Időpont: " + nap + " " + tol + " - " + ig);
                System.out.println("Helyszín: " + helyszin);
                System.out.println("Oktató: " + oktato);
                System.out.println("Szak: " + szak);
                System.out.println("----------------------------------");

                // --- Új XML elem létrehozása és feltöltése ---
                Element oraElem = newDoc.createElement("ora");
                oraElem.setAttribute("id", id);
                oraElem.setAttribute("tipus", tipus);

                Element targyElem = newDoc.createElement("targy");
                targyElem.setTextContent(targy);
                oraElem.appendChild(targyElem);

                Element idopont = newDoc.createElement("idopont");
                Element napElem = newDoc.createElement("nap");
                napElem.setTextContent(nap);
                Element tolElem = newDoc.createElement("tol");
                tolElem.setTextContent(tol);
                Element igElem = newDoc.createElement("ig");
                igElem.setTextContent(ig);
                idopont.appendChild(napElem);
                idopont.appendChild(tolElem);
                idopont.appendChild(igElem);
                oraElem.appendChild(idopont);

                Element helyszinElem = newDoc.createElement("helyszin");
                helyszinElem.setTextContent(helyszin);
                oraElem.appendChild(helyszinElem);

                Element oktatoElem = newDoc.createElement("oktato");
                oktatoElem.setTextContent(oktato);
                oraElem.appendChild(oktatoElem);

                Element szakElem = newDoc.createElement("szak");
                szakElem.setTextContent(szak);
                oraElem.appendChild(szakElem);

                root.appendChild(oraElem);
            }
        }

        // --- XML mentése fájlba ---
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // szépen formázott
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(newDoc);
        StreamResult result = new StreamResult(new File("orarend1.xml"));

        transformer.transform(source, result);

        System.out.println("\n✅ Az új XML fájl sikeresen létrehozva: orarend_kimentes.xml");
    }
}
