/*
 * classe de test a la mano, peut difficilement être automatisable
 */
package com.ldub.gereville.model.dao;


import com.ldub.gereville.model.Pays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lionel
 */
public class PaysDAOTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         //test ajout
        Pays p = new Pays();
        p.setNom("BELGIQUE");
        
        try {
            PaysDAO.save(p);
            System.out.println("pays créé:" + p.getId());
        } catch (Exception ex) {
            Logger.getLogger(GerevilleBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //test update
        Pays p2 = PaysDAO.findById(p.getId());
        System.out.println(p2.getNom());
        

        p2.setNom("UK");
        try {
            PaysDAO.update(p2);
            System.out.println(p2.getNom());
        } catch (Exception ex) {
            Logger.getLogger(GerevilleBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //test suppression
        try {
            PaysDAO.delete(p2);
            
        } catch (Exception ex) {
            Logger.getLogger(GerevilleBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        List<Pays> payss = PaysDAO.findAll();
        for (Pays pays : payss) {
            System.out.println("pays:" + pays.getNom());
        }
        
        
       
        

    }
    
}
