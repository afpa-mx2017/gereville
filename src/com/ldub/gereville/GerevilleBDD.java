/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldub.gereville;

import com.ldub.gereville.model.Pays;
import com.ldub.gereville.model.dao.PaysDAO;
import java.util.List;

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
        
    }
    
}
