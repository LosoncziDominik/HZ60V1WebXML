//package neptunkodJSON;

import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONWriteHZ60V1 {

    public static void main(String[] args) {

        try (FileReader reader = new FileReader("orarendHZ60V1.json")) {

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            JSONObject root = (JSONObject) jsonObject.get("HZ60V1_orarend");
            JSONArray lessons = (JSONArray) root.get("ora");

            System.out.println("HZ60V1 Órarend 2025 ősz:\n");

            for (int i = 0; i < lessons.size(); i++) {
                JSONObject lesson = (JSONObject) lessons.get(i);
                JSONObject time = (JSONObject) lesson.get("idopont");

                System.out.println("Tárgy: " + lesson.get("targy"));
                System.out.println("Időpont: " + time.get("nap") + " " + time.get("tol") + "-" + time.get("ig"));
                System.out.println("Helyszín: " + lesson.get("helyszin"));
                System.out.println("Oktató: " + lesson.get("oktato"));
                System.out.println("Szak: " + lesson.get("szak"));
                System.out.println();
            }

            JSONObject ujJson = new JSONObject();
            ujJson.put("HZ60V1_orarend", root);

            FileWriter writer = new FileWriter("orarendHZ60V11.json");
            writer.write(ujJson.toJSONString());
            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}