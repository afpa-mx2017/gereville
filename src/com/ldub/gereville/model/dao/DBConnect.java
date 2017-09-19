/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldub.gereville.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lionel
 */
public class DBConnect {
    
    
    private static Connection conn =null;

	 final static String URL = "jdbc:mysql://localhost/gereville?noAccessToProcedureBodies=true";
        /**
         * 
         * @return RunTimeException() if any pb
         */
        public static Connection  getConnection () 
        {
            if (conn==null) {

                     try
                     {
                         Class.forName("com.mysql.jdbc.Driver").newInstance();

                       // 
                         conn=  DriverManager.getConnection(URL,"root","admin");
                     }
                       catch(SQLException sqlE)
                       {
                               //TODO Logging
                           System.out.println("Sql Erreur " + sqlE.getMessage());
                           throw new RuntimeException();
                       }
                       catch(Exception e)
                       {
                          e.printStackTrace();
                          throw new RuntimeException(e);
                       }
            }

            return conn;

       }
}
