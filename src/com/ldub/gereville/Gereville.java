/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldub.gereville;

import com.ldub.gereville.model.Capitale;
import com.ldub.gereville.model.Pays;
import com.ldub.gereville.model.Ville;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lionel
 */
public class Gereville {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Pays p = new Pays(1, "FRANCE");
        
        
        /*
         * instanciation de 3 objets Ville de 3 manière différente.
         * 
         */
        Pays pFrance = new Pays(1, "FRANCE");
        //sans constructeur ou constructeur vide
        Ville v1 = new Ville();
        v1.setPays(pFrance);
        v1.setNom("Toulouse");
        v1.setNbHabitants(500000);
        
        Ville v2 = new Ville( "PARIS",pFrance,1000000);
        Ville v3 = new Ville("LYON", pFrance);
        v3.setNbHabitants(1000000);
        
        //ajout des villes dans une liste
        List<Ville> villes = new ArrayList<>();
        
        
        villes.add(v1);
        villes.add(v2);
        villes.add(v3);
        
        //parcours d'une liste de ville
        for (Ville v : villes) {
            System.out.println(v);
        }
        
        
        // obtenir le nom du pays via une instance de Ville
        //String nomPays = v3.getPays().getNom();
        //System.out.println("nomPays:" + nomPays);
        

        
        Ville v4 = new Capitale("Paris",pFrance,12550000);
        System.out.println(v4);
        
        
        //exemple avec HashMap
        Map<String, List<Ville>> map = new HashMap<>();
        
        List<Ville> villes2 = new ArrayList<>();
        map.put("FRANCE", villes2);
        
        
        villes2.add(new Ville("PARIS", pFrance, 12122121));
        villes2.add(new Ville("RENNES", pFrance, 800000));
        
        for (Map.Entry<String, List<Ville>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<Ville> vs = entry.getValue();
            for (Ville ville : vs) {
                System.out.println(ville);
            }
            
        }
    }
    
}
