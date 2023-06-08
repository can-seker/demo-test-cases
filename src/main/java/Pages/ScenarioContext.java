package Pages;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ScenarioContext {
    public void setId(Integer id)throws Exception{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File("data\\Property.json"));
            ((ObjectNode) jsonNode).put("id", id);
            String updatedJson = jsonNode.toString();
        try (FileWriter fileWriter = new FileWriter("data\\Property.json")) {
            fileWriter.write(updatedJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer getId() throws Exception{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(new File("data\\Property.json"));
        return rootNode.get("id").asInt();
    }
}
