/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Commande;
import Entities.Commercant;
import Entities.Partcipation;
import Entities.Produit;
import Entities.User;
import Services.CommandeServices;
import Services.CommercantServices;
import Services.ParticipationService;
import Services.ProduitServices;
import Services.UserServices;
import com.teknikindustries.bulksms.SMS;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author skan
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, AddressException, MessagingException {
        // TODO code application logic here
         
  /*
    int max=0; int var=0;int compteurTab=0;
    int o; int cle;
         Produit pInterm = new Produit();
         CommandeServices com=new CommandeServices();
         ProduitServices s=new ProduitServices();
  List<Commande> liste= com.selectCommandesAll();
   List<Produit> listeProduits= s.selectProduitsConfirmes(1);
  int tableauEntier[] = new int[liste.size()];
     Produit tableauProduits[] = new Produit[liste.size()];
 for(int i=0;i<listeProduits.size();i++){
     tableauProduits[compteurTab]=listeProduits.get(i);
      for(int j=0;j<liste.size();j++){
         
    if (liste.get(j).getIdProduit()==listeProduits.get(i).getIdProduit())
        var=var+liste.get(j).getQuantite();
   
    }
       tableauEntier[compteurTab]=var;
     compteurTab++;
     var=0;
     }
  
for (int i = 1; i < tableauEntier.length; i++) {
			cle = tableauEntier[i];
			int j = i;
			while ((j >= 1) && (tableauEntier[j - 1] > cle)) {
				tableauEntier[j]  = tableauEntier[j - 1] ;
				j = j - 1;
			}
			tableauEntier[j] = cle;
		}
	


 for(int m=0;m<tableauEntier.length;m++){System.out.println(tableauEntier[m]);}
 for(int h=0;h<tableauProduits.length;h++){System.out.println(tableauProduits[h]);} 
  
  
  */
  CommercantServices cs=new CommercantServices();
  UserServices us=new UserServices();

 Commercant monta=cs.selectCommercantForLogin("skan", "1111");
 System.out.println(monta.getNom());
 
 User u=us.selectUsersAllForLogin("mondher", "mondher");
 System.out.println(u.getId());
 
}
    
    
    
    
}
