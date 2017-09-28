package com.ldub.gereville.ui;


import com.ldub.gereville.model.Pays;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;



/**
 * Main window
 * @author lionel
 *
 */
public class UIGereville extends JFrame {
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
	
  private JPanel contentPane;
  private JLabel lblPays = new JLabel();
  private JComboBox<Pays> cbPays = new JComboBox<>();
  private JButton btnNewPays = new JButton("+");
  private JButton btnDelPays = new JButton("-");
  private JButton btnNewVille = new JButton();
  private JButton btnQuit = new JButton();
  
  private JTable uiListVilles;


 
  private JScrollPane jScrollPane1 = new JScrollPane();
  private GridBagLayout gridBagLayout1 = new GridBagLayout();
  
  private AppGereville controller;
  
  final Color alternateColor=new Color(242,242,242);
 
 

  

  public UIGereville(AppGereville controller,TableModel ville_model) {
	  this.controller =  controller;
	  
	  uiListVilles = new JTable(ville_model) {

		private static final long serialVersionUID = 1L;

			//define custom row renderer
		    public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
		        // get the current row
		        Component comp = super.prepareRenderer(renderer, row, col);
		        
		        // now get the current font used for this cell
		         Font font = comp.getFont();
		         
		         
		        
		        // even index, not selected
		        if (row % 2 == 0) {
		            comp.setBackground(alternateColor);
		        } else {
		            comp.setBackground(Color.white);
		        }
		        if (row == uiListVilles.getSelectedRow()){
		        	 comp.setFont(font.deriveFont(Font.BOLD));
		        	 comp.setBackground(Color.GRAY);
		         }
		        return comp;
		    }
		};
	  
	  uiListVilles.setModel(ville_model);
	  
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);

    jbInit();

  }
  

  
  
  //Initialiser le composant
  private void jbInit()  {
	  
	this.setSize(new Dimension(493, 312));
	this.setTitle("Consultation des villes de pays");

	contentPane = (JPanel) this.getContentPane();
	contentPane.setLayout(gridBagLayout1);
	
    lblPays.setFont(new java.awt.Font("SansSerif", 1, 16));
    lblPays.setText("Pays");
    
    JPanel pPanel = new JPanel();
    pPanel.add(lblPays);
    pPanel.add(cbPays);
    
    
    btnNewVille.setText("Nouvelle Ville");
    btnNewVille.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
    		controller.onNewVilleEvent();
      }
    });
    
    btnQuit.setToolTipText("");
    btnQuit.setText("Quitter");
    btnQuit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	  System.exit(0);
      }
    });
    
	
	
    uiListVilles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //seulement une selection est possible
    
    //enable sorting
    uiListVilles.setAutoCreateRowSorter(true);
   
	//addMouseListener(this);
    uiListVilles.addMouseListener(new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getClickCount() ==2){
				int row = uiListVilles.convertRowIndexToModel(uiListVilles.getSelectedRow());
				VilleTableModel model = (VilleTableModel) uiListVilles.getModel();
				
				controller.onSelectedVilleEvent(model.getVille(row));
			}
			
		}
	});
    uiListVilles.addKeyListener(new KeyAdapter() {
		
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode()==KeyEvent.VK_DELETE){
				int reponse = JOptionPane.showConfirmDialog(null, "sure ?");
				if (reponse==JOptionPane.YES_OPTION){
					int row = uiListVilles.convertRowIndexToModel(uiListVilles.getSelectedRow());
					VilleTableModel model = (VilleTableModel) uiListVilles.getModel();
					controller.deleteVille(model.getVille(row));
				}
			}
		}

		
	});
   
    
    cbPays.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	 if (cbPays.getSelectedIndex()>-1){
       		    controller.onSelectedPaysEvent((Pays)cbPays.getSelectedItem());
       	  }
			
		}
	});
    
    contentPane.add(pPanel);
    pPanel.add(btnNewPays);
    
    btnNewPays.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
    		  controller.onNewPaysEvent();
      }
    });
    
    pPanel.add(btnDelPays);
    
    btnNewPays.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
      		  controller.onDelPaysEvent();
        }
      }); 
    
    
    contentPane.add(btnQuit,  new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(24, 31, 28, 52), 11, 5));
    contentPane.add(btnNewVille,  new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(24, 53, 28, 0), 0, 5));
     contentPane.add(jScrollPane1,  new GridBagConstraints(0, 1, 3, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(15, 39, 0, 41), 409, 153));
    jScrollPane1.setViewportView(uiListVilles);

   

  }

  // catch window close event
    @Override
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }

  /**
   * display Pays list
   * @param payss Collection of pays
   */
  public void afficheListePays(List<Pays> payss)
  {
	//reinit
	cbPays.removeAllItems();
	//add a fake country to reprensent ALL the countries
	cbPays.addItem(new Pays("TOUS"));
	for (Pays pays: payss){
		 cbPays.addItem(pays);
	}
	  
	
  }
  
  public Pays getSelectedPays(){
	  return (Pays)cbPays.getSelectedItem();
  }
  
  public void selectPays(Pays pays){
	  cbPays.setSelectedItem(pays);
	  
  }
  

  

  
  public void displayErrorMessage(String msg){
	  JOptionPane.showMessageDialog(this,msg,"Error",
              JOptionPane.WARNING_MESSAGE);
  }
  
  public void displaySuccessMessage(String msg){
	  JOptionPane.showMessageDialog(this,msg,"Success",
              JOptionPane.INFORMATION_MESSAGE);
  }


}