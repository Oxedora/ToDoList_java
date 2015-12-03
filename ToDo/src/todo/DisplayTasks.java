package todo;

import java.util.Vector;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class DisplayTasks extends JPanel{
	private Vector<Task> taskList; // keeps informations for updates
	
	private class ButtonPushed extends JButton{
		public ButtonPushed(String s){super(s);}
	}
	
	public DisplayTasks(Vector<Task> taskList, String title) {
		super();
		this.taskList = taskList; // keeps informations for updates

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // display contents in columns
		this.add(new JLabel(title)); // if tasks are in progress or done
		for(Task t : taskList){ 
			// for every tasks, display its title and its interval of time
			LocalDate beg = t.getBeginningDate(); // getting the creation date to light code
			LocalDate end = t.getEndingDate(); // getting the ending date to light code
			String buttonText = "<HTML><BODY><center>" // behavior of text is done in HTML
								+t.getTitle() // task title
								+"</center><BR>";
			if(t.getClass().getName() == LongTerm.class.getName()){
				buttonText += beg.getDayOfMonth()+"/"+beg.getMonthValue()+"/"+beg.getYear()+" - "; // creation date
			}
			buttonText += end.getDayOfMonth()+"/"+end.getMonthValue()+"/"+end.getYear() // ending date
						+"</BODY></HTML>";
			ButtonPushed button = new ButtonPushed(buttonText);
	        button.setAlignmentX(Component.CENTER_ALIGNMENT); // center the button in the displayed list
	        if(t.isLate()){button.setForeground(Color.red);} // button is red if the task is late
	        this.add(button); // adding button to the panel
		}
	}
}
