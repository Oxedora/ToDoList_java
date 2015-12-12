package todo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import todo.model.Punctual;
import todo.model.Task;
import todo.view.ButtonPushed;
import todo.view.DetailedTask;
import todo.view.DisplayTasks;

public class DeleteTaskListener implements ActionListener{
	private DetailedTask detailedTask;
	private Vector<Task> inProgress;
	private Vector<Task> finished;
	private DisplayTasks displayInProgress;
	private DisplayTasks displayFinished;
	
	/**
	 * Enable to delete the selected task when the button is clicked
	 * @param inP : the list of tasks in progress
	 * @param done : the list of done tasks
	 * @param dt : the panel that display the informations of the selected task
	 * @param dip : the panel that contains the tasks in progress
	 * @param df : the panel that contains the done tasks
	 */
	public DeleteTaskListener(Vector<Task> inP, Vector<Task> done, DetailedTask dt, DisplayTasks dip, DisplayTasks df) {
		super();
		this.inProgress = inP;
		this.detailedTask = dt;
		this.finished = done;
		this.displayInProgress = dip;
		this.displayFinished = df;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Task currentTask = detailedTask.getButtonP().getTask(); // getting the selected task
		
		JLabel message = new JLabel("By the seven hells, do you want to doom "+currentTask.getTitle()+" ???"); // warns the user about deleting the task
		
		int answer = JOptionPane.showConfirmDialog(null, message, "Satan simulator", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE); // creates the dialog
		
		if(answer ==  JOptionPane.OK_OPTION){ // if the user said "yes"
			if(currentTask.getIsDone()){ // if the task is done
				finished.remove(currentTask); // done task removed from finished tasks
				this.displayFinished.sortTask(finished, detailedTask); // sorts the remaining tasks & refresh the display
				if(this.finished.size() > 0){ // if there still tasks
					this.detailedTask.setTask(new ButtonPushed(finished.get(0), finished.get(0).getButtonText())); // displays the first one
				}else{this.detailedTask.setTask(new ButtonPushed(new Punctual(), new Punctual().getButtonText()));} // else displays the legacy task 
			}
			else{
				inProgress.remove(currentTask);
				this.displayInProgress.sortTask(inProgress, detailedTask);
				if(this.inProgress.size() > 0){
					this.detailedTask.setTask(new ButtonPushed(inProgress.get(0), inProgress.get(0).getButtonText()));
				}else{this.detailedTask.setTask(new ButtonPushed(new Punctual(), new Punctual().getButtonText()));}
			}
		}
	}

}
