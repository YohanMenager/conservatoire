/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sio.leo.projet_conservatoire;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sio.leo.projet_conservatoire.classes.DAO;
import sio.leo.projet_conservatoire.classes.Partition;

/**
 * FXML Controller class
 *
 * @author MENAGER
 */
public class PartitionsEleveController implements Initializable {

    @FXML
    private TableView liste;
        @FXML
        private TableColumn<Partition, String> colNom;
        @FXML
        private TableColumn<Partition, String> colAuteur;  
        @FXML
        private TableColumn<Partition, Number> colPage;  
        @FXML
        private TableColumn<Partition, Number> colId;
    
    @FXML
    private TextField txtRecherche;
        
    
    private final ObservableList<Partition> ol;
    
    private String query;
    private final Connection cnx;
    private final Statement smt;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    

    public PartitionsEleveController() {
        this.smt = DAO.getStatement();
        this.cnx = DAO.getConnection();
        this.ol = FXCollections.observableArrayList();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNom.setCellValueFactory(cellData->cellData.getValue().propertyNom());
        colAuteur.setCellValueFactory(cellData->cellData.getValue().propertyAuteur());
        colPage.setCellValueFactory(cellData->cellData.getValue().propertyPage());
        colId.setCellValueFactory(cellData->cellData.getValue().propertyNum());
        remplirListe();
        remplirTableau();
    }    
    
    /**
     * recherche l'entrée de l'utilisateur et renvoie une liste récupérée dans la base de données
     */
    @FXML
    private void recherche()
    {
        ol.clear();
        query="select P.PARNUM, PARNOM, PARAUTEUR, NUMEROPAGECLASSEUR "
                + "from PARTITIONS P "
                + "join PARTITIONELEVE PE on P.PARNUM = PE.PARNUM "
                + "where ELENUM = "+App.getEleve().getNum()+" "
                + "and PARAUTEUR = ?;";
        try
        {
            pstmt = cnx.prepareStatement(query);
            pstmt.setString(1, txtRecherche.getText());
            rs=pstmt.executeQuery();
            while(rs.next())
            {
                String titre = rs.getString("PARNOM");
                String auteur = rs.getString("PARAUTEUR");
                int page = rs.getInt("NUMEROPAGECLASSEUR");
                int num = rs.getInt("PARNUM");
                ol.add(new Partition(num, titre, auteur, page));                
            }
        }
        catch(SQLException e)
        {
            System.out.println("Erreur remplirListe : "+e);
        }
    }
    @FXML
    private void réinitialiser()
    {
        ol.clear();
        remplirListe();
    }
    
    /**
     * remplit le tableau avec la liste
     */
    private void remplirTableau()
    {
        liste.setItems(ol);
    }
    
    /**
     * remplie la liste depuis la base de données
     */
    private void remplirListe()
    {
        query="select P.PARNUM, PARNOM, PARAUTEUR, NUMEROPAGECLASSEUR "
                + "from PARTITIONS P "
                + "join PARTITIONELEVE PE on P.PARNUM = PE.PARNUM "
                + "where ELENUM = "+App.getEleve().getNum()+";";
        try
        {
            rs=smt.executeQuery(query);
            while(rs.next())
            {
                String titre = rs.getString("PARNOM");
                String auteur = rs.getString("PARAUTEUR");
                int page = rs.getInt("NUMEROPAGECLASSEUR");
                int num = rs.getInt("PARNUM");
                ol.add(new Partition(num, titre, auteur, page));
            }                 
        }
        catch(SQLException e)
        {
            System.out.println("Erreur remplirListe : "+e);
        }

    }
    
    /**
     * supprime la sélection dans la base de données
     */
    @FXML 
    private void supprimer()
    {
        Partition p = (Partition)liste.getSelectionModel().getSelectedItem();
        query = "delete from PARTITIONELEVE where PARNUM="+p.getnum()+" and ELENUM = "+App.getEleve().getNum()+";";
        try
        {
            smt.executeUpdate(query);
        }
        catch(SQLException e)
        {
            System.out.println("Erreur remplirListe : "+e);
        }
        réinitialiser();       
    }
}
