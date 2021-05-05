package ml.pkom.jsonconfig.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

public class YamlToJson {

    public static String convert(String yaml) throws JsonProcessingException 
    {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> obj = new Yaml().load(yaml);
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        return json;
    }
}