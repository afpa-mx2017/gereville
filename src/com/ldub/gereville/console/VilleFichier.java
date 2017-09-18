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
public class VilleFichier
{
  public String strFichier;
  private char cMode;	//  = "E" pour ouvrir et écrire le flux,
  //	= "L" pour ouvrir et lire
        private ObjectOutputStream oFW; // Flux d'écriture
        private ObjectInputStream iFW;	// Flux de lecture

        public  VilleFichier()
        {
                strFichier = "villes.txt"; // nom par defaut
        }

        public  VilleFichier(String strNomF)
        {
                 strFichier=strNomF;
        }

        public boolean Ouvrir (String o)
        {
                try
                {
                        cMode = (o.toUpperCase()).charAt(0);
                        if ( cMode == 'E')	// Ouverture du flux de sortie
                                oFW= new ObjectOutputStream(new FileOutputStream(strFichier));
                        else				// Ouverture du flux d'entrée
                                iFW= new ObjectInputStream(new FileInputStream(strFichier));
                        return true;
                }
                catch (IOException e)
                { // Absence du fichier sur disque
                        System.out.println("Fichier inexistant, faire la saisie et la sauvegarde");
                        return false;
                }
        }

        public void Ecrire (ListeVille o) throws Exception
        {
                if (o != null)
                        oFW.writeObject(o); // Ecriture du flux
        }
        public  Object Lire()
        {
          // Lecture du flux
            try
            {
                Object o =  (Object) iFW.readObject();
                return o;	// renvoi de l'objet ListeVille
             }
            catch (Exception e)
                { // Absence du fichier sur disque
                        System.out.println("Fichier inexistant, faire la saisie et la sauvegarde");
                        return null;
                }
        }
        public void Fermer (String f) throws Exception 	{
                cMode = (f.toUpperCase()).charAt(0);
                if ( cMode == 'L')
                        iFW.close();	// fermeture du flux d'entrée
                else
                        oFW.close();	// fermeture du flux de sortie
	}
}
