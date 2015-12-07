package todo.view;

import javax.swing.JButton;
import todo.model.Task;

/* Define the behavior of the buttons containing the tasks */
public class ButtonPushed extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Task task; // The task that this button refers to
	
	public ButtonPushed(Task t, String s){
		super(s);
		this.task = t;
	}
	
	public Task getTask(){
		return this.task;
	}
	
	public void setTask(){
		this.setText(this.task.getButtonText());
	}
}