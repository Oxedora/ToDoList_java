package todo;

import java.awt.Button;
import java.awt.HeadlessException;
import java.time.LocalDate;

//Boutons qui contiendrons les tâches. Cliquer dessus affichera la tache au centre
public class ButtonInfo extends Button{
	private String title;
	private LocalDate taskEndDate;
	
	public ButtonInfo(String title, LocalDate taskEndDate)
			throws HeadlessException {
		super(title+"\n"+taskEndDate.toString());
		this.title = title;
		this.taskEndDate = taskEndDate;
	}		
	
}