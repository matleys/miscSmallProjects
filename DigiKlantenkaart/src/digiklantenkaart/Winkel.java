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
public class Winkel implements Serializable{
    private static final long serialVersionUID = 9674;
    private String winkelID, naam, beloningTekst;
    private double aantalPuntenPerEuro;
            
    public Winkel(String winkelID, String naam, double aantalPuntenPerEuro, String beloningTekst){
        this.winkelID = winkelID;
        this.naam = naam;
        this.beloningTekst = beloningTekst;
        this.aantalPuntenPerEuro = aantalPuntenPerEuro;
    }       

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getWinkelID() {
        return winkelID;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeloningTekst() {
        return beloningTekst;
    }

    public double getAantalPuntenPerEuro() {
        return aantalPuntenPerEuro;
    }
    
    @Override
    public int hashCode(){
        return this.winkelID.hashCode();
    }
    
    @Override
    public boolean equals(Object object){
        try{
           if(object instanceof Winkel == false)
               return false;
           Winkel winkel = (Winkel) object;
           if(this.aantalPuntenPerEuro != winkel.getAantalPuntenPerEuro())
               return false;
           if(this.beloningTekst.equals(winkel.getBeloningTekst()) == false)
               return false;
           if(this.naam.equals(winkel.getNaam()) == false)
               return false;
           if(this.winkelID.equals(winkel.getWinkelID()) == false)
               return false;
           return true;
        }
        catch(NullPointerException e){
            return false;
        }
    }
    
    @Override
    public String toString(){
        return this.naam +" ("+this.winkelID +") geeft "+this.aantalPuntenPerEuro+" punten per euro";
    }
    
}
