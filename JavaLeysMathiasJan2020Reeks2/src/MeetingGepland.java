
import java.time.LocalDateTime;
import java.util.HashSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matleys
 */
public class MeetingGepland extends Meeting {
    private LocalDateTime geplandTijdstip;
    private HashSet<String> deelnemers;
    
    public MeetingGepland(LocalDateTime tijdstip, Meeting meeting){
        super(meeting.getTitel(),meeting.getDuur(),meeting.getTijdstippen());
        this.deelnemers = new HashSet<>();
        boolean tijdstipOk = meeting.getTijdstippen().contains(tijdstip);
        if(tijdstipOk){
            this.geplandTijdstip = tijdstip;
        int indexTijdstip = 0;
        for(int i = 0; i < meeting.getTijdstippen().size();i++){
            if(meeting.getTijdstippen().get(i).equals(this.geplandTijdstip))
                indexTijdstip = i;
        }
        for(String email: meeting.getVoorkeuren().keySet()){
            if(meeting.getVoorkeuren().get(email).get(indexTijdstip).equals(Voorkeur.beschikbaar)) 
                this.deelnemers.add(email);
        } 
        }
    }

    public LocalDateTime getGeplandTijdstip() {
        return geplandTijdstip;
    }

    public void setGeplandTijdstip(LocalDateTime geplandTijdstip) {
        this.geplandTijdstip = geplandTijdstip;
    }

    public HashSet<String> getDeelnemers() {
        return deelnemers;
    }

    public void setDeelnemers(HashSet<String> deelnemers) {
        this.deelnemers = deelnemers;
    }
    
    @Override
    public String toString(){
        String s = "Geplande Meeting:\n";
        s+=this.geplandTijdstip+"\n";
        s+=this.getDuur()+"\n";
        s+="Deelnemers: ";
        for(String email: this.deelnemers){
            s+=email+" ";
        } 
        return s.trim();
    }
    
}