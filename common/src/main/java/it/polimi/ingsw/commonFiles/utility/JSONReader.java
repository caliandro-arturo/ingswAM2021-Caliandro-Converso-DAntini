package it.polimi.ingsw.commonFiles.utility;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;

public class JSONReader {
    private static final Gson gson = new Gson();
    private static final Type mapOfStrings = new TypeToken<Map<String, String>>() {}.getType();

    private static InputStreamReader readFile(String fileName) {
        return new InputStreamReader(
                Objects.requireNonNull(JSONReader.class.getClassLoader().getResourceAsStream(fileName)));
    }

    public static Map<String, String> readMap(String fileName) throws IOException {
        Map<String, String> map;
        try (InputStreamReader file = readFile(fileName)) {
            map = gson.fromJson(file, mapOfStrings);
        }
        return map;
    }
}
