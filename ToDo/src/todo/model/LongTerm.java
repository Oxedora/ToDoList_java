package todo.model;

import java.time.*;
import java.time.temporal.*;

public class LongTerm extends Task {

	private static final long serialVersionUID = 1L;
	LocalDate beginningDate;
	int actualProgress;


	/**
	 * @param title : the title of the task
	 * @param description : the description of the task
	 * @param type : the type of the task
	 * @param importance : the importance of the task
	 * @param endingDate : the date at which the task must be done
	 * @param beginningDate : the date at which the task begin
	 * @throws TaskException : the error for not suitable value
	 */
	public LongTerm(String title, String description, String type, 
			Importance importance, LocalDate beginningDate, LocalDate endingDate) throws TaskException {
		super(title, description, type, importance, endingDate);
		
		if(beginningDate.isAfter(endingDate)){
			throw new TaskException(endingDate.toString()+" is before beginning date : "
		+beginningDate.toString());
		}
		
		this.beginningDate = beginningDate;
		this.actualProgress = 0;
	}


	//Getters & Setters
	/* (non-Javadoc)
	 * @see todo.model.Task#getBeginningDate()
	 */
	public LocalDate getBeginningDate() {
		return beginningDate;
	}

	/* (non-Javadoc)
	 * @see todo.model.Task#setBeginningDate(java.time.LocalDate)
	 */
	public void setBeginningDate(LocalDate beginningDate) throws TaskException {
		if(beginningDate.isAfter(this.getEndingDate()) || beginningDate.isBefore(this.getCreationDate())){
			throw new TaskException("beginningDate must be between creationDate : "+
		this.getCreationDate().toString()+" and endingDate : "+this.getEndingDate().toString());
		}
		
		this.beginningDate = beginningDate;
	}

	/* (non-Javadoc)
	 * @see todo.model.Task#getActualProgress()
	 */
	public int getActualProgress() {
		return this.actualProgress;
	}

	/* (non-Javadoc)
	 * @see todo.model.Task#setActualProgress(int)
	 */
	public void setActualProgress(int actualProgress) throws TaskException {
		if(actualProgress < this.actualProgress || actualProgress > 100){
			throw new TaskException("New actualProgress must be above actualProgress : "+this.actualProgress+
					"and below or equal to 100");
		}
		this.actualProgress = actualProgress;
	}

	/* (non-Javadoc)
	 * @see todo.model.Task#isLate()
	 */
	public Boolean isLate() {
		if(this.getIsDone()){return false;}
		if(LocalDate.now().isAfter(getEndingDate())) {return true;}
		
		long daysBetween = this.getBeginningDate().until(this.getEndingDate(), ChronoUnit.DAYS);

		//If the progress is under 25% and if today date exceed 1/4 of the task duration, the task is late
		return this.getActualProgress() < 25 && (LocalDate.now().isAfter(this.getBeginningDate().plus(daysBetween/4, 
				ChronoUnit.DAYS))) ? true : //If the progress is under 50% and if today date exceed 2/4 of the task duration, the task is late
					this.getActualProgress() < 50 && (LocalDate.now().isAfter(this.getBeginningDate().plus(daysBetween/2, 
							ChronoUnit.DAYS)))? true : //If the progress is under 75% and if today date exceed 3/4 of the task duration, the task is late
								this.getActualProgress() < 75 && (LocalDate.now().isAfter(this.getBeginningDate().plus(3*daysBetween/4, 
										ChronoUnit.DAYS))) ? true :
											false;
	}


	/* (non-Javadoc)
	 * @see todo.model.Task#getIntEndingDate()
	 */
	@Override
	public LocalDate getIntEndingDate() {
		long daysBetween = this.getBeginningDate().until(this.getEndingDate(), ChronoUnit.DAYS);

		return this.getActualProgress() < 25 ? this.getBeginningDate().plus(daysBetween/4, ChronoUnit.DAYS) :
			this.getActualProgress() < 50 ? this.getBeginningDate().plus(daysBetween/2, ChronoUnit.DAYS) :
				this.getActualProgress() < 75 ? this.getBeginningDate().plus(3*daysBetween/4, ChronoUnit.DAYS) :
					this.getEndingDate();
	}


	/* (non-Javadoc)
	 * @see todo.model.Task#getButtonText()
	 */
	@Override
	public String getButtonText() {
		return "<HTML><BODY><center>" // behavior of text is done in HTML
				+this.getTitle()
				+"</center><BR>"
				+this.beginningDate.getDayOfMonth()+"/"
				+this.beginningDate.getMonthValue()+"/"
				+this.beginningDate.getYear()+" - "
				+this.getEndingDate().getDayOfMonth()+"/"
				+this.getEndingDate().getMonthValue()+"/"
				+this.getEndingDate().getYear() // ending date
				+"</BODY></HTML>";
	}

}
