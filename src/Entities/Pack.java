/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author lenovo
 */
public class Pack {
    
    public int idPack;
  
    public int prixPack;
    public String   image;

    public int getIdPack() {
        return idPack;
    }

    public void setIdPack(int idPack) {
        this.idPack = idPack;
    }

    public int getPrixPack() {
        return prixPack;
    }

    public void setPrixPack(int prixPack) {
        this.prixPack = prixPack;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Pack() {
    }

    public Pack(int id,int prix, String image) {
        
        this.idPack = id ; 
        this.prixPack = prix;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Pack{" + "prix=" + prixPack + ", image=" + image + '}';
    }

    public Pack(int idPack) {
        this.idPack = idPack;
    }

    
    
}
