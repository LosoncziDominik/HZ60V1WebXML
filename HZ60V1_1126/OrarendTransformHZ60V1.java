import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class OrarendTransformHZ60V1 {

    public static void main(String[] args) {
        String xmlFile = "HZ60V1_orarend.xml";
        String xslFile = "HZ60V1_orarend.xsl";
        String outXml  = "HZ60V1_orarend.out.xml";
        String outHtml = "HZ60V1_orarend.html";

        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(
                    new StreamSource(new File(xslFile)));

            transformer.transform(
                    new StreamSource(new File(xmlFile)),
                    new StreamResult(new File(outXml)));

            transformer.transform(
                    new StreamSource(new File(xmlFile)),
                    new StreamResult(new File(outHtml)));

            System.out.println("Kész fájlok:");
            System.out.println(" - " + outXml);
            System.out.println(" - " + outHtml);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
