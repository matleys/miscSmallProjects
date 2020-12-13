/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digiklantenkaart;

import java.io.Serializable;

/**
 *
 * @author mathias
 */
public class Transactie implements Serializable{
   private static final long serialVersionUID = 113;
   private String winkelID;
   private double bedrag;
   private int punten;
   private Date datum;
   
   public Transactie(String winkelID, double bedrag, int punten){
       this.winkelID = winkelID;
       this.bedrag = bedrag;
       this.punten = punten;
       this.datum = new Date();
   }
   
   @Override
   public int hashCode(){
       return this.winkelID.hashCode() + this.datum.hashCode() + (int) this.bedrag + this.punten;
   }
   
   @Override
   public boolean equals(Object object){
       try{
           Transactie trans = (Transactie) object;
           if(trans.bedrag != this.bedrag)
               return false;
           if(trans.datum.equals(this.datum) == false)
               return false;
           if(trans.punten != this.punten)
               return false;
           if(trans.winkelID.equals(this.winkelID) == false)
               return false;
           return true;
                       
                      
       }
       catch(Exception e){
           return false;
       }
   }
   
   @Override
   public String toString(){
       return "Aankoop op " + this.datum + " van " + this.bedrag+ " ("+this.punten+" punten) in "+ this.winkelID;
   }
   
}
