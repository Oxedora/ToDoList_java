package todo.controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

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
	private ButtonPushed buttonP;
	private DetailedTask dt;

	public ListenerEdit(ButtonPushed buttonP, DetailedTask dt){this.buttonP = buttonP; this.dt = dt;}

	public void actionPerformed(ActionEvent e) {
		boolean isokay = false;
		Importance[] imp = {Importance.High, Importance.Normal, Importance.Low};
		
		//Loop that display the JPanel with the textField to get the information to update the task
		while(!isokay){
			isokay = true;
			JTextField tTitle = new JTextField(buttonP.getTask().getTitle()); //Field to get the title of the task
			JTextField tType = new JTextField(buttonP.getTask().getType()); //Field to get the type of the task
			JTextField tDesc = new JTextField(buttonP.getTask().getDescription()); //Field to get the description of the task
			JComboBox<Importance> tImp = new JComboBox<Importance>(imp); //JComboBox to select the desired Importance
			tImp.setSelectedItem(buttonP.getTask().getImportance()); //Set to the actual importance
			JTextField tDateBeg= new JTextField(buttonP.getTask().getBeginningDate().toString()); //Field to get the beginning date
			JTextField tDateEnd= new JTextField(buttonP.getTask().getEndingDate().toString()); //Field to get the ending date
			JPanel editPan; //The JPanel that hold all of the above
			
			if(buttonP.getTask().getClass().getName() == LongTerm.class.getName()){
				editPan = new JPanel(new GridLayout(6,1,5,5));
			}
			else{
				editPan = new JPanel(new GridLayout(5,1,5,5));
			}

			//Adding the elements
			editPan.add(new JLabel("New task title :"));
			editPan.add(tTitle);
			editPan.add(new JLabel("New task type : "));
			editPan.add(tType);
			editPan.add(new JLabel("New description : "));
			editPan.add(tDesc);
			editPan.add(new JLabel("New importance : "));
			editPan.add(tImp);
			if(buttonP.getTask().getClass().getName() == LongTerm.class.getName()){
				editPan.add(new JLabel("New fall date : "));
				editPan.add(tDateBeg);
			}
			editPan.add(new JLabel("New unhappy date : "));
			editPan.add(tDateEnd);
			
			//Showing the pop-up
			JOptionPane.showConfirmDialog(null, editPan, "The Fate modifier", JOptionPane.OK_CANCEL_OPTION);

			/*
			 * Getting the change if there is and display an error pop up 
			 * if wrong informations are given
			 */
			try {
				buttonP.getTask().setTitle(tTitle.getText());
				buttonP.getTask().setType(tType.getText());
				buttonP.getTask().setDescription(tDesc.getText());
				buttonP.getTask().setImportance((Importance) tImp.getSelectedItem());
				buttonP.getTask().setBeginningDate(LocalDate.parse(tDateBeg.getText()));
				buttonP.getTask().setEndingDate(LocalDate.parse(tDateEnd.getText()));
				buttonP.setTask(); //Setting the task in the DisplayTask JPanel
				dt.setTask(buttonP); //Setting the informations of the task in the DetailedTask JPanel
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
