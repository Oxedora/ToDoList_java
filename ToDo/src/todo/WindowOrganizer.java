package todo;

import java.awt.*;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.*;

public class WindowOrganizer extends JFrame{
	
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
		/******************** THE NORTH PANEL ********************/
		/*********************************************************/
		
		/******************* Creating the panel ******************/
		JPanel north  = new JPanel(); // new panel for the north part of the frame
		north.setLayout(new FlowLayout()); // to center the information
		
		/***************** Creating the comboBox *****************/
		String[] sortArray = new String[3]; // all kinds of sort created
		sortArray[0] = "Sort by date of death";
		sortArray[1] = "Sort by intermediate date of putrefaction";
		sortArray[2] = "Sort by importance of death";
		JComboBox sortCB = new JComboBox(sortArray); // created comboBox to choose the sort
		
		/********* Adding the comboBox to the north panel ********/
		north.add(sortCB); // sorts added to the north panel
		north.setBackground(Color.darkGray); // color of the north panel
		
		
		/*********************************************************/
		/******************** THE CENTER PANEL *******************/
		/*********************************************************/
		
		/******************* Creating the panel ******************/
		JPanel center = new JPanel(); // new panel for the center part of the frame
		center.setLayout(new BorderLayout()); // to correctly display the information
		
		/************* Creating the low-level panels *************/
		DisplayTasks inProgressTasks = new DisplayTasks(progressList, "Still alive..."); // display of tasks in progress
		inProgressTasks.setBackground(Color.lightGray); // adding a color to distinguish it from other parts
		
		DetailedTask detailedTask = new DetailedTask(progressList.get(0)); // display details of the task chosen by the user
				
		DisplayTasks doneTasks = new DisplayTasks(finishedList, "Dead tasks"); // display of done tasks
		doneTasks.setBackground(Color.gray); // adding a color to distinguish it from other parts
		
		/**** Adding the low-level panels to the center panel ***/
		center.add(inProgressTasks, BorderLayout.WEST); // Tasks in progress are displayed in the west side
		center.add(detailedTask, BorderLayout.CENTER); // The detailed task is displayed in the center side
		center.add(doneTasks, BorderLayout.EAST); // Done tasks are displayed in the east side
		
		
		/*********************************************************/
		/******************** THE SOUTH PANEL ********************/
		/*********************************************************/
		
		/**************** Creating the south panel ***************/
		JPanel south  = new JPanel(); // new panel for the south part of the frame
		south.setLayout(new FlowLayout()); // to correctly display the information
		
		/************** Creating the low-level panel *************/
		JPanel lowLevel = new JPanel(); // new panel to order the contents
		lowLevel.setLayout(new BorderLayout()); // to correctly display the information
		south.setBackground(Color.darkGray); // adding a color to distinguish it from other parts of other panels
		
		/********** Creating the contents of south panel **********/
		// array of type to display them
		String[] typesArray = new String[types.size()]; // matching the length of the Vector containing the types
		for(int i = 0; i < types.size(); i++){typesArray[i] = types.get(i);} // init the array filled with the types
		JComboBox typesCB = new JComboBox(typesArray); // adding the array of type into a JComboBox for interactions with the user
		
		// indicate the user this is the list of types
		Button theseAreTypes = new Button("Choose your fate : ");
		theseAreTypes.setBackground(Color.WHITE);
		theseAreTypes.setForeground(Color.BLACK);
		
		// allow the user to create new tasks
		Button addTask = new Button("Give life, like god... or Frankenstein");
		addTask.setBackground(Color.WHITE);
		addTask.setForeground(Color.ORANGE);
		
		// allow the user to create new tasks
		Button delTask = new Button("Doom a task");
		delTask.setBackground(Color.BLACK);
		delTask.setForeground(Color.RED);
		
		/********* Adding the contents to the south panel ********/
		south.add(theseAreTypes); // name the comboBox for the user
		south.add(typesCB);
		south.add(addTask);
		south.add(delTask);
		
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
