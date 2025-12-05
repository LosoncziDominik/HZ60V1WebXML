import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomQuery1HZ60V1 {
    public static void main(String[] argv) throws SAXException, IOException, ParserConfigurationException, TransformerException {
        File xmlFile = new File("HZ60V1_orarend.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
        System.out.println("------------------------------------\n");

        NodeList orak = doc.getElementsByTagName("ora");

        System.out.print("Tárgyak: ");
        for (int i = 0; i < orak.getLength(); i++) {
            Element ora = (Element) orak.item(i);
            String tipusAttr = ora.getAttribute("tipus");
            if (tipusAttr.equals("eloadas")) {
                System.out.print(ora.getElementsByTagName("targy").item(0).getTextContent() + ", ");
            }
        }

        System.out.println("\n");

        Element elso_ora = (Element) orak.item(0);
        String id = elso_ora.getAttribute("id");
        String tipus = elso_ora.getAttribute("tipus");
        String targy = elso_ora.getElementsByTagName("targy").item(0).getTextContent();

        Element idopontElem = (Element) elso_ora.getElementsByTagName("idopont").item(0);
        String nap = idopontElem.getElementsByTagName("nap").item(0).getTextContent();
        String tol = idopontElem.getElementsByTagName("tol").item(0).getTextContent();
        String ig = idopontElem.getElementsByTagName("ig").item(0).getTextContent();

        String helyszin = elso_ora.getElementsByTagName("helyszin").item(0).getTextContent();
        String oktato = elso_ora.getElementsByTagName("oktato").item(0).getTextContent();
        String szak = elso_ora.getElementsByTagName("szak").item(0).getTextContent();

        System.out.println("ID: " + id);
        System.out.println("Típus: " + tipus);
        System.out.println("Tantárgy: " + targy);
        System.out.println("Időpont: " + nap + " " + tol + " - " + ig);
        System.out.println("Helyszín: " + helyszin);
        System.out.println("Oktató: " + oktato);
        System.out.println("Szak: " + szak);

        DocumentBuilderFactory newFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder newBuilder = newFactory.newDocumentBuilder();
        Document newDoc = newBuilder.newDocument();

        Element root = newDoc.createElement("orarend");
        newDoc.appendChild(root);

        Node importedOra = newDoc.importNode(elso_ora, true);
        root.appendChild(importedOra);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(newDoc);

        String filename = "elso_ora_" + id + ".xml";
        StreamResult result = new StreamResult(new File(filename));
        transformer.transform(source, result);

        System.out.println("\n");

        System.out.print("Oktatók: ");
        for(int i = 0; i < orak.getLength(); i++){
            Element ora = (Element) orak.item(i);

            String tipusAttr = ora.getAttribute("tipus");
            if (tipusAttr.equals("eloadas")) {
            System.out.print(ora.getElementsByTagName("oktato").item(0).getTextContent() + ", ");
            }
        }
    }
}