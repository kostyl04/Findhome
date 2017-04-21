package by.kostyl.findhome.mail;



public abstract class MessageTemplate {
	private static final String from="kostyl04@gmail.com";
	private String message;
	private String to;
	private String subject;
	public String generateMessage(){
		return null;
		
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public static String getFrom() {
		return from;
	};

	

}
