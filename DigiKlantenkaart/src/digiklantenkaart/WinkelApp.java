/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digiklantenkaart;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 *
 * @author mathias
 */
public class WinkelApp implements IWinkelApp {
    private HashMap<String,KlantenKaart> klanten;
    private Winkel winkel;
    private KlantenKaart aangemeldeKlant;
    
    public WinkelApp(String klantenBestand, String winkelBestand) throws KlantenKaartException{
        this.klanten = new HashMap<>();
        try{
        this.laadKlanten(klantenBestand);
        }catch(Exception e){
        throw new KlantenKaartException("Fout bij lezen van bestand" + klantenBestand); 
        }
        try{
            this.laadWinkel(winkelBestand);
        }catch(Exception e){
            throw new KlantenKaartException("Fout bij lezen van bestand " + winkelBestand);
        }
    }

    public WinkelApp() {
        this.klanten = new HashMap<>();
    }
    
    @Override
    public int laadKlanten(String klantenBestand) throws KlantenKaartException{
        try{
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(klantenBestand));
            HashMap<String,KlantenKaart> kl = (HashMap)inputStream.readObject();
            inputStream.close();
            this.klanten = kl;
            System.out.println("Klanten gelezen van bestand "+klantenBestand);
            return this.klanten.size();
            
        }
        catch(Exception e){
            throw new KlantenKaartException("Fout bij lezen van bestand" + klantenBestand);
        }
    }
    
    @Override
    public void laadWinkel(String winkelBestand) throws KlantenKaartException{
       try{
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(winkelBestand));
            Winkel w = (Winkel)inputStream.readObject();
            inputStream.close();
            this.winkel = w;
            System.out.println("Winkel gelezen van bestand "+winkelBestand);
            
        }
        catch(Exception e){
            throw new KlantenKaartException("Fout bij lezen van bestand " + winkelBestand);
        }
    }
    
    @Override
    public int getAantalPunten() throws KlantenKaartException{
        HashMap<Winkel,Integer> hash;
        if(this.aangemeldeKlant == null)
            throw new KlantenKaartException("Geen klant aangemeld!");
        if(this.winkel == null)
            throw new KlantenKaartException("Geen winkel ingeladen!");
        hash =this.aangemeldeKlant.getVerzameldePuntenPerWinkel();
        if(hash.get(this.winkel) != null)
            return hash.get(this.winkel);
        else
                return 0;
    }
    
    @Override
    public String aanmeldenKlant(String klantID) throws KlantenKaartException{
        String s = "";
        if(this.winkel == null)
            throw new KlantenKaartException("Geen winkel ingeladen!");
        if(this.klanten.get(klantID) == null)
            throw new KlantenKaartException("Klant '"+klantID+"' niet gevonden!");
        
        this.aangemeldeKlant = this.klanten.get(klantID);
        s += "Welkom "+this.aangemeldeKlant.getVoornaam()+" "+this.aangemeldeKlant.getNaam()+"\n Aantal punten verzameld: ";
        s += this.getAantalPunten()+"\n"+ this.winkel.getBeloningTekst();
        return s;
    }
    
    @Override
    public String registrerenTransactie(double bedrag) throws KlantenKaartException{
        if(this.aangemeldeKlant == null)
            throw new KlantenKaartException("Geen klant aangemeld!");
        if(this.winkel == null)
            throw new KlantenKaartException("Geen winkel ingeladen!");

        KlantenKaart k;
        int punten;
        k = this.aangemeldeKlant;
        punten =(int) (this.winkel.getAantalPuntenPerEuro()*bedrag);
        k.toevoegenTransactie(this.winkel, bedrag, punten);
        return k.getVoornaam()+" "+k.getNaam()+", je transactie werd geregistreerd!!!\nAantal punten verzameld: "+ k.getVerzameldePuntenPerWinkel().get(this.winkel);
    }
    
    @Override
    public String registrerenBeloning(int aantalPunten) throws KlantenKaartException{
       HashMap<Winkel,Integer> hash;
        if(this.aangemeldeKlant == null)
            throw new KlantenKaartException("Geen klant aangemeld!");
        if(this.winkel == null)
            throw new KlantenKaartException("Geen winkel ingeladen!");
       
       hash = this.aangemeldeKlant.getVerzameldePuntenPerWinkel();
       int punten;
       try{
       punten = hash.get(this.winkel);
       }catch(Exception e){
           punten = 0;
       }
       if((punten - aantalPunten) < 0)
           throw new KlantenKaartException("U heeft slechts "+punten+" punten beschikbaar!!");
       else{
           hash.put(this.winkel, (punten - aantalPunten));
           return this.aangemeldeKlant.getVoornaam()+" "+this.aangemeldeKlant.getNaam()+", je beloning werd geregistreerd!!!\nAantal punten verzameld: "+hash.get(this.winkel);
       }   
    }
    
    @Override
    public String getEmailAdressenKlanten(){
        if(this.klanten.isEmpty())
            return "";
        String s = "";
        int counter = 0;
        for(KlantenKaart k: this.klanten.values()){
           if(k.getVerzameldePuntenPerWinkel().get(this.winkel) != null && k.getVerzameldePuntenPerWinkel().get(this.winkel) != 0){
               if(counter == 0){
                    s+=k.getEmail();
                    counter++;
               }
               else
                   s +=";"+k.getEmail();
           } 
        }
        return s;
    }
    
    @Override
    public int bewaarKlanten(String klantenBestand) throws KlantenKaartException{
        if(this.klanten == null)
            throw new KlantenKaartException("Fout bij schrijven naar bestand "+ klantenBestand);
        try{
            int aantal = this.klanten.size();
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(klantenBestand));
            outputStream.writeObject(this.klanten);
            outputStream.close();
            return aantal;
        }catch(Exception e){
            throw new KlantenKaartException("Fout bij schrijven naar bestand "+ klantenBestand);
        }
    }
    
    @Override
    public void bewaarWinkel(String winkelBestand) throws KlantenKaartException{
        if(this.winkel == null)
            throw new KlantenKaartException("Geen winkel ingeladen!");
        try{
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(winkelBestand));
        outputStream.writeObject(this.winkel);
        outputStream.close();
        System.out.println("Winkel weggeschreven naar bestand "+winkelBestand);
        }catch(Exception e){
            throw new KlantenKaartException("Fout bij schrijven naar bestand "+winkelBestand);
        }
    }
    
    @Override
    public String printOverzichtGespaardePunten(String klantID) throws KlantenKaartException{
        String s = "";
        int counter = 0;
        KlantenKaart k;
        if(this.klanten.get(klantID) == null)
            throw new KlantenKaartException("Klant '"+klantID+"' niet gevonden!");
        
        k = this.klanten.get(klantID);
        s += "Overzicht voor "+ k.getVoornaam()+" "+k.getNaam()+"\n-------------\n";
        HashMap<Winkel,Integer> hash = k.getVerzameldePuntenPerWinkel();
        for(Winkel w: hash.keySet()){
            if(hash.get(w) != 0){
                s +="\n"+w.getNaam()+": "+hash.get(w)+" punten";
                counter++;
            }
        }
        if(counter == 0)
            s = k.getVoornaam()+" "+k.getNaam()+" heeft nog geen punten verzameld";
        return s;
    }
}