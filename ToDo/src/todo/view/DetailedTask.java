package todo.view;

import java.util.Vector;

import javax.swing.*;

import todo.model.LongTerm;
import todo.model.Task;

public class DetailedTask extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ButtonPushed buttonP;
	private Vector<String> types;
	private JButton setToDone = new JButton("AAAAAAAAAAND ITS DONE !");
	private JButton editButton = new JButton("The Fate modifier");

	/* Display all the informations about the selected task */
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
		}
		this.add(new JLabel(t.getFullEndingDate()));
		if(t.getIsDone()){
			this.add(new JLabel(t.getFullEffectiveEndingDate()));
		}
		this.add(new JLabel(t.getFullIsLate()));
		this.add(new JLabel(t.getFullIsDone()));

		if(!t.getIsDone()){
			this.add(editButton); // allow editing on the task
			
			this.add(this.setToDone); // validate the done task
		}
	}
	
	public JButton getEditButton() {
		return editButton;
	}

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
		}
		this.add(new JLabel(t.getFullEndingDate()));
		if(t.getIsDone()){
			this.add(new JLabel(t.getFullEffectiveEndingDate()));
		}
		this.add(new JLabel(t.getFullIsLate()));
		this.add(new JLabel(t.getFullIsDone()));

		if(!t.getIsDone()){
			this.add(editButton); // allow editing on the task
			
			this.add(this.setToDone);
		}
	}
	
	public JButton getSetToDone() {
		return setToDone;
	}

	public void setSetToDone(JButton setToDone) {
		this.setToDone = setToDone;
	}

	public Vector<String> getTypes() {
		return types;
	}

	public void setTypes(Vector<String> types) {
		this.types = types;
	}

	public ButtonPushed getButtonP() {
		return buttonP;
	}

	public void setButtonP(ButtonPushed buttonP) {
		this.buttonP = buttonP;
	}
}