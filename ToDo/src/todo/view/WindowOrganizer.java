package todo.view;

import java.awt.*;

import java.util.Vector;

import javax.swing.*;

import todo.model.Task;
import todo.controller.AddTaskListener;
import todo.controller.SortListener;

public class WindowOrganizer extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel north  = new JPanel(); // new panel for the north part of the frame
	JPanel center = new JPanel(); // new panel for the center part of the frame
	JPanel south  = new JPanel(); // new panel for the south part of the frame
	
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
		
		/********************* The north panel *******************/
		this.north = new NorthPanel();

		/********************* The center panel ******************/
		this.center = new CenterPanel(progressList, finishedList);
		
		/********************* The south panel *******************/
		this.south = new SouthPanel(types);


		/**************** Interaction by listeners ***************/
		((NorthPanel) this.north).getSortCB().addActionListener(
				new SortListener(progressList, 
								((CenterPanel) this.center).getInProgressTasks(), 
								((CenterPanel) this.center).getDetailedTask()));
		((NorthPanel) this.north).getSortCB().addActionListener(
				new SortListener(finishedList, 
								((CenterPanel) this.center).getDoneTasks(), 
								((CenterPanel) this.center).getDetailedTask()));
		
		((SouthPanel) this.south).getAddTask().addActionListener(
				new AddTaskListener(progressList, 
									((CenterPanel) this.center).getInProgressTasks(), 
									((CenterPanel) this.center).getDetailedTask(), 
									types));
		//((SouthPanel) this.south).getDelTask().addActionListener(new DeleteTaskListener());
		
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
