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

public class Produit  {
   
    private Integer idProduit;
  
    private Integer prix;
    
    private String type;
    
    private Integer quantite;
   
    private String nom;
  
    private String description;
   
    private String region;
  
    private String image;
  
    private int confirmation;
   
    private int idCommercant;

    public Produit() {
    }
    
     public Produit(String nom) {
        this.nom = nom;
    }

    public Produit(Integer quantite) {
        this.quantite = quantite;
    }

    public Produit(String image ,String nom,Integer prix, String description,Integer quantite) {
        this.prix = prix;
        this.quantite = quantite;
        this.nom = nom;
        this.description = description;
        this.image = image;
    }

    public Produit(Integer idProduit,String image, String nom,int prix,String description,int quantite,int confirmation) {
        this.idProduit = idProduit;
        this.prix = prix;
        this.type = type;
        this.quantite = quantite;
        this.nom = nom;
        this.description = description;
        this.idCommercant = idCommercant;
        this.region = region;
        this.image = image;
        this.confirmation=confirmation;
    }
    
        public Produit(String nom,int prix) {
            this.nom=nom;
        this.prix = prix;

    }
        
        public Produit(String nom,int id,String i) {
            this.nom=nom;
        this.idProduit = id;
this.image = i ;
    }

    public Produit(Integer idProduit, Integer prix) {
        this.idProduit = idProduit;
        this.prix = prix;
    }

    public Produit(String nom,Integer prix, String description,Integer quantite,String image) {
        this.image = image;
        this.nom = nom;
        this.prix = prix;
   this.description = description;
          this.quantite = quantite;
       
    }
    
    

    public Produit(Integer idProduit, Integer prix, String type, Integer quantite, String nom, String description,int idCommercant ,String region, String image, int confirmation) {
        this.idProduit = idProduit;
        this.prix = prix;
        this.type = type;
        this.quantite = quantite;
        this.nom = nom;
        this.description = description;
        this.region = region;
        this.image = image;
        this.confirmation = confirmation;
   this.idCommercant=idCommercant;
    }

    
   

    public Produit(Integer idProduit, int confirmation) {
        this.idProduit = idProduit;
        this.confirmation = confirmation;
    }

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(int confirmation) {
        this.confirmation = confirmation;
    }

    public int getIdCommercant() {
        return idCommercant;
    }

    public void setIdCommercant(int idCommercant) {
        this.idCommercant = idCommercant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduit != null ? idProduit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.idProduit == null && other.idProduit != null) || (this.idProduit != null && !this.idProduit.equals(other.idProduit))) {
            return false;
        }
        return true;
    }

     @Override
    public String toString() {
        return "" +nom+ " ";
    }

}
