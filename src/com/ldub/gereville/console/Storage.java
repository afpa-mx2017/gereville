/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldub.gereville.console;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author pf
 */
public class Storage {

    public static final String strFichier = "villes.txt";

    public static void sauvegarde(ListeVille o) throws Exception {
        if (o != null) {
            ObjectOutputStream oFW = new ObjectOutputStream(new FileOutputStream(strFichier));
            oFW.writeObject(o); // Ecriture du flu
            oFW.close();
        }

    }

    public static ListeVille restauration() {
        // Lecture du flux
        try {
            ObjectInputStream iFW = new ObjectInputStream(new FileInputStream(strFichier));
            Object o = (Object) iFW.readObject();
            iFW.close();
            return (ListeVille) o;	// renvoi de l'objet ListeVille
        } catch (Exception e) { // Absence du fichier sur disque
            System.out.println("Fichier inexistant, faire la saisie et la sauvegarde");
            return null;
        }
    }

}
