package hackathon.bot.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hackathon.bot.application.service.BotInputProcessor;

public class BotChat {
	public static List<String>userInputs=new ArrayList<String>();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<String>userInputs=new ArrayList<String>();
		try{
			System.out.println("Hey How can I help you?");
			String line=null;
			while(true){
				line=scanner.nextLine();
				userInputs.add(line);
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
