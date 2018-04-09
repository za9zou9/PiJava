/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


/**
 *
 * @author skan
 */

public class Evenement {
   
   
    private Integer idEvenement;
   
    private Date date;
   
    private String lieu;
   
    private String description;
  
    private String image;
  
    private double latitude;
  
    private double longitude;
    
    

    public Evenement() {
    }

    public Evenement(Integer idEvenement) {
        this.idEvenement = idEvenement;
    }

    public Evenement(int idEvenement,Date date, String lieu, String description,String image) {
        this.idEvenement=idEvenement;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.image=image;
    }

    public Integer getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(Integer idEvenement) {
        this.idEvenement = idEvenement;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvenement != null ? idEvenement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evenement)) {
            return false;
        }
        Evenement other = (Evenement) object;
        if ((this.idEvenement == null && other.idEvenement != null) || (this.idEvenement != null && !this.idEvenement.equals(other.idEvenement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Evenement[ idEvenement=" + lieu + " ]";
    }
    
}
