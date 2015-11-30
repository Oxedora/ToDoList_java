package todo;

import java.awt.*;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.*;

public class WindowOrganizer extends JFrame{
	
	//Classes internes de JPanels qui contiendront les listes de tâches sous forme de boutons
	private class panTask extends JPanel{
		private Vector<Task> taskList;
		private String title;
		
		public panTask(Vector<Task> taskList, String title) {
			super();
			this.taskList = taskList;
			this.title = title;
			
			for(Task t : this.taskList){
				this.add(new ButtonInfo(t.getTitle(), t.getEndingDate()));
			}
		}
		
	//Creation de boutons pour chaque tâche qui affichent leurs informations dans le JPanel du centre
	}
	
	//Boutons qui contiendrons les tâches. Cliquer dessus affichera la tache au centre
	private class ButtonInfo extends Button{
		private String title;
		private LocalDate taskEndDate;
		
		public ButtonInfo(String title, LocalDate taskEndDate)
				throws HeadlessException {
			super();
			this.title = title;
			this.taskEndDate = taskEndDate;
		}
		
		
	}
	public WindowOrganizer(){
		this.setTitle("Organizer");
		this.setSize(300, 600);
		//Pour centrer la fenetre
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		//Instanciation des 3 JPanels principaux
		JPanel panProgress = new JPanel();
		panProgress.setBackground(Color.lightGray);
		
		
		JPanel panView = new JPanel();
		panView.setBackground(Color.white);
		
		JPanel panFinished = new JPanel();
		panFinished.setBackground(Color.darkGray);
		
		this.getContentPane().add(panProgress);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
