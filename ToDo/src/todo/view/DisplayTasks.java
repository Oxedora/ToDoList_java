package todo.view;

import java.util.Vector;

import javax.swing.*;

import todo.controller.ListenerDisplayButton;
import todo.model.Task;

import java.awt.*;
import todo.view.DetailedTask;

public class DisplayTasks extends JPanel {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;

	/**
	 * Init all the buttons containing the tasks
	 * @param taskList : the list of task to be displayed
	 * @param title : the title of the panel
	 * @param dt : the panel where the task must display his informations
	 */
	public DisplayTasks(Vector<Task> taskList, String title, DetailedTask dt) {
		super();
		this.title = title;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // display contents in columns
		JLabel rowTitle = new JLabel(title);
		this.add(rowTitle); // if tasks are in progress or done
		rowTitle.setAlignmentX(CENTER_ALIGNMENT);

		for(Task t : taskList){ 
			// for every tasks, display its title and its interval of time in a button
			ButtonPushed button = new ButtonPushed(t, t.getButtonText());
			button.addActionListener(new ListenerDisplayButton(dt));
			button.setAlignmentX(Component.CENTER_ALIGNMENT); // center the button in the displayed list
			if(t.isLate()){button.setForeground(Color.red);} // button is red if the task is late
			this.add(button); // adding button to the panel
		}
	}

	/**
	 * @param taskList : the vector to sort
	 * @param dt : the panel where information display
	 */
	public void sortTask(Vector<Task> taskList, DetailedTask dt){
		this.removeAll();
		this.repaint();
		this.revalidate();

		JLabel rowTitle = new JLabel(this.title);
		this.add(rowTitle); // if tasks are in progress or done
		rowTitle.setAlignmentX(CENTER_ALIGNMENT);

		for(Task t : taskList){ 
			System.out.println(t.getTitle());
			// for every tasks, display its title and its interval of time in a button
			ButtonPushed button = new ButtonPushed(t, t.getButtonText());
			button.addActionListener(new ListenerDisplayButton(dt));
			button.setAlignmentX(Component.CENTER_ALIGNMENT); // center the button in the displayed list
			if(t.isLate()){button.setForeground(Color.red);} // button is red if the task is late
			this.add(button); // adding button to the panel
		}
		
	}

}
