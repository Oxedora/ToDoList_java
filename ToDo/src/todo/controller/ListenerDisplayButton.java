package todo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import todo.view.ButtonPushed;
import todo.view.DetailedTask;


//The listener for the clickable tasks
public class ListenerDisplayButton implements ActionListener{
	private DetailedTask dt;
	
	public ListenerDisplayButton(DetailedTask dt){
		this.dt = dt;
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.dt.setTask((ButtonPushed) e.getSource());
	}

}
