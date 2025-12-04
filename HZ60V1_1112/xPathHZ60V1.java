import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;

public class xPathHZ60V1 {

    private static void printHeader(int n, String description, String xpathExpr) {
        System.out.println("\n" + n + ") " + description);
        System.out.println("   XPath: " + xpathExpr);
    }

    private static void printNodeList(NodeList nodes) {
        for (int i = 0; i < nodes.getLength(); i++) {
            Node n = nodes.item(i);
            System.out.print("   -> <" + n.getNodeName() + ">");

            NamedNodeMap attrs = n.getAttributes();
            if (attrs != null && attrs.getLength() > 0) {
                System.out.print(" [");
                for (int j = 0; j < attrs.getLength(); j++) {
                    Node a = attrs.item(j);
                    System.out.print(a.getNodeName() + "=\"" + a.getNodeValue() + "\"");
                    if (j < attrs.getLength() - 1) System.out.print(", ");
                }
                System.out.print("]");
            }

            String text = n.getTextContent();
            if (text != null) {
                text = text.trim();
                if (!text.isEmpty()) {
                    System.out.print(" : " + text);
                }
            }
            System.out.println();
        }

        if (nodes.getLength() == 0) {
            System.out.println("   (nincs találat)");
        }
    }

    public static void main(String[] args) throws Exception {
        String xmlFile = "studentHZ60V1.xml";

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlFile);
        doc.getDocumentElement().normalize();

        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();

        String q1 = "/class/student";
        printHeader(1, "Osszes student elem, amely a class gyermeke", q1);
        printNodeList((NodeList) xpath.evaluate(q1, doc, XPathConstants.NODESET));

        String q2 = "//student[@id='02']";
        printHeader(2, "student elem, amelynek id=\"02\"", q2);
        printNodeList((NodeList) xpath.evaluate(q2, doc, XPathConstants.NODESET));

        String q3 = "//student";
        printHeader(3, "Osszes student elem, fuggetlenul a helyetol", q3);
        printNodeList((NodeList) xpath.evaluate(q3, doc, XPathConstants.NODESET));

        String q4 = "/class/student[2]";
        printHeader(4, "Masodik student elem a class alatt", q4);
        printNodeList((NodeList) xpath.evaluate(q4, doc, XPathConstants.NODESET));

        String q5 = "/class/student[last()]";
        printHeader(5, "Utolso student elem a class alatt", q5);
        printNodeList((NodeList) xpath.evaluate(q5, doc, XPathConstants.NODESET));

        String q6 = "/class/student[last()-1]";
        printHeader(6, "Utolso elotti student elem a class alatt", q6);
        printNodeList((NodeList) xpath.evaluate(q6, doc, XPathConstants.NODESET));

        String q7 = "/class/student[position() <= 2]";
        printHeader(7, "Elso ket student elem a class alatt", q7);
        printNodeList((NodeList) xpath.evaluate(q7, doc, XPathConstants.NODESET));

        String q8 = "/class/*";
        printHeader(8, "A class root element osszes gyermek eleme", q8);
        printNodeList((NodeList) xpath.evaluate(q8, doc, XPathConstants.NODESET));

        String q9 = "//student[@*]";
        printHeader(9, "Minden student, amely rendelkezik attributummal", q9);
        printNodeList((NodeList) xpath.evaluate(q9, doc, XPathConstants.NODESET));

        String q10 = "//*";
        printHeader(10, "A dokumentum osszes eleme", q10);
        printNodeList((NodeList) xpath.evaluate(q10, doc, XPathConstants.NODESET));

        String q11 = "/class/student[kor > 20]";
        printHeader(11, "A class osszes student eleme, ahol kor > 20", q11);
        printNodeList((NodeList) xpath.evaluate(q11, doc, XPathConstants.NODESET));

        String q12 = "//student/keresztnev | //student/vezeteknev";
        printHeader(12, "Minden student kereszteneve es vezetekneve", q12);
        printNodeList((NodeList) xpath.evaluate(q12, doc, XPathConstants.NODESET));
    }
}
