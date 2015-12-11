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
	 * @param inProgress
	 * @param finished
	 * @param typesList
	 */
	public Organizer(Vector<Task> inProgress, Vector<Task> finished, Vector<String> typesList) {
		this.inProgress = inProgress;
		this.finished = finished;
		this.typesList = typesList;
	}

	/**
	 * @param t
	 * @throws TaskException 
	 */
	public void unfinishedToFinished(Task t) throws TaskException{
		if(!t.getIsDone()){
			throw new TaskException(t.getTitle()+" : this task is not done");
		}

		if(!this.inProgress.contains(t)){
			throw new TaskException(t.getTitle()+" : no such task in progress");
		}

		this.inProgress.remove(t);
		this.finished.add(t);
	}

	/**
	 * @param newType
	 * @throws TaskException 
	 */
	public void createType(String newType) throws TaskException{
		if(this.typesList.contains(newType)){
			throw new TaskException("This type already exists");
		}

		this.typesList.add(newType);
	}

	/**
	 * @param oldType
	 * @throws TaskException 
	 */
	public void deleteType(String oldType) throws TaskException{
		if(this.typesList.contains(oldType)){
			throw new TaskException(oldType+" : no such type");
		}

		this.typesList.remove(oldType);

		for(Task t : inProgress){
			if(t.getType() == oldType){
				t.setType("");
			}
		}

		for(Task t : finished){
			if(t.getType() == oldType){
				t.setType("");
			}
		}
	}

	/**
	 * @param oldType
	 * @param newType
	 * @throws TaskException 
	 */
	public void editType(String oldType, String newType) throws TaskException{
		if(!this.typesList.contains(oldType)){
			throw new TaskException(oldType+" : no such type");
		}

		this.deleteType(oldType);
		this.createType(newType);
		for(Task t : inProgress){
			if(t.getType() == oldType){
				t.setType(newType);
			}
		}

		for(Task t : finished){
			if(t.getType() == oldType){
				t.setType(newType);
			}
		}
	}

	public Vector<Task> getInProgress() {
		return inProgress;
	}

	public void setInProgress(Vector<Task> inProgress) {
		this.inProgress = inProgress;
	}

	public Vector<Task> getFinished() {
		return finished;
	}

	public void setFinished(Vector<Task> finished) {
		this.finished = finished;
	}

	public Vector<String> getTypesList() {
		return typesList;
	}

	public void setTypesList(Vector<String> typesList) {
		this.typesList = typesList;
	}

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

