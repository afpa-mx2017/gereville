package com.ldub.gereville.console;


import com.ldub.gereville.model.Capitale;
import com.ldub.gereville.model.Pays;
import com.ldub.gereville.model.Ville;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class GerevilleV3 {

    
    static HashMap<String, ArrayList> listePays = new HashMap();
    static List<Pays> payss = new ArrayList<>();
    

    public static void main(String[] args) throws Exception {
        
        //initialisation de 3 pays
        payss.add(new Pays(1, "FRANCE"));
        payss.add(new Pays(2, "ESPAGNE"));
        payss.add(new Pays(3, "BELGIQUE"));
        
        ListeVille listeVille = new ListeVille();

        int choix;

        Ville v;
        boolean bMaj = false;

        // affichage du menu
        affMenu();
        choix = Lire.getInt();
        while (choix != 7) {
            while (choix != 1 && choix != 2 && choix != 3 && choix != 4
                    && choix != 5 && choix != 6 && choix != 7) {
                System.out.println("Veuillez saisir une option");
                affMenu();
                choix = Lire.getInt();
            }
            switch (choix) {
                case 1: // création de villes et de capitales
                    v = saisieVille();
                    // si on ajoute une nouvelle ville dans le dictionnaire, on l'ajoute aussi dans la liste des villes
                    if (ajouteVille(v)) {
                        listeVille.add(v);
                    }
                    break;

                case 2: // liste des villes et capitales des pays
                    listeVillesEtCapitales();
                    break;

                case 3: // Capitale et villes d'un pays
                    listePays(); // affichage de la liste des pays
                    System.out.println("Veuillez choisir un pays : ");
                    String pays = Lire.getString().toUpperCase();
                    while (pays.equals("")) {
                        System.out.println("Veuillez choisir un pays : ");
                        pays = Lire.getString();
                    }
                    consulterVilles(pays);
                    break;
                case 4: // Liste des pays
                    listePays();
                    break;

                case 5:
                    if (!listeVille.isEmpty()) {
                        Storage.sauvegarde(listeVille);
                        bMaj = true;
                        System.out.println("La liste est sauvegardée");
                    } else {
                        System.out.println("La liste de villes est vide");
                    }
                    break;
                case 6: // restauration de la liste des pays
                    try {
                        if (listeVille != null) {
                            listeVille.clear();
                        }
                        if (listePays != null) {
                            listePays.clear();
                        }
                        ListeVille lv = Storage.restauration();
                        if (lv!=null) listeVille = lv;
                        
                        // remplissage du dictionnaire
                        for (int i = 0; i < listeVille.size(); i++) {
                            ajouteVille((Ville) listeVille.get(i));
                        }
                        System.out.println("La liste est restaurée");
 
                    } catch (Exception e) {
                        System.out.println("Veuillez sauvegarder les données avant");
                    }
                    break;
                case 7:
                    System.out.println("Fin du programme");
                    System.exit(0);
                    break;

            }
            affMenu();
            choix = Lire.getInt();
        }
        System.out.println("Fin du programme");
    }

    public static void affMenu() {
        System.out.println("\nGereville - Saisie et consultation des villes et capitales\n");
        System.out.println("1- Création de villes et capitales");
        System.out.println("2- Liste des villes et capitales des pays");
        System.out.println("3- Capitale et villes d'un pays");
        System.out.println("4- Liste des pays");
        System.out.println("5- Sauvegarde de la liste des villes ");
        System.out.println("6- Restauration de la liste des villes");
        System.out.println("7- Fin");
        System.out.println("\nFaites un choix : ");
    }

    public static Ville saisieVille() {
        String nom, pres;
        int nbhab;
        
        Integer.parseInt("2");

        System.out.println("\nSaisie d'une ville");

        // saisie du nom de la ville
        System.out.println("Nom : ");
        nom = Lire.getString();
        while (nom.equals("")) {
            System.out.println("Veuillez entrer une chaine de caractères non vide");
            System.out.println("Nom : ");
            nom = Lire.getString().toUpperCase();
        }
        // saisie du pays
        Pays pays = null;
        do {
            System.out.println("Choisissez votre Pays : ");
            for(Pays p : payss){
                System.out.println(p.getId() + " - " + p.getNom());
            }
            int idPays = Lire.getInt();
            pays = getPays(idPays);
            if (pays==null){
                System.out.println("humm, pas trouvé de pays...");
            }
        }
        while(pays==null);

        // saisie du nombre d'habitants
        System.out.println("Nombre d'habitants : ");
        nbhab = Lire.getInt(); // champ vide accepté
        while (nbhab < -1) // si nbhab=-1 -> nombre inconnu
        {
            System.out.println("Veuillez saisir un entier positif ou nul");
            nbhab = Lire.getInt();
        }
        // saisie du pays
        System.out.println("Président ou roi (seulement pour une capitale): ");
        pres = Lire.getString().toUpperCase();
        if (pres.equals("")) {
            return new Ville(nom, pays, nbhab);
        } else {
            Capitale cap = new Capitale(nom, pays, nbhab);
            cap.setPresident(pres);
            return cap;
        }
    }

    // fonction ajouteVille dans le dictionnaire listePays
    public static boolean ajouteVille(Ville uneVille) {
        String pays = uneVille.getPays().getNom().toUpperCase();
        // on consulte le dictionnaire sur la clé pays
        ArrayList<Ville> lVilles = (ArrayList) listePays.get(pays);
        if (lVilles == null) // si pas de villes pour ce pays
        {
            lVilles = new ArrayList();
            listePays.put(pays, lVilles);
        } else // test si uneVille existe déja dans la liste
        {
            for (Ville ville : lVilles) {
                if (ville.getNom().toUpperCase().equals(uneVille.getNom().toUpperCase())) {
                    return false;
                }
            }
        }
        // uneVille n'est pas dans listeVilles donc on l'ajoute
        lVilles.add(uneVille);
        return true;
    }

    // fonction consulterVillesEtCapitales
    public static void listeVillesEtCapitales() {
        Set sPays = listePays.entrySet();
        Iterator itPays = sPays.iterator();

        while (itPays.hasNext()) {
            Map.Entry pays = (Map.Entry) itPays.next();
            System.out.println("\n" + pays.getKey());
            ArrayList<Ville> villesPays = (ArrayList) pays.getValue();
            for (Ville v1 : villesPays) {
                System.out.println(v1);
            }
        }
    }

    // fonction consulterPays()
    public static void listePays() {
        
        if (listePays.isEmpty()) {
            System.out.println("aucun pays a afficher");
        }else{
             Set sPays = listePays.entrySet();
            Iterator itPays = sPays.iterator();
            while (itPays.hasNext()) {
                Map.Entry pays = (Map.Entry) itPays.next();
                System.out.println("\n" + pays.getKey());
            }
        }
        
       
    }

    // fonction consulterVilles d'un pays
    public static void consulterVilles(String pays) {
        ArrayList<Ville> villesPays = (ArrayList) listePays.get(pays);
        if (villesPays == null) {
            System.out.println("Pays absent");
        } else {
            for (Ville v1 : villesPays) {
                System.out.println(v1);
            }
        }

    }
    
    /**
     * obtenir un pays par son identifiant
     * @param paysId id du pays
     * @return instance de Pays ou null si non trouvé dans la liste
     */
    private static Pays getPays(int paysId){
        
        for (Pays p: payss){
            if (p.getId()==paysId){
                return p;
            }
        }
        
        return null;
    }
}
