import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class HallgatoTransformHZ60V1 {

    public static void main(String[] args) {
        String xmlFile   = "hallgatoHZ60V1.xml";
        String xslFile   = "hallgatoHZ60V1.xsl";
        String outXml    = "hallgatoHZ60V1.out.xml";
        String outHtml   = "hallgatoHZ60V1.html";      

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
