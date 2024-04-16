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
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sio.leo.projet_conservatoire.classes.DAO;
import sio.leo.projet_conservatoire.classes.discipline;

/**
 * FXML Controller class
 *
 * @author MENAGER
 */
public class InscriptionController implements Initializable {

    @FXML
    TextField txtNom;
    @FXML
    TextField txtPrenom;
    @FXML
    TextField txtLogin;
    @FXML
    PasswordField pwdMdp;
    @FXML
    PasswordField pwdConfirme;
    
    @FXML
    ComboBox<String> cbbCycle;
    @FXML
    ComboBox<String>cbbAnnee;
    @FXML
    ComboBox cbbDis;
    
    String query;
        
    private final Connection cnx = DAO.getConnection();
    private final Statement smt=DAO.getStatement();
    private ResultSet rs;
    private PreparedStatement pstmt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbbCycle.getItems().addAll("1", "2", "3");
        cbbAnnee.getItems().addAll("1", "2", "3", "4", "5");
        query = "select * "
                + "from DISCIPLINE;";
        try
        {
            rs=smt.executeQuery(query);
            while(rs.next())
            {
                cbbDis.getItems().add(new discipline(rs.getInt("DISNUM"), rs.getString("DISLIBELLE")));
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
            
            if(formulairerempli())
            {
                query="insert into ELEVE "
                + "values("+DAO.idEleveValide()+",?,?,?,?,?,?,PASSWORD(?));";
                pstmt=cnx.prepareStatement(query);
                discipline choixdis=(discipline)cbbDis.getSelectionModel().getSelectedItem();
                int choixcycle=Integer.parseInt(cbbCycle.getSelectionModel().getSelectedItem());
                int choixannee=Integer.parseInt(cbbAnnee.getSelectionModel().getSelectedItem());
                pstmt.setInt(1, choixdis.getNum());
                System.out.println(pstmt.toString());
                pstmt.setString(2, txtNom.getText());
                pstmt.setString(3, txtPrenom.getText());
                pstmt.setInt(4, choixcycle);
                pstmt.setInt(5,choixannee);
                pstmt.setString(6, txtLogin.getText());
                pstmt.setString(7, pwdMdp.getText());

                pstmt.executeUpdate();

                retour();                    
            }

            
        }
        catch(SQLException e)
        {
            System.out.println("Erreur ajout eleve : "+e);
        }     
    }
    
    private boolean formulairerempli()
    {
        if(cbbDis.getSelectionModel().getSelectedIndex()<0)
        {
            return false;
        }
        else if(cbbCycle.getSelectionModel().getSelectedIndex()<0)
        {
            return false;
        }
        else if(cbbAnnee.getSelectionModel().getSelectedIndex()<0)
        {
            return false;
        }
        else if(txtNom.getText().isBlank())
        {
            return false;
        }
        else if(txtPrenom.getText().isBlank())
        {
            return false;
        }
        else if(txtLogin.getText().isBlank())
        {
            return false;
        }
        else if(!LoginExiste())
        {
            System.out.println("sio.leo.projet_conservatoire.InscriptionController.formulairerempli()");
            return false;
        }        
        else if(pwdMdp.getText().isBlank())
        {
            return false;
        }
        else return pwdMdp.getText().equals(pwdConfirme.getText());

    }
    private boolean LoginExiste()
    {
        query="select ELELOGIN from ELEVE where ELELOGIN = ?";
        try {
            pstmt=cnx.prepareStatement(query);
            pstmt.setString(1, txtLogin.getText()); 
            rs=pstmt.executeQuery();
            if(rs.next())
            {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
   
    @FXML
    private void retour()
    {
        
        try {
            App.setRoot("Connexion");
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
