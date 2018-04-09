/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author didid
 */
public class dataSource {
    
    private static dataSource instance;
    private String url="jdbc:mysql://localhost:3306/mysoulmate";
    private String login="root";
    private String password="";
    private Connection con;
    
    private dataSource()
    {   try {
        con = DriverManager.getConnection(url,login,password);
        } catch (SQLException ex) {
            Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
    public Connection getConnection()
    {
           return con;
    }
    public static dataSource getInstance()
    {
        if (instance==null)
            instance =new  dataSource();
            return instance;
            
            
            
            
            }
}
