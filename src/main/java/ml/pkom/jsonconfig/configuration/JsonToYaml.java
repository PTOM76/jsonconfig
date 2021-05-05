package ml.pkom.jsonconfig.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

public class JsonToYaml {

    public static String convert(String json) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> obj = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
        DumperOptions opts = new DumperOptions();
        opts.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        String yaml = new Yaml(opts).dump(obj);
        return yaml;
    }
}