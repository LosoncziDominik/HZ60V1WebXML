import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class HZ60V1DomQuery {
    public static void main(String[] argv) throws SAXException, IOException, ParserConfigurationException {
        // XML betöltése
        File xmlFile = new File("HZ60V1_XML.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());

        // Mozik
        NodeList mozik = doc.getElementsByTagName("mozi");
        System.out.println("\n Mozik:");
        for (int i = 0; i < mozik.getLength(); i++) {
            Element mozi = (Element) mozik.item(i);
            String mkod = mozi.getAttribute("Mkod");
            String nev = mozi.getElementsByTagName("név").item(0).getTextContent().trim();

            Element cim = (Element) mozi.getElementsByTagName("cím").item(0);
            String varos = cim.getElementsByTagName("város").item(0).getTextContent();
            String utca = cim.getElementsByTagName("utca").item(0).getTextContent();
            String hazszam = cim.getElementsByTagName("házszám").item(0).getTextContent();

            System.out.println("  [" + mkod + "] " + nev + " - " + varos + ", " + utca + " " + hazszam);
        }

        // Dolgozók
        NodeList dolgozok = doc.getElementsByTagName("dolgozó");
        System.out.println("\n Dolgozók:");
        for (int i = 0; i < dolgozok.getLength(); i++) {
            Element d = (Element) dolgozok.item(i);
            String dkod = d.getAttribute("Dkod");
            String nev = d.getElementsByTagName("név").item(0).getTextContent();
            String muszak = d.getElementsByTagName("műszak").item(0).getTextContent();
            String beosztas = d.getElementsByTagName("beosztás").item(0).getTextContent();
            System.out.println("  [" + dkod + "] " + nev + " (" + muszak + ", " + beosztas + ")");
        }

        // Filmek
        NodeList filmek = doc.getElementsByTagName("film");
        System.out.println("\n Filmek:");
        for (int i = 0; i < filmek.getLength(); i++) {
            Element f = (Element) filmek.item(i);
            String fkod = f.getAttribute("Fkod");
            String cim = f.getElementsByTagName("cím").item(0).getTextContent();
            String hossz = f.getElementsByTagName("hossz").item(0).getTextContent();
            String ertekeles = f.getElementsByTagName("értékelés").item(0).getTextContent();
            System.out.println("  [" + fkod + "] " + cim + " (" + hossz + " perc) - értékelés: " + ertekeles);
        }

        // Vásárlók
        NodeList vasarlok = doc.getElementsByTagName("vásárló");
        System.out.println("\n Vásárlók:");
        for (int i = 0; i < vasarlok.getLength(); i++) {
            Element v = (Element) vasarlok.item(i);
            String vkod = v.getAttribute("Vkod");
            String nev = v.getElementsByTagName("név").item(0).getTextContent();
            String kor = v.getElementsByTagName("életkor").item(0).getTextContent();

            Element foglalas = (Element) v.getElementsByTagName("foglalás").item(0);
            String filmCim = foglalas.getElementsByTagName("film_cím").item(0).getTextContent();
            String idopont = foglalas.getElementsByTagName("időpont").item(0).getTextContent();
            String nyelv = foglalas.getElementsByTagName("nyelv").item(0).getTextContent();

            System.out.println("  [" + vkod + "] " + nev + " (" + kor + " éves) → " +
                               filmCim + " (" + idopont + ", " + nyelv + ")");
        }

        // Büfék
        NodeList bufek = doc.getElementsByTagName("büfé");
        System.out.println("\n Büfék:");
        for (int i = 0; i < bufek.getLength(); i++) {
            Element b = (Element) bufek.item(i);
            String bkod = b.getAttribute("Bkod");
            System.out.println("  Büfé [" + bkod + "]:");
            NodeList etelek = b.getElementsByTagName("étel");
            for (int j = 0; j < etelek.getLength(); j++) {
                Element etel = (Element) etelek.item(j);
                String termek = etel.getElementsByTagName("termék").item(0).getTextContent();
                String tipus = etel.getElementsByTagName("típus").item(0).getTextContent();
                String ar = etel.getElementsByTagName("ár").item(0).getTextContent();
                System.out.println("    " + termek + " (" + tipus + ") - " + ar + " Ft");
            }
        }

    }
}
