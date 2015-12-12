package todo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;

import javax.swing.*;

import todo.model.SortByEndingDate;
import todo.model.SortByImportance;
import todo.model.SortByIntEndingDate;
import todo.model.Task;
import todo.view.DetailedTask;
import todo.view.DisplayTasks;

public class SortListener implements ActionListener{
	private Vector<Task> taskList;
	private DisplayTasks dit;
	private DetailedTask dt;


	/**
	 * Enable to sort the lists when a sort is selected
	 * @param taskList : list of tasks to be sorted
	 * @param dit : the panel that display the tasklist
	 * @param dt : the panel that display the information
	 */
	public SortListener(Vector<Task> taskList, DisplayTasks dit, DetailedTask dt) {
		super();
		this.taskList = taskList;
		this.dit = dit;
		this.dt = dt;
	}


	@SuppressWarnings("rawtypes")
	@Override
	public void actionPerformed(ActionEvent e) {
		String sort = ((JComboBox) e.getSource()).getSelectedItem().toString();

		switch(sort){
		case "Sort by date of death" :
			Collections.sort(taskList, new SortByEndingDate());
			break;
		case "Sort by intermediate date of putrefaction" :
			Collections.sort(taskList, new SortByIntEndingDate());
			break;
		case "Sort by importance of death" :
			Collections.sort(taskList, new SortByImportance());
			break;
		}
		this.dit.sortTask(taskList, dt);
	}
}