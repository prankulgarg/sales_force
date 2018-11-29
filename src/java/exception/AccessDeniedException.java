package exception;

public class AccessDeniedException extends Exception {

	
	
	private static final long serialVersionUID = 1L;
	/**
	 * @param
	 */
	public AccessDeniedException(){
		super("ACL restriction at bl level");
	}
	/**
	 * 
	 * @param message
	 */
	public AccessDeniedException(String message){
		super(message);
	}
	/**
	 * 
	 * @param throwable
	 */
	public AccessDeniedException(Throwable throwable){
		super(throwable);
	}
	/**
	 * 
	 * @param message
	 * @param throwable
	 */
	public AccessDeniedException(String message,Throwable throwable){
		super(message,throwable);
	}

}
