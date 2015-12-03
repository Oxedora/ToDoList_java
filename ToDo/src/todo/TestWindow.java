package todo;

import java.time.LocalDate;

import javax.swing.*;

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
		todo.inProgress.add(t1);
		todo.inProgress.add(t2);
		todo.inProgress.add(t3);
		todo.finished.add(t4);
		todo.finished.add(t2);
		todo.finished.get(0).setIsDone(true);
		todo.typesList.addElement("travail");
		todo.typesList.addElement("personnel");
		
		WindowOrganizer worg = new WindowOrganizer(todo.inProgress, todo.finished, todo.typesList);
		
	}
}
