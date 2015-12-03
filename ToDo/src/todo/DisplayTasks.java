package todo;

import java.util.Vector;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class DisplayTasks extends JPanel{
	private Vector<Task> taskList;
	
	private class ButtonPushed extends JButton{
		public ButtonPushed(String s){super(s);}
	}
	
	public DisplayTasks(Vector<Task> taskList, String title) {
		super();
		this.taskList = taskList; // garde en mémoire, pour les updates

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // layout affichant les éléments en colonne
		this.add(new JLabel(title));
		for(Task t : taskList){
			LocalDate beg = t.getCreationDate();
			LocalDate end = t.getEndingDate();
			ButtonPushed button = new ButtonPushed("<HTML><BODY><center>" // comportement du texte en HTML
										+t.getTitle() // titre de la tâche
										+"</center><BR>"
										+beg.getDayOfMonth()+"/"+beg.getMonthValue()+"/"+beg.getYear() // date de début
										+" - "
										+end.getDayOfMonth()+"/"+end.getMonthValue()+"/"+end.getYear() // date de fin
										+"</BODY></HTML>");
	        button.setAlignmentX(Component.CENTER_ALIGNMENT); // bouton centré dans la liste
	        if(t.isLate()){button.setForeground(Color.red);} // bouton rouge si la tâche est en retard
	        this.add(button); // ajout du bouton au panel
		}
	}
}
