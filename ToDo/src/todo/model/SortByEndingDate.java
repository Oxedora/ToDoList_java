package todo.model;

import java.util.*;

public class SortByEndingDate implements Comparator<Task> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * Sort the list by the ending date of the tasks
	 */
	@Override
	public int compare(Task o1, Task o2) {
		return o1.getEndingDate().compareTo(o2.getEndingDate());
	}

}
