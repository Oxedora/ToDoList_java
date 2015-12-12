package todo.model;

import java.util.Comparator;

public class SortByImportance implements Comparator<Task>{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * Sort the list by the importance of the tasks
	 */
	@Override
	public int compare(Task o1, Task o2) {
		return o1.getImportance().compareTo(o2.getImportance()) != 0 ? 
				o1.getImportance().compareTo(o2.getImportance()) : 
					o1.getEndingDate().compareTo(o2.getEndingDate());
	}
	
}
