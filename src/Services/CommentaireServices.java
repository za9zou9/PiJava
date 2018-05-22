/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.dataSource;
import Entities.Commentaire;
import Entities.Stories;
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
 * @author Imene
 */
public class CommentaireServices {
  Connection con;
    Statement st;
    ResultSet rs;
    public CommentaireServices()
    {
     con=dataSource.getInstance().getConnection();}   
    
    public List<Commentaire> selectCommentairesAll(int idStorie)
    { String req="Select * from commentaire where idStorie='" + idStorie + "'";
      List<Commentaire> list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);

            while(rs.next())
            {
           Commentaire c=new Commentaire(rs.getInt("idStorie"),rs.getInt("idCommentaire"),rs.getString("description"));
           if(c.getIdStorie()==idStorie){
           list.add(c);}
            }
            
        } catch (SQLException ex) {
           Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        } 
    
    
    public List<Commentaire> selectCommentaires()
    { String req="Select * from commentaire ";
      List<Commentaire> list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
           
            while(rs.next())
            {
           Commentaire c=new Commentaire(rs.getInt("idStorie"),rs.getInt("idCommentaire"),rs.getString("description"));
           list.add(c);
            }
            
        } catch (SQLException ex) {
           Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        } 
    
    
     public void insertCommentaire(Commentaire com) throws SQLException
    {
        String req="INSERT INTO `commentaire`"+"(`description`,`idStorie`,`id`)"+ "VALUES (?,?,1)";
         try{
        PreparedStatement ste = con.prepareStatement(req);
        
     ste.setString(1,com.getDescription());
     ste.setInt(2,com.getIdStorie());
    
        
        ste.executeUpdate();
         }
         catch (SQLException ex)
         {            Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);

             }

                
                
                }
     
     
     public void UpdateCommentaire(Commentaire commentaire, Integer idCommentaire) 
    {
      
        String req = "UPDATE commentaire SET description=? where idCommentaire=? ";
        try {
            PreparedStatement pre = con.prepareStatement(req);
           
              pre.setString(1,commentaire.getDescription());
          pre.setInt(2,idCommentaire);
            int i = pre.executeUpdate();
            if (i==1) { 
                System.out.println("SUCCES");}
         
        
      
        } catch (Exception e) {
            e.printStackTrace();
             System.out.println("Erreur");
        }
    }
    
     public void DeleteCommentaire(int idCommentaire) throws SQLException
    {
        String req="DELETE FROM Commentaire WHERE idCommentaire = ?";
         try{
        PreparedStatement ste = con.prepareStatement(req);
        ste.setInt(1,idCommentaire);
         System.out.println("SUCCES");
        ste.executeUpdate();
         }
         catch (SQLException ex)
         {            Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);}
    }

    
     
    
}
