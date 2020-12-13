
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matleys
 */
public class Meeting implements Serializable{
    private String titel;
    private int duur;
    private ArrayList<LocalDateTime> tijdstippen;
    private HashMap<String,ArrayList<Voorkeur>> voorkeuren;

    public Meeting(String titel, int duur, ArrayList<LocalDateTime> tijdstippen) {
        this.titel = titel;
        this.duur = duur;
        this.tijdstippen = tijdstippen;
        this.voorkeuren = new HashMap<>();
    }
    
    public void toevoegenVoorkeur(String email, LocalDateTime tijdstip, Voorkeur voorkeur){
        try{
        boolean tijdIsOptie = false;
        for(LocalDateTime l: this.tijdstippen){
            if(l.equals(tijdstip)){
                tijdIsOptie = true;
            }
        }
        if(tijdIsOptie == false){
            throw new TijdException();
        }
        
        boolean alVoorkeur = false;
        for(String adres: this.voorkeuren.keySet()){
            if(adres.equals(email)){
                if(this.voorkeuren.get(email).isEmpty() == false)
                    alVoorkeur = true;
            }
        }
        
        if(alVoorkeur){
            for(int index = 0; index < this.tijdstippen.size();index++){
                if(this.tijdstippen.get(index).equals(tijdstip)){
                    this.voorkeuren.get(email).remove(index);
                    this.voorkeuren.get(email).add(index, voorkeur);
                }
            }
        } else{
            ArrayList<Voorkeur> v = new ArrayList<>(this.tijdstippen.size());
            for(int i = 0; i < this.tijdstippen.size(); i++){
                v.add(Voorkeur.niet_bepaald);
            }
            this.voorkeuren.put(email, v);
            for(int index = 0; index < this.tijdstippen.size();index++){
                if(this.tijdstippen.get(index).equals(tijdstip)){
                    this.voorkeuren.get(email).remove(index);
                    this.voorkeuren.get(email).add(index, voorkeur);
                }
            } 
        }
        
        }catch(TijdException ex){
            System.out.println("De datum "+tijdstip.toString()+" is geen geldige optie voor de meeting "+this.titel);
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.titel);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Meeting other = (Meeting) obj;
        if (this.duur != other.duur) {
            return false;
        }
        if (!Objects.equals(this.titel, other.titel)) {
            return false;
        }
        return true;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getDuur() {
        return duur;
    }

    public void setDuur(int duur) {
        this.duur = duur;
    }

    public ArrayList<LocalDateTime> getTijdstippen() {
        return tijdstippen;
    }

    public void setTijdstippen(ArrayList<LocalDateTime> tijdstippen) {
        this.tijdstippen = tijdstippen;
    }

    public HashMap<String, ArrayList<Voorkeur>> getVoorkeuren() {
        return voorkeuren;
    }

    public void setVoorkeuren(HashMap<String, ArrayList<Voorkeur>> voorkeuren) {
        this.voorkeuren = voorkeuren;
    }

    @Override
    public String toString() {
        String s = "Meeting voorstel:\n";
        s+=this.titel+"\n"+this.duur+"\n";
            for(String email: this.voorkeuren.keySet()){
                s+=email+": ";
                for(Voorkeur v: this.voorkeuren.get(email)){
                    s+= v+" ";
                }
                s = s.trim();
                s+="\n";
            }
        return s.trim();
    }
    
}
