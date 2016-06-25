package hackathon.bot.application.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hackathon.bot.application.model.BotApplicationConstant;
import hackathon.bot.application.model.Feedback;
import hackathon.bot.application.model.QuestionAnswer;
import hackathon.bot.application.model.QuestionConstant;
import hackathon.bot.application.model.RuleMatcherResultEnum;
import hackathon.bot.application.util.BotUtil;

public class BotInputProcessor {
	public static final BotInputProcessor INSTANCE=new BotInputProcessor();
	private BotInputProcessor(){}
	private static HashMap<Integer,List<Feedback>> feedbackCountMap=new HashMap<Integer,List<Feedback>>();
	public Object process(String userInput){
		Object result=processRules(userInput);
		if(result==null){
			return RuleMatcherResultEnum.SORRY_CANT_UNDERSTAND;
		}
		return result;
	}

	private Object processRules(String userInput) {
		List<RuleMatcherResultEnum>results=new ArrayList<RuleMatcherResultEnum>();
		validate(RuleMatcher.isMaxRating(userInput),results);	
		validate(RuleMatcher.countMaxRating(userInput),results);
		validate(RuleMatcher.getPeopleGivenMaxRating(userInput),results);
		validate(RuleMatcher.isMinRating(userInput),results);
		validate(RuleMatcher.countMinRating(userInput),results);
		validate(RuleMatcher.getPeopleGivenMinRating(userInput),results);
		validate(RuleMatcher.countRating(userInput),results);

		if(!results.isEmpty()){
			switch (results.get(results.size()-1)) {
			case COUNT_MAX_RATING:
				return getCountRating(5);
			case PEOPLE_MAX_RATING:
				return getPeopleWithRating(5);
			case COUNT_MIN_RATING:
				return getCountRating(1);
			case PEOPLE_MIN_RATING:
				return getPeopleWithRating(1);
			case COUNT_RATING:
				if(BotUtil.checkOccurenceAndReturnKeyword(userInput, BotApplicationConstant.ratings)!=null){
					return getCountRating(Integer.valueOf(BotUtil.checkOccurenceAndReturnKeyword(userInput, BotApplicationConstant.ratings)));
				}else{
					return RuleMatcherResultEnum.SORRY_CANT_UNDERSTAND;
				}
			default:
				return RuleMatcherResultEnum.SORRY_CANT_UNDERSTAND;
			}
		}
		return null;
	}

	private Object getPeopleWithRating(int rating) {
		if(feedbackCountMap.isEmpty()){
			List<Feedback>feedbacks=XmlDAO.INSTANCE.fetchAllFeedbacks();
			for (Feedback feedback : feedbacks) {
				for (QuestionAnswer questionAnswer : feedback.getQuestions()) {
					if(questionAnswer.getQuestion()!=null && questionAnswer.getQuestion().equalsIgnoreCase(QuestionConstant.RATING_QUES)){
						//fetch existing list of feedbacks
						Integer ratingFromAnswer=Integer.valueOf(questionAnswer.getAnswer());
						List<Feedback>feedbacksOfRating=feedbackCountMap.get(rating);
						if(feedbacksOfRating==null){
							feedbacksOfRating=new ArrayList<Feedback>();
						}
						feedbacksOfRating.add(feedback);
						feedbackCountMap.put(ratingFromAnswer, feedbacksOfRating);
						break;
					}
				}
			}
		}
		return feedbackCountMap.get(rating);
	}

	private Object getCountRating(int rating) {
		List<Feedback>feedbacks=(List<Feedback>) getPeopleWithRating(rating);
		if(feedbacks!=null && !feedbacks.isEmpty()){
			return feedbacks.size();
		}
		return 0;
	}

	private void validate(RuleMatcherResultEnum result, List<RuleMatcherResultEnum> results) {
		if(result!=RuleMatcherResultEnum.NONE)	{
			results.add(result);
		}
	}
}
