package todo;

public class TaskException extends Exception {
	
	public TaskException(){
		super();
	}
	
	public TaskException(String errMsg){
		super(errMsg);
	}
}
