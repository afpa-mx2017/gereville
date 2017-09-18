/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldub.gereville.model;

/**
 *
 * @author lionel
 */
public class Capitale extends Ville{
    
    
    private String president;

    public Capitale(String nom, String pays, int nbHabitants, String president) {
        super(nom, pays, nbHabitants);
        this.president = president;
    }
    
    
    
    
    @Override
    public void affDesc(){
        super.affDesc();
        System.out.println(this.nom + " est une capitale de " + this.pays + " de catégorie " + this.categorie());
        
    }

    @Override
    public String toString() {
        return super.toString() + " Capitale{" + "president=" + president + '}';
    }
    
    
}
