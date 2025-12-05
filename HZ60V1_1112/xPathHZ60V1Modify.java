import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.*;

public class xPathHZ60V1Modify {

    public static void main(String[] args) throws Exception {
        String xmlFile = "studentHZ60V1.xml";

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlFile);
        doc.getDocumentElement().normalize();

        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();

        String exprString = "//student[@id='01']";

        XPathExpression expr = xpath.compile(exprString);
        Node studentNode = (Node) expr.evaluate(doc, XPathConstants.NODE);

        if (studentNode == null) {
            System.out.println("Nincs olyan student, amelynek id=\"01\".");
            return;
        }

        if (studentNode.getNodeType() == Node.ELEMENT_NODE) {
            Element studentElem = (Element) studentNode;
            NodeList keresztnevek = studentElem.getElementsByTagName("keresztnev");
            if (keresztnevek.getLength() > 0) {
                Node keresztnevNode = keresztnevek.item(0);
                keresztnevNode.setTextContent("Katinka");
            }
        }

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        System.out.println("A modositott id=\"01\" student elem:");
        transformer.transform(new DOMSource(studentNode), new StreamResult(System.out));
    }
}
