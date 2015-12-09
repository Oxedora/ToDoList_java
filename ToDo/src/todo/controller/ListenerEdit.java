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
import todo.model.TaskException;
import todo.view.ButtonPushed;
import todo.view.DetailedTask;

public class ListenerEdit implements ActionListener{
	private DetailedTask dt;

	public ListenerEdit(DetailedTask dt){
		this.dt = dt;
	}

	public void actionPerformed(ActionEvent e) {
		boolean isokay = false;
		Importance[] imp = {Importance.High, Importance.Normal, Importance.Low};
		
		//Loop that display the JPanel with the textField to get the information to update the task
		while(!isokay){
			isokay = true;
			JTextField tTitle = new JTextField(this.dt.getButtonP().getTask().getTitle()); //Field to get the title of the task
			String[] typesArray = new String[dt.getTypes().size()];
			for(int i = 0; i < dt.getTypes().size(); i++){typesArray[i] = dt.getTypes().get(i);} // init the array filled with the types
			JComboBox typesCB = new JComboBox(typesArray); // adding the array of type into a JComboBox for interactions with the user

			JTextField tDesc = new JTextField(this.dt.getButtonP().getTask().getDescription()); //Field to get the description of the task
			JComboBox<Importance> tImp = new JComboBox<Importance>(imp); //JComboBox to select the desired Importance
			tImp.setSelectedItem(this.dt.getButtonP().getTask().getImportance()); //Set to the actual importance
			JTextField tDateBeg= new JTextField(this.dt.getButtonP().getTask().getBeginningDate().toString()); //Field to get the beginning date
			JTextField tDateEnd= new JTextField(this.dt.getButtonP().getTask().getEndingDate().toString()); //Field to get the ending date
			JPanel editPan; //The JPanel that hold all of the above
			
			if(this.dt.getButtonP().getTask().getClass().getName() == LongTerm.class.getName()){
				editPan = new JPanel(new GridLayout(6,1,5,5));
			}
			else{
				editPan = new JPanel(new GridLayout(5,1,5,5));
			}

			//Adding the elements
			editPan.add(new JLabel("New task title :"));
			editPan.add(tTitle);
			editPan.add(new JLabel("New task type : "));
			editPan.add(typesCB);
			editPan.add(new JLabel("New description : "));
			editPan.add(tDesc);
			editPan.add(new JLabel("New importance : "));
			editPan.add(tImp);
			if(this.dt.getButtonP().getTask().getClass().getName() == LongTerm.class.getName()){
				editPan.add(new JLabel("New fall date : "));
				editPan.add(tDateBeg);
			}
			editPan.add(new JLabel("New unhappy date : "));
			editPan.add(tDateEnd);
			
			//Showing the pop-up
			int answer = JOptionPane.showConfirmDialog(null, editPan, "The Fate modifier", JOptionPane.OK_CANCEL_OPTION);

			/*
			 * Getting the change if there is and display an error pop up 
			 * if wrong informations are given
			 */
			if(answer == JOptionPane.OK_OPTION){
				try {
					this.dt.getButtonP().getTask().setTitle(tTitle.getText());
					this.dt.getButtonP().getTask().setType(typesCB.getSelectedItem().toString());
					this.dt.getButtonP().getTask().setDescription(tDesc.getText());
					this.dt.getButtonP().getTask().setImportance((Importance) tImp.getSelectedItem());
					this.dt.getButtonP().getTask().setBeginningDate(LocalDate.parse(tDateBeg.getText()));
					this.dt.getButtonP().getTask().setEndingDate(LocalDate.parse(tDateEnd.getText()));
					this.dt.getButtonP().setText(); //Setting the task in the DisplayTask JPanel
					dt.setTask(this.dt.getButtonP()); //Setting the informations of the task in the DetailedTask JPanel
				} catch (TaskException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), 
							"Doom on you",JOptionPane.ERROR_MESSAGE);
					isokay = false;
				}
				catch(java.time.format.DateTimeParseException e1){
					JOptionPane.showMessageDialog(null, "Date muste be of the form yyyy-mm-dd",
							"Doom on you", JOptionPane.ERROR_MESSAGE);
					isokay = false;
				}
			}
		}
	}
}
