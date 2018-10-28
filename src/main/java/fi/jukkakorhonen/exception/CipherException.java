package fi.jukkakorhonen.exception;

public class CipherException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Exception exception;
	private String exceptionmessage;
	
	public CipherException() {}
	
	public CipherException(Exception exception, String message) {
		this.exception = exception;
		this.exceptionmessage = message;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String getExceptionmessage() {
		return exceptionmessage;
	}

	public void setExceptionmessage(String exceptionmessage) {
		this.exceptionmessage = exceptionmessage;
	}
	
	
	
}
