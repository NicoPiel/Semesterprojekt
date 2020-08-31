import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.team38.semesterprojekt.garage.Garage;
import de.team38.semesterprojekt.network.JsonHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestJsonHandler {
    String jsonString;
    JsonObject json;
    JsonObject json1;
    JsonObject response;
    JsonObject response1;

    @Test
    @DisplayName("Tests (Leave)(No car found with this id) ")
    public void testJsonHandlerLeaveUnknownCar() {
        jsonString = "{\"action\":\"leave\",\"id\":66}";
        json = (JsonObject) JsonParser.parseString(jsonString);
        response = JsonHandler.handlePost(json);
        assertEquals("\"error\"", response.get("result").toString());
        assertEquals("\"No car found with this id\"",  response.get("message").toString());
    }
    @Test
    @DisplayName("Tests (Leave) on success with valid input data")
    public void testJsonHandlerKnownCarCanLeave() {
        jsonString = "{\"action\":\"enter\",\"id\":66,\"category\":\"DEFAULT\"}";

        json = (JsonObject) JsonParser.parseString(jsonString);
        response = JsonHandler.handlePost(json);
        assertEquals("\"success\"", response.get("result").toString());


        jsonString = "{\"action\":\"leave\",\"id\":66}";
        json1 = (JsonObject) JsonParser.parseString(jsonString);
        response1 = JsonHandler.handlePost(json1);

        assertEquals("\"success\"", response1.get("result").toString());
        assertEquals("\"\"", response1.get("message").toString());

    }
    @Test
    @DisplayName("Tests (Enter)(Category does not exist)")
    public void testJsonHandlerCategoryEnter(){
        jsonString = "{\"action\":\"enter\",\"id\":66,\"category\":\"NOTACATEGORY\"}";
        json = (JsonObject) JsonParser.parseString(jsonString);
        response = JsonHandler.handlePost(json);

        assertEquals("\"error\"", response.get("result").toString());
        assertEquals("\"Category does not exist\"",  response.get("message").toString());
    }

    @Test
    @DisplayName("Tests (Enter)(Id already exists)")
    public void testJsonHandlerIDalreadyInUse(){
        jsonString = "{\"action\":\"enter\",\"id\":66,\"category\":\"DEFAULT\"}";
        json = (JsonObject) JsonParser.parseString(jsonString);

        response = JsonHandler.handlePost(json);
        response1 = JsonHandler.handlePost(json);

        assertEquals("\"success\"", response.get("result").toString());

        assertEquals("\"error\"", response1.get("result").toString());
        assertEquals("\"Id already exists\"",  response1.get("message").toString());
    }

    @Test
    @DisplayName("Tests (Enter) on success with valid data")
    public void testJsonHandlerEnter(){
        jsonString = "{\"action\":\"enter\",\"id\":6,\"category\":\"DEFAULT\"}";
        json = (JsonObject) JsonParser.parseString(jsonString);
        response = JsonHandler.handlePost(json);
        assertEquals("\"success\"", response.get("result").toString());

    }

    @Test
    @DisplayName("Tests (Action)(Unknown action)")
    public void testJsonHandlerAction() {
        jsonString = "{\"action\":\"NOTANACTION\",\"id\":66,\"category\":\"DEFAULT\"}";
        json = (JsonObject) JsonParser.parseString(jsonString);
        response = JsonHandler.handlePost(json);
        assertEquals("\"error\"", response.get("result").toString());
        assertEquals("\"Unknown action\"", response.get("message").toString());
    }

    @Test
    @DisplayName("Tests (data) (Unknown data request)")
    public void testJSONHandlerMeta(){
        jsonString = "{\"data\":\"NODATA\"}";
        json = (JsonObject) JsonParser.parseString(jsonString);
        response = JsonHandler.handlePost(json);
        assertEquals("\"error\"", response.get("result").toString());
        assertEquals("\"Unknown data request\"", response.get("message").toString());
    }

    @Test
    @DisplayName("Tests data request for cars")
    public void testJSONHandlerDataCars(){
        jsonString = "{\"data\":\"cars\"}";
        json = (JsonObject) JsonParser.parseString(jsonString);
        response = JsonHandler.handlePost(json);
        assertEquals("\"success\"", response.get("result").toString());
    }
    @Test
    @DisplayName("Tests data request for meta")
    public void testJSONHandlerDataMeta(){
        jsonString = "{\"data\":\"meta\"}";
        json = (JsonObject) JsonParser.parseString(jsonString);
        response = JsonHandler.handlePost(json);
        assertEquals("\"success\"", response.get("result").toString());
    }
    @Test
    @DisplayName("Testing faulty JSON Input")
    public void testFaultyJSONInput(){
        jsonString ="{\"NOTAJSONSTING\" : \"FAULTY DATA\"}";
        json = (JsonObject) JsonParser.parseString(jsonString);
        response = JsonHandler.handlePost(json);
        assertEquals("\"error\"", response.get("result").toString());
        assertEquals("\"Unknown JSON structure\"", response.get("message").toString());
    }
    @Test
    @DisplayName("Testing simulation_start")
    public void testSimulationStart(){
        jsonString ="{\"action\" : \"simulation_start\"}";
        json = (JsonObject) JsonParser.parseString(jsonString);
        response = JsonHandler.handlePost(json);
        assertEquals("\"success\"", response.get("result").toString());
    }
    @Test
    @DisplayName("Testing simulation_stop")
    public void testSimulationStop(){
        jsonString ="{\"action\" : \"simulation_stop\"}";
        json = (JsonObject) JsonParser.parseString(jsonString);
        response = JsonHandler.handlePost(json);
        assertEquals("\"success\"", response.get("result").toString());
    }
    
    @Test
    @DisplayName("Naive Benchmark of Garage#getCarByID()")
    public void naiveGetCarByIDBenchmark() {
        double totalAvg = 0;
        
        for (int a = 1; a <= 10; a++) {
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    Garage.getInstance().findById(j);
                }
            }
    
            long start;
            long end;
    
            double avg = 0;
    
            for (int i = 0; i < 1000; i++) {
                start = System.nanoTime();
                for (int j = 0; j < 100; j++) {
                    Garage.getInstance().findById(j);
                }
                end = System.nanoTime();
                double result = ((double) (end - start)) / 10000;
                avg += result;
            }
    
            avg /= 1000;
            totalAvg += avg;
    
            System.out.println("Average " + a + ": " + avg + "ms");
        }
        
        totalAvg /= 10;
        
        System.out.println("Total Average: " + totalAvg + "ms");
    }
}
