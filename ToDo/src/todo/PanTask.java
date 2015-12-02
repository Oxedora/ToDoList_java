package todo;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.*;


//Classes internes de JPanels qui contiendront les listes de tâches sous forme de boutons
public class PanTask extends JPanel{
	private Vector<Task> taskList;
	private String title;
	private JTable buttonTab;
	
	public PanTask(Vector<Task> taskList, String title) {
		super();
		this.taskList = taskList;
		this.title = title;
		this.setLayout(new BorderLayout());
		
		String[] titleT = {this.title};
		ButtonInfo[][] tab = new ButtonInfo[1][this.taskList.size()];
		int cpt = 0;
		for(Task t : this.taskList){
			tab[0][cpt] = new ButtonInfo(t.getTitle(), t.getEndingDate());
			cpt++;
		}
		
		buttonTab = new JTable(tab, titleT);
		/*
		 * Creation de boutons pour chaque tâche qui affichent leurs informations
		 *  dans le JPanel du centre		
		 */
		this.getRootPane().add(buttonTab, BorderLayout.CENTER);
	}              

}
