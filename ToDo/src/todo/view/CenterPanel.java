package todo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.*;

import todo.controller.EditTypeListener;
import todo.model.Punctual;
import todo.model.Task;

public class CenterPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DetailedTask detailedTask;	
	private DisplayTasks inProgressTasks;	
	private DisplayTasks doneTasks;
	private JList<String> typeList;
	private Vector<String> types;

	public CenterPanel(Vector<Task> progressList, Vector<Task> finishedList, Vector<String> types){
		this.types = types;
		
		this.setLayout(new BorderLayout()); // to correctly display the information
		
		/************* Managing the type list  *************/
		JButton addType = new JButton("life a type");
		JButton editType = new JButton("mess a type");
		JButton delType = new JButton("Doom a type");
		
		editType.addActionListener(new EditTypeListener(this, progressList));
		
		JPanel buttonTypePane = new JPanel();//Contains buttons to interact with the types
		buttonTypePane.setLayout(new GridLayout(0, 1));
		
		buttonTypePane.add(addType);
		buttonTypePane.add(editType);
		buttonTypePane.add(delType);
		
		typeList = new JList<String>(this.types);
		
		JPanel typePanel = new JPanel();//Contains types and button to interact with them
		typePanel.setLayout(new FlowLayout());
		
		typePanel.add(typeList);
		typePanel.add(buttonTypePane);
		
		/************* Creating the low-level panels *************/
		this.detailedTask = new DetailedTask(
				new ButtonPushed(new Punctual(), new Punctual().getButtonText()),
				types
				); // display details of the task chosen by the user
		JScrollPane scrollDetail= new JScrollPane(detailedTask);
		
		JPanel middlePane = new JPanel();//Contains DetailedTask and types
		middlePane.setLayout(new GridLayout(0, 1));
		
		middlePane.add(scrollDetail);
		middlePane.add(typePanel);
		
		this.inProgressTasks = new DisplayTasks(progressList, "Still alive...", detailedTask); // display of tasks in progress
		this.inProgressTasks.setBackground(Color.lightGray); // adding a color to distinguish it from other parts
		JScrollPane scrollProgress = new JScrollPane(inProgressTasks);
		
		this.doneTasks = new DisplayTasks(finishedList, "Dead tasks", detailedTask); // display of done tasks
		this.doneTasks.setBackground(Color.gray); // adding a color to distinguish it from other parts
		JScrollPane scrollDone= new JScrollPane(doneTasks);
		
		/**** Adding the low-level panels to the center panel ***/
		this.add(scrollProgress, BorderLayout.WEST); // Tasks in progress are displayed in the west side
		this.add(middlePane, BorderLayout.CENTER); // The detailed task is displayed in the center side
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
	
	public JList<String> getTypeList() {
		return typeList;
	}

	public void setTypeList(Vector<String> typeList) {
		this.typeList.removeAll();
		this.typeList.repaint();
		this.typeList.revalidate();
		
		this.typeList.setListData(types);
	}
	
	public Vector<String> getTypes(){
		return this.types;
	}
}
