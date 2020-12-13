/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrm;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author mathias
 */
public class HRM implements IHRM{
    private ArrayList<Employee> employees;
    private ArrayList<EmployeeRequest> requests;
    
    public HRM(ArrayList<Employee> employees){
      this.employees = employees;
      this.requests = new ArrayList<>();
    }
    
    @Override
    public void addVacationRequest(String employeeID, int nrOfDays){
        for(Employee emp: this.employees){
           if(emp.getEmployeeID().equals(employeeID)){
               VacationRequest vac = new VacationRequest(nrOfDays, emp);
               this.requests.add(vac);
               return;
           }    
        }
        System.out.println("De werknemer bestaat niet!"); 
    }
    
    @Override
    public void addReimbursementRequest(String employeeID, double amount, String description){
     for(Employee emp: this.employees){
         if(emp.getEmployeeID().equals(employeeID)){
             ReimbursementRequest reim = new ReimbursementRequest(amount, description, emp);
             this.requests.add(reim);
             return;
         }
        }
      System.out.println("De werknemer bestaat niet!");
    }
    
    @Override
    public ArrayList<EmployeeRequest> getEmployeeRequests(String employeeID, Decision preProcDecision, Decision mgrDecision){
      ArrayList<EmployeeRequest> EmpReq = new ArrayList<>();
      boolean empexists = false;
      Employee requester = null;
      for(Employee emp: this.employees){
         if(emp.getEmployeeID().equals(employeeID)){
           empexists = true;
           requester = emp;
         }
             
      }
      if(empexists == false){
        System.out.println("De werknemer bestaat niet!");  
        return null;
      }
      else{
        
       for(EmployeeRequest req: this.requests){
           if(req.getManagerDecision().equals(mgrDecision)
                   && req.getPreProcessingDecision().equals(preProcDecision)
                   && req.getRequester().equals(requester)){
               EmpReq.add(req);
           }
       }
       return EmpReq;   
      } 
    }
    
    @Override
    public void preProcessRequests(){
       ArrayList<VacationRequest> UndecVacReq = new ArrayList<>();
       ArrayList<ReimbursementRequest> UndecReimReq = new ArrayList<>();
       for(EmployeeRequest req: this.requests){
           if(req.getManagerDecision() == Decision.Undecided){
               if(req instanceof VacationRequest)
                   UndecVacReq.add((VacationRequest) req);
               else
                   UndecReimReq.add((ReimbursementRequest) req);
           }
       }
       if(UndecVacReq.isEmpty() == false){
       for(VacationRequest vac: UndecVacReq){
           if(vac.getRequester().getNrOfFreeDays()
                   >= vac.getNrOfDays())
               vac.setPreProcessingDecision(Decision.Accepted);
           else
               vac.setPreProcessingDecision(Decision.NotAccepted);
       }
       }
       
       if(UndecReimReq.isEmpty() == false){
       for(ReimbursementRequest reim: UndecReimReq){
           if(reim.getRequester().getBudget()
                   >= reim.getAmount())
               reim.setPreProcessingDecision(Decision.Accepted);
           else
               reim.setPreProcessingDecision(Decision.NotAccepted);
       }
       }
    }
    
   @Override
   public void approveRequest(String requestID, String managerID){
       EmployeeRequest request = null;
       Employee manager = null;
       Employee requester = null;
       for(EmployeeRequest empreq: this.requests){
           if(empreq.getRequestID().equals(requestID)){
               request = empreq;
               requester = request.getRequester();
           }
       }
       for(Employee emp: this.employees){
           if(emp.getEmployeeID().equals(managerID)){
               manager = emp;
           }
       }
       
       if((request == null) || (manager == null) || (requester == null)){
           System.out.println("De aanvraag kan niet goedgekeurd worden!");
           return;
       }
       if(requester.getManager().equals(manager) == false){
           System.out.println("De aanvraag kan niet goedgekeurd worden!");
           return;
       }
       if(request.getPreProcessingDecision().equals(Decision.Accepted) == false){
           System.out.println("De aanvraag kan niet goedgekeurd worden!");
           return;
       }
       if(request.getManagerDecision().equals(Decision.Undecided) == false){
           System.out.println("De aanvraag kan niet goedgekeurd worden!");
           return;
       }
       
       request.setManagerDecision(Decision.Accepted);
       if(request instanceof VacationRequest){
          VacationRequest vacreq = (VacationRequest) request; 
          int freedays = requester.getNrOfFreeDays() - vacreq.getNrOfDays();
          requester.setNrOfFreeDays(freedays);
          this.preProcessRequests();
       }
       else{
          ReimbursementRequest reimreq = (ReimbursementRequest) request; 
          double bud = requester.getBudget() - reimreq.getAmount();
          requester.setBudget(bud);
          this.preProcessRequests();
       }
       
   }
   
   @Override
   public void disapproveRequest(String requestID, String managerID){
       EmployeeRequest request = null;
       Employee manager = null;
       Employee requester = null;
       for(EmployeeRequest empreq: this.requests){
           if(empreq.getRequestID().equals(requestID)){
               request = empreq;
               requester = request.getRequester();
           }
       }
       for(Employee emp: this.employees){
           if(emp.getEmployeeID().equals(managerID)){
               manager = emp;
           }
       }
       
       if((request == null) || (manager == null) || (requester == null)){
           System.out.println("De aanvraag kan niet afgekeurd worden!");
           return;
       }
       if(requester.getManager().equals(manager) == false){
           System.out.println("De aanvraag kan niet afgekeurd worden!");
           return;
       }
       if(request.getManagerDecision().equals(Decision.Undecided) == false){
           System.out.println("De aanvraag kan niet afgekeurd worden!");
           return;
       }
       
       request.setManagerDecision(Decision.NotAccepted);
       
   }
   
   @Override
   public String toString(){
       boolean geenRequest = true;
       String tekst = "";
       Employee[] employees = new Employee[this.employees.size()];
       
       for(int i = 0; i < this.employees.size(); i++){
          employees[i] = this.employees.get(i);
       }
       Arrays.sort(employees);
       
       for(Employee emp: employees){
        tekst += "\n";
        tekst += emp.toString();
        tekst += "\n" + "----------------------" + "\n";
        tekst += "Goedgekeurde aanvragen:" + "\n";
        for(EmployeeRequest req: this.requests){
            if((req.getRequester().equals(emp)) && (req.getManagerDecision().equals(Decision.Accepted))){
                tekst += req.toString() + "\n";
                geenRequest = false;
            }
        }
        if(geenRequest){
            tekst += "Geen requests beschikbaar" + "\n";
        }
        geenRequest = true;
        
        tekst+= "Geweigerde aanvragen:"+ "\n";
        for(EmployeeRequest req: this.requests){
            if((req.getRequester().equals(emp)) && (req.getManagerDecision().equals(Decision.NotAccepted))){
                tekst += req.toString() + "\n";
                geenRequest = false;
            }
        }
        if(geenRequest){
            tekst += "Geen requests beschikbaar" + "\n";
        }
        geenRequest = true;
        
        tekst += "Aanvragen die nog moeten goedgekeurd worden:" + "\n";
        for(EmployeeRequest req: this.requests){
            if((req.getRequester().equals(emp)) && (req.getManagerDecision().equals(Decision.Undecided))){
                tekst += req.toString() + "\n";
                geenRequest = false;
            }
        }
        if(geenRequest){
            tekst += "Geen requests beschikbaar" + "\n";
        }
        geenRequest = true;
       }
       
    return tekst;   
   }
}