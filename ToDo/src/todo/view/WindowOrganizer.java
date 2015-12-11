package todo.view;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;

import todo.model.Task;
import todo.controller.AddTaskListener;
import todo.controller.AppraisalListener;
import todo.controller.DeleteTaskListener;
import todo.controller.EditTaskListener;
import todo.controller.ItsDoneListener;
import todo.controller.SortListener;

public class WindowOrganizer extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	NorthPanel  north; // new panel for the north part of the frame
	CenterPanel center; // new panel for the center part of the frame
	SouthPanel  south; // new panel for the south part of the frame
	
	public WindowOrganizer(Vector<Task> progressList, Vector<Task> finishedList, Vector<String> types){
		/*********************************************************/
		/******************* WINDOW PARAMETERS *******************/
		/*********************************************************/
		
		this.setTitle("Organizer of curse, of darkness... OF DOOM"); // window title
		this.setSize(700, 600); // window size
		this.setLocationRelativeTo(null); // center window
		//this.setResizable(false); // window is not resizable
		this.setLayout(new BorderLayout()); // main frame has a borderLayout
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define the close operation
		
		
		/*********************************************************/
		/******************** LOW-LEVEL PANELS *******************/
		/*********************************************************/
		
		/********************* The center panel ******************/
		this.center = new CenterPanel(progressList, finishedList, types); // manage the in progress list of tasks
																		  // manage the finished list of tasks
																		  // manage the displayed task chosen by the user
		
		this.center.getDetailedTask().getSetToDone().addActionListener( // adding the listener of done tasks
				new ItsDoneListener(this.center, 
						   			progressList, 
						   			finishedList));
		this.center.getDetailedTask().getEditButton().addActionListener(
				new EditTaskListener(this.center,
									progressList,
									finishedList));
		
		/********************* The north panel *******************/
		this.north = new NorthPanel(); // manage the sort
		
		this.north.getSortCB().addActionListener( // adding the sort listener for tasks in progress
				new SortListener(progressList, 
								this.center.getInProgressTasks(), 
								this.center.getDetailedTask()));
		this.north.getSortCB().addActionListener( // adding the sort listener for finished tasks
				new SortListener(finishedList, 
								this.center.getDoneTasks(), 
								this.center.getDetailedTask()));

		/********************* The south panel *******************/
		this.south = new SouthPanel(); // manage the adding/deletion of a task and the appraisal of the work done
		
		this.south.getAddTask().addActionListener( // adding listener to add a task in the in progress list of tasks
				new AddTaskListener(progressList,
									types,
									this.center));
		this.south.getDelTask().addActionListener( // adding a listener to delete a task (in the in progress list or the done list)
				new DeleteTaskListener(
						progressList,
						finishedList,
						this.center.getDetailedTask(),
						this.center.getInProgressTasks(),
						this.center.getDoneTasks()));
		
		this.south.getBilan().addActionListener(new AppraisalListener(progressList, finishedList));
		
		/*********************************************************/
		/******************** THE MAIN FRAME *********************/
		/*********************************************************/
		
		
		/**** Adding all the previous panels to the main frame ***/
		this.add(north, BorderLayout.NORTH); // adding the north panel to the north part of the main frame
		this.add(center, BorderLayout.CENTER); // adding the center panel to the center part of the main frame
		this.add(south, BorderLayout.SOUTH); // adding the south panel to the south part of the main frame

		this.setVisible(true); // Display all contents of the frame (has to be done in last, but no least !)
	}

	
	
}
