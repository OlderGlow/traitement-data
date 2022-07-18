package fr.diginamic.bll;

import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;
import fr.diginamic.entite.*;

import java.io.*;
import java.util.*;

/**
 * @author Julien Picquet
 * @version 1.0
 * @see ReadFileManager
 * @see File
 * @see ObjectMapper
 * Classe qui permet de lire un fichier JSON et de récupérer les données dans un objet Java
 * Cet objet Java est ensuite utilisé pour créer un objet de type List<Personne>
 */
public class ReadFileManager {

    private final static String RESOURCES_PATH = "src/main/resources/";
    private final static String JSON_PATH = "films.json";

    /**
     * Méthode qui permet de lire un fichier JSON et de récupérer les données dans un objet Java.
     * Cet objet Java est ensuite utilisé pour créer un objet de type List de personnes
     * @return List<Personne>
     */
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
