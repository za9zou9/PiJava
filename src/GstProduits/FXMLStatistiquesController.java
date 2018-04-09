/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GstProduits;

import Entities.Commande;
import Entities.Produit;
import EventsUsers.FXMListeDesEventsController;
import Services.CommandeServices;
import Services.ProduitServices;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author skan
 */
public class FXMLStatistiquesController implements Initializable {
 CommandeServices cs=new CommandeServices();
ProduitServices PrS=new ProduitServices();
 Produit pss=new Produit();
 Produit pss2=new Produit();
 Produit pss3=new Produit();
 Produit pss4=new Produit();
    @FXML
    private BarChart<Produit,Number> chart;
     final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        
    @FXML
    private BarChart<Produit, Number> chart2;
 final CategoryAxis xAxis2 = new CategoryAxis();
        final NumberAxis yAxis2 = new NumberAxis();
    @FXML
    private FontAwesomeIconView retour;
    @FXML
    private AnchorPane ancho;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       
        
     pss=StatQteMaxProduit();  
      int x=StatQteMaxQte();   
      
      pss3=StatQteMinProduit();
       int x3=StatQteMinQte();
        
 chart.setTitle("Les quantités commandées ");
        xAxis.setLabel("Produit");       
        yAxis.setLabel("Valeur");   
        
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Max");       
        series1.getData().add(new XYChart.Data(pss.getNom(), x));
        
         XYChart.Series series3 = new XYChart.Series();
        series3.setName("Min");       
        series3.getData().add(new XYChart.Data(pss3.getNom(), x3));
        
   chart.getData().addAll(series1,series3);
   
   
  //float x2=StatMaxVenduGain();
   pss2=StatMaxVenduProduit();
   float x2=StatMaxVenduGain();
   
   pss4=StatGainMinProduit();
   float x4=StatGainMinGain();

    chart2.setTitle("Les gains récoltés ");
        xAxis2.setLabel("Produit");       
        yAxis2.setLabel("Valeur");   
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Max");       
        series2.getData().add(new XYChart.Data(pss2.getNom(), x2));
        
