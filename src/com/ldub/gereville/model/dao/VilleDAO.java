package com.ldub.gereville.model.dao;

import com.ldub.gereville.model.Pays;
import com.ldub.gereville.model.Ville;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class VilleDAO {
///////////////	 
	 
	
	
	 public static List<Ville> getVilles(int numPays){
		 
		 Pays p = PaysDAO.findById(numPays);
		 
		 
		 String sql = "SELECT * FROM ville WHERE pays_id =" + numPays;
		 
		 Connection c = DBConnect.getConnection();
		 
		 List<Ville> villes = new ArrayList<>();
		   Statement stm;
			try {
				stm = c.createStatement();
				
		        ResultSet rs = stm.executeQuery(sql);
		      
		        
		        while (rs.next()){
		        	int nbhabitant = rs.getInt("nbhabitant");
		        	String nomVille = rs.getString("nom");
		        	int numVille = rs.getInt("id");
		        	
		        	Ville v = new Ville();
		        	v.setNom(nomVille);
		        	v.setNbHabitants(nbhabitant);
		        	v.setId(numVille);
		        	v.setPays(p);
		        	
		        	
		        	villes.add(v);
		        }
		        rs.close();
				
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			
			return villes;
	 }
	 
	
	 
	 public static void deleteVille(Ville v) throws Exception{
		 Connection c = DBConnect.getConnection();
		 PreparedStatement stm;
		 try {

				
				stm = c.prepareStatement("DELETE FROM ville WHERE id = ?");
				stm.setInt(1, v.getId());
				
				stm.executeUpdate();
				
				//
				Ville cap = v.getPays().getCapitale();
				if (cap.equals(v)){ //need to update pays to remove capitale
					stm = c.prepareStatement("UPDATE pays SET capitale = NULL WHERE id = ?");
					stm.setInt(1, v.getPays().getId());
					stm.executeUpdate();
				}
				
		 } catch (SQLException e) {
				//pb if here
				 throw new Exception("error while deleting  data in table ville" + e.getMessage());
		}	
	 }
	 
	 
	 public static void updateVille(Ville v) throws Exception {
		Connection c = DBConnect.getConnection();
		 PreparedStatement stm;
		 try {
				
				stm = c.prepareStatement("UPDATE ville SET nom = ? , nbhabitant = ? WHERE id = ?");
				stm.setString(1, v.getNom());
				stm.setInt(2, v.getNbHabitants());
				stm.setInt(3, v.getId());
				
				stm.executeUpdate();
                                
                                //
				Ville cap = v.getPays().getCapitale();
				if (cap.equals(v)){ //need to update pays to remove capitale
					stm = c.prepareStatement("UPDATE pays SET capitale = NULL WHERE id = ?");
					stm.setInt(1, v.getPays().getId());
					stm.executeUpdate();
				}
				
		 } catch (SQLException e) {
				//pb if here
				 throw new Exception("error while updating  data in table ville" + e.getMessage());
		}	
	 }
	 
	 public static void createVille(Ville v) throws Exception{
		 
		 Connection c = DBConnect.getConnection();
		 PreparedStatement stm;
		 
		try {
			
			stm = c.prepareStatement("INSERT INTO ville (nom, nbhabitant, pays_id) VALUES (?,?,?)",Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, v.getNom());
			stm.setInt(2, v.getNbHabitants());
			stm.setInt(3, v.getPays().getId());
			
			stm.executeUpdate();
			
			
			
			// get autoincrement
			 ResultSet rs = stm.getGeneratedKeys(); 
			 
			 if (rs.next()) {
				v.setId(rs.getInt(1));
				
				Ville cap = v.getPays().getCapitale();
				if (cap!=null && cap.equals(v)){
					stm = c.prepareStatement("UPDATE pays SET capitale = ? WHERE id= ?");
					stm.setInt(1, v.getId());
					stm.setInt(2, v.getPays().getId());
					
					stm.executeUpdate();
				}
				
			 }else{
				 //pb if here
				 throw new Exception("error while insert new data in table ville");
			 }
			 
			
			stm.close();
			
		} catch (SQLException e) {
			//pb if here
			 throw new Exception("error while insert new data in table ville" + e.getMessage());
		}	
		 
		 
		 
	 }
	 

}
