package todo.model;

import java.time.*;

public class Punctual extends Task {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		return (this.getIsDone() ? false : LocalDate.now().isAfter(this.getEndingDate()));
	}

	public LocalDate getIntEndingDate() {
		return this.getEndingDate();
	}

	public LocalDate getBeginningDate() {
		return this.getCreationDate();
	}

	public String getButtonText() {
		return "<HTML><BODY><center>" // behavior of text is done in HTML
				+this.getTitle()
				+"</center><BR>"
				+this.getEndingDate().getDayOfMonth()+"/"+this.getEndingDate().getMonthValue()
				+"/"+this.getEndingDate().getYear() // ending date
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
