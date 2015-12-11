package todo.controller;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import todo.model.Task;
import todo.view.CenterPanel;

public class ItsDoneListener implements ActionListener{
	private CenterPanel center;
	private Vector<Task> inProgress;
	private Vector<Task> finished;
	
	public ItsDoneListener(CenterPanel center, Vector<Task> inProgress, Vector<Task> finished){
		super();
		this.center = center;
		this.inProgress = inProgress;
		this.finished = finished;
	}

	public void actionPerformed(ActionEvent e) {
		JPanel confirmPane = new JPanel();
		confirmPane.setLayout(new FlowLayout());
		confirmPane.add(new JLabel("Is this task really done ? (irreversible action)"));
		int answer = JOptionPane.showConfirmDialog(null, confirmPane, "Kill it with fire", JOptionPane.OK_CANCEL_OPTION);
		
		if(answer == JOptionPane.OK_OPTION){
			this.center.getDetailedTask().getButtonP().getTask().setEffectiveEndingDate(LocalDate.now()); // task is done today
			this.center.getDetailedTask().getButtonP().getTask().setIsDone(true); // the task is now done
			
			this.inProgress.removeElement(this.center.getDetailedTask().getButtonP().getTask()); // removing the task from tasks in progress
			this.center.getInProgressTasks().sortTask(this.inProgress, this.center.getDetailedTask()); // updates the view
			this.center.getInProgressTasks().repaint();
			this.center.getInProgressTasks().revalidate();
			
			this.finished.add(this.center.getDetailedTask().getButtonP().getTask()); // adding the task to finished tasks
			this.center.getDoneTasks().sortTask(this.finished, this.center.getDetailedTask()); // updates the view
			this.center.getDoneTasks().repaint();
			this.center.getDoneTasks().revalidate();
			
			this.center.getDetailedTask().setTask(this.center.getDetailedTask().getButtonP());
			this.center.repaint();
			this.center.revalidate();
		}
	}

}
