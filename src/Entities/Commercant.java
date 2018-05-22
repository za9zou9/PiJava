/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author skan
 */

public class Commercant implements Serializable  {
  
    private Integer idCommercant;
   
    private String pseudo;
    
    private String nom;
    
    private String prenom;
   
    private String mdp;
    
   

    public Commercant() {
    }
    
     public Commercant(Commercant com) {
   this.idCommercant=com.idCommercant;
     }

    public Commercant(Integer idCommercant) {
        this.idCommercant = idCommercant;
    }
    
     private static Commercant instance;
  
    public static Commercant getInstance(){
        if (instance == null)
            instance = new Commercant();
        
    return instance;
    }

  
    
    public static void setInstance(Commercant user){
    instance = new Commercant(user);
    }
    
    public  void sedeconnecter(){
    instance=null;
    }

    public Commercant(String pseudo, String nom, String prenom, String mdp) {
      
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
    }

    public Integer getIdCommercant() {
        return idCommercant;
    }

    public void setIdCommercant(Integer idCommercant) {
        this.idCommercant = idCommercant;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
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

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCommercant != null ? idCommercant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commercant)) {
            return false;
        }
        Commercant other = (Commercant) object;
        if ((this.idCommercant == null && other.idCommercant != null) || (this.idCommercant != null && !this.idCommercant.equals(other.idCommercant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Commercant[ idCommercant=" + idCommercant + " ]";
    }
    
}
