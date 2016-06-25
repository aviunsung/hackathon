package hackathon.bot.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import hackathon.bot.application.model.BotApplicationConstant;
import hackathon.bot.application.model.ProcessedResult;
import hackathon.bot.application.model.RuleMatcherResultEnum;
import hackathon.bot.application.service.AIEngine;
import hackathon.bot.application.service.BotInputProcessor;
import hackathon.bot.application.util.BotUtil;

public class BotChat {
	public static List<String>userInputs=new ArrayList<String>();
	public static List<String>userInputsYesNo=new ArrayList<String>();

	public static void main(String[] args) throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		List<String>userInputs=new ArrayList<String>();
		System.out.println("Hey How can I help you?");
		String line=null;
		while(true){
			line=scanner.nextLine();
			userInputs.add(line);
			if(line.equalsIgnoreCase("exit")){
				break;
			}
			ProcessedResult processedResult=BotInputProcessor.INSTANCE.process(line);
			System.out.println(processedResult.getMessage());
			Thread.sleep(3000);
			List<RuleMatcherResultEnum>reccomendations=AIEngine.getReccomendationMap().get(processedResult.getInput());
			Random random=new Random(5);
			if(reccomendations!=null){
				RuleMatcherResultEnum rule=reccomendations.get(random.nextInt(5));
				if(rule!=null){
					System.out.println(rule.value);
					String userConsent=scanner.nextLine();
					if(BotUtil.checkOccurence(userConsent, BotApplicationConstant.yes)){
						BotInputProcessor.INSTANCE.processRuleMatcherResult(rule, line);
					}
				}
			}
		}
	}
}
