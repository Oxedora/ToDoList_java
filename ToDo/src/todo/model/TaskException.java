package todo.model;



public class TaskException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TaskException(){
		super();
	}
	
	public TaskException(String errMsg){
		super(errMsg);
	}
}
