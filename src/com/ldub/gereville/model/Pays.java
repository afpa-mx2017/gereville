/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldub.gereville.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lionel
 */
public class Pays {
    
    private int id;
    private String nom;
    private List<Ville> villes = new ArrayList<>();
    

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
    
    
    
    
}
