package todo.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class NorthPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<String> sortArray = new ArrayList<String>(); // all kinds of sort created
	@SuppressWarnings("rawtypes")
	JComboBox sortCB; // contains the sorts
	
	/**
	 * Contains the sort JComboBox to sort the tasks
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public NorthPanel(){
		this.setLayout(new FlowLayout()); // to center the information
		
		/***************** Creating the comboBox *****************/
		this.sortArray.add("Sort by date of death");
		this.sortArray.add("Sort by intermediate date of putrefaction");
		this.sortArray.add("Sort by importance of death");
		this.sortCB = new JComboBox(sortArray.toArray(new String[sortArray.size()])); // created comboBox to choose the sort
		
		/********* Adding the comboBox to the north panel ********/
		this.add(sortCB); // sorts added to the north panel
		this.setBackground(Color.darkGray); // color of the north panel
	}
	
	/**
	 * @return the list of sort
	 */
	public ArrayList<String> getSortArray(){
		return this.sortArray;
	}
	
	/**
	 * @param newSort : the new sort tu put in the comboBox
	 */
	public void setASort(String newSort){
		this.sortArray.add(newSort);
		this.updateSortCB();
	}
	
	/**
	 * @param oldSort : the sort to be deleted
	 */
	public void delASort(String oldSort){
		this.sortArray.remove(oldSort);
		this.updateSortCB();
	}
	
	/**
	 * Update the view of the JComboBox
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateSortCB(){
		this.sortCB.removeAllItems();
		this.sortCB = new JComboBox((ComboBoxModel) sortArray);
	}

	/**
	 * @return the JComboBox
	 */
	@SuppressWarnings("rawtypes")
	public JComboBox getSortCB() {
		return sortCB;
	}

	/**
	 * @param sortCB : the new JComboBox
	 */
	@SuppressWarnings("rawtypes") 
	public void setSortCB(JComboBox sortCB) {
		this.sortCB = sortCB;
	}

	/**
	 * @param sortArray : the new list of sorts
	 */
	public void setSortArray(ArrayList<String> sortArray) {
		this.sortArray = sortArray;
	}
}
