package todo.view;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SouthPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton addTask = new JButton("Give life");
	JButton delTask = new JButton("Doom a task");
	JButton Bilan = new JButton("Done so far...");
	
	/**
	 * Constructor
	 * This panel contains the buttons to add/delete a task and generate the results
	 */
	public SouthPanel(){
		this.setLayout(new FlowLayout()); // to correctly display the information
		this.setBackground(Color.darkGray); // adding a color to distinguish it from other parts of other panels
		
		/********** Creating the contents of south panel **********/
		// allow the user to create new tasks
		
		this.addTask.setBackground(Color.WHITE);
		this.addTask.setForeground(Color.ORANGE);
		
		// allow the user to delete tasks
		this.delTask.setBackground(Color.BLACK);
		this.delTask.setForeground(Color.RED);
		
		//Allow the user to generate an apparaisal of all his tasks during a period
		this.Bilan.setBackground(Color.GREEN);
		this.Bilan.setForeground(Color.YELLOW);
		
		/********* Adding the contents to the south panel ********/
		this.add(addTask);
		this.add(delTask);
		this.add(Bilan);
		
	}
	
	/**
	 * @return button that generate the bilan
	 */
	public JButton getBilan() {
		return Bilan;
	}
	
	/**
	 * @param bilan : the new button
	 */
	public void setBilan(JButton bilan) {
		Bilan = bilan;
	}
	
	/**
	 * @return the button to add a task
	 */
	public JButton getAddTask() {
		return addTask;
	}

	/**
	 * @param addTask : the new button
	 */
	public void setAddTask(JButton addTask) {
		this.addTask = addTask;
	}

	/**
	 * @return the button to delete a task
	 */
	public JButton getDelTask() {
		return delTask;
	}

	/**
	 * @param delTask : the new button
	 */
	public void setDelTask(JButton delTask) {
		this.delTask = delTask;
	}

}
