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

public class Ligne  {

   
    
     private Integer idProduit;
      private Integer idPack;
    
       private Integer idLigne;

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public Integer getIdPack() {
        return idPack;
    }

    public void setIdPack(Integer idPack) {
        this.idPack = idPack;
    }

    public Integer getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(Integer idLigne) {
        this.idLigne = idLigne;
    }

    public Ligne(Integer idProduit, Integer idPack, Integer idLigne) {
        this.idProduit = idProduit;
        this.idPack = idPack;
        this.idLigne = idLigne;
    }

    @Override
    public String toString() {
        return "Ligne{" + "idProduit=" + idProduit + ", idPack=" + idPack + ", idLigne=" + idLigne + '}';
    }

    public Ligne() {
    }

  
    
}
