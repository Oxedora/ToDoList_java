package todo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.*;

public class EditButton extends JButton {
	private Task t;
	public EditButton(Task t){
		super("The Fate modifier");
		this.t = t;
	}
	
}
