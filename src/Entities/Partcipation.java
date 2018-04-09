/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


/**
 *
 * @author skan
 */

public class Partcipation  {
   
    private Integer reponse;
  
    private int id;
   
    private int idEvenement;

    public Partcipation() {
    }

    public Partcipation(Integer reponse) {
        this.reponse = reponse;
    }

    public Integer getReponse() {
        return reponse;
    }

    public void setReponse(Integer reponse) {
        this.reponse = reponse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public Partcipation(Integer reponse, int id, int idEvenement) {
        this.reponse = reponse;
        this.id = id;
        this.idEvenement = idEvenement;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reponse != null ? reponse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partcipation)) {
            return false;
        }
        Partcipation other = (Partcipation) object;
        if ((this.reponse == null && other.reponse != null) || (this.reponse != null && !this.reponse.equals(other.reponse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Partcipation[ reponse=" + reponse + " ]";
    }
    
}
