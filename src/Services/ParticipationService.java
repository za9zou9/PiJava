/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.dataSource;
import Entities.Commercant;
import Entities.Evenement;
import Entities.Partcipation;
import Entities.Produit;
import Entities.User;
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
public class ParticipationService {
    
     Connection con;
    Statement st;
    ResultSet rs;
    

 public ParticipationService()
    {
     con=dataSource.getInstance().getConnection();}
    


public void insertParticipation(int u,int e) throws SQLException
    { 
   String req="INSERT INTO `partcipation`"+"(`id`, `idEvenement`)"+ "VALUES (?,?)";
         try{
        PreparedStatement ste = con.prepareStatement(req);
        ste.setInt(1,u);
        ste.setInt(2,e);
         ste.executeUpdate();
         }
         catch (SQLException ex)
         {            Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex); }}
 
 





public List<Partcipation> selectParticipants(int idE)
    { String req="Select * from partcipation where idEvenement='" + idE + "'";
      List<Partcipation> list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
            Partcipation g=new Partcipation(rs.getInt("reponse"),rs.getInt("id"),rs.getInt("idEvenement"));
            list.add(g);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
         return list;
       
        }   
    


public void DeleteParticipation(int id) throws SQLException
    {
        String req="DELETE FROM partcipation WHERE reponse = ?";
         try{
        PreparedStatement ste = con.prepareStatement(req);
        ste.setInt(1,id);
       
        ste.executeUpdate();
         }
         catch (SQLException ex)
         {            Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);}
    }









}