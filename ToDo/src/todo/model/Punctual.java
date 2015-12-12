package todo.model;

import java.time.*;

public class Punctual extends Task {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param title : the title of the task
	 * @param description : the description of the task
	 * @param type : the type of the task
	 * @param importance : the importance of the task
	 * @param endingDate : the date at which the task must be done
	 * @throws TaskException : the error for not suitable value
	 */
	public Punctual(String title, String description, String type, Importance importance,
			LocalDate endingDate) throws TaskException {
		super(title, description, type, importance, endingDate);
	}
	
	/**
	 * The default constructor of Punctual
	 */
	public Punctual(){
		super();
	}
	
	/* (non-Javadoc)
	 * @see todo.model.Task#isLate()
	 */
	public Boolean isLate(){
		return (this.getIsDone() ? false : LocalDate.now().isAfter(this.getEndingDate()));
	}

	/* (non-Javadoc)
	 * @see todo.model.Task#getIntEndingDate()
	 */
	public LocalDate getIntEndingDate() {
		return this.getEndingDate();
	}

	/* (non-Javadoc)
	 * @see todo.model.Task#getBeginningDate()
	 */
	public LocalDate getBeginningDate() {
		return this.getCreationDate();
	}

	/* (non-Javadoc)
	 * @see todo.model.Task#getButtonText()
	 */
	public String getButtonText() {
		return "<HTML><BODY><center>" // behavior of text is done in HTML
				+this.getTitle()
				+"</center><BR>"
				+this.getEndingDate().getDayOfMonth()+"/"+this.getEndingDate().getMonthValue()
				+"/"+this.getEndingDate().getYear() // ending date
				+"</BODY></HTML>";
	}

	/* (non-Javadoc)
	 * @see todo.model.Task#setBeginningDate(java.time.LocalDate)
	 */
	@Override
	public void setBeginningDate(LocalDate beginDate) throws TaskException {		
	}

	/* (non-Javadoc)
	 * @see todo.model.Task#getActualProgress()
	 */
	@Override
	public int getActualProgress() {
		return (this.getIsDone() ? 100 : 0);
	}

	/* (non-Javadoc)
	 * @see todo.model.Task#setActualProgress(int)
	 */
	@Override
	public void setActualProgress(int n) {
		return;
	}
}
