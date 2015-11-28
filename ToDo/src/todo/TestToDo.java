package todo;

import java.time.LocalDate;
import java.util.Collections;

public class TestToDo {

	public static void main(String[] args) {
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
		
		System.out.println("Comparaison de High et Normal : "+Importance.High.compareTo(Importance.Normal)+"\n");
		
		System.out.println("Avant le tri");
		for(Task t : todo.inProgress){
			System.out.println(t.getTitle());
		}
		
		Collections.sort(todo.inProgress, new SortByEndingDate());
	
		System.out.println("Tri par dates de fin");
		for(Task t : todo.inProgress){
			System.out.println(t.getTitle());
		}
	
		Collections.sort(todo.inProgress, new SortByIntEndingDate());
		
		System.out.println("Tri par dates de fin intermédiaires");
		for(Task t : todo.inProgress){
			System.out.println(t.getTitle());
		}
	
		Collections.sort(todo.inProgress, new SortByImportance());
	
		System.out.println("Tri par importance, puis dates de fin");
		for(Task t : todo.inProgress){
			System.out.println(t.getTitle());
		}
		
	}

}
