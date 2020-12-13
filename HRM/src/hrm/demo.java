/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrm;

import java.util.ArrayList;

/**
 *
 * @author mathias
 */
public class demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>(4);
        Employee fredjen = new Employee("fg68034", null, "Frederik", "Gailly", "male", "zee ");
        Employee gigi = new Employee("gp10523", null, "Geert", "Poels", "male", " zdz");
        Employee jan = new Employee("jc45291", gigi, "Jan", "Claes", "male", "ejr");
        Employee steve = new Employee("sm75328", fredjen, "Steven", "Mertens", "male", "zhjef");
        
        employees.add(fredjen);
        employees.add(gigi);
        employees.add(jan);
        employees.add(steve);
        
        HRM hrm = new HRM(employees);
        jan.setNrOfFreeDays(1000);
        hrm.addVacationRequest(jan.getEmployeeID(), 1);
        hrm.preProcessRequests();
        hrm.disapproveRequest(hrm.getEmployeeRequests(jan.getEmployeeID(), Decision.Accepted, Decision.Undecided).get(0).getRequestID(), jan.getManager().getEmployeeID());
        hrm.addVacationRequest(jan.getEmployeeID(), 1);
        hrm.preProcessRequests();
        hrm.approveRequest(hrm.getEmployeeRequests(jan.getEmployeeID(), Decision.Accepted, Decision.Undecided).get(0).getRequestID(), jan.getManager().getEmployeeID());
        hrm.addVacationRequest(jan.getEmployeeID(), 1);
        hrm.preProcessRequests();
        hrm.disapproveRequest(hrm.getEmployeeRequests(jan.getEmployeeID(), Decision.Accepted, Decision.Undecided).get(0).getRequestID(), jan.getManager().getEmployeeID());
        
        steve.setNrOfFreeDays(10000);
        
        hrm.addVacationRequest(steve.getEmployeeID(), 1);
        hrm.preProcessRequests();
        hrm.disapproveRequest(hrm.getEmployeeRequests(steve.getEmployeeID(), Decision.Accepted, Decision.Undecided).get(0).getRequestID(), steve.getManager().getEmployeeID());
        hrm.addVacationRequest(steve.getEmployeeID(), 1);
        hrm.preProcessRequests();
        hrm.approveRequest(hrm.getEmployeeRequests(steve.getEmployeeID(), Decision.Accepted, Decision.Undecided).get(0).getRequestID(), steve.getManager().getEmployeeID());
        
        
        
        
        
        gigi.setNrOfFreeDays(1000);
        hrm.addVacationRequest(gigi.getEmployeeID(), 1);
        hrm.preProcessRequests();
         hrm.approveRequest(hrm.getEmployeeRequests(gigi.getEmployeeID(), Decision.Accepted, Decision.Undecided).get(0).getRequestID(), gigi.getManager().getEmployeeID());
        
        System.out.println(hrm.toString());
    }
    
}
