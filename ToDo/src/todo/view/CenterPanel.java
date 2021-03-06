package todo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.*;

import todo.controller.AddTypeListener;
import todo.controller.DeleteTypeListener;
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

	/**
	 * The panel that hold the two display task panels (done and not done) and the detailed task panel
	 * @param progressList : list of tasks that aren't done
	 * @param finishedList : list of tasks that are done
	 * @param types : list of types
	 */
	public CenterPanel(Vector<Task> progressList, Vector<Task> finishedList, Vector<String> types){
		this.types = types;	
		this.typeList = new JList<String>(this.types);	
		this.setLayout(new BorderLayout()); // to correctly display the information
		
		/***************************************************/
		/************* Managing the type list **************/
		/***************************************************/
		
		/************** Creating the buttons ***************/
		JButton addType = new JButton("life a type");  // creates a type
		JButton editType = new JButton("mess a type"); // edits a type
		JButton delType = new JButton("Doom a type");  // deletes a type
		
		/************** Listening the buttons **************/

		editType.addActionListener(new EditTypeListener(this, progressList)); // listens the edit button
		addType.addActionListener(new AddTypeListener(this));
		delType.addActionListener(new DeleteTypeListener(this, progressList));
		
		/***** Adding the buttons to the button panel *****/
		JPanel buttonTypePane = new JPanel();// Contains buttons to interact with the types
		buttonTypePane.setLayout(new GridLayout(0, 1)); // displays the buttons in columns
		
		buttonTypePane.add(addType);
		buttonTypePane.add(editType);
		buttonTypePane.add(delType);
		
		/***** Displaying the types and their buttons *****/
		JPanel typePanel = new JPanel();// Contains types and buttons to interact with them
		typePanel.setLayout(new FlowLayout()); // displays informations in lines
		typePanel.add(this.typeList); // displays the list of types
		typePanel.add(buttonTypePane); // displays the buttons
		
		/***************************************************/
		/*********** Managing the tasks display ************/
		/***************************************************/
		
		/***** Displaying the tasks chosen by the user *****/
		this.detailedTask = new DetailedTask(
				new ButtonPushed(new Punctual(), new Punctual().getButtonText()),
				types
				); // display details of the task chosen by the user
		JScrollPane scrollDetail= new JScrollPane(detailedTask);
		
		JPanel middlePane = new JPanel();// Contains DetailedTask and types
		middlePane.setLayout(new GridLayout(0, 1));
		middlePane.add(scrollDetail);
		middlePane.add(typePanel);
		
		/******** Displaying the tasks in progress ********/
		this.inProgressTasks = new DisplayTasks(progressList, "Still alive...", detailedTask); // display of tasks in progress
		this.inProgressTasks.setBackground(Color.lightGray); // adding a color to distinguish it from other parts
		JScrollPane scrollProgress = new JScrollPane(inProgressTasks);
		
		/********* Displaying the finished tasks **********/
		this.doneTasks = new DisplayTasks(finishedList, "Dead tasks", detailedTask); // display of done tasks
		this.doneTasks.setBackground(Color.gray); // adding a color to distinguish it from other parts
		JScrollPane scrollDone= new JScrollPane(doneTasks);
		
		/***************************************************/
		/* Adding the low-level panels to the center panel */
		/***************************************************/
		this.add(scrollProgress, BorderLayout.WEST); // Tasks in progress are displayed in the west side
		this.add(middlePane, BorderLayout.CENTER); // The detailed task is displayed in the center side
		this.add(scrollDone, BorderLayout.EAST); // Done tasks are displayed in the east side	
	}

	/**
	 * @return the panel detailed task
	 */
	public DetailedTask getDetailedTask() {
		return detailedTask;
	}

	/**
	 * @param detailedTask : the new detailed task
	 */
	public void setDetailedTask(DetailedTask detailedTask) {
		this.detailedTask = detailedTask;
	}

	/**
	 * @return the panel of tasks in progress
	 */
	public DisplayTasks getInProgressTasks() {
		return inProgressTasks;
	}

	/**
	 * @param inProgressTasks : the new panel of tasks in progress
	 */
	public void setInProgressTasks(DisplayTasks inProgressTasks) {
		this.inProgressTasks = inProgressTasks;
	}

	/**
	 * @return the panel of tasks done
	 */
	public DisplayTasks getDoneTasks() {
		return doneTasks;
	}

	/**
	 * @param doneTasks : the new panel of done tasks
	 */
	public void setDoneTasks(DisplayTasks doneTasks) {
		this.doneTasks = doneTasks;
	}
	
	/**
	 * @return the list of types
	 */
	public JList<String> getTypeList() {
		return typeList;
	}

	/**
	 * @param typeList : the list of types
	 */
	public void setTypeList(Vector<String> typeList) {
		this.typeList.removeAll();
		this.typeList.repaint();
		this.typeList.revalidate();
		
		this.typeList.setListData(types);
	}
	
	/**
	 * @return the vector of types
	 */
	public Vector<String> getTypes(){
		return this.types;
	}
}
