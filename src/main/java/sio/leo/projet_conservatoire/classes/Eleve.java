/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sio.leo.projet_conservatoire.classes;

/**
 *
 * @author MENAGER
 */
public class Eleve {
    private int num;
    private String nom;
    private String prenom;
    private int cycle;
    private int annee;
    //private String disciplinePrincipale;
    private String login;
    private String mdp;

    public Eleve(int num, String nom, String prenom, int cycle, int annee, String login, String mdp) {
        this.num = num;
        this.nom = nom;
        this.prenom = prenom;
        this.cycle = cycle;
        this.annee = annee;
        this.login = login;
        this.mdp = mdp;
    }
    public Eleve(int num, String nom, String prenom, int cycle, int annee) {
        this.num = num;
        this.nom = nom;
        this.prenom = prenom;
        this.cycle = cycle;
        this.annee = annee;
    }
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }


    
}