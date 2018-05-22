/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Imene
 */
public class Commentaire {
    private int id;
    private int idCommentaire;
    private String description;
    private int idStorie;
 public Commentaire() {
    }

    public Commentaire(String description, int idStorie) {
        this.description = description;
        this.idStorie = idStorie;
    }


    public Commentaire(int idStorie, int idCommentaire, String description) {
        
        this.idCommentaire = idCommentaire;
        this.description= description;
        this.idStorie = idStorie;
        
    }
     public Commentaire( int idCommentaire, String description) {
     
        this.idCommentaire = idCommentaire;
        this.description= description;
     
        
    }
     public Commentaire(String description){
     this.description=description;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdStorie() {
        return idStorie;
    }

    public void setIdStorie(int idStorie) {
        this.idStorie = idStorie;
    }

    
   
    
    @Override
    public String toString() {
        return "Entities.Commentaire[ description=" + description + "  ]";
    }
    
    
}
