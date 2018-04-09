/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author skan
 */

public class User {
    
    private int id;
    private String roles;
    private String username;
    private String email;
    private String password;
   
    private String pseudo;
    
    private String nom;
   
    private String mail;
  
    private String prenom;
  
    private Date dateDeNaissance;
    
    private String image;
    
    private String etat;
  
    private String mdp;
   
    private String sexe;
    
    private String profession;
   
    private String pays;
   
    private String ville;
    
    private String tel;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
  
    private Integer age;
   
    private Integer idStorie;
   
    private Integer idQuiz;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(String email,String etat) {
        
       
        this.email = email;
        this.etat=etat;
     
    }
    
     public User(String email,String username,String pays,String ville,int age,String profession,String sexe) {
        
       
        this.email = email;
        this.username=username;
        this.pays=pays;
        this.ville=ville;
        this.age=age;
        this.profession=profession;
        this.sexe=sexe;
     
    }
     
      public User(String nom,String prenom,String username,Date date,String email ,String tel,String etat,String sexe,int age) {
        
       this.nom=nom;
       this.prenom=prenom;
       this.dateDeNaissance=date;
        this.tel=tel;
        this.etat=etat;
         this.email = email;
        this.username=username;
        this.sexe=sexe;
        this.age=age;
     
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getIdStorie() {
        return idStorie;
    }

    public void setIdStorie(Integer idStorie) {
        this.idStorie = idStorie;
    }

    public Integer getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(Integer idQuiz) {
        this.idQuiz = idQuiz;
    }

  

 
    @Override
    public String toString() {
        return "Entities.User[ id=" + id + " ]";
    }
    
}
