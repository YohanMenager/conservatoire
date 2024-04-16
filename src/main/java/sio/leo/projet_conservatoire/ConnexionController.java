/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sio.leo.projet_conservatoire;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sio.leo.projet_conservatoire.classes.DAO;
import sio.leo.projet_conservatoire.classes.Eleve;

/**
 * FXML Controller class
 *
 * @author MENAGER
 */
public class ConnexionController implements Initializable {

    @FXML
    PasswordField pwfMdp;
    
    @FXML
    TextField txtfLogin;
    
    @FXML
    Label erreur;
    
    Connection cnx=DAO.getConnection();
    PreparedStatement pstmt;
    ResultSet rs;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    @FXML
    private void validerConnexion() throws IOException
    {
        String login=txtfLogin.getText();
        String mdp = pwfMdp.getText();
        
        String query="select * from ELEVE where ELELogin =? and ELEMDP=PASSWORD(?);";
        try
        {
            pstmt=cnx.prepareStatement(query);
            {
                pstmt.setString(1, login);
                pstmt.setString(2, mdp);
                rs = pstmt.executeQuery();
                if(rs.next())
                {
                    int num = rs.getInt("ELENUM");
                    String nom=rs.getString("ELENOM");
                    String prenom=rs.getString("ELEPRENOM");
                    int cycle=rs.getInt("ELECYCLE");
                    int annee=rs.getInt("ELEANNEECYCLE");
                    App.setEleve(new Eleve(num, nom, prenom, cycle, annee));
                    System.out.println(prenom+" "+nom);
                    App.setRoot("Accueil");
                }
                else
                {
                    System.out.println("pas de résultat");
                    erreur.setText("Les informations rentrées n'ont pas permis de vous identifier.");
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println("Erreur SQL : "+e);
        }
    }
    
    @FXML
    private void inscription()
    {
        try
        {
            App.setRoot("inscription");
        }
        catch(IOException e)
        {
            System.out.println("erreur changement de page");
        }
    }
}