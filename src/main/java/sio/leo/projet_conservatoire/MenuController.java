/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sio.leo.projet_conservatoire;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sio.leo.projet_conservatoire.classes.DAO;

/**
 * FXML Controller class
 *
 * @author MENAGER
 */
public class MenuController implements Initializable {

    @FXML
    private Label nom;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nom.setText(App.getEleve().getPrenom()+" "+App.getEleve().getNom());
    }    
    @FXML
    public void Horaire() throws IOException
    {
        App.setRoot("horaires");
    }
    
    @FXML
    public void saisiePartition() throws IOException
    {       
        App.setRoot("PartitionsInstrument");
    }
    
    @FXML
    public void recherchePartition() throws IOException
    {    
        App.setRoot("PartitionsEleve");
    }   
    
    @FXML
    public void Quitter() throws SQLException
    {
        DAO.Décconexion();
        System.exit(0);
    }
}