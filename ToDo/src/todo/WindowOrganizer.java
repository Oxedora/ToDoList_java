package todo;

import java.awt.*;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.*;

public class WindowOrganizer extends JFrame{
	
	public WindowOrganizer(Vector<Task> progressList, Vector<Task> finishedList, Vector<String> types){
		this.setTitle("Organizer");
		
		// en attendant d'avoir intégré les layoutManager
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
		int hauteur = (int)tailleEcran.getHeight(); 
		int largeur = (int)tailleEcran.getWidth();
		
		this.setSize(700, 600);
		//Pour centrer la fenetre
		this.setLocationRelativeTo(null);
		//this.setResizable(false);
		//this.setLayout(new BorderLayout());
		
		
		JPanel[][] jpTab = new JPanel[3][3];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				jpTab[i][j] = new JPanel();
			}
		}
		jpTab[0][1].setLayout(new FlowLayout());
		String[] comboBoxItems = new String[types.size()];
		for(int i = 0; i < types.size(); i++){comboBoxItems[i] = types.get(i);}
		JComboBox cb = new JComboBox(comboBoxItems);
		jpTab[0][1].add(cb);
		
		
		//Instanciation des 3 JPanels principaux
		DisplayTasks progressPan = new DisplayTasks(progressList, "Tasks in progress");
		progressPan.setBackground(Color.lightGray);
		
		DetailedTask detailedTask = new DetailedTask(finishedList.get(0));
		
		DisplayTasks finishedPan = new DisplayTasks(finishedList, "Finished tasks");
		finishedPan.setBackground(Color.gray);
		
		//Ajout des panels a la fenêtre

		this.getContentPane().add(progressPan, BorderLayout.WEST);
		this.getContentPane().add(detailedTask, BorderLayout.CENTER);
		this.getContentPane().add(finishedPan, BorderLayout.EAST);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void addComponentsToPane(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
 
        
    }

}
