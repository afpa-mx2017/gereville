
package com.ldub.gereville.console;

/*********************************************************************
  Nom du programme  :  Lire.java
  Auteur            :  Lécu Regis
  Mise a jour       :  février 2001, maj nov 2003 jcc
  Fonction          :  bibliothèque de fonctions de lecture

  Tous les noms de fonctions devraient commencer par une minuscule.En
  dehors de cette boîte à outils de fonctions, cette convention sera
  scrupuleusement respectée.
**********************************************************************/
import java.io.*;

public class Lire
{
        public static int getInt()
        {
                try
                {
                        String strLine = getiString();
                        Integer intn = new Integer(strLine);
                        return intn.intValue();
                }
                catch( Exception e)
                {
                        System.err.println("Erreur Entier " + e);
                        return -1;
                }
        }
        public static long getLong()
        {
                try
                {
                  String strLine = getiString();
                  Integer intn = new Integer(strLine);
                  return intn.longValue();
                }
                catch( Exception e)
                {
                        System.out.println("Erreur Reel Long " + e);
                        return -1;
                }
        }
        public static float getFloat()
        {
                try
                {
                        String strLine = getiString();
                        Float fN  = new  Float(strLine);
                        return fN.floatValue();
                }
                catch( Exception e)
                {
                  System.err.println("Erreur Reel  " + e);
                  return -1;
                }
        }
        public static double getDouble()
        {
                try
                {
                        String strLine = getiString();
                        Double dN = new Double(strLine);
                        return dN.doubleValue();
                }
                catch( Exception e)
                {
                        System.err.println("Erreur Reel double " + e);
                        return -1;
                }
        }
        public static char getChar()
        {
                try
                {
                        String strLine = getiString();
                        Character C = new Character(strLine.charAt(0));
                        return C.charValue();
                }
                catch( Exception e)
                {
                        System.err.println("Erreur charactere " + e);
                        return ' ';
                }
        }
        public static String getString()
        {
          String tmp = "";
          char C='\0';
          try {
                   while ((C=(char) System.in.read()) !='\n')
                   {
                    if (C != '\r')  tmp = tmp+C;
                   }
             }
           catch (IOException e)
                  {
                    System.out.println("Erreur de frappe" + e);
                    System.exit(0);
                  }
           return tmp;

        }
        private static String getiString()
        throws Exception
        {
                byte[] bArray = new byte[80];
                System.in.read(bArray);
                String strLine = new String(bArray);
                return strLine.substring(0,strLine.indexOf("\n"));
        }
}
