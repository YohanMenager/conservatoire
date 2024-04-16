/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sio.leo.projet_conservatoire.classes;

/**
 *
 * @author MENAGER
 */
public class discipline {
    private int num;
    private String nom;

    public discipline(int num, String nom) {
        this.num = num;
        this.nom = nom;
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
    @Override
    public String toString()
    {
        return nom;
    }
}
