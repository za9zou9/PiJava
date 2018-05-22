/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.dataSource;
import Entities.Commande;
import Entities.Commercant;
import Entities.Produit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author skan
 */
public class ProduitServices {
     Connection con;
    Statement st;
    ResultSet rs;
    

 public ProduitServices()
    {
     con=dataSource.getInstance().getConnection();}


 public void insertProduit(Produit p) throws SQLException
    { CommercantServices sc=new CommercantServices();
    Commercant c =new Commercant();
        String req="INSERT INTO `produit`"+"(`prix`, `type`,`quantite`,`nom`,`description`,`confirmation`,`region`,`image`,`idCommercant`)"+ "VALUES (?,?,?,?,?,?,?,?,?)";
         try{
        PreparedStatement ste = con.prepareStatement(req);
        ste.setFloat(1,p.getPrix());
        ste.setString(2,p.getType());
        ste.setInt(3,p.getQuantite());
        ste.setString(4,p.getNom());
        ste.setString(5,p.getDescription());
         ste.setInt(6,p.getConfirmation());
         ste.setString(7,p.getRegion());
          ste.setString(8,p.getImage());
          ste.setInt(9,p.getIdCommercant());
         
          
        ste.executeUpdate();
         }
         catch (SQLException ex)
         {            Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);

             }

                
                
                }
 
 
 
 
 
 public List<Produit> selectProduitsAll()
    { String req="Select * from produit";
      List<Produit> list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
            Produit g=new Produit(rs.getInt("idProduit"),rs.getInt("prix"),rs.getString("type"),rs.getInt("quantite"),rs.getString("nom"),rs.getString("description"),rs.getInt("idCommercant"),rs.getString("region"),rs.getString("image"),rs.getInt("confirmation"));
            list.add(g);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }   

    
 
  public List<Produit> selectProduitsConfirmes(int idC)
    { String req="Select * from produit where confirmation=1 and idCommercant='" + idC + "'";
      List<Produit> list = new ArrayList<>();
        try {
           Statement statement = con.createStatement();
            ResultSet resultat = statement.executeQuery(req);
            while(resultat.next())
            {
            Produit g=new Produit(resultat.getInt("idProduit"),resultat.getInt("prix"),resultat.getString("type"),resultat.getInt("quantite"),resultat.getString("nom"),resultat.getString("description"),resultat.getInt("idCommercant"),resultat.getString("region"),resultat.getString("image"),resultat.getInt("confirmation"));
            list.add(g);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }   
  
  
  
  public List<Produit> selectProduitsEnAttente(int idC)
    { String req="Select * from produit where confirmation=0 and idCommercant='" + idC + "'";
      List<Produit> list = new ArrayList<>();
        try {
           Statement statement = con.createStatement();
            ResultSet resultat = statement.executeQuery(req);
            while(resultat.next())
            {
            Produit g=new Produit(resultat.getInt("idProduit"),resultat.getInt("prix"),resultat.getString("type"),resultat.getInt("quantite"),resultat.getString("nom"),resultat.getString("description"),resultat.getInt("idCommercant"),resultat.getString("region"),resultat.getString("image"),resultat.getInt("confirmation"));
            list.add(g);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }   
  
  
  
     
 
 
 
 
 
 
 
  public void DeleteProduit(int id) throws SQLException
    {
        String req="DELETE FROM produit WHERE idProduit = ?";
         try{
        PreparedStatement ste = con.prepareStatement(req);
        ste.setInt(1,id);
       
        ste.executeUpdate();
         }
         catch (SQLException ex)
         {            Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);}
    }
 
 
 
  
  public void UpdateProduit(int id,Float prix,String type,int quantite,String nom,String description,String region,String image) throws SQLException
    {
        String req="UPDATE produit SET prix = ?, type = ?, quantite = ?, nom = ?, description = ?, region = ?, image = ? WHERE idProduit = ?";
         try{
        PreparedStatement ste = con.prepareStatement(req);
        ste.setFloat(1,prix);
        ste.setString(2,type);
        ste.setInt(3,quantite);
        ste.setString(4,nom);
        ste.setString(5,description);
        ste.setString(6,region);
        ste.setString(7,image);
        ste.setInt(8,id);
        ste.executeUpdate();
         }
         catch (SQLException ex)
         {            Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);}
    }
    
 
  
   public List<Produit> selectProduitsByIDP(int id)
    { String req="Select * from produit where confirmation=1 and idProduit='" + id + "'";
      List<Produit> list = new ArrayList<>();
        try {
           Statement statement = con.createStatement();
            ResultSet resultat = statement.executeQuery(req);
            while(resultat.next())
            {
            Produit g=new Produit(resultat.getInt("idProduit"),resultat.getInt("prix"),resultat.getString("type"),resultat.getInt("quantite"),resultat.getString("nom"),resultat.getString("description"),resultat.getInt("idCommercant"),resultat.getString("region"),resultat.getString("image"),resultat.getInt("confirmation"));
            list.add(g);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }   
  
 
    public List<Produit> selectPrix()
    { String req="produit.prix from commande inner join produit where (commande.idProduit=produit.idProduit)";
      List list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
           
           Produit p = new Produit(rs.getInt("prix"));
           list.add(p);
           
            }
            
        } catch (SQLException ex) {
           Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }
     
     public List<Commande> selectCommandeP()
    { String req="Select idProduit,quantite from commande ";
      List list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
           Commande c=new Commande(rs.getInt("quantite"),rs.getInt("idProduit"));
            list.add(c);
            }
            
        } catch (SQLException ex) {
           Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }
     
      public List<Produit> select()
    { String req="Select prix from produit ";
      List list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
           Produit p=new Produit(rs.getInt("prix"));
            list.add(p);
            }
            
            
        } catch (SQLException ex) {
           Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }
     
    public List<Produit> selectProduitsAll2()
    { String req="Select * from produit";
      List<Produit> list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
           Produit c=new Produit(rs.getInt("idProduit"),rs.getString("image"),rs.getString("nom"),rs.getInt("prix"),rs.getString("description"),rs.getInt("quantite"),rs.getInt("confirmation"));
            if(c.getConfirmation()==1 && c.getQuantite()>0){
            list.add(c);}
            }
            
        } catch (SQLException ex) {
           Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        } 
    
