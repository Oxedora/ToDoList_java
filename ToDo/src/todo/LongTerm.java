package todo;

import java.time.*;
import java.time.temporal.*;

public class LongTerm extends Task {

	LocalDate beginningDate;
	int actualProgress;


	/**
	 * @param title
	 * @param description
	 * @param type
	 * @param endingDate
	 * @param beginningDate
	 * @throws TaskException 
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
	/**
	 * @return
	 */
	public LocalDate getBeginningDate() {
		return beginningDate;
	}

	/**
	 * @param beginningDate
	 * @throws TaskException 
	 */
	public void setBeginningDate(LocalDate beginningDate) throws TaskException {
		if(beginningDate.isAfter(this.endingDate) || beginningDate.isBefore(this.creationDate)){
			throw new TaskException("beginningDate must be between creationDate : "+
		this.creationDate.toString()+" and endingDate : "+this.endingDate.toString());
		}
		
		this.beginningDate = beginningDate;
	}

	/**
	 * @return
	 */
	public int getActualProgress() {
		return actualProgress;
	}

	/**
	 * @param actualProgress
	 */
	public void setActualProgress(int actualProgress) throws TaskException {
		if(actualProgress < this.actualProgress || actualProgress > 100){
			throw new TaskException("New actualProgress must be above actualProgress : "+this.actualProgress+
					"and below or equal to 100");
		}
		this.actualProgress = actualProgress;
	}

	/**
	 * @return True if the task is late, false otherwise 
	 *
	 */
	public Boolean isLate() {
		if(LocalDate.now().isAfter(endingDate)) {return true;}
		
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


	@Override
	public LocalDate getIntEndingDate() {
		long daysBetween = this.getBeginningDate().until(this.getEndingDate(), ChronoUnit.DAYS);

		return this.getActualProgress() < 25 ? this.getBeginningDate().plus(daysBetween/4, ChronoUnit.DAYS) :
			this.getActualProgress() < 50 ? this.getBeginningDate().plus(daysBetween/2, ChronoUnit.DAYS) :
				this.getActualProgress() < 75 ? this.getBeginningDate().plus(3*daysBetween/4, ChronoUnit.DAYS) :
					this.getEndingDate();
	}

}
