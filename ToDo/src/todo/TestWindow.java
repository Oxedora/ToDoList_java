package todo;

import java.time.LocalDate;

import javax.swing.*;

public class TestWindow {
	public static void main(String[] args) throws TaskException {
		LocalDate d1 = LocalDate.of(2015, 12, 14);
		LocalDate d2 = LocalDate.of(2016, 1, 3);
		LocalDate d3 = LocalDate.of(2016, 1, 10);
		LocalDate d4 = LocalDate.of(2016, 1, 7);
		
		Punctual t1 = new Punctual("Courses", "Legumes, viande et eau", "", 
				Importance.Normal, d1);
		LongTerm t2 = new LongTerm("Partiels", "Prepare uranus", "scolaire", 
				Importance.High, d2, d3);
		Punctual t3 = new Punctual("Truc", "faire des trucs", "", 
				Importance.Low, d4);
		
		Organizer todo = new Organizer();
		todo.inProgress.add(t1);
		todo.inProgress.add(t2);
		todo.inProgress.add(t3);
		
		WindowOrganizer worg = new WindowOrganizer(todo.inProgress, todo.finished);
		
	}
}
