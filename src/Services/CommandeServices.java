/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.dataSource;
import Entities.Commande;
import Entities.Produit;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
            Produit g=new Produit(resultat.getInt("idProduit"),resultat.getInt("prix"),resultat.getString("type"),resultat.getInt("quantite"),resultat.getString("nom"),resultat.getString("description"),resultat.getInt("idCommercant"),resultat.getString("region"),resultat.getString("image"),resultat.getInt("confirmation"));
            list.add(g);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }   
     
     
     public List<Produit> select()
    { String req="Select prix from produit ";
      List list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
           Produit p=new Produit(rs.getInt("prix"));
            list.add(p);
            }
            
            
        } catch (SQLException ex) {
           Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }
    
     public List<Commande> selectCommandeAll() 
    { 
      
        
        String req="Select commande.quantite,produit.nom,produit.prix,produit.idProduit from commande inner join produit where (commande.idProduit=produit.idProduit)";
      List list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
           Commande c=new Commande(rs.getInt("quantite"),rs.getInt("idProduit"));
           Produit p = new Produit(rs.getString("nom"),rs.getInt("prix"));
           
           list.add(p);
            list.add(c);
           
            }
            
        } catch (SQLException ex) {
           Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return list;
       
        }
       public List<Commande> selectCommande1() throws FileNotFoundException, DocumentException, BadElementException, IOException
    { 
        
        Document PDFLogReport = new Document();  
         PdfWriter.getInstance(PDFLogReport, new FileOutputStream("facture.pdf"));  
         PDFLogReport.open();      
        
         //we have two columns in our table  
         PdfPTable LogTable = new PdfPTable(3);  
         //create a cell object  
         PdfPCell table_cell;  
        
	       
                   
                  PdfPCell cell=new PdfPCell(new Paragraph("Facture"));
                  cell.setColspan(4);
                  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                  cell.setBackgroundColor(BaseColor.YELLOW);
                  LogTable.addCell(cell);
        String req="Select commande.quantite,produit.nom,produit.prix,produit.idProduit from commande inner join produit where (commande.idProduit=produit.idProduit)";
      List list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
           Commande c=new Commande(rs.getInt("quantite"),rs.getInt("idProduit"));
           Produit p = new Produit(rs.getString("nom"),rs.getInt("prix"));
           Integer total=c.getQuantite()*p.getPrix();
           
           
           list.add(p);
            list.add(c);
              
	             
                 String pr= rs.getString("prix"); 
                 table_cell=new PdfPCell(new Phrase(pr));  
                 LogTable.addCell(table_cell);  
                 String nom= rs.getString("nom"); 
                 table_cell=new PdfPCell(new Phrase(nom));  
                 LogTable.addCell(table_cell); 
                 String qte= rs.getString("quantite"); 
                 table_cell=new PdfPCell(new Phrase(qte));  
                 LogTable.addCell(table_cell); 
                  
            }
            
        } catch (SQLException ex) {
           Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        PDFLogReport.add(LogTable); 
        PDFLogReport.close();
        return list;
       
        }
     public List<Commande> selectCommandeP()
    { String req="Select idProduit,quantite from commande ";
      List list = new ArrayList<>();
        try {
            st=con.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
           Commande c=new Commande(rs.getInt("quantite"),rs.getInt("idProduit"));
            list.add(c);
            }
            
        } catch (SQLException ex) {
           Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
       
        }
     
     
     public void ajouterCommande(Commande c) throws SQLException{
     
        
String req="INSERT INTO commande(quantite,idProduit) VALUES (?,?)";
         try{
        PreparedStatement ste = con.prepareStatement(req);
        
     ste.setInt(1,c.getQuantite());
     ste.setInt(2,c.getIdProduit());
        
        ste.executeUpdate();
         }
         catch (SQLException ex)
         {            Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);

             }

                
                
                
                }
       public void UpdateCommande(Commande commande, Integer idProduit) 
    {
      
        String req = "UPDATE commande SET quantite=? where idProduit=? ";
        try {
            PreparedStatement pre = con.prepareStatement(req);
           
              pre.setInt(1,commande.getQuantite());
          pre.setInt(2,idProduit);
            int i = pre.executeUpdate();
            if (i==1) { 
                System.out.println("SUCCES");}
         
        
      
        } catch (Exception e) {
            e.printStackTrace();
             System.out.println("Erreur");
        }
    }
    
        public void DeleteCommande(int idProduit) throws SQLException
    {
        String req="DELETE FROM Commande WHERE idProduit = ?";
         try{
        PreparedStatement ste = con.prepareStatement(req);
        ste.setInt(1,idProduit);
         System.out.println("SUCCES");
        ste.executeUpdate();
         }
         catch (SQLException ex)
         {            Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);}
    }
        
        public int CalculTotal()
        {String req="Select commande.quantite,produit.prix,produit.idProduit from commande inner join produit where (commande.idProduit=produit.idProduit) ";
      int f=0;
        try {
           
            st=con.createStatement();
            rs=st.executeQuery(req);
            System.out.println("SUCCES");
        while(rs.next())
            {
          Commande c=new Commande(rs.getInt("quantite"),rs.getInt("idProduit"));
           Produit p = new Produit(rs.getInt("prix"));
        f=c.getQuantite()*p.getPrix();
            }
            
        } catch (SQLException ex) {
           Logger.getLogger(dataSource.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
        return f;
       
        }
     
     
     
     
}
