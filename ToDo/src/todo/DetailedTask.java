package todo;

import javax.swing.*;
import java.awt.*;

public class DetailedTask extends JPanel{
	private Task currentTask; // keeps informations for updates
	
	private class editButton extends JButton{
		public editButton(String s){
			super(s);
		}
	}
	
	public DetailedTask(Task t){
		this.currentTask = t;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // display contents in columns

		this.setBorder(BorderFactory.createTitledBorder(t.getTitle())); // display title in a border

		// display all informations of the task
		this.add(new JLabel("What I've done ? : "+t.getDescription()));
		this.add(new JLabel("My sin : "+t.getType()));
		this.add(new JLabel("Gravity of sin : "+t.getImportance().toString()));
		this.add(new JLabel("Happy date : "+t.displayDate(t.getCreationDate())));
		if(t.getClass().getName() == LongTerm.class.getName()){
			this.add(new JLabel("The date between : "+t.displayDate(t.getIntEndingDate())));
		}
		this.add(new JLabel("Unhappy date : "+t.displayDate(t.getEndingDate())));
		this.add(new JLabel("Mycelium ? : "+t.isLate().toString()));
		this.add(new JLabel("Dead task : "+t.getIsDone().toString()));
		
		this.add(new editButton("The fate modifier")); // allow editing on the task
	}
}
