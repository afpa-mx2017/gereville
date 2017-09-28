package com.ldub.gereville.ui;

import com.ldub.gereville.model.Pays;
import com.ldub.gereville.model.Ville;
import com.ldub.gereville.model.dao.DBConnect;
import com.ldub.gereville.model.dao.PaysDAO;
import com.ldub.gereville.model.dao.VilleDAO;
import com.lionel.gereville.utils.EventQueueProxy;


import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AppGereville {

    private UIGereville mainUI;
    private UIfrmVille frmVille;
    private UIfrmPays frmPays;
    //private UIlistVille listVilleUI;

    //models
    private VilleTableModel villeTableModel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    AppGereville app = new AppGereville();
                    //catch RuntimeExceptions during execution
                    EventQueue queue = Toolkit.getDefaultToolkit().getSystemEventQueue();
                    queue.push(new EventQueueProxy());

                    app.mainUI.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public AppGereville() {
        initialize();
    }

    private void initialize() {

        /**
         * init models
         */
        villeTableModel = new VilleTableModel();

        /**
         * init views
         */
        //main window
        mainUI = new UIGereville(this, villeTableModel);
        mainUI.setVisible(false);

        //pays form
        frmPays = new UIfrmPays(this);
        frmPays.setVisible(false);

        //ville form
        frmVille = new UIfrmVille(this);
        frmVille.setVisible(false);

        // Center main frame
        centerFrame(mainUI);
        centerFrame(frmVille);
        centerFrame(frmPays);

        /**
         * initialize data
         */
        //villeModel = new VilleTableModel();
        //check if database connection is Ok
        try {
            DBConnect.getConnection();

            mainUI.afficheListePays(PaysDAO.findAll());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(mainUI, "was unable to connect to database, please check that your dabase server is started, error is displayed in console ", "Error",
                    JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
            System.exit(0);

        }

    }

    private void centerFrame(JFrame ui) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = ui.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        ui.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

    }

    public void createPays(Pays p) {
        try {
            //TODO test if not exist
            PaysDAO.save(p);
            frmPays.setVisible(false);
            mainUI.afficheListePays(PaysDAO.findAll());
        } catch (Exception ex) {
            Logger.getLogger(AppGereville.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        

    }

    public void createVille(Ville v) {
        //TODO check if already exist
        try {
            VilleDAO.createVille(v);
            //if everything went fine update view list
            villeTableModel.addVille(v);
            //user changed Pays ?

            Pays p = mainUI.getSelectedPays();
            if (!p.equals(v.getPays())) {
                mainUI.selectPays(v.getPays());
            }
            frmVille.setVisible(false);
        } catch (Exception e) {
            frmVille.displayErrorMessage(e.getMessage());
        }

    }

    public void updateVille(Ville v) {
        try {
            VilleDAO.updateVille(v);

            villeTableModel.updateVille(v); //notify model & view
            frmVille.setVisible(false);
        } catch (Exception e) {
            frmVille.displayErrorMessage(e.getMessage());
        }

        //mainUI.selectPays(v.getPays()); //on indique qu'on veut afficher le pays en cours
    }

    public void onExitEvent() {
        // TODO Auto-generated method stub

    }

    public void onSelectedPaysEvent(Pays pays) {
        villeTableModel.clear();
        if (pays.getNom().equals("TOUS")) {

            //TODO not optimized 
            for (Pays p : PaysDAO.findAll()) {
                List<Ville> villes = VilleDAO.getVilles(p.getId());
                villeTableModel.addAllVille(villes);
            }

        } else {
            List<Ville> villes = VilleDAO.getVilles(pays.getId());
            villeTableModel.addAllVille(villes);
        }

    }

    public void onNewVilleEvent() {
        centerFrame(frmVille);
        frmVille.clear();

        frmVille.afficherPays(PaysDAO.findAll());
        //get current selected Pays
        Pays sPays = mainUI.getSelectedPays();
        frmVille.selectPays(sPays);

        //check if not any Capitale already stored
        frmVille.setVisible(true);

    }

    public void onSelectedVilleEvent(Ville v) {
        centerFrame(frmVille);
        frmVille.clear();
        frmVille.afficherPays(PaysDAO.findAll());
        frmVille.afficherVille(v);
        frmVille.setVisible(true);
    }

    public void deleteVille(Ville v) {
        //TODO dao
        try {
            VilleDAO.deleteVille(v);
        } catch (Exception e) {
            mainUI.displayErrorMessage(e.getMessage());
        }
        villeTableModel.deleteVille(v);

    }

    public void onNewPaysEvent() {
        frmPays.clear();
        frmPays.setVisible(true);

    }

    public void onDelPaysEvent() {
        frmPays.clear();
        frmPays.setVisible(true);

    }

}
