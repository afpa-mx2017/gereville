/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldub.gereville.model.dao;

import com.ldub.gereville.model.Pays;
import com.ldub.gereville.model.dao.PaysDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lionel
 */
public class GerevilleBDD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                //huhu testouille bdd
        
        List<Pays> payss = PaysDAO.findAll();
        for (Pays pays : payss) {
            System.out.println("pays:" + pays.getNom());
        }
        
        
        //test ajout
        Pays p = new Pays();
        p.setNom("BELGIQUE");
        
        try {
            PaysDAO.save(p);
            System.out.println(p.getId());
        } catch (Exception ex) {
            Logger.getLogger(GerevilleBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //test findOneBy
        Pays p2 = PaysDAO.findById(p.getId());
        System.out.println(p2.getNom());
        
        //test update
        p2.setNom("UK");
        try {
            PaysDAO.update(p2);
            System.out.println(p2.getNom());
        } catch (Exception ex) {
            Logger.getLogger(GerevilleBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            //suppression:
            PaysDAO.delete(p2);
        } catch (Exception ex) {
            Logger.getLogger(GerevilleBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
