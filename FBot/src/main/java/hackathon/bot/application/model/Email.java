package hackathon.bot.application.model;

public class Email {

	private String to;
	private String msg;
	private String subject;

	public Email() {
	}

	public Email(String to, String msg, String subject) {
		super();
		this.to = to;
		this.msg = msg;
		this.subject = subject;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Email [to=" + to + ", msg=" + msg + ", subject=" + subject + "]";
	}

}
