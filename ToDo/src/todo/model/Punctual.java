package todo.model;

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
	
	public Punctual(){
		super();
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

	public String getButtonText() {
		return "<HTML><BODY><center>" // behavior of text is done in HTML
				+this.title // task title
				+"</center><BR>"
				+this.endingDate.getDayOfMonth()+"/"+this.endingDate.getMonthValue()
				+"/"+this.endingDate.getYear() // ending date
				+"</BODY></HTML>";
	}

	@Override
	public void setBeginningDate(LocalDate beginDate) throws TaskException {		
	}

	@Override
	public int getActualProgress() {
		return (this.getIsDone() ? 100 : 0);
	}

	@Override
	public void setActualProgress(int n) {
		return;
	}
}
