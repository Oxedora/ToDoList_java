package todo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;

import javax.swing.*;

public class SortListener implements ActionListener{
	private Vector<Task> taskList;
	private DisplayTasks dit;
	private DetailedTask dt;


	public SortListener(Vector<Task> taskList, DisplayTasks dit, DetailedTask dt) {
		this.taskList = taskList;
		this.dit = dit;
		this.dt = dt;
	}


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