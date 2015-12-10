package todo.controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import todo.model.Task;
import todo.model.TaskException;
import todo.view.CenterPanel;

public class EditTypeListener implements ActionListener{
	private CenterPanel center;
	private Vector<Task> taskList;

	public EditTypeListener(CenterPanel center, Vector<Task> taskList) {
		super();
		this.center = center;
		this.taskList = taskList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean isokay = false;

		while(!isokay){
			JTextField tType = new JTextField(center.getTypeList().getSelectedValue());
			
			if(tType.getText().length() == 0){ // if user didn't select any types, asks him to
				JOptionPane.showMessageDialog(null, "Please, select a type.", "Mess it !", JOptionPane.OK_OPTION);
				break; // gets out of the loop because other lines are now useless
			}
			
			isokay = false;
			JPanel editPane = new JPanel();
			editPane.setLayout(new GridLayout(0, 1));
			editPane.add(new JLabel("New type name : "));
			editPane.add(tType);

			int answer = JOptionPane.showConfirmDialog(null, editPane, "Mess it !", JOptionPane.OK_CANCEL_OPTION);

			if(answer == JOptionPane.OK_OPTION){
				String newType = tType.getText();
				try {
					if(newType == ""){
						JOptionPane.showMessageDialog(null, "Not an acceptable name", "Doom on you", JOptionPane.ERROR_MESSAGE);
						isokay = false;
					}
					else{
						String oldType = center.getTypeList().getSelectedValue();
						center.getTypes().remove(oldType);
						center.getTypes().add(newType);

						for(Task t : this.taskList){
							if(t.getType() == oldType){
								t.setType(newType);
							}
						}
						this.center.setTypeList(this.center.getTypes());
						isokay = true;
					}
				} catch (TaskException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Doom on you", JOptionPane.ERROR_MESSAGE);
					isokay = false;
				}
			}
			else{isokay = true;}
		}
	}
}
