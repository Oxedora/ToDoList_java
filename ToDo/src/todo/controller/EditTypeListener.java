package todo.controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import todo.view.CenterPanel;

public class EditTypeListener implements ActionListener{
	private CenterPanel center;
	
	public EditTypeListener(CenterPanel center) {
		super();
		this.center = center;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JPanel editPane = new JPanel();
		editPane.setLayout(new GridLayout(0, 1));
		
		JTextField tType = new JTextField(center.getTypeList().getSelectedValue());
		
		editPane.add(new JLabel("New type name : "));
		editPane.add(tType);
		
		int answer = JOptionPane.showConfirmDialog(null, editPane, "Mess it !", JOptionPane.OK_CANCEL_OPTION);
		
		if(answer == JOptionPane.OK_OPTION){
			center.setTypeList(this.center.getTypeList());
			// mettre a jour la liste des types
			// parcourir la liste tache pour modifier l'ancien type
			for()
		}
	}
	
	
}
