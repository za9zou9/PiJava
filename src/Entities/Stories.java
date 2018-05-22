/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author Imene
 */
public class Stories {
    private int id;
    private int idStorie;
    private String description ;
    private String image ;
    private String titre ;

    public Stories() {
    }

    public Stories(int idStorie) {
        this.idStorie = idStorie;
    }
    

    public Stories(int idStorie, String description, String image, String titre) {
        this.idStorie = idStorie;
        this.description = description;
        this.image = image;
        this.titre = titre;
    }

    public Stories(String description, String image, String titre) {
        this.description = description;
        this.image = image;
        this.titre = titre;
    }
    
    
    

    public Stories(int id, int idStorie, String description, String image, String titre) {
        this.id = id;
        this.idStorie = idStorie;
        this.description = description;
        this.image = image;
        this.titre = titre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdStorie() {
        return idStorie;
    }

    public void setIdStorie(int idStorie) {
        this.idStorie = idStorie;
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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "Story{" + "id=" + id + ", idStorie=" + idStorie + ", description=" + description + ", image=" + image + ", titre=" + titre + '}';
    }

  
}
