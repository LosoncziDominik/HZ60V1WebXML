import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import java.io.File;

public class xPathQueryHZ60V1 {

     public static void main(String[] args) throws Exception {
        String xmlFileIn  = "HZ60V1_orarend.xml";
        String xmlFileOut = "HZ60V1_orarend2.xml";

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document inDoc = db.parse(xmlFileIn);
        inDoc.getDocumentElement().normalize();

        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
        String exprStr = "/LD_orarend/ora[position() <= 3]";
        XPathExpression expr = xpath.compile(exprStr);
        NodeList list = (NodeList) expr.evaluate(inDoc, XPathConstants.NODESET);

        System.out.println("Az elso 3 ora az orarendben:");

        for (int i = 0; i < list.getLength(); i++) {
            Element ora = (Element) list.item(i);
            String id = ora.getAttribute("id");
            String tipus = ora.getAttribute("tipus");
            String targy = ora.getElementsByTagName("targy").item(0).getTextContent().trim();
            Element idopont = (Element) ora.getElementsByTagName("idopont").item(0);
            String nap = idopont.getElementsByTagName("nap").item(0).getTextContent().trim();
            String tol = idopont.getElementsByTagName("tol").item(0).getTextContent().trim();
            String ig  = idopont.getElementsByTagName("ig").item(0).getTextContent().trim();
            String helyszin = ora.getElementsByTagName("helyszin").item(0).getTextContent().trim();

            System.out.println("------------");
            System.out.println("id = " + id + ", tipus = " + tipus);
            System.out.println("Targy   : " + targy);
            System.out.println("Nap     : " + nap);
            System.out.println("Ido     : " + tol + " - " + ig);
            System.out.println("Helyszin: " + helyszin);
        }

        Document outDoc = db.newDocument();
        Element root = outDoc.createElement("elso3ora");
        outDoc.appendChild(root);

        for (int i = 0; i < list.getLength(); i++) {
            Node ora = list.item(i);
            Node imported = outDoc.importNode(ora, true);
            root.appendChild(imported);
        }

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        t.transform(new DOMSource(outDoc), new StreamResult(new File(xmlFileOut)));
        System.out.println("\nAz elso 3 ora kiirva XML-be is: " + xmlFileOut);
    }
}