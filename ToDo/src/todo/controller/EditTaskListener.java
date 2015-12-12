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
import todo.model.Task;
import todo.model.TaskException;
import todo.view.CenterPanel;

public class EditTaskListener implements ActionListener{
	private CenterPanel center;
	private Vector<Task> inProgress;
	private Vector<Task> finished;
	
	/**
	 * Enable to edit a task when the button is clicked
	 * @param center : the panel that contains the relevant view components
	 * @param inProgress : the list of task in progress
	 * @param finished : the list of done tasks
	 */
	public EditTaskListener(CenterPanel center, Vector<Task> inProgress, Vector<Task> finished){
		this.center = center;
		this.inProgress = inProgress;
		this.finished = finished;
	}

	public void actionPerformed(ActionEvent e) {
		boolean isokay = false;
		Importance[] imp = {Importance.High, Importance.Normal, Importance.Low};

		//Loop that display the JPanel with the textField to get the information to update the task
		while(!isokay){
			isokay = true;
			JTextField tTitle = new JTextField(this.center.getDetailedTask().getButtonP().getTask().getTitle()); //Field to get the title of the task
			String[] typesArray = new String[this.center.getDetailedTask().getTypes().size()];
			for(int i = 0; i < this.center.getDetailedTask().getTypes().size(); i++){typesArray[i] = this.center.getDetailedTask().getTypes().get(i);} // init the array filled with the types
			JComboBox<String> typesCB = new JComboBox<String>(typesArray); // adding the array of type into a JComboBox for interactions with the user
			JTextField tProg = new JTextField(String.valueOf(this.center.getDetailedTask().getButtonP().getTask().getActualProgress()));
			JTextField tDesc = new JTextField(this.center.getDetailedTask().getButtonP().getTask().getDescription()); //Field to get the description of the task
			JComboBox<Importance> tImp = new JComboBox<Importance>(imp); //JComboBox to select the desired Importance
			tImp.setSelectedItem(this.center.getDetailedTask().getButtonP().getTask().getImportance()); //Set to the actual importance
			JTextField tDateBeg= new JTextField(this.center.getDetailedTask().getButtonP().getTask().getBeginningDate().toString()); //Field to get the beginning date
			JTextField tDateEnd= new JTextField(this.center.getDetailedTask().getButtonP().getTask().getEndingDate().toString()); //Field to get the ending date
			JPanel editPan; //The JPanel that hold all of the above

			if(this.center.getDetailedTask().getButtonP().getTask().getClass().getName() == LongTerm.class.getName()){
				editPan = new JPanel(new GridLayout(7,1,5,5));
			}
			else{
				editPan = new JPanel(new GridLayout(5,1,5,5));
			}

			//Adding the elements
			editPan.add(new JLabel("New task title :"));
			editPan.add(tTitle);
			editPan.add(new JLabel("New task type : "));
			editPan.add(typesCB);
			if(this.center.getDetailedTask().getButtonP().getTask().getClass().getName() == LongTerm.class.getName()){
				editPan.add(new JLabel("New task progress : "));
				editPan.add(tProg);
			}
			editPan.add(new JLabel("New description : "));
			editPan.add(tDesc);
			editPan.add(new JLabel("New importance : "));
			editPan.add(tImp);
			if(this.center.getDetailedTask().getButtonP().getTask().getClass().getName() == LongTerm.class.getName()){
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
					if(Integer.parseInt(tProg.getText()) == 100){
						answer = JOptionPane.showConfirmDialog(null, 
								"Progress of 100 ? By the divines, it's gonna be done ! Are you sure ? It cannot be undone !",
								"Holy end", JOptionPane.YES_NO_OPTION);
					}
					if(answer == JOptionPane.YES_OPTION || answer == JOptionPane.OK_OPTION){
						this.center.getDetailedTask().getButtonP().getTask().setTitle(tTitle.getText());
						this.center.getDetailedTask().getButtonP().getTask().setType(typesCB.getSelectedItem().toString());
						this.center.getDetailedTask().getButtonP().getTask().setActualProgress(Integer.parseInt(tProg.getText()));
						this.center.getDetailedTask().getButtonP().getTask().setDescription(tDesc.getText());
						this.center.getDetailedTask().getButtonP().getTask().setImportance((Importance) tImp.getSelectedItem());
						this.center.getDetailedTask().getButtonP().getTask().setBeginningDate(LocalDate.parse(tDateBeg.getText()));
						this.center.getDetailedTask().getButtonP().getTask().setEndingDate(LocalDate.parse(tDateEnd.getText()));
						this.center.getDetailedTask().getButtonP().setText(); //Setting the task in the DisplayTask JPanel
						this.center.getDetailedTask().setTask(this.center.getDetailedTask().getButtonP()); //Setting the informations of the task in the DetailedTask JPanel
						
						if(Integer.parseInt(tProg.getText()) == 100){
							this.center.getDetailedTask().getButtonP().getTask().setIsDone(true); // the task is now done
							
							this.inProgress.removeElement(this.center.getDetailedTask().getButtonP().getTask()); // removing the task from tasks in progress
							this.center.getInProgressTasks().sortTask(this.inProgress, this.center.getDetailedTask()); // updates the view
							this.center.getInProgressTasks().repaint();
							this.center.getInProgressTasks().revalidate();
							
							this.finished.add(this.center.getDetailedTask().getButtonP().getTask()); // adding the task to finished tasks
							this.center.getDoneTasks().sortTask(this.finished, this.center.getDetailedTask()); // updates the view
							this.center.getDoneTasks().repaint();
							this.center.getDoneTasks().revalidate();
							
							this.center.getDetailedTask().setTask(this.center.getDetailedTask().getButtonP());
							this.center.repaint();
							this.center.revalidate();
						}
					}
					else{isokay=false;}
				}
				catch (TaskException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), 
							"Doom on you",JOptionPane.ERROR_MESSAGE);
					isokay = false;
				}
				catch(java.time.format.DateTimeParseException e1){
					JOptionPane.showMessageDialog(null, "Date must be of the form yyyy-mm-dd",
							"Doom on you", JOptionPane.ERROR_MESSAGE);
					isokay = false;
				}
				catch(java.lang.NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Enter a valid number",
							"Doom on you", JOptionPane.ERROR_MESSAGE);
					isokay = false;
				}
			}
		}
	}
}
