/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldub.gereville;

import com.ldub.gereville.model.Capitale;
import com.ldub.gereville.model.Ville;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author lionel
 */
public class GerevilleV2 {
    
    
    static List<Ville> villes = new ArrayList<>();
    static HashMap<String, List<Ville>> all = new HashMap<>();
    
    static Scanner scan = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        for (Ville v : villes) {
            System.out.println(v);
        }
        
        List list = new ArrayList();
        
        
        for (int i = 0; i < villes.size(); i++) {
            Ville v = villes.get(i);
            
        }
        
       while(true){
           afficheMenuPrincipal();
       }
    
       
       
       
        
    }
    
    private static void afficheMenuPrincipal(){
        
        System.out.println("1 - Création de villes et capitales");
        System.out.println("2 – Liste des villes et capitales des pays");
        System.out.println("3 – Capitale et Villes d’un Pays");
        System.out.println("4 - Liste des pays");
        System.out.println("5 - Fin");

        
        String tmp = scan.nextLine();
        int choix = Integer.parseInt(tmp);
        
        switch(choix){
            case 1:
                saisie();
                break;
            case 2:
                afficheVilles();
                break;
            case 5: 
                System.out.println("bye bye");
                System.exit(0);
            default:
                break;
        }
    }

    private static void saisie() {
        
        Ville v;
        
        System.out.println("Nom:");
        String nom = scan.nextLine();
        
        System.out.println("nbhab:");
        String tmp = scan.nextLine();
        int nbhab = Integer.parseInt(tmp);
        
        System.out.println("pays:");
        String pays = scan.nextLine();

        
        System.out.println("est une capitale ? (o/n)");
        String choix = scan.nextLine();
        if (choix.equals("o")){
            System.out.println("nom du president:");
            String pres = scan.nextLine();
            
            v = new Capitale(nom, pays, nbhab, pres);

        }else{
            v = new Ville(nom, pays, nbhab);
            
        }
        villes.add(v);
        
        

        
    }

    private static void afficheVilles() {
        for (Ville ville : villes) {
            System.out.println(ville);
        }
    }
    
}
