package todo.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class SouthPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] typesArray;
	@SuppressWarnings("rawtypes")
	JComboBox typesCB;
	Button theseAreTypes = new Button("Choose your fate : ");
	Button addTask = new Button("Give life, like god... or Dr. Frankenstein");
	Button delTask = new Button("Doom a task");
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SouthPanel(Vector<String> types){
		this.setLayout(new FlowLayout()); // to correctly display the information
		this.setBackground(Color.darkGray); // adding a color to distinguish it from other parts of other panels
		
		/********** Creating the contents of south panel **********/
		// array of type to display them
		this.typesArray = new String[types.size()];
		for(int i = 0; i < types.size(); i++){typesArray[i] = types.get(i);} // init the array filled with the types
		this.typesCB = new JComboBox(typesArray); // adding the array of type into a JComboBox for interactions with the user
		
		// indicate the user this is the list of types
		this.theseAreTypes.setBackground(Color.WHITE);
		this.theseAreTypes.setForeground(Color.BLACK);
		
		// allow the user to create new tasks
		
		this.addTask.setBackground(Color.WHITE);
		this.addTask.setForeground(Color.ORANGE);
		
		// allow the user to delete tasks
		this.delTask.setBackground(Color.BLACK);
		this.delTask.setForeground(Color.RED);
		
		/********* Adding the contents to the south panel ********/
		this.add(theseAreTypes); // name the comboBox for the user
		this.add(typesCB);
		this.add(addTask);
		this.add(delTask);
		
	}

	public String[] getTypesArray() {
		return typesArray;
	}

	public void setTypesArray(String[] typesArray) {
		this.typesArray = typesArray;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getTypesCB() {
		return typesCB;
	}

	@SuppressWarnings("rawtypes") 
	public void setTypesCB(JComboBox typesCB) {
		this.typesCB = typesCB;
	}

	public Button getTheseAreTypes() {
		return theseAreTypes;
	}

	public void setTheseAreTypes(Button theseAreTypes) {
		this.theseAreTypes = theseAreTypes;
	}

	public Button getAddTask() {
		return addTask;
	}

	public void setAddTask(Button addTask) {
		this.addTask = addTask;
	}

	public Button getDelTask() {
		return delTask;
	}

	public void setDelTask(Button delTask) {
		this.delTask = delTask;
	}

}
