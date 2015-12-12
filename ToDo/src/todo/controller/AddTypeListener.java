package todo.controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import todo.view.CenterPanel;

public class AddTypeListener implements ActionListener{
	private CenterPanel center;

	/**
	 * Enable the button to add a type when clicked
	 * @param center : the panel that contains the relevant view components
	 */
	public AddTypeListener(CenterPanel center) {
		super();
		this.center = center;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean isokay = false;

		while(!isokay){
			JTextField tType = new JTextField(center.getTypeList().getSelectedValue());

			JPanel editPane = new JPanel();
			editPane.setLayout(new GridLayout(0, 1));
			editPane.add(new JLabel("New type name : "));
			editPane.add(tType);

			int answer = JOptionPane.showConfirmDialog(null, editPane, "Give it !", JOptionPane.OK_CANCEL_OPTION);
		
			if(answer == JOptionPane.OK_OPTION){
				String newType = tType.getText();
				if(newType.length() == 0){
					JOptionPane.showMessageDialog(null, "Not an acceptable name", "Doom on you", JOptionPane.ERROR_MESSAGE);
					isokay = false;
				}
				else{
					center.getTypes().add(newType);
					this.center.setTypeList(this.center.getTypes());
					isokay = true;
				}
			}
			else{isokay = true;}

		}
	}

}
