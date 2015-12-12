package todo.model;

import java.util.Comparator;

public class SortByIntEndingDate implements Comparator<Task>{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * Sort the lists by the intermediate ending date of the tasks
	 */
	@Override
	public int compare(Task o1, Task o2) {
		return o1.getIntEndingDate().compareTo(o2.getIntEndingDate());
	}
}