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
		return (this.isDone ? false : LocalDate.now().isAfter(this.getEndingDate()));
	}

	public LocalDate getIntEndingDate() {
		return this.getEndingDate();
	}

	public LocalDate getBeginningDate() {
		return this.getCreationDate();
	}
}
