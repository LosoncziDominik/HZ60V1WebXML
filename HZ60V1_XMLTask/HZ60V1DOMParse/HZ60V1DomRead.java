import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class HZ60V1DomRead
{
    public static void main(String[] argv) throws SAXException, IOException, ParserConfigurationException
    {
        // Dokumentum létrehozása
        File xmlFile = new File("HZ60V1_XML.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());

        // Mozik
        NodeList mozik = doc.getElementsByTagName("mozi");
        System.out.println("Mozik");
        for (int i = 0; i < mozik.getLength(); i++) {
            Element mozi = (Element) mozik.item(i);
            System.out.println("Mozi kód: " + mozi.getAttribute("Mkod"));
            System.out.println("Név: " + mozi.getElementsByTagName("név").item(0).getTextContent());

            Element cim = (Element) mozi.getElementsByTagName("cím").item(0);
            System.out.println("  Város: " + cim.getElementsByTagName("város").item(0).getTextContent());
            System.out.println("  Utca: " + cim.getElementsByTagName("utca").item(0).getTextContent());
            System.out.println("  Házszám: " + cim.getElementsByTagName("házszám").item(0).getTextContent());
            System.out.println();
        }

        // Dolgozók
        NodeList dolgozok = doc.getElementsByTagName("dolgozó");
        System.out.println("\nDolgozók");
        for (int i = 0; i < dolgozok.getLength(); i++) {
            Element d = (Element) dolgozok.item(i);
            System.out.println("Dolgozó kód: " + d.getAttribute("Dkod"));
            System.out.println("Mozi kód (M_D): " + d.getAttribute("M_D"));
            System.out.println("Név: " + d.getElementsByTagName("név").item(0).getTextContent());
            System.out.println("Műszak: " + d.getElementsByTagName("műszak").item(0).getTextContent());
            System.out.println("Beosztás: " + d.getElementsByTagName("beosztás").item(0).getTextContent());
            System.out.println();
        }

        // Termek
        NodeList termek = doc.getElementsByTagName("terem");
        System.out.println("\nTermek");
        for (int i = 0; i < termek.getLength(); i++) {
            Element t = (Element) termek.item(i);
            System.out.println("Terem kód: " + t.getAttribute("Tkod"));
            System.out.println("Mozi kód (M_T): " + t.getAttribute("M_T"));
            System.out.println("Férőhely: " + t.getElementsByTagName("férőhely").item(0).getTextContent());
            System.out.println("Típus: " + t.getElementsByTagName("típus").item(0).getTextContent());
            System.out.println("Hangrendszer: " + t.getElementsByTagName("hangrendszer").item(0).getTextContent());
            System.out.println("Extrák: " + t.getElementsByTagName("extrák").item(0).getTextContent());
            System.out.println();
        }

        // Filmek
        NodeList filmek = doc.getElementsByTagName("film");
        System.out.println("\nFilmek");
        for (int i = 0; i < filmek.getLength(); i++) {
            Element f = (Element) filmek.item(i);
            System.out.println("Film kód: " + f.getAttribute("Fkod"));
            System.out.println("Mozi kód (M_F): " + f.getAttribute("M_F"));
            System.out.println("Cím: " + f.getElementsByTagName("cím").item(0).getTextContent());
            System.out.println("Hossz: " + f.getElementsByTagName("hossz").item(0).getTextContent() + " perc");
            System.out.println("Rendező: " + f.getElementsByTagName("rendező").item(0).getTextContent());
            System.out.println("Év: " + f.getElementsByTagName("év").item(0).getTextContent());
            System.out.println("Értékelés: " + f.getElementsByTagName("értékelés").item(0).getTextContent());
            System.out.println();

            NodeList szereplok = f.getElementsByTagName("szereplő");
            System.out.print("Szereplők: ");
            for (int j = 0; j < szereplok.getLength(); j++) {
                System.out.print(szereplok.item(j).getTextContent());
                if (j < szereplok.getLength() - 1) System.out.print(", ");
            }
            System.out.println();
        }

        // Jegyek
        NodeList jegyek = doc.getElementsByTagName("jegy");
        System.out.println("\nJegyek");
        for (int i = 0; i < jegyek.getLength(); i++) {
            Element j = (Element) jegyek.item(i);
            System.out.println("Jegy kód: " + j.getAttribute("Jkod"));
            System.out.println("Mozi kód (M_J): " + j.getAttribute("M_J"));
            System.out.println("Ár: " + j.getElementsByTagName("ár").item(0).getTextContent() + " Ft");
            System.out.println("Kedvezmény: " + j.getElementsByTagName("kedvezmény").item(0).getTextContent());
            System.out.println();
        }

        // Vásárlók
        NodeList vasarlok = doc.getElementsByTagName("vásárló");
        System.out.println("\nVásárlók");
        for (int i = 0; i < vasarlok.getLength(); i++) {
            Element v = (Element) vasarlok.item(i);
            System.out.println("Vásárló kód: " + v.getAttribute("Vkod"));
            Element cim = (Element) v.getElementsByTagName("cím").item(0);
            System.out.println("  Város: " + cim.getElementsByTagName("város").item(0).getTextContent());
            System.out.println("  Utca: " + cim.getElementsByTagName("utca").item(0).getTextContent());
            System.out.println("  Házszám: " + cim.getElementsByTagName("házszám").item(0).getTextContent());
            System.out.println("Név: " + v.getElementsByTagName("név").item(0).getTextContent());
            System.out.println("Életkor: " + v.getElementsByTagName("életkor").item(0).getTextContent());

            Element foglalas = (Element) v.getElementsByTagName("foglalás").item(0);
            System.out.println("Foglalás - Film: " + foglalas.getElementsByTagName("film_cím").item(0).getTextContent());
            System.out.println("Foglalás - Időpont: " + foglalas.getElementsByTagName("időpont").item(0).getTextContent());
            System.out.println("Foglalás - Nyelv: " + foglalas.getElementsByTagName("nyelv").item(0).getTextContent());
            System.out.println();
        }

        // Büfék
        NodeList bufek = doc.getElementsByTagName("büfé");
        System.out.println("\nBüfék");
        for (int i = 0; i < bufek.getLength(); i++) {
            Element b = (Element) bufek.item(i);
            System.out.println("Büfé kód: " + b.getAttribute("Bkod"));
            System.out.println("Mozi kód (M_B): " + b.getAttribute("M_B"));

            // Ételek
            NodeList etelek = b.getElementsByTagName("étel");
            for (int j = 0; j < etelek.getLength(); j++) {
                Element etel = (Element) etelek.item(j);
                System.out.println("  Étel: " + etel.getElementsByTagName("termék").item(0).getTextContent() +
                        " (" + etel.getElementsByTagName("típus").item(0).getTextContent() + "), Ár: " +
                        etel.getElementsByTagName("ár").item(0).getTextContent() + " Ft");

                // Méretek
                NodeList meretek = etel.getElementsByTagName("méret");
                System.out.print("    Méretek: ");
                for (int k = 0; k < meretek.getLength(); k++) {
                    System.out.print(meretek.item(k).getTextContent());
                    if (k < meretek.getLength() - 1) System.out.print(", ");
                }
                System.out.println();
            }

            // Italok
            NodeList italok = b.getElementsByTagName("ital");
            for (int j = 0; j < italok.getLength(); j++) {
                Element ital = (Element) italok.item(j);
                System.out.println("  Ital: " + ital.getElementsByTagName("termék").item(0).getTextContent() +
                        " (" + ital.getElementsByTagName("típus").item(0).getTextContent() + "), Ár: " +
                        ital.getElementsByTagName("ár").item(0).getTextContent() + " Ft");

                // Űrtartalmak
                NodeList urtartalmak = ital.getElementsByTagName("űrtartalom");
                System.out.print("    Űrtartalmak: ");
                for (int k = 0; k < urtartalmak.getLength(); k++) {
                    System.out.print(urtartalmak.item(k).getTextContent() + " ml");
                    if (k < urtartalmak.getLength() - 1) System.out.print(", ");
                }
                System.out.println();
            }

            // Egyéb termékek
            NodeList egyebek = b.getElementsByTagName("egyéb");
            for (int j = 0; j < egyebek.getLength(); j++) {
                Element e = (Element) egyebek.item(j);
                System.out.println("  Egyéb: " + e.getElementsByTagName("termék").item(0).getTextContent() +
                        ", Ár: " + e.getElementsByTagName("ár").item(0).getTextContent() + " Ft");
            }
        }
    }
}
