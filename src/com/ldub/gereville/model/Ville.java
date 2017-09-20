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
    
    protected int id;
    protected String nom;
    protected Pays pays;
    protected int nbHabitants;
   

    
    public Ville(){
    }

    public Ville(String nom, Pays pays, int nbHabitants) {
        this.nom = nom;
        this.pays = pays;
        this.nbHabitants = nbHabitants;
    }

    public Ville(String nom, Pays pays) {
        this.nom = nom;
        this.pays = pays;
    }
    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        
        this.pays = pays;
    }

    public int getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(int nbHabitants) {
        this.nbHabitants = nbHabitants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
