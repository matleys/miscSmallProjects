
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
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
public class MeetingPlanner {
    private HashSet<Meeting> meetings;

    public MeetingPlanner() {
        this.meetings = new HashSet<>();
    }
    
    public void toevoegenMeeting(String titel, int duur, ArrayList<LocalDateTime> tijdstippen){
        this.meetings.add(new Meeting(titel,duur,tijdstippen));
    }
    
    public void verwijderenMeeting(String titel){
        Meeting meet = null;
        boolean bevatMeeting = false;
        for(Meeting m: this.meetings){
            if(m.getTitel().equals(titel)){
                bevatMeeting = true;
                meet = m;
            }
        }
        if(bevatMeeting){
            this.meetings.remove(meet);
        } else{
            System.out.println("De meeting "+titel+" bestaat niet");
        }
    }
    
    public void toevoegenVoorkeur(String titel, String email, LocalDateTime tijd, Voorkeur voorkeur){
        Meeting meet = null;
        boolean bevatMeeting = false;
        for(Meeting m: this.meetings){
            if(m.getTitel().equals(titel)){
                bevatMeeting = true;
                meet = m;
            }
        }
        if(bevatMeeting){
            meet.toevoegenVoorkeur(email, tijd, voorkeur);
        } else{
            System.out.println("De meeting "+titel+" bestaat niet");
        }
    }
    
    public void planMeeting(String titel, LocalDateTime tijd){
        Meeting meet = null;
        boolean bevatMeeting = false;
        for(Meeting m: this.meetings){
            if(m.getTitel().equals(titel)){
                bevatMeeting = true;
                meet = m;
            }
        }
        if(bevatMeeting){
            MeetingGepland nieuw = new MeetingGepland(tijd, meet);
            this.meetings.remove(meet);
            this.meetings.add(nieuw);
        } else{
            System.out.println("De meeting "+titel+" bestaat niet");
        }
    }
    
    public boolean nagaanConflicten(String email, LocalDateTime tijd, int duur){
        boolean conflict = false;
        for(Meeting m: this.meetings){
            if(m instanceof MeetingGepland){
                if(((MeetingGepland) m).getDeelnemers().contains(email)){
                    
                    if(((MeetingGepland) m).getGeplandTijdstip().isBefore(tijd)){
                        if(((MeetingGepland) m).getGeplandTijdstip().plusMinutes(m.getDuur()).isAfter(tijd))
                            conflict = true;
                } 
                    else if((((MeetingGepland) m).getGeplandTijdstip().isEqual(tijd))){
                        conflict = true;
                } 
                    else if(((MeetingGepland) m).getGeplandTijdstip().isAfter(tijd)){
                        if(((MeetingGepland) m).getGeplandTijdstip().isBefore(tijd.plusMinutes(duur)))
                            conflict = true;
                }
                
                }
            }
        }
        return conflict;
    }
    
    public void leesMeeting(File file){
        try{
           ObjectInputStream in = new ObjectInputStream(new FileInputStream(file.getName()));
           Meeting m = (Meeting) in.readObject();
           this.meetings.add(m);
           in.close();
        }catch(FileNotFoundException ex){
            System.out.println("Het bestand '"+file.getName()+"' kan niet gevonden worden!");
        }catch(Exception ex){
            System.out.println("Het bestand '"+file.getName()+"' is niet correct!");
        }
    }
    
    public void bewaarMeeting(File file, String titel){
        Meeting meet = null;
        boolean bevatMeeting = false;
        for(Meeting m: this.meetings){
            if(m.getTitel().equals(titel)){
                bevatMeeting = true;
                meet = m;
            }
        }
        if(bevatMeeting){
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(new FileOutputStream(file.getName()));
                out.writeObject(meet);
                out.close();
            } catch (Exception ex) {
            }
            
        } else{
            System.out.println("De meeting "+titel+" bestaat niet");
        }
    }
    
    public String printMeeting(String titel){
       Meeting meet = null;
        boolean bevatMeeting = false;
        for(Meeting m: this.meetings){
            if(m.getTitel().equals(titel)){
                bevatMeeting = true;
                meet = m;
            }
        }
        if(bevatMeeting){
            return meet.toString();
        } else{
            return "De meeting "+titel+" bestaat niet";
        } 
    }
    
    public static void main(String[] args){
        
        //aanmaken meetingplanner
        MeetingPlanner plan = new MeetingPlanner();
        String email1 = "bart@hotmail.com";
        String email2 = "jan@hotmail.com";
        ArrayList<LocalDateTime> tijden = new ArrayList<>(4);
        LocalDate datum = LocalDate.of(2020, Month.MARCH, 5);
        LocalTime time1 = LocalTime.of(16, 0);
        LocalTime time2 = LocalTime.of(16, 30);
        LocalTime time3 = LocalTime.of(17, 0);
        LocalTime time4 = LocalTime.of(17, 30);
        
        
        LocalDateTime tijd1 = LocalDateTime.of(datum, time1);
        LocalDateTime tijd2 = LocalDateTime.of(datum, time2);
        LocalDateTime tijd3 = LocalDateTime.of(datum, time3);
        LocalDateTime tijd4 = LocalDateTime.of(datum, time4);
        
        tijden.add(tijd1);
        tijden.add(tijd2);
        tijden.add(tijd3);
        tijden.add(tijd4);
        
        String titel = "Stuurgroep Activerend Onderwijs";
        
        //aanmaken meeting
        Meeting meeting = new Meeting(titel,90,tijden);
        plan.toevoegenMeeting(titel, 90, tijden);
        
        //toevoegen voorkeuren
        plan.toevoegenVoorkeur(titel, email2, tijd4, Voorkeur.beschikbaar);
        meeting.toevoegenVoorkeur(email2, tijd4, Voorkeur.beschikbaar);
        
        plan.toevoegenVoorkeur(titel, email1, tijd2, Voorkeur.niet_beschikbaar);
        meeting.toevoegenVoorkeur(email1, tijd2, Voorkeur.niet_beschikbaar);
        
        plan.toevoegenVoorkeur(titel, email1, tijd1, Voorkeur.beschikbaar);
        meeting.toevoegenVoorkeur(email1, tijd1, Voorkeur.beschikbaar);
        
        //meeting printen naar console
        System.out.println(meeting.toString());
        
        //meeting inplannen
        MeetingGepland geplandeMeeting= new MeetingGepland(tijd1,meeting);
        plan.planMeeting(titel, tijd1);
        
        //meeting wegschrijven
        File file = new File("Meeting Stuurgroep Activerend Onderwijs");
        plan.bewaarMeeting(file, titel);
        
        
    }
    
}
