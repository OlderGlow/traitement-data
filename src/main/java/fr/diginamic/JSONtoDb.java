package fr.diginamic;

import fr.diginamic.bll.*;
import fr.diginamic.entite.*;

import java.io.*;
import java.util.*;

public class JSONtoDb {
    public static void main(String[] args) {
        ActeurManager acteurManager = ActeurManager.getInstance();
        FilmManager filmManager = FilmManager.getInstance();
        RoleManager roleManager = RoleManager.getInstance();
        RealisateurManager realisateurManager = RealisateurManager.getInstance();
        try {
            List<Acteur> acteurs = ReadFileManager.readJsonFile();
            for(Acteur acteurJson : acteurs) {
                Acteur acteur = acteurManager.create(acteurJson);
                for(Role roleJson : acteurJson.getRoles()){
                    Film filmJson = roleJson.getFilm();
                    Set<Acteur> castingPrincipals = filmJson.getCastingPrincipal();
                    Set<Acteur> acteurFilms = filmJson.getActeurs();
                    Set<Realisateur> realisateurs = filmJson.getRealisateurs();
                    filmJson.setCastingPrincipal(new HashSet<>());
                    filmJson.setActeurs(new HashSet<>());
                    filmJson.setRealisateurs(new HashSet<>());
                    Film film = filmManager.create(filmJson);
                    // traitement de la liaison entre acteur et film => castingPrincipals
                    for(Acteur acteurCastingJson : castingPrincipals){
                        Acteur acteurCasting = acteurManager.create(acteurCastingJson);
                        film.addCastingPrincipal(acteurCasting);
                        acteurCasting.addCastingPrincipal(film);
                    }
                    // traitement de la liaison entre acteur et film => acteurs
                    for(Acteur acteurFilmJson : acteurFilms){
                        Acteur acteurFilm = acteurManager.create(acteurFilmJson);
                        film.addActeurFilm(acteurFilm);
                        acteurFilm.addFilm(film);
                    }
                    // traitement de la liaison entre film et realisateur
                    for(Realisateur realisateurJson : realisateurs){
                        Realisateur realisateurFilm = realisateurManager.create(realisateurJson);
                        film.addRealisateur(realisateurFilm);
                        realisateurFilm.addFilm(film);
                    }
                    roleManager.create(new Role(roleJson.getCharacterName(), film, acteur));
                }
        }
        } catch (IOException | BLLException e) {
            e.printStackTrace();
        }
    }
}
