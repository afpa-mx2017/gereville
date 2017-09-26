/*
 * exemple de classe de test unitaire
 */
package com.ldub.gereville.model.dao;

import com.ldub.gereville.model.Pays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lionel
 */
public class PaysDAOUnitTest {
    
    public PaysDAOUnitTest() {
    }
  
    
    @Before
    public void setUp() {
    }
    


    @Test
    public void crudTest() throws Exception {
    
        
        
        Pays p1 = new Pays("ARGENTINE");
        PaysDAO.save(p1);
        Logger.getLogger(PaysDAOUnitTest.class.getName()).log(Level.INFO, "pays créé:" + p1.getId());

        //test modif
        p1.setNom("BRESIL");
        PaysDAO.update(p1);
        
        Pays p = PaysDAO.findById(p1.getId());
        Assert.assertEquals(p1.getNom(), p.getNom());
        
        //test findall
        List<Pays> payss = PaysDAO.findAll();
        Assert.assertTrue((payss.size()>=1));
        
        //test delete
        PaysDAO.delete(p);
        Pays pdel = PaysDAO.findById(p.getId());
        Assert.assertNull(pdel);
        
    
    }
}
