/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.dataSource;
import Entities.Ligne;
import Entities.MesAmis;
import Entities.Pack;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author lenovo
 */
public class CrudPack {
    
    
    
    Connection con;
    Statement st;
    ResultSet rs;
    
    public CrudPack()
    {
     con=dataSource.getInstance().getConnection();}
    
    //final ObservableList options =FXCollections.observableArrayList();
    public void addPack( Pack p ) throws SQLException
    {
    String req ="INSERT INTO pack   (prixPack,image) values (?,?)";
    
        PreparedStatement pst = dataSource.getInstance().getConnection().prepareStatement(req);
        
        
        pst.setInt(1, p.getPrixPack());
        pst.setString(2, p.getImage());
        
        pst.executeUpdate();
    
    
    }
    
    
    public void addLigne( Ligne l ) throws SQLException
    {
    String req ="INSERT INTO ligne  (idPack,idProduit) values (?,?)";
    
        PreparedStatement pst = dataSource.getInstance().getConnection().prepareStatement(req);
        
        
        pst.setInt(1, l.getIdPack());
        pst.setInt(2, l.getIdProduit());
        
        pst.executeUpdate();
    
    
    }
    
    public List<Pack> selectPack()
    { String req="Select * from pack";
      List<Pack> list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
            Pack g=new Pack(rs.getInt("idPack"),rs.getInt("prixPack"),rs.getString("image"));
            list.add(g);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudPack.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }
    
    
    
    public List<User> UserAll(int id)
    { String req="Select * from user where user.id !=?";
      List<User> list = new ArrayList<>();
      PreparedStatement preparedStatement;
        try {
            preparedStatement = dataSource.getInstance().getConnection().prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
            User g=new User(rs.getInt("id"),rs.getString("username"),rs.getString("image"),rs.getInt("age"),rs.getString("etat"),rs.getString("sexe"));
            list.add(g);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudPack.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }
    
    
     public List<Produit> Produit(int id) throws SQLException
    { 
      String req="SELECT * FROM produit INNER JOIN ligne ON produit.idProduit = ligne.idProduit where ligne.idPack =?";
      List<Produit> liste = new ArrayList<>();
      PreparedStatement preparedStatement;
        try {
            preparedStatement = dataSource.getInstance().getConnection().prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
              
            while(resultSet.next())
            {
            Produit g=new Produit(resultSet.getString("nom"),resultSet.getInt("idProduit"));
            liste.add(g);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudPack.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return liste;
       
        }
    
        public void DeletePack(int id) throws SQLException
    {
        String req="DELETE FROM pack WHERE idPack = ?";
         try{
        PreparedStatement ste = con.prepareStatement(req);
        ste.setInt(1,id);
       
        ste.executeUpdate();
         }
         catch (SQLException ex)
         {            Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);}
    }
   
        
        
        
         public void AjouterAmis(int idAmis, int id , String pseudo ) throws SQLException
    {
    String req ="INSERT INTO mesamis   (idMesAmis,id,pseudo) values (?,?,?)";
    
        PreparedStatement pst = dataSource.getInstance().getConnection().prepareStatement(req);
        
         pst.setInt(1,idAmis);
       pst.setInt(2, id);
         pst.setString(3, pseudo);
       
        
        pst.executeUpdate();
    
    
    }
        
         
        public User RechercheId(String username) throws SQLException
    { 
      
      
      User S = null;
       String req="SELECT * FROM user where user.username = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = dataSource.getInstance().getConnection().prepareStatement(req);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                S = new User(resultSet.getInt(1) ,resultSet.getString("username") , resultSet.getString("sexe"), resultSet.getString("image") ); }
        } catch (SQLException ex) {
        }
        return S;
      
      
        } 
        
        
        
         public User RechercheU(String username) throws SQLException
    { 
      
      
      User S = null;
       String req="SELECT * FROM user where user.username = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = dataSource.getInstance().getConnection().prepareStatement(req);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                S = new User(resultSet.getInt(1) ,
                        resultSet.getString("username") , resultSet.getString("image") ); }
        } catch (SQLException ex) {
        }
        return S;
      
      
        } 
        
          public Pack RechercheIdPack(int id) throws SQLException
    { 
      
      
      Pack S = null;
       String req="SELECT * FROM pack where pack.idPack = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = dataSource.getInstance().getConnection().prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                S = new Pack(resultSet.getInt("idPack") ); }
        } catch (SQLException ex) {
        }
        return S;
      
      
        }
         
          
          
            public int Max() throws SQLException
    { 
      
      
      int max = 0 ;
       String req="SELECT MAX(pack.idPack) FROM pack ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = dataSource.getInstance().getConnection().prepareStatement(req);
          
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               
            max = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
        }
        return max;
      
      
        }
          
          
           public void AjouterMesPack(int idPack,int id ) throws SQLException
    {
    String req ="INSERT INTO mespack  (idPack,id) values (?,?)";
    
        PreparedStatement pst =dataSource.getInstance().getConnection().prepareStatement(req);
        
         pst.setInt(1,idPack);
          pst.setInt(2,id);
       
        
       
        
        pst.executeUpdate();
    
    
    }
     
           
           
            public List<MesAmis> mesAmis(int id)
    { 
        String req="Select * from mesamis where mesamis.id = ?";
      List<MesAmis> list = new ArrayList<>();
       PreparedStatement preparedStatement;
        try {
            preparedStatement = dataSource.getInstance().getConnection().prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
         MesAmis g=new MesAmis(rs.getInt("id"),rs.getInt("idMesAmis"),rs.getInt("idAmis"),rs.getString("pseudo"));
            list.add(g);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudPack.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }
           
           
           
            public void DeleteAmis(int id) throws SQLException
    {
        String req="DELETE FROM mesamis WHERE mesamis.idMesAmis =? and mesamis.id = 1";
         try{
        PreparedStatement ste = con.prepareStatement(req);
        ste.setInt(1,id);
       
        ste.executeUpdate();
         }
         catch (SQLException ex)
         {            Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);}
    }
            
          
            public List<Produit> Musique()
    { String req="Select * from produit where produit.type='musique' and produit.quantite > 0 ";
      List<Produit> list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
           Produit p=new Produit(rs.getString("nom"),rs.getInt("idProduit"),rs.getString("image"));
            list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudPack.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }
            
             public List<Produit> Fleurs()
    { String req="Select * from produit where produit.type='fleurs' and produit.quantite > 0 ";
      List<Produit> list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
            Produit p=new Produit(rs.getString("nom"),rs.getInt("idProduit"),rs.getString("image"));
            list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudPack.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }
         
             
              public List<Produit> Salle()
    { String req="Select * from produit where produit.type='salle' and produit.quantite > 0 ";
      List<Produit> list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
            Produit p=new Produit(rs.getString("nom"),rs.getInt("idProduit"),rs.getString("image"));
            list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudPack.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }
         
              
              
      public void ajouterPoint( int point,int id ) throws SQLException
    {
    String req ="update user SET point =? where user.id =?";
    
        PreparedStatement pst = dataSource.getInstance().getConnection().prepareStatement(req);
        
        
        pst.setInt(1, point);
        pst.setInt(2, id);
        
        pst.executeUpdate();
    
    
    }         
              
           
}
