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
public class PartitionsInstrumentController implements Initializable {

    @FXML
    private TableView liste;
        @FXML
        private TableColumn<Partition, Number> colId;
        @FXML
        private TableColumn<Partition, String> colNom;
        @FXML
        private TableColumn<Partition, String> colAuteur;
    
    @FXML
    private TextField txtPage;
    @FXML
    private TextField txtTitrePartition;
    @FXML
    private TextField txtNomAuteur;
    
    private final Connection cnx;
    private final Statement smt;
    private ResultSet rs;
    private PreparedStatement pstmt;
    
    String query;
    
    ObservableList<Partition> ol = FXCollections.observableArrayList();

    public PartitionsInstrumentController() {
        this.smt = DAO.getStatement();
        this.cnx = DAO.getConnection();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(cellData->cellData.getValue().propertyNum());
        colNom.setCellValueFactory(cellData->cellData.getValue().propertyNom());
        colAuteur.setCellValueFactory(cellData->cellData.getValue().propertyAuteur());
        remplirListe();
    }
    
    private void remplirListe()
    {
        ol.clear();
        query = "select * from PARTITIONS";
        try
        {
            rs = smt.executeQuery(query);

            while(rs.next())
            {
                int id = rs.getInt("PARNUM");
                String nom = rs.getString("PARNOM");
                String auteur = rs.getString("PARAUTEUR");
                ol.add(new Partition(id, nom, auteur));
            }
            liste.setItems(ol);
        }
        catch(SQLException e)
        {
            System.out.println("Erreur SQL : "+e);
        }        
    }
    
    @FXML
    public void ajoutClasseur()
    {
        query="";
        try
        {
            int page = Integer.parseInt(txtPage.getText());
            Partition p = (Partition)liste.getSelectionModel().getSelectedItem();
            query = "insert into PARTITIONELEVE values ("+App.getEleve().getNum()+", "+p.getnum()+", "+page+");";
        }
        catch(NumberFormatException e)
        {
            System.out.println("erreur nombre incorrect: "+e);
        }
        try
        {
            smt.execute(query);
        }
        catch(SQLException e)
        {
            System.out.println("erreur ajout classeur : "+e);
        }
        
    }
    
    @FXML
    public void ajoutPartition()
    {
        
        String titre = txtTitrePartition.getText();
        String nomAuteur = txtNomAuteur.getText();
        query="insert into PARTITIONS values("+DAO.idPartitionValide()+",?,?);";
        try
        {
            pstmt=cnx.prepareStatement(query);
            {
                pstmt.setString(1, titre);
                pstmt.setString(2, nomAuteur);
                pstmt.executeUpdate();
            }
        }
        catch(SQLException e)
        {
            System.out.println("Erreur ajout partition : "+e);
        }
        remplirListe();
    }
    
}
