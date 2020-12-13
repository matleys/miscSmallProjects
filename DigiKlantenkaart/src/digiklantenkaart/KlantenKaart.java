/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digiklantenkaart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author mathias
 */
public class KlantenKaart implements Serializable {
    private static final long serialVersionUID = 552;
    private String klantID, voornaam, naam, email;
    private ArrayList<Transactie> transacties;
    private HashMap<Winkel, Integer> verzameldePuntenPerWinkel;
    
    public KlantenKaart(String klantID, String voornaam, String naam, String email){
       this.naam = naam;
       this.klantID = klantID;
       this.voornaam = voornaam;
       this.email = email;
       this.transacties = new ArrayList<>();
       this.verzameldePuntenPerWinkel = new HashMap<>();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getKlantID() {
        return klantID;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getNaam() {
        return naam;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Transactie> getTransacties() {
        return transacties;
    }

    public HashMap<Winkel, Integer> getVerzameldePuntenPerWinkel() {
        return verzameldePuntenPerWinkel;
    }
    
    public void toevoegenTransactie(Winkel winkel, double bedrag, int punten){
        Transactie trans = new Transactie(winkel.getWinkelID(), bedrag, punten);
        this.transacties.add(trans);
        try{
        int tot = this.verzameldePuntenPerWinkel.get(winkel) + punten;
        this.verzameldePuntenPerWinkel.put(winkel, tot);
        }
        catch(NullPointerException e){
            this.verzameldePuntenPerWinkel.put(winkel, punten);
        }
    }
    
    @Override
    public int hashCode(){
        return this.voornaam.hashCode() + this.naam.hashCode()+ this.email.hashCode()+this.klantID.hashCode()+this.transacties.hashCode()+this.verzameldePuntenPerWinkel.hashCode();
    }
    
    @Override
    public boolean equals(Object object){
        try{
            KlantenKaart kl = (KlantenKaart) object;
            return kl.hashCode() == this.hashCode();
        }
        catch(Exception e){
            return false;
        }
    }
    
    @Override
    public String toString(){
        int totaal = 0;
        for(Integer i: this.verzameldePuntenPerWinkel.values())
            totaal += i;
        return "Klantenkaart van "+this.voornaam+" "+this.naam+" ("+this.klantID+"): "+this.transacties.size()+" transactie(s) in "+this.verzameldePuntenPerWinkel.size()+ " winkel(s) en een totaal van "+totaal+" beschikbare punten" ;
    }
    
}
