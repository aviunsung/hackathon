package hackathon.bot.application.service;

import hackathon.bot.application.model.COMMANDS;
import hackathon.bot.application.model.Email;

import java.util.Scanner;

public class BotLauncher {

	private static SendMailService mailService = new SendMailService();

	public static void main(String[] args) {

		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);

			while (true) {
				printMessage("Enter command or for exit type exit:\t");
				String sentence = scanner.nextLine();
				if (sentence.equalsIgnoreCase("exit")) {
					break;
				}

				if (sentence.equalsIgnoreCase("help")) {
					printHelp();
					continue;
				}

				if (sentence.equalsIgnoreCase(COMMANDS.SEND_MAIL.getCommands())) {
					Email email = getMessageDetail(scanner);
					doJob(COMMANDS.SEND_MAIL, email);
				} else {
					System.out.println("Sorry! this Command is not avialable\t");
				}
			}

			printMessage("Bye bye !!!");
		} catch (Exception e) {
			System.out.println("Unable to process request, plz retry again");
		} finally {
			scanner.close();
		}

	}

	public static void printMessage(String message) {
		char[] charArray = message.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			System.out.print(charArray[i]);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void doJob(COMMANDS command, Email email) {
		switch (command) {
		case SEND_MAIL:
			mailService.sendOutlookMail(email.getTo(), email.getMsg(), email.getSubject());
			break;

		default:
			break;
		}
	}
	
	public static void printHelp(){
		System.out.println("\tHELP");
		System.out.println("\t=====================================");
		System.out.println("\t1. For sending mail type send mail.");
		System.out.println("\t2. To Exit system type exit.");
	}
	
	public static Email getMessageDetail(Scanner scanner){
		printMessage("Enter Message :: ");
		String msg = scanner.nextLine();
		printMessage("Enter Subject :: ");
		String subject = scanner.nextLine();
		printMessage("Enter Recipient :: ");
		String to = scanner.nextLine();
		Email email = new Email(to, msg, subject);
		return email;
	}
}
