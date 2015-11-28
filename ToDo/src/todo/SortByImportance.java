package todo;

import java.util.Comparator;

public class SortByImportance implements Comparator<Task>{

	@Override
	public int compare(Task o1, Task o2) {

		return o1.getImportance().compareTo(o2.getImportance()) != 0 ? 
				o1.getImportance().compareTo(o2.getImportance()) : 
					o1.getEndingDate().compareTo(o2.getEndingDate());
	}
	
}
