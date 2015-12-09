package todo.view;

import java.util.Vector;

import javax.swing.*;

import todo.controller.ListenerEdit;
import todo.model.LongTerm;
import todo.model.Task;

public class DetailedTask extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ButtonPushed buttonP;
	private Vector<String> types;

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
		this.add(new JLabel(t.getFullIsLate()));
		this.add(new JLabel(t.getFullIsDone()));

		if(!t.getIsDone()){
			EditButton editButton = new EditButton(t);
			editButton.addActionListener(new ListenerEdit(this));
			this.add(editButton); // allow editing on the task
			
			JButton setToDone = new JButton("AAAAAAAAAAND ITS DONE !");
			//setToDone.
			this.add(setToDone); // validate the done task
		}
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
		this.add(new JLabel(t.getFullIsLate()));
		this.add(new JLabel(t.getFullIsDone()));

		if(!t.getIsDone()){
			EditButton button = new EditButton(t);
			button.addActionListener(new ListenerEdit(this));

			this.add(button); // allow editing on the task
		}
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