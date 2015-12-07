package todo.view;

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

	/* Display all the informations about the selected task */
	public DetailedTask(ButtonPushed buttonP){
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
			EditButton button = new EditButton(t);
			button.addActionListener(new ListenerEdit(this.buttonP, this));

			this.add(button); // allow editing on the task
		}
	}
	
	public void setTask(ButtonPushed buttonP){
		this.removeAll();
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
			button.addActionListener(new ListenerEdit(this.buttonP, this));

			this.add(button); // allow editing on the task
		}
	}
}