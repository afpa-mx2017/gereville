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

    public Capitale(String nom, Pays pays, int nbHabitants) {
        super( nom, pays, nbHabitants);
        //this.president = president;
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }
    
    

    @Override
    public String toString() {
        return super.toString() + " Capitale{" + "president=" + president + '}';
    }
    
    
}
