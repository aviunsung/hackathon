package hackathon.bot.application;

import java.util.Scanner;

import hackathon.bot.application.service.BotInputProcessor;

public class BotChat {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try{
			System.out.println("Hey How can I help you?");
			String line=null;
			while(true){
				line=scanner.nextLine();
				if(line.equalsIgnoreCase("exit")){
					break;
				}
				System.out.println(BotInputProcessor.INSTANCE.process(line));
			}
		}finally{
			scanner.close();
		}

	}
}
