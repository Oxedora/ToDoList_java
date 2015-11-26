package todo;

import java.time.*;

/**
 * @author oxedora
 *
 */
public abstract class Task {
	String 	title;
	String 	description;
	String 	type;
	Enum<Importance> importance;
	LocalDate creationDate;
	LocalDate endingDate;
	Boolean isDone;
	
	/**
	 * @param title
	 * @param description
	 * @param type
	 * @param endingDate
	 */
	public Task(String title, String description, String type, Enum<Importance> importance, LocalDate endingDate) {
		this.title = title;
		this.description = description;
		this.type = type;
		this.importance = importance;
		this.creationDate = LocalDate.now();
		this.endingDate = endingDate;
		this.isDone = false;
	}

	
	//Getters & Setters
	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title) { // exception si nul
		// exception si isDone
			this.title = title;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(String type) {
		// exception si isDone
		this.type = type;
	}

	/**
	 * @return
	 */
	public LocalDate getCreationDate() {
		return creationDate;
	}

	/**
	 * @return
	 */
	public LocalDate getEndingDate() {
		return endingDate;
	}

	/**
	 * @param endingDate
	 */
	public void setEndingDate(LocalDate endingDate) { // exception si < creationDate & nul
		// exception si isDone
		this.endingDate = endingDate;
	}

	/**
	 * @return
	 */
	public Boolean getIsDone() {
		return isDone;
	}

	/**
	 * @param isDone
	 */
	public void setIsDone(Boolean isDone) { // exception si tache finie
		this.isDone = isDone;
	}
	
	public abstract Boolean isLate();
	
	public abstract LocalDate getIntEndingDate();
	
}