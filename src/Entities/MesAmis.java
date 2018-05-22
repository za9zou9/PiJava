/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author lenovo
 */
public class MesAmis {
    
     public int id;
  
   
    public int idMesAmis;
    
    public int idAmis ;
    
    public String pseudo ; 

    public MesAmis(int id, int idMesAmis, int idAmis, String pseudo) {
        this.id = id;
        this.idMesAmis = idMesAmis;
        this.idAmis = idAmis;
        this.pseudo = pseudo;
    }

    public MesAmis() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMesAmis() {
        return idMesAmis;
    }

    public void setIdMesAmis(int idMesAmis) {
        this.idMesAmis = idMesAmis;
    }

    public int getIdAmis() {
        return idAmis;
    }

    public void setIdAmis(int idAmis) {
        this.idAmis = idAmis;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    @Override
    public String toString() {
        return "MesAmis{" + "id=" + id + ", idMesAmis=" + idMesAmis + ", idAmis=" + idAmis + ", pseudo=" + pseudo + '}';
    }
    
    
    
    
}
