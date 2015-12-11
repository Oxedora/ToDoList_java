package todo.controller;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
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
			
			int answer = JOptionPane.showConfirmDialog(null, selectDate, 
					"Preview of work", JOptionPane.OK_CANCEL_OPTION);
			
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
						/* panel that displays the informations */
						JPanel displayAppraisal = new JPanel();
						displayAppraisal.setLayout(new FlowLayout());
						
						/* total of tasks */
						int total = 0;
						for(Task t : this.inProgress){total++;}
						for(Task t : this.finished){total++;}
						if(total == 0){total = 1;}
						
						/* tasks in progress to display */
						JList<String> inProgress = new JList<String>(
								inProgress(LocalDate.parse(tDateBeg.getText()),
										LocalDate.parse(tDateEnd.getText())));
						displayAppraisal.add(inProgress);
						
						/* percentages on tasks */
						int doneInTime = doneInTime(LocalDate.parse(tDateBeg.getText()),
						 		LocalDate.parse(tDateEnd.getText()));
						int doneLate = doneLate(LocalDate.parse(tDateBeg.getText()),
								LocalDate.parse(tDateEnd.getText()));
						int stillNotDone = stillNotDone(LocalDate.parse(tDateBeg.getText()),
								LocalDate.parse(tDateEnd.getText()));
						
						/* displaying the percentages */
						JPanel displayPerCent = new JPanel(); // displays all percentages 
						displayPerCent.setLayout(new BoxLayout(displayPerCent, BoxLayout.Y_AXIS)); // in columns
						
						displayPerCent.add(new JLabel(doneInTime+" tasks done in time."));
						displayPerCent.add(new JLabel(doneLate+" tasks done late."));
						displayPerCent.add(new JLabel(stillNotDone+" tasks still not done and late."));
						
						displayAppraisal.add(displayPerCent);
						
						JOptionPane.showConfirmDialog(null, displayAppraisal, "View of work from "+tDateBeg.getText()+
					" to "+tDateEnd.getText(), JOptionPane.OK_CANCEL_OPTION);
					}
				} catch(java.time.format.DateTimeParseException e1){
					JOptionPane.showMessageDialog(null, "Date must be of the form yyyy-mm-dd",
							"Doom on you", JOptionPane.ERROR_MESSAGE);
				}
			}else{isOk = true;}
		}
	}
	
	public Vector<String> inProgress(LocalDate beg, LocalDate end){
		Vector<String> tasksToDo = new Vector<String>();
		for(Task t : this.inProgress){
			if(t.getEndingDate().isAfter(beg.minusDays(1)) // if the ending date is during the period
					&& t.getEndingDate().isBefore(end.plusDays(1)))
			{
				tasksToDo.add(t.getTitle());
			}
		}
		return tasksToDo;
	}
	
	public int doneInTime(LocalDate beg, LocalDate end){
		int doneInTime = 0;
		for(Task t : this.finished){
			if(t.getEndingDate().isAfter(beg.minusDays(1)) // if the ending date is during the period
					&& t.getEndingDate().isBefore(end.plusDays(1))
					&& t.getEffectiveEndingDate().isBefore(t.getEndingDate().plusDays(1))) // if done in time
			{
				doneInTime++;
			}
		}
		return doneInTime;
	}
	
	public int doneLate(LocalDate beg, LocalDate end){
		int doneLate = 0;
		for(Task t : this.finished){
			if(t.getEndingDate().isAfter(beg.minusDays(1)) // if the ending date is during the period
					&& t.getEndingDate().isBefore(end.plusDays(1))
					&& t.getEffectiveEndingDate().isAfter(t.getEndingDate())) // if done late
			{
				doneLate++;
			}
		}
		return doneLate;
	}
	
	public int stillNotDone(LocalDate beg, LocalDate end){
		int stillNotDone = 0;
		for(Task t : this.inProgress){
			if(t.getEndingDate().isAfter(beg.minusDays(1)) // if the ending date is during the period
					&& t.getEndingDate().isBefore(end.plusDays(1))
					&& t.isLate()) // the task is late
			{
				stillNotDone++;
			}
		}
		return stillNotDone;
	}
}
