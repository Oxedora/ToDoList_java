package todo;

import java.awt.*;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.*;

public class WindowOrganizer extends JFrame{
	
	public WindowOrganizer(Vector<Task> progressList, Vector<Task> finishedList){
		this.setTitle("Organizer");
		this.setSize(300, 600);
		//Pour centrer la fenetre
		this.setLocationRelativeTo(null);
		//this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		//Instanciation des 3 JPanels principaux
		PanTask progressPan = new PanTask(progressList, "Tasks in progress");
		progressPan.setBackground(Color.lightGray);
		
		PanTask finishedPan = new PanTask(finishedList, "Finished tasks");
		finishedPan.setBackground(Color.darkGray);
		
		//Ajout des panels a la fenêtre
		this.getContentPane().add(progressPan, BorderLayout.WEST);
		this.getContentPane().add(finishedPan, BorderLayout.EAST);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
