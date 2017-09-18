/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldub.gereville.model;

import java.io.Serializable;

/**
 *
 * @author lionel
 */
public class Ville implements Serializable{
    
    
    protected String nom;
    protected String pays;
    protected int nbHabitants;
   

    
    public Ville(){
    }

    public Ville(String nom, String pays, int nbHabitants) {
        this.nom = nom;
        this.pays = pays;
        this.nbHabitants = nbHabitants;
    }

    public Ville(String nom, String pays) {
        this.nom = nom;
        this.pays = pays;
    }
    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        
        this.pays = pays;
    }

    public int getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(int nbHabitants) {
        this.nbHabitants = nbHabitants;
    }
    
    
    
    
    public void affDesc(){
        System.out.println(this.nom + " est une ville de " + this.pays + " de catégorie " + this.categorie());
        
    }
    
    protected char categorie(){
        char categorie = '?';
        if (nbHabitants <= 0){
            categorie = '?';
        }  else if (nbHabitants> 0 && nbHabitants < 50000){
            categorie = 'A';
        }else if(nbHabitants>= 50000 && nbHabitants < 200000){
            categorie = 'B';
        }else if(nbHabitants>= 200000 && nbHabitants < 1000000){
            categorie = 'C';
        }else{
            categorie = 'D';
        }
        
        return categorie;
    }

    @Override
    public String toString() {
        return "Ville{" + "nom=" + nom + ", pays=" + pays + ", nbHabitants=" + nbHabitants + '}';
    }
    
    
    
}
