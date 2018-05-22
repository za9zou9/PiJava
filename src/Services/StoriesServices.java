/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.dataSource;
import Entities.Stories;
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
 * @author Semer
 */
public class StoriesServices {
    
      Connection con;
    Statement st;
    ResultSet rs;
    public StoriesServices()
    {
     con=dataSource.getInstance().getConnection();}   
    
    public List<Stories> selectStoriesAll()
    { String req="Select * from stories  ";
      List<Stories> list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
            
            while(rs.next())
            {
           Stories S =new Stories (rs.getInt("idStorie"),rs.getString("description"),rs.getString("image"),rs.getString("titre"));
            list.add(S);
            }
            
        } catch (SQLException ex) {
           Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }   
    
    public void insertStories(Stories s) throws SQLException
    {
        String req="INSERT INTO stories"+"(`description`,`image`,`titre`)"+ "VALUES (?,?,?)";
         try{
        PreparedStatement ste = con.prepareStatement(req);
       ste.setString(1,s.getDescription());
         ste.setString(2,s.getImage());
        ste.setString(3,s.getTitre());
        
        ste.executeUpdate();
         }
         catch (SQLException ex)
         {            Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);

             }

                
                
                }

    
    public void UpdateStories (String description ,int idStorie) throws SQLException
    {
        String req="UPDATE stories SET  description = ? WHERE idStorie = ?";
         try{
        PreparedStatement ste = con.prepareStatement(req);
        
        ste.setString(1,description);
        ste.setInt(2, idStorie);
        
       
        ste.executeUpdate();
         }
         catch (SQLException ex)
         {            Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);}
    }
    
    
    
    public void DeleteStories(int idStorie) throws SQLException
    {
        String req="DELETE FROM stories WHERE idStorie = ?";
         try{
        PreparedStatement ste = con.prepareStatement(req);
        ste.setInt(1,idStorie);
       
        ste.executeUpdate();
         }
         catch (SQLException ex)
         {            Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);}
    }
    
}
