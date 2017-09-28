/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldub.gereville.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lionel
 */
public class Pays implements Serializable{
    
    private int id;
    private String nom;
    private Capitale capitale;
    private List<Ville> villes = new ArrayList<>();
    
    public Pays(){
        
    }
    
    public Pays(String nom) {
        this.nom = nom;
    }
    
    public Pays(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    
    
    public void addVille(Ville v){
        this.villes.add(v);
    }

    public List<Ville> getVilles() {
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }

    public Capitale getCapitale() {
        return capitale;
    }

    public void setCapitale(Capitale capitale) {
        this.capitale = capitale;
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom.toUpperCase();
    }

    @Override
    public String toString() {
        return  nom;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pays other = (Pays) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
}
