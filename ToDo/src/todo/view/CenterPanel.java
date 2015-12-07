package todo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import todo.model.Task;

public class CenterPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DetailedTask detailedTask;
	JScrollPane scrollDetail;
	
	DisplayTasks inProgressTasks;
	JScrollPane scrollProgress;
	
	DisplayTasks doneTasks;
	JScrollPane scrollDone;
	
	public CenterPanel(Vector<Task> progressList, Vector<Task> finishedList){
		this.setLayout(new BorderLayout()); // to correctly display the information
		
		/************* Creating the low-level panels *************/
		this.detailedTask = new DetailedTask(
				new ButtonPushed(progressList.get(0), progressList.get(0).getButtonText())
				); // display details of the task chosen by the user
		this.scrollDetail= new JScrollPane(detailedTask);
		
		this.inProgressTasks = new DisplayTasks(progressList, "Still alive...", detailedTask); // display of tasks in progress
		this.inProgressTasks.setBackground(Color.lightGray); // adding a color to distinguish it from other parts
		this.scrollProgress = new JScrollPane(inProgressTasks);
		
		this.doneTasks = new DisplayTasks(finishedList, "Dead tasks", detailedTask); // display of done tasks
		this.doneTasks.setBackground(Color.gray); // adding a color to distinguish it from other parts
		this.scrollDone= new JScrollPane(doneTasks);
		
		/**** Adding the low-level panels to the center panel ***/
		this.add(scrollProgress, BorderLayout.WEST); // Tasks in progress are displayed in the west side
		this.add(scrollDetail, BorderLayout.CENTER); // The detailed task is displayed in the center side
		this.add(scrollDone, BorderLayout.EAST); // Done tasks are displayed in the east side	
	}

	public DetailedTask getDetailedTask() {
		return detailedTask;
	}

	public void setDetailedTask(DetailedTask detailedTask) {
		this.detailedTask = detailedTask;
	}

	public DisplayTasks getInProgressTasks() {
		return inProgressTasks;
	}

	public void setInProgressTasks(DisplayTasks inProgressTasks) {
		this.inProgressTasks = inProgressTasks;
	}

	public DisplayTasks getDoneTasks() {
		return doneTasks;
	}

	public void setDoneTasks(DisplayTasks doneTasks) {
		this.doneTasks = doneTasks;
	}

}
