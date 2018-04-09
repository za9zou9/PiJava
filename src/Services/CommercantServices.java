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
    
    
    
}
