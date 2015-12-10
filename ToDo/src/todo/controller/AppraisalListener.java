package todo.controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import todo.model.Task;
import todo.model.TaskException;

public class AppraisalListener  implements ActionListener{
	private Vector<Task> inProgress;
	private Vector<Task> finished;

	public AppraisalListener(Vector<Task> inProgress, Vector<Task> finished) {
		super();
		this.inProgress = inProgress;
		this.finished = finished;
	}

	public void actionPerformed(ActionEvent e) {
		boolean isOk = false;
		
		while(!isOk){
			JPanel selectDate = new JPanel();
			selectDate.setLayout(new GridLayout(0, 1));
			
			JTextField tDateBeg= new JTextField("yyyy-mm-dd"); //Field to get the beginning date
			JTextField tDateEnd= new JTextField("yyyy-mm-dd"); //Field to get the ending date
			
			selectDate.add(new JLabel("From : "));
			selectDate.add(tDateBeg);
			selectDate.add(new JLabel("To : "));
			selectDate.add(tDateEnd);
			
			int answer = JOptionPane.showConfirmDialog(null, selectDate, "Preview of work", JOptionPane.OK_CANCEL_OPTION);
			
			if(answer == JOptionPane.OK_OPTION){
				try{
					if(tDateBeg.getText().equals("yyyy-mm-dd") || tDateEnd.getText().equals("yyyy-mm-dd")){
						JOptionPane.showMessageDialog(null, "Please fill the fields.",
								"Doom on you", JOptionPane.ERROR_MESSAGE);
					}
					else if(LocalDate.parse(tDateEnd.getText()).isBefore(LocalDate.parse(tDateBeg.getText()))){
						JOptionPane.showMessageDialog(null, "Beginning date must be before ending date.",
								"Doom on you", JOptionPane.ERROR_MESSAGE);
					}
					else{
						
					}
				} catch(java.time.format.DateTimeParseException e1){
					JOptionPane.showMessageDialog(null, "Date must be of the form yyyy-mm-dd",
							"Doom on you", JOptionPane.ERROR_MESSAGE);
					isOk = false;
				}
			}else{isOk = true;}
		}
	}
}
