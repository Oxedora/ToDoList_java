package todo;

import java.time.*;


public abstract class Task {
	String 	title;
	String 	description;
	String 	type;
	Importance importance;
	LocalDate creationDate;
	LocalDate endingDate;
	Boolean isDone;
	
	/**
	 * @param title
	 * @param description
	 * @param type
	 * @param endingDate
	 */
	public Task(String title, String description, String type, Importance importance,
			LocalDate endingDate) throws TaskException{
		
		if(endingDate.isBefore(LocalDate.now())){
			throw new TaskException(endingDate.toString()+" is before creationDate : "
		+this.creationDate);
		}
		
		if(title == null){
			throw new TaskException("Get a real name u junk");
		}
		
		
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
		return this.title;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title) throws TaskException{ // exception si nul
		// exception si isDone
		if(title == null){
			throw new TaskException("Get a real name u Junk");
		}
		
		if(this.isDone){
			throw new TaskException("Cannot modify ended tasks");
		}
		
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
	public void setDescription(String description) throws TaskException{
		if(this.isDone){
			throw new TaskException("Cannot modify ended tasks");
		}
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
	public void setType(String type) throws TaskException{
		// exception si isDone?
		if(this.isDone){
			throw new TaskException("Cannot modify ended tasks");
		}
		
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
	
	public abstract LocalDate getBeginningDate();
	
	public String displayDate(LocalDate date){
		return(date.getDayOfMonth()+"/"+date.getMonthValue()+"/"+date.getYear());
	}

	/**
	 * @param endingDate
	 */
	public void setEndingDate(LocalDate endingDate) throws TaskException{ // exception si < creationDate & nul
		// exception si isDone
		if(endingDate.isBefore(creationDate)){
			throw new TaskException(endingDate.toString()+" is before creationDate : "+this.creationDate);
		}
		if(this.isDone){
			throw new TaskException("Cannot modify ended tasks");
		}
		this.endingDate = endingDate;
	}

	/**
	 * @return
	 */
	public Boolean getIsDone() {
		return isDone;
	}
	
	/**
	 * @param importance
	 */
	public void setImportance(Importance importance) {
		this.importance = importance;
	}

	/**
	 * @return
	 */
	public Importance getImportance() {
		return importance;
	}


	/**
	 * @param isDone
	 */
	public void setIsDone(Boolean isDone) { // exception si tache finie
		this.isDone = isDone;
	}
	
	/**
	 * @return
	 */
	public abstract Boolean isLate();
	
	/**
	 * @return
	 */
	public abstract LocalDate getIntEndingDate();
	
}