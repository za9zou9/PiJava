/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.dataSource;
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
         ste.setBoolean(6,p.getConfirmation());
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
            Produit g=new Produit(rs.getInt("idProduit"),rs.getInt("prix"),rs.getString("type"),rs.getInt("quantite"),rs.getString("nom"),rs.getString("description"),rs.getInt("idCommercant"),rs.getString("region"),rs.getString("image"),rs.getBoolean("confirmation"));
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
            Produit g=new Produit(resultat.getInt("idProduit"),resultat.getInt("prix"),resultat.getString("type"),resultat.getInt("quantite"),resultat.getString("nom"),resultat.getString("description"),resultat.getInt("idCommercant"),resultat.getString("region"),resultat.getString("image"),resultat.getBoolean("confirmation"));
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
            Produit g=new Produit(resultat.getInt("idProduit"),resultat.getInt("prix"),resultat.getString("type"),resultat.getInt("quantite"),resultat.getString("nom"),resultat.getString("description"),resultat.getInt("idCommercant"),resultat.getString("region"),resultat.getString("image"),resultat.getBoolean("confirmation"));
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
            Produit g=new Produit(resultat.getInt("idProduit"),resultat.getInt("prix"),resultat.getString("type"),resultat.getInt("quantite"),resultat.getString("nom"),resultat.getString("description"),resultat.getInt("idCommercant"),resultat.getString("region"),resultat.getString("image"),resultat.getBoolean("confirmation"));
            list.add(g);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }   
  
 
}
