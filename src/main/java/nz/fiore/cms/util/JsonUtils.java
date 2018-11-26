package nz.fiore.cms.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonUtils {

    public static Map<String, Object> fromJson(String body) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        // convert JSON string to Map
        map = mapper.readValue(body, new TypeReference<Map<String, String>>() {
        });
        return map;
    }
}
