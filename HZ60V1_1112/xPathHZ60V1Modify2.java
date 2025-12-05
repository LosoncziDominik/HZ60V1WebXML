import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import java.io.File;

public class xPathHZ60V1Modify2 {

    public static void main(String[] args) throws Exception {
        String xmlFileIn  = "HZ60V1_orarend.xml";
        String xmlFileOut = "HZ60V1_orarend3.xml";

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlFileIn);
        doc.getDocumentElement().normalize();

        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
        String exprStr = "/LD_orarend/ora[position() <= 3]";
        XPathExpression expr = xpath.compile(exprStr);
        NodeList list = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

        System.out.println("A modositott 3 ora (helyszin = ONLINE):");

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        for (int i = 0; i < list.getLength(); i++) {
            Element ora = (Element) list.item(i);

            Node helyNode = ora.getElementsByTagName("helyszin").item(0);
            if (helyNode != null) {
                helyNode.setTextContent("ONLINE");
            }

            System.out.println("------------");
            t.transform(new DOMSource(ora), new StreamResult(System.out));
            System.out.println();
        }

        Transformer tAll = tf.newTransformer();
        tAll.setOutputProperty(OutputKeys.INDENT, "yes");
        tAll.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        tAll.transform(new DOMSource(doc), new StreamResult(new File(xmlFileOut)));
        System.out.println("\nA teljes modositott orarend kiirva XML-be: " + xmlFileOut);
    }
}
