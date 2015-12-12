package todo.model;

import java.time.*;
import java.io.*;


public abstract class Task implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String 	title;
	private String 	description;
	private String 	type;
	private Importance importance;
	private LocalDate creationDate;
	private LocalDate endingDate;
	private LocalDate effectiveEndingDate;
	private Boolean isDone;
	
	/**
	 * Paramaterized constructor of Task
	 * @param title : the title of the task
	 * @param description : the description of the task
	 * @param importance : the importance of the task
	 * @param type : the type of the task
	 * @param endingDate : the date at which the task end
	 * @throws TaskException : the error for not suitable value
	 */
	public Task(String title, String description, String type, Importance importance,
			LocalDate endingDate) throws TaskException{
		
		if(endingDate.isBefore(LocalDate.now())){
			throw new TaskException(endingDate.toString()+" is before creationDate : "
		+this.creationDate);
		}
		
		if(title == null || title == ""){
			throw new TaskException("Get a real name u junk");
		}
		
		
		this.title = title;
		this.description = description;
		this.type = type;
		this.importance = importance;
		this.creationDate = LocalDate.now();
		this.endingDate = endingDate;
		this.effectiveEndingDate = null;
		this.isDone = false;
	}
	
	/**
	 * Default constructor for Task
	 */
	public Task(){
		this.title = "Genesis";
		this.description = "Alpha & Omega";
		this.type = "cheated";
		this.importance = Importance.High;
		this.creationDate = LocalDate.parse("1993-06-21");
		this.endingDate = LocalDate.parse("1994-01-24");
		this.effectiveEndingDate = LocalDate.parse("1994-01-24");
		this.isDone = true;
	}

	
	/**
	 * @return the date at which the task really ended
	 */
	public LocalDate getEffectiveEndingDate() {
		return effectiveEndingDate;
	}

	/**
	 * @param effectiveEndingDate : the date at which the task really ended
	 */
	public void setEffectiveEndingDate(LocalDate effectiveEndingDate) {
		this.effectiveEndingDate = effectiveEndingDate;
	}

	//Getters & Setters
	/**
	 * @return the title of the task
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Change the title of the task
	 * @param title : the new title of the task
	 * @throws TaskException : title can't be null
	 */
	public void setTitle(String title) throws TaskException{
		// exception si isDone
		if(title == null || title == ""){
			throw new TaskException("Get a real name u Junk");
		}
		
		if(this.isDone){
			throw new TaskException("Cannot modify ended tasks");
		}
		
			this.title = title;
	}

	/**
	 * @return the description of the task
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description : the new description of the task
	 * @throws TaskException : a task cannot be modified if done
	 */
	public void setDescription(String description) throws TaskException{
		if(this.isDone){
			throw new TaskException("Cannot modify ended tasks");
		}
		this.description = description;
	}

	/**
	 * @return the type of the task
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type : the new type of the task
	 * @throws TaskException : a task can't be modified if done
	 */
	public void setType(String type) throws TaskException{
		// exception si isDone?
		if(this.isDone){
			throw new TaskException("Cannot modify ended tasks");
		}
		
		this.type = type;
	}
	
	/**
	 * @return the date at which the task was created
	 */
	public LocalDate getCreationDate() {
		return creationDate;
	}
	
	/**
	 * @return the ending date
	 */
	public LocalDate getEndingDate() {
		return endingDate;
	}
	
	/**
	 * @return the date at which the task begin
	 */
	public abstract LocalDate getBeginningDate();
	
	/**
	 * @param date the date to be displayed correctly
	 * @return the well written date
	 */
	public String displayDate(LocalDate date){
		return(date.getDayOfMonth()+"/"+date.getMonthValue()+"/"+date.getYear());
	}

	/**
	 * @param endingDate : the new ending date for the task
	 * @throws TaskException : endingDate must be after creation date
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
	 * @param beginDate : the new date at which the task begin
	 * @throws TaskException : the beginDate must be after creation date and before ending date
	 */
	public abstract void setBeginningDate(LocalDate beginDate) throws TaskException;
	
	/**
	 * @return if the task is done or not
	 */
	public Boolean getIsDone() {
		return isDone;
	}
	
	/**
	 * @param importance : the new importance of the task
	 */
	public void setImportance(Importance importance) {
		this.importance = importance;
	}

	/**
	 * @return the importance of the task
	 */
	public Importance getImportance() {
		return importance;
	}


	/**
	 * @param isDone : the new state of the task
	 */
	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
		if(isDone){
			this.setEffectiveEndingDate(LocalDate.now());
			try {
				this.setActualProgress(100);
			} catch (TaskException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Getters for view part : display of detailed task
	/**
	 * @return the text used to display the description in the view, html enable it to return at next line
	 */
	public String getFullDescription(){
		return "<html><p>What I've done ? : "+this.description+"</p><html>";
	}
	
	/**
	 * @return the text used to display the type in the view, html enable it to return at next line
	 */
	public String getFullType(){
		return "<html><p>My sin : "+this.type+"</p><html>";
	}
	
	/**
	 * @return the text used to display the actual progress in the view, html enable it to return at next line
	 */
	public String getFullActualProgress(){
		return "<html><p>Progression : "+this.getActualProgress()+"%</p><html>";
	}
	
	/**
	 * @return the text used to display the importance in the view, html enable it to return at next line
	 */
	public String getFullImportance(){
		return	"<html><p>Gravity of sin : "+this.importance.toString()+"</p><html>";	
	}
	
	/**
	 * @return the text used to display the creation date in the view, html enable it to return at next line
	 */
	public String getFullCreationDate(){
		return "<html><p>Happy date : "+this.displayDate(this.creationDate)+"</p><html>";
	}
	
	/**
	 * @return the text used to display the beginning date in the view, html enable it to return at next line
	 */
	public String getFullBeginningDate(){
		return "<html><p>Fall date : "+this.displayDate(this.getBeginningDate())+"</p><html>";
	}
	
	/**
	 * @return the text used to display the intermediate ending date in the view, html enable it to return at next line
	 */
	public String getFullIntEndingDate(){
		return "<html><p>The date between : "
				+this.displayDate(this.getIntEndingDate())+"</p><html>";
	}
	
	/**
	 * @return the text used to display the ending date in the view, html enable it to return at next line
	 */
	public String getFullEndingDate(){
		return "<html><p>Unhappy date : "
				+this.displayDate(this.endingDate)+"</p><html>";
	}
	
	/**
	 * @return the text used to display the date at which the task really ended in the view, html enable it to return at next line
	 */ 
	public String getFullEffectiveEndingDate(){
		return "<html><p>Real unhappy date : "
				+this.displayDate(this.effectiveEndingDate)+"</p><html>";
	}
	
	/**
	 * @return the text used to display if the task is late in the view, html enable it to return at next line
	 */
	public String getFullIsLate(){
		return "<html><p>Mycelium ? : "+this.isLate().toString()+"</p><html>";
	}
	
	/**
	 * @return the text used to display if the task is done in the view, html enable it to return at next line
	 */
	public String getFullIsDone(){
		return "<html><p>Dead task : "+this.getIsDone().toString()+"</p><html>";
	}
	
	/**
	 * @return the percentage of progress of the task
	 */
	public abstract int getActualProgress();
	
	/**
	 * @param n : the new percentage of progress of the task
	 * @throws TaskException : must be above actual progress but a maximum of 100
	 */
	public abstract void setActualProgress(int n) throws TaskException;
	
	/**
	 * @return if the task is late or not
	 */
	public abstract Boolean isLate();
	
	/**
	 * @return the intermediate ending date
	 */
	public abstract LocalDate getIntEndingDate();
	
	/**
	 * @return the text to display on the button in the gui
	 */
	public abstract String getButtonText();
}