package todo;

import java.time.LocalDate;
import java.util.Collections;

import todo.model.Importance;
import todo.model.LongTerm;
import todo.model.Organizer;
import todo.model.Punctual;
import todo.model.SortByEndingDate;
import todo.model.SortByImportance;
import todo.model.SortByIntEndingDate;
import todo.model.Task;
import todo.model.TaskException;

public class TestToDo {

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
		todo.getInProgress().add(t1);
		todo.getInProgress().add(t2);
		todo.getInProgress().add(t3);
		
		System.out.println("Comparaison de High et Normal : "+Importance.High.compareTo(Importance.Normal)+"\n");
		
		System.out.println("Avant le tri");
		for(Task t : todo.getInProgress()){
			System.out.println(t.getTitle());
		}
		
		Collections.sort(todo.getInProgress(), new SortByEndingDate());
	
		System.out.println("Tri par dates de fin");
		for(Task t : todo.getInProgress()){
			System.out.println(t.getTitle());
		}
	
		Collections.sort(todo.getInProgress(), new SortByIntEndingDate());
		
		System.out.println("Tri par dates de fin intermédiaires");
		for(Task t : todo.getInProgress()){
			System.out.println(t.getTitle());
		}
	
		Collections.sort(todo.getInProgress(), new SortByImportance());
	
		System.out.println("Tri par importance, puis dates de fin");
		for(Task t : todo.getInProgress()){
			System.out.println(t.getTitle());
		}
		
	}

}
