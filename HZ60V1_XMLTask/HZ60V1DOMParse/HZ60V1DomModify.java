import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class HZ60V1DomModify
{
    public static void main(String argv[]) {

        try {
            // Fájl beolvasása
            File inputFile = new File("HZ60V1_XML.xml");

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());

            // Mozi nevének módosítása
            Node mozi = doc.getElementsByTagName("mozi").item(0);
            Element moziElem = (Element) mozi;

            // Név element tartalmának módosítása
            Node nevNode = moziElem.getElementsByTagName("név").item(0);
            nevNode.setTextContent("Miskolc Egyetem Mozi");

            System.out.println(" Mozi név módosítva: " + nevNode.getTextContent());

            // Dolgozó beosztásának módosítása
            Node dolgozo = doc.getElementsByTagName("dolgozó").item(1);
            Element dolgozoElem = (Element) dolgozo;

            Node beosztasNode = dolgozoElem.getElementsByTagName("beosztás").item(0);
            beosztasNode.setTextContent("Pénztáros");

            System.out.println(" Dolgozó beosztás módosítva: " + beosztasNode.getTextContent());

            // Értékelés módosítása
            Node film = doc.getElementsByTagName("film").item(2);
            Element filmElem = (Element) film;

            Node ertekelesNode = filmElem.getElementsByTagName("értékelés").item(0);
            String regiErtekeles = ertekelesNode.getTextContent();
            ertekelesNode.setTextContent("9.1");

            System.out.println(" Film értékelés módosítva: " + regiErtekeles + " → " + ertekelesNode.getTextContent());

            // Ár változtatása +10%
            Node jegy = doc.getElementsByTagName("jegy").item(0);
            Element jegyElem = (Element) jegy;

            Node arNode = jegyElem.getElementsByTagName("ár").item(0);
            int ar = Integer.parseInt(arNode.getTextContent());
            int ujAr = (int) (ar * 1.1);
            arNode.setTextContent(String.valueOf(ujAr));

            System.out.println(" Jegy ár módosítva: " + ar + " Ft → " + ujAr + " Ft");

            // LEKÉRDEZÉS: Cseréljük le az egyik büfé ital típusát
            Node bufe = doc.getElementsByTagName("büfé").item(0);
            Element bufeElem = (Element) bufe;

            Node italNode = bufeElem.getElementsByTagName("ital").item(0);
            Element italElem = (Element) italNode;
            Node tipusNode = italElem.getElementsByTagName("típus").item(0);
            tipusNode.setTextContent("Zero Cukormentes");

            System.out.println(" Ital típusa módosítva: " + tipusNode.getTextContent());
            
            // Kimenet kiírása
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);
            StreamResult consoleResult = new StreamResult(System.out);

            System.out.println("\n--- MÓDOSÍTOTT XML ---");
            transformer.transform(source, consoleResult);

            // Fileba mentés
            StreamResult fileResult = new StreamResult(new File("HZ60V1_mozi_MOD.xml"));
            transformer.transform(source, fileResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