        XYChart.Series series4 = new XYChart.Series();
        series4.setName("Min");       
        series4.getData().add(new XYChart.Data(pss4.getNom(), x4));
        
        
   chart2.getData().addAll(series2,series4);
    
   
    
    
    
    }    
    
    
    
    // LES FONCTIONS MAX
    
    public Produit StatQteMaxProduit(){
    int max=0; int var=0;
         Produit pInterm = new Produit();
         CommandeServices com=new CommandeServices();
         ProduitServices s=new ProduitServices();
  List<Commande> liste= com.selectCommandesAll();
   List<Produit> listeProduits= s.selectProduitsConfirmes(1);
  
 for(int i=0;i<listeProduits.size();i++){
     
      for(int j=0;j<liste.size();j++){
         
    if (liste.get(j).getIdProduit()==listeProduits.get(i).getIdProduit())
        var=var+liste.get(j).getQuantite();
   
    }
     if (var>max) {max=var; pInterm=listeProduits.get(i); }  
     var=0;
     }
        return pInterm;
    }
    
    
    public int StatQteMaxQte(){
    int max=0; int var=0;
         Produit pInterm = new Produit();
         CommandeServices com=new CommandeServices();
         ProduitServices s=new ProduitServices();
  List<Commande> liste= com.selectCommandesAll();
   List<Produit> listeProduits= s.selectProduitsConfirmes(1);
  
 for(int i=0;i<listeProduits.size();i++){
     
      for(int j=0;j<liste.size();j++){
         
    if (liste.get(j).getIdProduit()==listeProduits.get(i).getIdProduit())
        var=var+liste.get(j).getQuantite();
   
    }
     if (var>max) {max=var; pInterm=listeProduits.get(i); }  
     var=0;
     }
        return max;
    }
    
    
    public Produit StatMaxVenduProduit(){
     int max=0; int var=0;
         Produit pInterm = new Produit();
         CommandeServices com=new CommandeServices();
         ProduitServices s=new ProduitServices();
  List<Commande> liste= com.selectCommandesAll();
   List<Produit> listeProduits= s.selectProduitsConfirmes(1);
  
 for(int i=0;i<listeProduits.size();i++){
     
      for(int j=0;j<liste.size();j++){
         
    if (liste.get(j).getIdProduit()==listeProduits.get(i).getIdProduit())
        var=var+(liste.get(j).getQuantite()*listeProduits.get(i).getPrix());
   
    }
     if (var>max) {max=var; pInterm=listeProduits.get(i); }  
     var=0;
     }
 return pInterm;
}
    
    
    public Float StatMaxVenduGain(){
     float max2=0; int var2=0;
         Produit pInterm2 = new Produit();
         CommandeServices com2=new CommandeServices();
         ProduitServices s2=new ProduitServices();
  List<Commande> liste2= com2.selectCommandesAll();
   List<Produit> listeProduits2= s2.selectProduitsConfirmes(1);
  
 for(int i=0;i<listeProduits2.size();i++){
     
      for(int j=0;j<liste2.size();j++){
         
    if (liste2.get(j).getIdProduit()==listeProduits2.get(i).getIdProduit())
        var2=var2+(liste2.get(j).getQuantite()*listeProduits2.get(i).getPrix());
   
    }
     if (var2>max2) {max2=var2; pInterm2=listeProduits2.get(i); }  
     var2=0;
     }
 return max2;
}
    
    
    // LES FONCTIONS MIN
    
    
    
    
    
    
    
    
    public int StatQteMinQte(){
    TreeMap<Integer,Integer> treemap = new TreeMap<Integer, Integer>();
    int min=0; int theId=0;
   Produit pInterm = new Produit();
         CommandeServices com=new CommandeServices();
         ProduitServices s=new ProduitServices();
  List<Commande> liste= com.selectCommandesAll();
   List<Produit> listeProduits= s.selectProduitsConfirmes(1);
  
 for(int i=0;i<listeProduits.size();i++){treemap.put(listeProduits.get(i).getIdProduit(), 0);}
 
 
 
 for(int j=0;j<liste.size();j++) {
    
 for (Map.Entry<Integer, Integer> entree : treemap.entrySet()) {
	if (liste.get(j).getIdProduit()==entree.getKey())
       entree.setValue(entree.getValue()+liste.get(j).getQuantite());
		}
     
}
 theId=treemap.firstKey();
 min=treemap.get(theId);
 for (Map.Entry<Integer, Integer> entree : treemap.entrySet()) {
	
    if (min>entree.getValue()) {min=entree.getValue();theId=entree.getKey();}
		}
 
 return min;
 
    }
    
    
    public Produit StatQteMinProduit(){
     TreeMap<Integer,Integer> treemap = new TreeMap<Integer, Integer>();
    int min=0; int theId=0;
   Produit pInterm = new Produit();
         CommandeServices com=new CommandeServices();
         ProduitServices s=new ProduitServices();
  List<Commande> liste= com.selectCommandesAll();
   List<Produit> listeProduits= s.selectProduitsConfirmes(1);
  
 for(int i=0;i<listeProduits.size();i++){treemap.put(listeProduits.get(i).getIdProduit(), 0);}
 
 
 
 for(int j=0;j<liste.size();j++) {
    
 for (Map.Entry<Integer, Integer> entree : treemap.entrySet()) {
	if (liste.get(j).getIdProduit()==entree.getKey())
       entree.setValue(entree.getValue()+liste.get(j).getQuantite());
		}
     
}
 theId=treemap.firstKey();
 min=treemap.get(theId);
 for (Map.Entry<Integer, Integer> entree : treemap.entrySet()) {
	
    if (min>entree.getValue()) {min=entree.getValue();theId=entree.getKey();}
		}
 
 List<Produit> lalista=s.selectProduitsByIDP(theId);
 pInterm=lalista.get(0);
 return pInterm;
 
    }
    
    
    
    public Produit StatGainMinProduit(){

 TreeMap<Integer,Integer> treemap = new TreeMap<Integer, Integer>();
 
    int min=0; int theId=0;
   Produit pInterm = new Produit();
         CommandeServices com=new CommandeServices();
         ProduitServices s=new ProduitServices();
  List<Commande> liste= com.selectCommandesAll();
   List<Produit> listeProduits= s.selectProduitsConfirmes(1);
  
 for(int i=0;i<listeProduits.size();i++){treemap.put(listeProduits.get(i).getIdProduit(), 0);}
 
 
 
 for(int j=0;j<liste.size();j++) {
    
 for (Map.Entry<Integer, Integer> entree : treemap.entrySet()) {
	if (liste.get(j).getIdProduit()==entree.getKey())
       entree.setValue(entree.getValue()+liste.get(j).getQuantite());
		}
     
}
 
 for (Map.Entry<Integer, Integer> entree : treemap.entrySet()) {
	List<Produit> lalista=s.selectProduitsByIDP(entree.getKey());
   entree.setValue(entree.getValue()*lalista.get(0).getPrix());
		}
 
theId=treemap.firstKey();
 min=treemap.get(theId);
 for (Map.Entry<Integer, Integer> entree : treemap.entrySet()) {
	
    if (min>entree.getValue()) {min=entree.getValue();theId=entree.getKey();}
		}
 
 List<Produit> lalista=s.selectProduitsByIDP(theId);
 pInterm=lalista.get(0);
 return pInterm;
}
    
    
    
    public float StatGainMinGain(){

 TreeMap<Integer,Integer> treemap = new TreeMap<Integer, Integer>();
 
    float min=0; int theId=0;
   Produit pInterm = new Produit();
         CommandeServices com=new CommandeServices();
         ProduitServices s=new ProduitServices();
  List<Commande> liste= com.selectCommandesAll();
   List<Produit> listeProduits= s.selectProduitsConfirmes(1);
  
 for(int i=0;i<listeProduits.size();i++){treemap.put(listeProduits.get(i).getIdProduit(), 0);}
 
 
 
 for(int j=0;j<liste.size();j++) {
    
 for (Map.Entry<Integer, Integer> entree : treemap.entrySet()) {
	if (liste.get(j).getIdProduit()==entree.getKey())
       entree.setValue(entree.getValue()+liste.get(j).getQuantite());
		}
     
}
 
 for (Map.Entry<Integer, Integer> entree : treemap.entrySet()) {
	List<Produit> lalista=s.selectProduitsByIDP(entree.getKey());
   entree.setValue(entree.getValue()*lalista.get(0).getPrix());
		}
 
theId=treemap.firstKey();
 min=treemap.get(theId);
 for (Map.Entry<Integer, Integer> entree : treemap.entrySet()) {
	
    if (min>entree.getValue()) {min=entree.getValue();theId=entree.getKey();}
		}
 
 
 return min;
}
    
    
    
    
    
    
    
    

    @FXML
    private void retourner(MouseEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGerer.fxml"));
        try {
            Parent root;
            root = loader.load();
            FXMLGererController interf = loader.getController();
            Scene scene =ancho.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLGererController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               }
           
    
    
    
}
