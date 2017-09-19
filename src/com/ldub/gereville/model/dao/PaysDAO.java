/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldub.gereville.model.dao;

import com.ldub.gereville.model.Pays;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    
    
     public static void update(Pays p) throws Exception {
	Connection c = DBConnect.getConnection();
        PreparedStatement stm;
        try {

            stm = c.prepareStatement("UPDATE pays SET nom = ? WHERE id = ?");
            stm.setString(1, p.getNom());
            stm.setInt(2, p.getId());

            stm.executeUpdate();

        } catch (SQLException e) {
                       //pb if here
            throw new Exception("pb lors de la mise a jour de pays:" + e.getMessage());
        }
     }

    public static void save(Pays p) throws Exception{

        Connection c = DBConnect.getConnection();
        PreparedStatement stm;
        
        stm = c.prepareStatement("INSERT INTO pays (nom) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, p.getNom());

        stm.execute();
        ResultSet rs = stm.getGeneratedKeys();

        if (rs.next()) {
            p.setId(rs.getInt(1));
        }
        stm.close();


    }

    /**
     * retourne la liste des pays
     *
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

    public static Pays findById(int numPays) {
        Pays p = null;
        Connection c = DBConnect.getConnection();
        Statement stm;
        try {
            stm = c.createStatement();

            String sql = "select * from pays WHERE pays.id=" + numPays;
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                p = new Pays(id, nom);

            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return p;
    }

}
