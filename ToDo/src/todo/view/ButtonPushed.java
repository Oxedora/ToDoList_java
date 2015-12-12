package todo.view;

import javax.swing.JButton;
import todo.model.Task;

/* Define the behavior of the buttons containing the tasks */
public class ButtonPushed extends JButton{
	
	private static final long serialVersionUID = 1L;
	private Task task; // The task that this button refers to
	
	/**
	 * Parameterized constructor
	 * ButtonPushed is the button contained in the displayTak panel
	 * and enable showing information of the tasks in the detailedTask panel
	 * @param t : the task it refers to
	 * @param s : the text it display to recognize the task
	 */
	public ButtonPushed(Task t, String s){
		super(s);
		this.task = t;
	}
	
	/**
	 * @return the task it refers to
	 */
	public Task getTask(){
		return this.task;
	}
	
	/**
	 * @param t : the new task
	 */
	public void setTask(Task t){
		this.task = t;
		this.setText();
	}
	
	/**
	 * Set the text the button must display
	 */
	public void setText(){
		this.setText(this.task.getButtonText());
	}
}