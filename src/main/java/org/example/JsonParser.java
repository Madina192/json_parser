package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.util.List;

public class JsonParser {
    public static void main(String[] args) throws JsonProcessingException {
        String json = "{\"laptop\": {\n" +
                "  \"laptop1\": {\"name\": \"Lenovo\",\"ram\": \"4 GB\", \"ssd\": \"256 GB\"},\n" +
                "  \"laptop2\": {\"name\": \"HP\",\"ram\": \"8 GB\", \"ssd\": \"512 GB\"}\n" +
                "}\n" + "}";

        JSONObject jsonObject = new JSONObject(json);
        String name = jsonObject.getJSONObject("laptop").getJSONObject("laptop1").getString("name");

        System.out.println(name);

        ObjectMapper mapper = new ObjectMapper();
        Laptop laptop1 = mapper.readValue(jsonObject.getJSONObject("laptop").getJSONObject("laptop1").toString(), Laptop.class);

        String json2 = "{\"laptop\": {\n" +
                "  \"laptop1\": [{\"name\": \"Lenovo\",\"ram\": \"4 GB\", \"ssd\": \"256 GB\"}, {\"name\": \"HP\",\"ram\": \"8 GB\", \"ssd\": \"512 GB\"}]\n" +
                "}\n" +
                "}";
        JSONObject jsonObject2 = new JSONObject(json2);
        List<Laptop> laptops = mapper.readValue(jsonObject2.getJSONObject("laptop").getJSONArray("laptop1").toString(), mapper.getTypeFactory().constructCollectionType(List.class, Laptop.class));
        System.out.println(laptops);
    }
}
