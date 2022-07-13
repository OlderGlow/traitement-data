package fr.diginamic;

import fr.diginamic.bll.*;
import fr.diginamic.entite.*;

import java.io.*;
import java.util.*;

public class JSONtoDb {
    public static void main(String[] args) throws IOException, BLLException {
        List<Acteur> acteurs = ReadFileManager.readJsonFile();
        for (Acteur acteur : acteurs) {
            ActeurManager.getInstance().create(acteur);
        }
    }
}
