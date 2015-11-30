package todo;

import java.time.*;

public class Punctual extends Task {

	/**
	 * @param title
	 * @param description
	 * @param type
	 * @param endingDate
	 * @throws TaskException 
	 */
	public Punctual(String title, String description, String type, Importance importance,
			LocalDate endingDate) throws TaskException {
		super(title, description, type, importance, endingDate);
	}
	
	/**
	 * @return
	 */
	public Boolean isLate(){
		return this.getEndingDate().isAfter(LocalDate.now());
	}

	public LocalDate getIntEndingDate() {
		return this.getEndingDate();
	}
}
