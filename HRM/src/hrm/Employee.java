/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrm;

/**
 *
 * @author mathias
 */
public class Employee extends Person implements Comparable <Employee> {
     
    private String employeeID;
    private Employee manager;
    private int nrOfFreeDays;
    private double budget;
    
    public Employee (String employeeID, Employee manager, 
            String firstname, String lastname, 
            String gender, String email)
    {
        super(firstname, lastname, gender, email); 
        this.employeeID = employeeID;
        this.manager = manager;
        this.nrOfFreeDays = 100;
        this.budget = 1000;
    }

    //getters and setters
    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public int getNrOfFreeDays() {
        return nrOfFreeDays;
    }

    public void setNrOfFreeDays(int nrOfFreeDays) {
        this.nrOfFreeDays = nrOfFreeDays;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
    //getters and setters
    
    @Override
    public boolean equals(Object object){
        boolean b = false;
        if((object != null) && (object instanceof Employee)){
            Employee emp = (Employee) object;
            b = this.getEmployeeID().equals(emp.getEmployeeID());
        }
        return b;
    }
    
    @Override
    public int compareTo(Employee emp){
        return this.employeeID.compareTo(emp.getEmployeeID()); 
    }
    
    @Override
    public String toString(){
        String tekst;
        tekst = this.getEmployeeID() + " ";
        tekst += this.getFirstname() + " " + this.getLastname();
        if(this.getManager() != null)
            tekst += " (Manager: " + this.getManager().getEmployeeID() + ")";
        return tekst;
    }
    
}
