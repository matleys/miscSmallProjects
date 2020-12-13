/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digiklantenkaart;

/**
 *
 * @author mathias
 */
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date implements Serializable {

	private static final long serialVersionUID = -2419868536330628838L;
	private static int OFFSET = 0;

	private LocalDate date;

	public Date(int year, int month, int dayOfMonth) {
		this(LocalDate.of(year, month, dayOfMonth));
	}

	public Date() {
		this(LocalDate.now().plusDays(OFFSET));
	}

	private Date(LocalDate date) {
		this.date = date;
	}

	public int getYear() {
		return date.getYear();
	}

	public int getMonth() {
		return date.getMonthValue();
	}

	public int getDayOfMonth() {
		return date.getDayOfMonth();
	}

	public boolean isBefore(Date other) {
		return this.date.isBefore(other.date);
	}

	public boolean isAfter(Date other) {
		return this.date.isAfter(other.date);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Date other = (Date) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	private static final DateTimeFormatter FORMATTER =  DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Override
	public String toString() {
		return date.format(FORMATTER);
	}

	public static Date parseDate(String dateString) {
		return new Date(LocalDate.parse(dateString, FORMATTER));
	}
}
