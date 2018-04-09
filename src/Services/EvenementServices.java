/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.dataSource;
import Entities.Evenement;
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
public class EvenementServices {
    
      Connection con;
    Statement st;
    ResultSet rs;
    
    public EvenementServices()
    {
     con=dataSource.getInstance().getConnection();}
    
    public void insertEvenement(Evenement e) throws SQLException
    {
        String req="INSERT INTO `evenement`"+"(`date`,`lieu`,`description`,`image`)"+ "VALUES (?,?,?,?)";
         try{
        PreparedStatement ste = con.prepareStatement(req);
        ste.setDate(1, (Date) e.getDate());
        ste.setString(2,e.getLieu());
        ste.setString(3,e.getDescription());
        ste.setString(4,e.getImage());
       
        ste.executeUpdate();
         }
         catch (SQLException ex)
         {            Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);}
    }


    public void UpdateEvenement(int id,Date date,String lieu,String description,String image) throws SQLException
    {
        String req="UPDATE evenement SET date = ?, lieu = ?, description = ?, image = ? WHERE idEvenement = ?";
         try{
        PreparedStatement ste = con.prepareStatement(req);
        ste.setDate(1,date);
        ste.setString(2,lieu);
        ste.setString(3,description);
        ste.setString(4,image);
        ste.setInt(5,id);
        ste.executeUpdate();
         }
         catch (SQLException ex)
         {            Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);}
    }
    
    
   public void DeleteEvenement(int id) throws SQLException
    {
        String req="DELETE FROM evenement WHERE idEvenement = ?";
         try{
        PreparedStatement ste = con.prepareStatement(req);
        ste.setInt(1,id);
       
        ste.executeUpdate();
         }
         catch (SQLException ex)
         {            Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);}
    }

    
    
    
    
    
    

  public List<Evenement> selectEvenemntAvenir()
    { String req="Select * from evenement where date>CURDATE()";
      List<Evenement> list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
            Evenement g=new Evenement(rs.getInt("idEvenement"),rs.getDate("date"),rs.getString("lieu"),rs.getString("description"),rs.getString("image"));
            list.add(g);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }   

  
   public List<Evenement> selectEvenemntPasses()
    { String req="Select * from evenement where date<CURDATE()";
      List<Evenement> list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
             Evenement g=new Evenement(rs.getInt("idEvenement"),rs.getDate("date"),rs.getString("lieu"),rs.getString("description"),rs.getString("image"));
            list.add(g);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }   

   public List<Evenement> selectEvenemntAll()
    { String req="Select * from evenement";
      List<Evenement> list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
           Evenement g=new Evenement(rs.getInt("idEvenement"),rs.getDate("date"),rs.getString("lieu"),rs.getString("description"),rs.getString("image"));
            list.add(g);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }   








}