public List<Produit> selectProduit()
    { String req="Select * from produit";
      List<Produit> list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
           Produit c=new Produit(rs.getInt("idProduit"),rs.getString("image"),rs.getString("nom"),rs.getInt("prix"),rs.getString("description"),rs.getInt("quantite"),rs.getInt("confirmation"));
            if(c.getConfirmation()==0){
            list.add(c);}
            }
            
        } catch (SQLException ex) {
           Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        } 

    public void AjouterProduit(Produit p){
  
      try {
          String req = "INSERT INTO Produit (nom,prix,description,quantite,image,confirmation) VALUES (?,?,?,?,?,1)";
          PreparedStatement pre = con.prepareStatement(req);
          pre.setString(1, p.getNom());
          pre.setDouble(2, p.getPrix());
         
          pre.setString(3, p.getDescription());
          pre.setInt(4, p.getQuantite());
          pre.setString(5, p.getImage());
         
          
          pre.executeUpdate();
          
      } catch (SQLException ex) {
          Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
      }
     
  
  }
public void UpdateProduit(Produit produit, Integer idProduit) 
    {
      
        String req = "UPDATE produit SET quantite=? ,confirmation=1 where idProduit=? ";
        try {
            PreparedStatement pre = con.prepareStatement(req);
           
              pre.setInt(1,produit.getQuantite());
          pre.setInt(2,idProduit);
            int i = pre.executeUpdate();
            if (i==1) { 
                System.out.println("SUCCES");
            }
         
        
      
        } catch (Exception e) {
            e.printStackTrace();
             System.out.println("Erreur");
        }
    }
    
   
   
}
