package todo.view;

import java.awt.Color;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Vector;

import javax.swing.*;

import todo.model.LongTerm;
import todo.model.Task;

public class DetailedTask extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private ButtonPushed buttonP;
	private Vector<String> types;
	private JButton setToDone = new JButton("AAAAAAAAAAND ITS DONE !");
	private JButton editButton = new JButton("The Fate modifier");

	/**
	 * Constructor of the DetailedTask : Display all the informations about the selected task 
	 * @param buttonP : the button containing the task which information must be displayed
	 * @param types : all the types availables for a task
	 */
	public DetailedTask(ButtonPushed buttonP, Vector<String> types){
		this.types = types;
		this.buttonP = buttonP; // keeps the information for updates
		Task t = buttonP.getTask();

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // display contents in columns

		this.setBorder(BorderFactory.createTitledBorder(t.getTitle())); // display title in a border

		// display all informations of the task
		this.add(new JLabel(t.getFullDescription()));
		this.add(new JLabel(t.getFullType()));
		this.add(new JLabel(t.getFullImportance()));
		this.add(new JLabel(t.getFullCreationDate()));
		if(t.getClass().getName() == LongTerm.class.getName()){
			this.add(new JLabel(t.getFullBeginningDate()));
			this.add(new JLabel(t.getFullIntEndingDate()));
			// display intermediate ending date for long term tasks
			this.add(new JLabel(t.getFullActualProgress()));
		}
		this.add(new JLabel(t.getFullEndingDate()));
		if(t.getIsDone()){
			this.add(new JLabel(t.getFullEffectiveEndingDate()));
		}
		else{
			long timeLeft = LocalDate.now().until(t.getEndingDate(), ChronoUnit.DAYS);
			if(timeLeft > 0){
				this.add(new JLabel("Due in "+timeLeft+" day(s)"));
			}
			else{
				JLabel late =new JLabel("Was due "+timeLeft+" day(s) ago !");
				late.setForeground(Color.RED);
				this.add(late);
			}
		}
		this.add(new JLabel(t.getFullIsLate()));
		this.add(new JLabel(t.getFullIsDone()));

		if(!t.getIsDone()){
			this.add(editButton); // allow editing on the task

			this.add(this.setToDone); // validate the done task
		}
	}

	/**
	 * @return the button of the task it refers to
	 */
	public JButton getEditButton() {
		return editButton;
	}

	/**
	 * Update the view of the task
	 * @param buttonP : the new task to be displyed
	 */
	public void setTask(ButtonPushed buttonP){
		this.removeAll();
		this.repaint();
		this.revalidate();

		this.buttonP = buttonP;

		Task t = buttonP.getTask(); // keeps the information for updates

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // display contents in columns

		this.setBorder(BorderFactory.createTitledBorder(t.getTitle())); // display title in a border

		// display all informations of the task
		this.add(new JLabel(t.getFullDescription()));
		this.add(new JLabel(t.getFullType()));
		this.add(new JLabel(t.getFullImportance()));
		this.add(new JLabel(t.getFullCreationDate()));
		if(t.getClass().getName() == LongTerm.class.getName()){
			this.add(new JLabel(t.getFullBeginningDate()));
			this.add(new JLabel(t.getFullIntEndingDate()));
			// display intermediate ending date for long term tasks
			this.add(new JLabel(t.getFullActualProgress()));
		}
		this.add(new JLabel(t.getFullEndingDate()));
		if(t.getIsDone()){
			this.add(new JLabel(t.getFullEffectiveEndingDate()));
		}
		else{
			long timeLeft = LocalDate.now().until(t.getEndingDate(), ChronoUnit.DAYS);
			if(timeLeft > 0){
				this.add(new JLabel("Due in "+timeLeft+" day(s)"));
			}
			else{
				JLabel late =new JLabel("Was due "+timeLeft+" day(s) ago !");
				late.setForeground(Color.RED);
				this.add(late);
			}
		}
		this.add(new JLabel(t.getFullIsLate()));
		this.add(new JLabel(t.getFullIsDone()));

		if(!t.getIsDone()){
			this.add(editButton); // allow editing on the task

			this.add(this.setToDone);
		}
	}

	/**
	 * @return the button that set a task to done
	 */
	public JButton getSetToDone() {
		return setToDone;
	}

	/**
	 * @return the list of types
	 */
	public Vector<String> getTypes() {
		return types;
	}

	/**
	 * @param types : the new list of types
	 */
	public void setTypes(Vector<String> types) {
		this.types = types;
	}

	/**
	 * @return the button that contains the displayed task
	 */
	public ButtonPushed getButtonP() {
		return buttonP;
	}

	/**
	 * @param buttonP : the new button containing the task to display
	 */
	public void setButtonP(ButtonPushed buttonP) {
		this.buttonP = buttonP;
	}
}