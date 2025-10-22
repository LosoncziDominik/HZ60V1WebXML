import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.File;

public class DomReadHZ60V1 {
    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("HZ60V1_XML.xml"));

        Element root = doc.getDocumentElement();
        printNode(root, 0);
    }

    private static void printNode(Node node, int depth) {
        String indent = "  ".repeat(depth);

        switch (node.getNodeType()) {
            case Node.ELEMENT_NODE:
                System.out.print(indent + "<" + node.getNodeName());

                NamedNodeMap attrs = node.getAttributes();
                for (int i = 0; i < attrs.getLength(); i++) {
                    Node attr = attrs.item(i);
                    System.out.print(" " + attr.getNodeName() + "=\"" + attr.getNodeValue() + "\"");
                }
                System.out.println(">");

                NodeList children = node.getChildNodes();
                for (int i = 0; i < children.getLength(); i++) {
                    Node child = children.item(i);
                    if (child.getNodeType() == Node.ELEMENT_NODE ||
                        (child.getNodeType() == Node.TEXT_NODE && !child.getTextContent().trim().isEmpty())) {
                        printNode(child, depth + 1);
                    }
                }

                System.out.println(indent + "</" + node.getNodeName() + ">");
                break;

            case Node.TEXT_NODE:
                System.out.println(indent + node.getTextContent().trim());
                break;
        }
    }
}