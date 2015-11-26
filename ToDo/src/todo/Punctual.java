package todo;

import java.time.*;

public class Punctual extends Task {

	/**
	 * @param title
	 * @param description
	 * @param type
	 * @param endingDate
	 */
	public Punctual(String title, String description, String type, Enum<Importance> importance, LocalDate endingDate) {
		super(title, description, type, importance, endingDate);
	}
	
	/**
	 * @return
	 */
	public Boolean isLate(){
		return this.getEndingDate().isAfter(LocalDate.now());
	}

	@Override
	public LocalDate getIntEndingDate() {
		return this.getEndingDate();
	}
}
