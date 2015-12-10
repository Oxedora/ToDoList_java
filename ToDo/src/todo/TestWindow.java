package todo;

import java.time.LocalDate;

import javax.swing.*;

import todo.model.Importance;
import todo.model.LongTerm;
import todo.model.Organizer;
import todo.model.Punctual;
import todo.model.TaskException;
import todo.view.WindowOrganizer;

public class TestWindow {
	public static void main(String[] args) throws TaskException {
		LocalDate d1 = LocalDate.of(2015, 12, 14);
		LocalDate d2 = LocalDate.of(2016, 1, 3);
		LocalDate d3 = LocalDate.of(2016, 1, 10);
		LocalDate d4 = LocalDate.of(2016, 1, 7);
		
		Punctual t1 = new Punctual("Courses", "Legumes, viande et eau", "divers", 
				Importance.Normal, d1);
		LongTerm t2 = new LongTerm("Partiels", "Prepare uranus", "scolaire", 
				Importance.High, d2, d3);
		Punctual t3 = new Punctual("Truc", "faire des trucs", "do you even truc ?", 
				Importance.Low, d4);
		Punctual t4 = new Punctual("doneTask", "already done !", "lol no care",
				Importance.Normal, d1);
		
		Organizer todo = new Organizer();
		todo.getInProgress().add(t1);
		todo.getInProgress().add(t2);
		todo.getInProgress().add(t3);
		todo.getFinished().add(t4);
		todo.getFinished().get(0).setIsDone(true);
		todo.getTypesList().addElement("travail");
		todo.getTypesList().addElement("personnel");
		todo.getTypesList().addElement("");
		
		WindowOrganizer worg = new WindowOrganizer(todo.getInProgress(), todo.getFinished(), todo.getTypesList());

	}
}
