package todo.view;

import javax.swing.*;
import todo.model.Task;

public class EditButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Task t;
	public EditButton(Task t){
		super("The Fate modifier");
		this.setT(t);
	}
	public Task getT() {
		return t;
	}
	public void setT(Task t) {
		this.t = t;
	}
	
}
