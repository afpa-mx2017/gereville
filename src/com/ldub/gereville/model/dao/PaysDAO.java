/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldub.gereville.model.dao;

import com.ldub.gereville.model.Pays;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lionel
 */
public class PaysDAO {

    /**
     * retourne la liste des pays
     * @return 
     */
    public static List<Pays> findAll() {

        Connection c = DBConnect.getConnection();

        List<Pays> ps = new ArrayList<>();
        // test avec select
        Statement stm;
        try {
            stm = c.createStatement();

            String sql = "select * from pays ";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                
                Pays p = new Pays(id, nom);

                ps.add(p);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return ps;
    }

}
