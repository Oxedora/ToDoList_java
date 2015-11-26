package todo;

import java.util.*;

public class Organizer {
	Vector<Task> inProgress;
	Vector<Task> finished;
	Vector<String> typesList;
	
	public Organizer() {
		this.inProgress = new Vector<Task>();
		this.finished = new Vector<Task>();
		this.typesList = new Vector<String>();
	}
	
	/**
	 * @param t
	 */
	public void unfinishedToFinished(Task t){
		if(this.inProgress.contains(t)){
			this.inProgress.remove(t);
			this.finished.add(t);
		}
	}

	/**
	 * @param newType
	 */
	public void createType(String newType){
		if(!this.typesList.contains(newType)){
			this.typesList.add(newType);
		}
	}
	
	/**
	 * @param oldType
	 */
	public void deleteType(String oldType){
		if(this.typesList.contains(oldType)){
			this.typesList.remove(oldType);
		}
		
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
	 */
	public void editType(String oldType, String newType){
		if(this.typesList.contains(oldType)){
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
	}

}
