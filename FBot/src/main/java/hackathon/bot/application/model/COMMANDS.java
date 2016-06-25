package hackathon.bot.application.model;

public enum COMMANDS {

	SEND_MAIL("SEND MAIL"), SEND_SKYPE("SEND SKYPE");

	private String command;

	public String getCommands() {
		return this.command;
	}

	COMMANDS(String action) {
		this.command = action;
	}

}
