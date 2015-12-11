package todo.view;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import todo.model.Organizer;
import todo.controller.AddTaskListener;
import todo.controller.AppraisalListener;
import todo.controller.DeleteTaskListener;
import todo.controller.EditTaskListener;
import todo.controller.ItsDoneListener;
import todo.controller.SortListener;

public class WindowOrganizer extends JFrame implements WindowListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Organizer organizer;
	NorthPanel  north; // new panel for the north part of the frame
	CenterPanel center; // new panel for the center part of the frame
	SouthPanel  south; // new panel for the south part of the frame
	
	public WindowOrganizer(){
		
		this.organizer = new Organizer();
		this.organizer.load();
		
		/*********************************************************/
		/******************* WINDOW PARAMETERS *******************/
		/*********************************************************/
		
		this.setTitle("Organizer of curse, of darkness... OF DOOM"); // window title
		this.setSize(700, 600); // window size
		this.setLocationRelativeTo(null); // center window
		//this.setResizable(false); // window is not resizable
		this.setLayout(new BorderLayout()); // main frame has a borderLayout
		addWindowListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define the close operation
		
		
		/*********************************************************/
		/******************** LOW-LEVEL PANELS *******************/
		/*********************************************************/
		
		/********************* The center panel ******************/
		this.center = new CenterPanel(this.organizer.getInProgress(),
				this.organizer.getFinished(),
				this.organizer.getTypesList()); // manage the in progress list of tasks
																		  // manage the finished list of tasks
																		  // manage the displayed task chosen by the user
		
		this.center.getDetailedTask().getSetToDone().addActionListener( // adding the listener of done tasks
				new ItsDoneListener(this.center, 
						   			this.organizer.getInProgress(), 
						   			this.organizer.getFinished()));
		this.center.getDetailedTask().getEditButton().addActionListener(
				new EditTaskListener(this.center,
									this.organizer.getInProgress(),
									this.organizer.getFinished()));
		
		/********************* The north panel *******************/
		this.north = new NorthPanel(); // manage the sort
		
		this.north.getSortCB().addActionListener( // adding the sort listener for tasks in progress
				new SortListener(this.organizer.getInProgress(), 
								this.center.getInProgressTasks(), 
								this.center.getDetailedTask()));
		this.north.getSortCB().addActionListener( // adding the sort listener for finished tasks
				new SortListener(this.organizer.getFinished(), 
								this.center.getDoneTasks(), 
								this.center.getDetailedTask()));

		/********************* The south panel *******************/
		this.south = new SouthPanel(); // manage the adding/deletion of a task and the appraisal of the work done
		
		this.south.getAddTask().addActionListener( // adding listener to add a task in the in progress list of tasks
				new AddTaskListener(this.organizer.getInProgress(),
									this.organizer.getTypesList(),
									this.center));
		this.south.getDelTask().addActionListener( // adding a listener to delete a task (in the in progress list or the done list)
				new DeleteTaskListener(
						this.organizer.getInProgress(),
						this.organizer.getFinished(),
						this.center.getDetailedTask(),
						this.center.getInProgressTasks(),
						this.center.getDoneTasks()));
		
		this.south.getBilan().addActionListener(new AppraisalListener(this.organizer.getInProgress(),
				this.organizer.getFinished()));
		
		/*********************************************************/
		/******************** THE MAIN FRAME *********************/
		/*********************************************************/
		
		
		/**** Adding all the previous panels to the main frame ***/
		this.add(north, BorderLayout.NORTH); // adding the north panel to the north part of the main frame
		this.add(center, BorderLayout.CENTER); // adding the center panel to the center part of the main frame
		this.add(south, BorderLayout.SOUTH); // adding the south panel to the south part of the main frame

		this.setVisible(true); // Display all contents of the frame (has to be done in last, but no least !)
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		this.organizer.save();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
