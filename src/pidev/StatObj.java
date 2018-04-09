/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Produit;

/**
 *
 * @author skan
 */
public class StatObj {
    
    private Produit produit;
    private int valeur;

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public StatObj(Produit produit, int valeur) {
        this.produit = produit;
        this.valeur = valeur;
    }

    public StatObj() {
    }
    
    
    
    
}
