import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONValidationHZ60V1 {

    public static void main(String[] args) {
        String schemaPath = "orarendHZ60V1Schema.json";
        String jsonPath   = "orarendHZ60V1.json";

        try (InputStream schemaIS = Files.newInputStream(Paths.get(schemaPath));
             InputStream jsonIS   = Files.newInputStream(Paths.get(jsonPath))) {

            JSONObject rawSchema = new JSONObject(new JSONTokener(schemaIS));
            Schema schema = SchemaLoader.load(rawSchema);

            JSONObject json = new JSONObject(new JSONTokener(jsonIS));

            schema.validate(json);

            System.out.println("Validation: A JSON megfelel a sémának.");

        } catch (ValidationException ve) {
            System.out.println("Validation: A JSON NEM felel meg a sémának!");
            System.out.println("Hiba: " + ve.getMessage());
            ve.getCausingExceptions().forEach(e ->
                System.out.println(" - " + e.getMessage())
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
