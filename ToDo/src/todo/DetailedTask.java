package todo;

import javax.swing.*;
import java.awt.*;

public class DetailedTask extends JPanel{
	private Task currentTask;
	
	private class editButton extends JButton{
		public editButton(String s){
			super(s);
		}
	}
	
	public DetailedTask(Task t){
		this.currentTask = t;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // layout affichant les éléments en colonne

		this.setBorder(BorderFactory.createTitledBorder(t.getTitle()));
		this.add(new JLabel("Description : "+t.getDescription()));
		this.add(new JLabel("Type : "+t.getType()));
		this.add(new JLabel("Importance : "+t.getImportance().toString()));
		this.add(new JLabel("Creation date : "+t.displayDate(t.getCreationDate())));
		this.add(new JLabel("Ending date : "+t.displayDate(t.getEndingDate())));
		this.add(new JLabel("Task late : "+t.isLate().toString()));
		this.add(new JLabel("Task done : "+t.getIsDone().toString()));
		
		this.add(new editButton("edit task"));
	}
}
