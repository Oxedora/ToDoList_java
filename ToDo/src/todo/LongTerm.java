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
	 */
	public LongTerm(String title, String description, String type, 
			Enum<Importance> importance, LocalDate endingDate, LocalDate beginningDate) {
		super(title, description, type, importance, endingDate);
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
	 */
	public void setBeginningDate(LocalDate beginningDate) {
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
	public void setActualProgress(int actualProgress) {
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
