package todo.model;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Organizer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Task> inProgress;
	private Vector<Task> finished;
	private Vector<String> typesList;

	/**
	 * Default constructor
	 */
	public Organizer() {
		this.inProgress = new Vector<Task>();
		this.finished = new Vector<Task>();
		this.typesList = new Vector<String>();
		this.typesList.add("");
	}

	/**
	 * Parameterized constructor
	 * @param inProgress : the vector of task that aren't done
	 * @param finished : the vector of task that are done
	 * @param typesList : the vector of types
	 */
	public Organizer(Vector<Task> inProgress, Vector<Task> finished, Vector<String> typesList) {
		this.inProgress = inProgress;
		this.finished = finished;
		this.typesList = typesList;
	}

	/**
	 * @return the list of task that aren't done
	 */
	public Vector<Task> getInProgress() {
		return inProgress;
	}

	/**
	 * @param inProgress : the new list of undone tasks
	 */
	public void setInProgress(Vector<Task> inProgress) {
		this.inProgress = inProgress;
	}

	/**
	 * @return the list of done tasks
	 */
	public Vector<Task> getFinished() {
		return finished;
	}

	/**
	 * @param finished : the new list of done tasks
	 */
	public void setFinished(Vector<Task> finished) {
		this.finished = finished;
	}

	/**
	 * @return the list of types
	 */
	public Vector<String> getTypesList() {
		return typesList;
	}

	/**
	 * @param typesList : the new list of types
	 */
	public void setTypesList(Vector<String> typesList) {
		this.typesList = typesList;
	}

	/**
	 * Enable the recovery of the organizer class,
	 * for a new instance of the windoworganizer
	 */
	public void load(){
		try{
			FileInputStream saveName = new FileInputStream("saveOrganizer");
			ObjectInputStream save = new ObjectInputStream(saveName);
			Organizer newOrg = (Organizer) save.readObject();

			if(newOrg == null){
				Object[] options = {"Ok"};
				JOptionPane.showOptionDialog(null, "No save found", "Loading error",
						JOptionPane.PLAIN_MESSAGE,
						JOptionPane.ERROR_MESSAGE,
						null, options, options[0]);
				}
			else{
				this.setInProgress(newOrg.getInProgress());
				this.setFinished(newOrg.getFinished());
				this.setTypesList(newOrg.getTypesList());
			}
			save.close();
		}
		catch(IOException e){
			Object[] options = {"Ok"};
			JOptionPane.showOptionDialog(null, e.getMessage(), "Loading error",
					JOptionPane.PLAIN_MESSAGE,
					JOptionPane.ERROR_MESSAGE,
					null, options, options[0]);
			}

		catch (ClassNotFoundException e) {
			Object[] options = {"Ok"};
			JOptionPane.showOptionDialog(null, e.getMessage(), "Loading error",
					JOptionPane.PLAIN_MESSAGE,
					JOptionPane.ERROR_MESSAGE,
					null, options, options[0]);
		}

	}

	/**
	 * Enable the serialization of the class organizer
	 * to be recovered later
	 */
	public void save(){
		try{
			FileOutputStream saveName = new FileOutputStream("saveOrganizer");
			ObjectOutputStream save = new ObjectOutputStream(saveName);
			save.writeObject(this);
			save.close();
		}
		catch(IOException e){
			Object[] options = {"Ok"};
			JOptionPane.showOptionDialog(null, e.getMessage(), "Saving error",
					JOptionPane.PLAIN_MESSAGE,
					JOptionPane.ERROR_MESSAGE,
					null, options, options[0]);
		}
		Object[] options = {"Ok"};
		JOptionPane.showOptionDialog(null, "Saving done !", "Saving",
				JOptionPane.PLAIN_MESSAGE,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options, options[0]);
	}
	
}

