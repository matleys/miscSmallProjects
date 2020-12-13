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
public class VacationRequest extends EmployeeRequest {

	private int nrOfDays;

	public VacationRequest(int nrOfDays, Employee employee) {
		super(employee);
		this.nrOfDays = nrOfDays;
	}

	public int getNrOfDays() {
		return nrOfDays;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		VacationRequest other = (VacationRequest) obj;
		if (nrOfDays != other.nrOfDays)
			return false;
		return true;
	}
}