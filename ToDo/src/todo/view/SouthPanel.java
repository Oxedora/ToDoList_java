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
	
	public JButton getBilan() {
		return Bilan;
	}
	
	public void setBilan(JButton bilan) {
		Bilan = bilan;
	}
	
	public JButton getAddTask() {
		return addTask;
	}

	public void setAddTask(JButton addTask) {
		this.addTask = addTask;
	}

	public JButton getDelTask() {
		return delTask;
	}

	public void setDelTask(JButton delTask) {
		this.delTask = delTask;
	}

}
