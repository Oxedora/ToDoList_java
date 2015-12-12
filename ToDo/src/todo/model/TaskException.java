package todo.model;



public class TaskException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor by default
	 */
	public TaskException(){
		super();
	}
	
	/**
	 * @param errMsg : the message displayed by the error
	 */
	public TaskException(String errMsg){
		super(errMsg);
	}
}
