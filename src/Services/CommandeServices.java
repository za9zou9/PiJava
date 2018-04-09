/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.dataSource;
import Entities.Commande;
import Entities.Evenement;
import Entities.Partcipation;
import Entities.Produit;
import java.sql.Connection;
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
public class CommandeServices {
    
     Connection con;
    Statement st;
    ResultSet rs;
    
    public CommandeServices()
    {
     con=dataSource.getInstance().getConnection();}
    
    
     public List<Commande> selectCommandesAll()
    { String req="Select * from commande";
      List<Commande> list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
           Commande c=new Commande(rs.getInt("idCommande"),rs.getInt("quantite"),rs.getInt("idPack"),rs.getInt("idProduit"),rs.getInt("id"));
            list.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }   
     
     
   
     
     
     public List<Produit> verifCommercant(int idProduit) {
      
      int x=0;
       List<Produit> liste=new ArrayList<Produit>();
      Produit p=new Produit();
        String req = "SELECT * FROM produit where idProduit='" + idProduit + "'";
        try {
            Statement statement = con.createStatement();
            ResultSet resultat = statement.executeQuery(req);
             while (resultat.next()) {
            
               p.setIdCommercant(resultat.getInt("idCommercant"));
            liste.add(p);

             }
             
            

        } catch (SQLException ex) {
            Logger.getLogger(CommercantServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;  }
    
     
     
     
     
     public List<Produit> selectProduitsParCommercant(int idCommercant)
    { String req="Select * from produit where idCommercant='" + idCommercant + "'";
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
