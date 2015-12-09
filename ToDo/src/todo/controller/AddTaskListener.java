package todo.controller;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import todo.model.Importance;
import todo.model.LongTerm;
import todo.model.Punctual;
import todo.model.Task;
import todo.model.TaskException;
import todo.view.DetailedTask;
import todo.view.DisplayTasks;

public class AddTaskListener implements ActionListener{
	private Vector<Task> taskList;
	private DisplayTasks dit;
	private DetailedTask dt;
	private Vector<String> types;
 
	public AddTaskListener(Vector<Task> taskList, DisplayTasks dit, DetailedTask dt, Vector<String> types){
		this.taskList = taskList;
		this.dit = dit;
		this.dt = dt;
		this.types = types;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Importance[] imp = {Importance.High, Importance.Normal, Importance.Low};
		boolean isokay = false;

		while(!isokay){	
			isokay = true;
			JTextField tTitle = new JTextField("Unknown"); //Field to get the title of the task
			String[] typesArray = new String[types.size()];
			for(int i = 0; i < types.size(); i++){typesArray[i] = types.get(i);} // init the array filled with the types
			JComboBox typesCB = new JComboBox(typesArray); // adding the array of type into a JComboBox for interactions with the user
			
			//JTextField tType = new JTextField(); //Field to get the type of the task
			JTextField tDesc = new JTextField(); //Field to get the description of the task
			JComboBox<Importance> tImp = new JComboBox<Importance>(imp); //JComboBox to select the desired Importance
			JTextField tDateBeg= new JTextField("yyyy-mm-dd"); //Field to get the beginning date
			JTextField tDateEnd= new JTextField("yyyy-mm-dd"); //Field to get the ending date
			JPanel editPan= new JPanel(new GridLayout(6,1,5,5)); //The JPanel that hold all of the above

			//Adding the elements
			editPan.add(new JLabel("New task title :"));
			editPan.add(tTitle);
			editPan.add(new JLabel("New task type : "));
			editPan.add(typesCB);
			editPan.add(new JLabel("New description : "));
			editPan.add(tDesc);
			editPan.add(new JLabel("New importance : "));
			editPan.add(tImp);
			editPan.add(new JLabel("New fall date : "));
			editPan.add(tDateBeg);
			editPan.add(new JLabel("New unhappy date : "));
			editPan.add(tDateEnd);

			//Showing the pop-up
			int answer = JOptionPane.showConfirmDialog(null, editPan, "God simulator", JOptionPane.OK_CANCEL_OPTION);
			
			if(answer ==  JOptionPane.OK_OPTION){
				try {
					if(tDateBeg.getText().equals("yyyy-mm-dd") || tDateBeg.getText().equals(tDateEnd.getText())){
						Punctual t = new Punctual(tTitle.getText(),
								typesCB.getSelectedItem().toString(), //tType.getText(),
								tDesc.getText(),
								(Importance)tImp.getSelectedItem(),
								LocalDate.parse(tDateEnd.getText())
								);
	
						taskList.add(t);
						dit.sortTask(taskList, dt);
					}
					else{
						LongTerm t = new LongTerm(tTitle.getText(),
								typesCB.getSelectedItem().toString(), //tType.getText(),
								tDesc.getText(),
								(Importance)tImp.getSelectedItem(),
								LocalDate.parse(tDateBeg.getText()),
								LocalDate.parse(tDateEnd.getText())
								);
	
						taskList.add(t);
						dit.sortTask(taskList, dt);
					}
				} catch (TaskException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), 
							"Doom on you",JOptionPane.ERROR_MESSAGE);
					isokay = false;
				}
				catch(java.time.format.DateTimeParseException e1){
					JOptionPane.showMessageDialog(null, "Date must be of the form yyyy-mm-dd",
							"Doom on you", JOptionPane.ERROR_MESSAGE);
					isokay = false;
				}
			}
		}	
		
	}
}
