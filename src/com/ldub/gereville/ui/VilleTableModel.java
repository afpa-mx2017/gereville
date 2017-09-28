package com.ldub.gereville.ui;

import com.ldub.gereville.model.Ville;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

public class VilleTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	
	private List<Ville> villes = new ArrayList<>(0);
	
	private String[] columnsHeader = {"nom", "nbhabitant", "pays", "capitale" };
	  
	private ImageIcon checkedIcon = new ImageIcon(getClass().getResource("/img/checked.png"));


	@Override
	public int getRowCount() {
		return villes.size();
	}

	@Override
	public int getColumnCount() {
		return columnsHeader.length;
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnsHeader[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Ville v = villes.get(rowIndex);
		switch (columnIndex){
			case 0: return v.getNom();
			case 1: return v.getNbHabitants();  
			case 2: return v.getPays().getNom();
			case 3:
				//check if current ville is a capitale
				Ville capitale = v.getPays().getCapitale();
				if (capitale!=null && capitale.equals(v)){
						return checkedIcon;
				}
				return "";
				

		}
		return null;
	}
	
	/*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
	public Class<?> getColumnClass(int columnIndex) {
		if (villes.isEmpty()) {
	        return Object.class;
	    }
	    return getValueAt(0, columnIndex).getClass();
    }
	
	public Ville getVille(int rowIndex){
		return villes.get(rowIndex);
	}
	
	public void addVille(Ville v){
		villes.add(v);
		
		fireTableRowsInserted(villes.size()-1, villes.size()-1);
	}
	
	public void updateVille(Ville v){
		int i = villes.indexOf(v);
		if (i>-1){ //exist
			villes.set(i, v); //replace
			fireTableRowsUpdated(i, i);
		}
		
	}
	
	public void addAllVille(List<Ville> vs){
		villes.addAll(vs);
		fireTableDataChanged();
	}
	
	public void setVilles(List<Ville> villes){
		this.villes = villes;
		//reconstruct all
		fireTableDataChanged();
	}
	
	public void deleteVille(Ville v){
		this.villes.remove(v);
		fireTableDataChanged();
	}
	
	
	public void clear(){
		villes.clear();
		
		fireTableDataChanged();
	}

}
