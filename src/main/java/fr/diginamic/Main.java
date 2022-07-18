package fr.diginamic;

import fr.diginamic.bll.*;
import fr.diginamic.entite.*;

import java.util.*;

/**
 * Classe principale pour lancer le programme
 * Cette classe permet de lancer un programme permettant d'effectuer des opérations de recherche sur une base de données.
 * Un menu est affiché à l'utilisateur pour lui permettre de choisir l'opération à accomplir.
 * @author Julien Picquet
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        ActeurManager acteurManager = ActeurManager.getInstance();
        FilmManager filmManager = FilmManager.getInstance();
        Scanner sc = new Scanner(System.in);
        int choix;
        do {
            menu();
            choix = sc.nextInt();
            switch (choix) {
                case 1:
                    sc.nextLine();
                    System.out.println("Quel acteur voulez-vous ?");
                    String nomActeur = sc.nextLine();
                    try {
                        List<Film> films = filmManager.selectMovieByActor(nomActeur);
                        for (Film film : films) {
                            System.out.println(film.getNom() + " (" + film.getAnneeSortie() + ")");
                        }
                    } catch (BLLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    sc.nextLine();
                    System.out.println("Quel film souhaitez-vous voir le casting ?");
                    String nomFilm = sc.nextLine();
                    try {
                        List<Acteur> acteurs = acteurManager.selectCastingByMovie(nomFilm);
                        for (Acteur acteur : acteurs) {
                            System.out.println(acteur.getIdentite());
                        }
                    } catch (BLLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    sc.nextLine();
                    System.out.println("Quelle est la première année ?");
                    String year1 = sc.nextLine();
                    System.out.println("Quelle est la deuxième année ?");
                    String year2 = sc.nextLine();
                    try {
                        List<Film> films = filmManager.selectMovieBetweenTwoYears(year1, year2);
                        for (Film film : films) {
                            System.out.println(film.getNom() + " (" + film.getAnneeSortie() + ")");
                        }
                    } catch (BLLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    sc.nextLine();
                    System.out.println("Nom du premier acteur : ");
                    String nomActeur1 = sc.nextLine();
                    System.out.println("Nom du deuxième acteur : ");
                    String nomActeur2 = sc.nextLine();
                    try {
                        List<Film> films = filmManager.selectMoviesCommonActors(nomActeur1, nomActeur2);
                        for (Film film : films) {
                            System.out.println(film.getNom() + " (" + film.getAnneeSortie() + ")");
                        }
                    } catch (BLLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        sc.nextLine();
                        System.out.println("Quelle est la première année ?");
                        String date1 = sc.nextLine();
                        System.out.println("Quelle est la deuxième année ?");
                        String date2 = sc.nextLine();
                        System.out.println("Quel est le nom de l'acteur ?");
                        String acteur = sc.nextLine();
                        List<Film> films = filmManager.selectMoviesBetweenDatesAndHavingActors(date1, date2, acteur);
                        for (Film film : films) {
                            System.out.println(film.getNom() + " (" + film.getAnneeSortie() + ")");
                        }
                    } catch (BLLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    sc.nextLine();
                    System.out.println("Quel est le titre du premier film ?");
                    String titre1 = sc.nextLine();
                    System.out.println("Quel est le titre du deuxième film ?");
                    String titre2 = sc.nextLine();
                    try {
                        List<Acteur> acteurs = acteurManager.selectActorsByTwoMovies(titre1, titre2);
                        for (Acteur acteur : acteurs) {
                            System.out.println(acteur.getIdentite());
                        }
                    } catch (BLLException e) {
                        System.out.println(e.getMessage());
                    }
                case 7:
                    break;
            }
        } while (choix != 99);
    }

    /**
     * Méthode permettant d'afficher le menu à l'utilisateur
     */
    public static void menu() {
        System.out.println("1 - Affichage de la filmographie d'un acteur");
        System.out.println("2 - Affichage du casting d’un film donné");
        System.out.println("3 - Affichage des films sortis entre 2 années données");
        System.out.println("4 - Affichage des films communs à 2 acteurs/actrices donnés");
        System.out.println("5 - Affichage des films sortis entre 2 années données et qui ont un acteur/actrice donné au casting");
        System.out.println("6 - Affichage des acteurs communs à deux films donnés");
        System.out.println("7 - Quitter");
        System.out.println("Votre choix :");
    }
}
