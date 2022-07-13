package fr.diginamic.bll;

import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;
import fr.diginamic.entite.*;

import java.io.*;
import java.util.*;

public class ReadFileManager {
    private final static String RESOURCES_PATH = "src/main/resources/";
    private final static String JSON_PATH = "films.json";

    public static List<Acteur> readJsonFile() throws IOException {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        om.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        om.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        om.registerModule(new JavaTimeModule());
        return om.readValue(new File(RESOURCES_PATH + JSON_PATH), new TypeReference<>() {
        });
    }
}
