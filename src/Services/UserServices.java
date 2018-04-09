/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.dataSource;
import Entities.Evenement;
import Entities.User;
import java.sql.Connection;
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
public class UserServices {
    
    Connection con;
    Statement st;
    ResultSet rs;
    
    public UserServices()
    {
     con=dataSource.getInstance().getConnection();}
    
    public List<User> selectUsersAll()
    { String req="Select * from user";
      List<User> list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
           User u=new User(rs.getString("email"),rs.getString("etat"));
           list.add(u);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }   

    
    
     public List<User> selectUserById(List<Integer> lst)
    {  List<User> list = new ArrayList<User>();
        for(int i=0;i<lst.size();i++){
        String req="Select * from user where id='" + lst.get(i) + "'";
     
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
           
            while(rs.next())
            {
           User u=new User(rs.getString("nom"),rs.getString("prenom"),rs.getString("username"),rs.getDate("dateDeNaissance"),rs.getString("email"),rs.getString("tel"),rs.getString("etat"),rs.getString("sexe"),rs.getInt("age"));
           list.add(u);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        return list;
       
        }   

    
}
