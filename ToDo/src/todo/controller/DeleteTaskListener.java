package todo.controller;

import java.awt.Button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import todo.view.DetailedTask;
import todo.view.DisplayTasks;

public class DeleteTaskListener implements ActionListener{
	private Button delButton;
	private DetailedTask currentTask;
	
	public DeleteTaskListener(Button delButton, DetailedTask dt) {
		super();
		this.delButton = delButton;
		this.currentTask = dt;
	}

	public DeleteTaskListener() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//int answer = JOptionPane.showConfirmDialog(null, "By the seven hells, do you want to doom \"".currentTask.get   ."???",
		//											"God simulator", JOptionPane.OK_CANCEL_OPTION);
		//if(answer ==  JOptionPane.OK_OPTION){
			
		//}
	}

}
