package todo;

import java.util.Comparator;

public class SortByIntEndingDate implements Comparator<Task>{

	@Override
	public int compare(Task o1, Task o2) {
		return o1.getIntEndingDate().compareTo(o2.getIntEndingDate());
	}
	
}
