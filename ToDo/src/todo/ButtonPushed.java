package todo;

import javax.swing.JButton;

/* Define the behavior of the buttons containing the tasks */
public class ButtonPushed extends JButton{
	private Task task; // The task that this button refers to
	
	public ButtonPushed(Task task, String s){
		super(s);
		this.task = task;
	}
	
	public Task getTask(){
		return this.task;
	}
	
	public void setTask(){
		this.setText(this.task.getButtonText());
	}
}