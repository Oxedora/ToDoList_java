package todo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
