package todo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import todo.model.Task;
import todo.model.TaskException;

public class DeleteTypeListener implements ActionListener{
	private CenterPanel center;
	private Vector<Task> taskList;

	public DeleteTypeListener(CenterPanel center, Vector<Task> taskList) {
		super();
		this.center = center;
		this.taskList = taskList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String doomedType = this.center.getTypeList().getSelectedValue();

		if(doomedType == null){ // if user didn't select any types, asks him to
			JOptionPane.showMessageDialog(null, "Please, select a type.", "Doo on you !", JOptionPane.OK_OPTION);
		}
		else{
			try {
				JLabel message = new JLabel("By the seven hells, do you want to doom "+doomedType+" ???"); // warns the user about deleting the task

				int answer = JOptionPane.showConfirmDialog(null, message, "Satan simulator", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE); // creates the dialog

				if(answer ==  JOptionPane.OK_OPTION){ 
					this.center.getTypes().remove(doomedType);
					this.center.setTypeList(this.center.getTypes());

					for(Task t : this.taskList){
						if(t.getDescription() == doomedType){
							t.setDescription("");
						}
						
					}
				}
			} catch (TaskException e1) {
				JOptionPane.showConfirmDialog(null, e1.getMessage(), "Doom on you !", JOptionPane.ERROR_MESSAGE);
			}

		}
	}
}	
