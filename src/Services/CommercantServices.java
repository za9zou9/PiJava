/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.dataSource;
import Entities.Commercant;
import Entities.Evenement;
import Entities.Produit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
public class CommercantServices {
    
     Connection con;
    Statement st;
    ResultSet rs;
    
    public CommercantServices()
    {
     con=dataSource.getInstance().getConnection();}
    
    
    
    
    public List<Commercant> selectCommercantID(int id) {
        List<Commercant> liste=new ArrayList<Commercant>();
        Commercant c = new Commercant();
        String req = "SELECT * FROM commercant where idCommercant='" + id + "'";
        try {
            Statement statement = con.createStatement();
            ResultSet resultat = statement.executeQuery(req);
             while (resultat.next()) {
            
                c.setIdCommercant(resultat.getInt("idCommercant"));
                c.setNom(resultat.getString("nom"));
                c.setPrenom( resultat.getString("prenom"));
                c.setPseudo(resultat.getString("pseudo"));
                c.setMdp(resultat.getString("mdp"));
              liste.add(c);

             }
            

        } catch (SQLException ex) {
            Logger.getLogger(CommercantServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
    
    
    public Commercant selectCommercantForLogin(String pseudo,String mdp) {
       
        Commercant c = new Commercant();
        String req = "SELECT * FROM commercant where pseudo='" + pseudo + "' and mdp='"+ mdp +"'";
        try {
            Statement statement = con.createStatement();
            ResultSet resultat = statement.executeQuery(req);
             while (resultat.next()) {
            
                c.setIdCommercant(resultat.getInt("idCommercant"));
                c.setNom(resultat.getString("nom"));
                c.setPrenom( resultat.getString("prenom"));
                c.setPseudo(resultat.getString("pseudo"));
                c.setMdp(resultat.getString("mdp"));
             

             }
            

        } catch (SQLException ex) {
            Logger.getLogger(CommercantServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
    
    public void insertCommercant(Commercant e) throws SQLException
    {
        String req="INSERT INTO `commercant`"+"(`pseudo`,`nom`,`prenom`,`mdp`)"+ "VALUES (?,?,?,?)";
         try{
        PreparedStatement ste = con.prepareStatement(req);
        ste.setString(1, e.getPseudo());
        ste.setString(2,e.getNom());
        ste.setString(3,e.getPrenom());
        ste.setString(4,e.getMdp());
       
        ste.executeUpdate();
         }
         catch (SQLException ex)
         {            Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);}
    }

    
    
}
