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
import java.util.Date;

public abstract class EmployeeRequest {

	private static int nrOfRequests = 0;

	private String requestID;
	private Date date;
	private Employee requester;
	private Decision preProcessingDecision;
	private Decision managerDecision;

	public EmployeeRequest(Employee employee) {
		date = new Date();
		requester = employee;
		nrOfRequests++;
		requestID = "request-" + nrOfRequests;
		preProcessingDecision = Decision.Undecided;
		managerDecision = Decision.Undecided;
	}

	public Employee getRequester() {
		return requester;
	}

	public String getRequestID() {
		return requestID;
	}

	public Decision getPreProcessingDecision() {
		return preProcessingDecision;
	}

	public Decision getManagerDecision() {
		return managerDecision;
	}

	public void setPreProcessingDecision(Decision preProcessingDecision) {
		this.preProcessingDecision = preProcessingDecision;
	}

	public void setManagerDecision(Decision managerDecision) {
		this.managerDecision = managerDecision;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeRequest other = (EmployeeRequest) obj;
		if (managerDecision != other.managerDecision)
			return false;
		if (preProcessingDecision != other.preProcessingDecision)
			return false;
		if (requestID == null) {
			if (other.requestID != null)
				return false;
		} else if (!requestID.equals(other.requestID))
			return false;
		if (requester == null) {
			if (other.requester != null)
				return false;
		} else if (!requester.equals(other.requester))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return requestID + ";" + date;
	}
}