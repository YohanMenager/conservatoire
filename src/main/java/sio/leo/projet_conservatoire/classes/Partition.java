/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sio.leo.projet_conservatoire.classes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author MENAGER
 */
public class Partition {
    private final IntegerProperty num=new SimpleIntegerProperty();
    private final StringProperty nom=new SimpleStringProperty();
    private final StringProperty nomAuteur=new SimpleStringProperty();
    private final IntegerProperty page=new SimpleIntegerProperty();
    

    public Partition(int unNum, String unNom, String unNomAuteur) {
        num.setValue(unNum);
        nom.setValue(unNom);
        nomAuteur.setValue(unNomAuteur);
    }
    
    public void setnum(int unNum)
    {
        num.setValue(unNum);
    }
    public void setnom(String unNom)
    {
        nom.setValue(unNom);
    }
    public void setAuteur(String unNomAuteur)
    {
        nomAuteur.setValue(unNomAuteur);
    }
    public int getnum()
    {
        return num.get();
    }
    public String getnom()
    {
        return nom.get();
    }
    public String getauteur()
    {
        return nomAuteur.get();
    }
    public IntegerProperty propertyNum()
    {
        return num;
    }
    public StringProperty propertyNom()
    {
        return nom;
    }
    public StringProperty propertyAuteur()
    {
        return nomAuteur;
    }

    public Partition(String unNom, String unNomAuteur, int unePage) 
    {
        nom.setValue(unNom);
        nomAuteur.setValue(unNomAuteur);
        page.setValue(unePage);
    }
    
    public Partition(int unNum, String unNom, String unNomAuteur, int unePage) 
    {
        num.setValue(unNum);
        nom.setValue(unNom);
        nomAuteur.setValue(unNomAuteur);
        page.setValue(unePage);
    }    
    public void setPage(int unePage)
    {
        page.setValue(unePage);
    }
    public int getPage()
    {
        return page.get();
    }
    public IntegerProperty propertyPage()
    {
        return page;
    }
            
    
}