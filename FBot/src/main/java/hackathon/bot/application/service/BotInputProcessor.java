package hackathon.bot.application.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hackathon.bot.application.model.BotApplicationConstant;
import hackathon.bot.application.model.Feedback;
import hackathon.bot.application.model.ProcessedResult;
import hackathon.bot.application.model.QuestionAnswer;
import hackathon.bot.application.model.QuestionConstant;
import hackathon.bot.application.model.RuleMatcherResultEnum;
import hackathon.bot.application.util.BotUtil;

public class BotInputProcessor {
	public static final BotInputProcessor INSTANCE=new BotInputProcessor();
	private BotInputProcessor(){}
	private static HashMap<Integer,List<Feedback>> feedbackCountMap=new HashMap<Integer,List<Feedback>>();
	List<Feedback>goodFeedbacks=new ArrayList<Feedback>();

	public ProcessedResult process(String userInput){
		ProcessedResult result=processRules(userInput);
		if(result==null){
			ProcessedResult processedResult=new ProcessedResult();
			processedResult.setResult(RuleMatcherResultEnum.SORRY_CANT_UNDERSTAND);
			return processedResult;
		}
		return result;
	}

	private ProcessedResult processRules(String userInput) {
		List<RuleMatcherResultEnum>results=new ArrayList<RuleMatcherResultEnum>();
		validate(RuleMatcher.isMaxRating(userInput),results);	
		validate(RuleMatcher.countMaxRating(userInput),results);
		validate(RuleMatcher.getPeopleGivenMaxRating(userInput),results);
		validate(RuleMatcher.isMinRating(userInput),results);
		validate(RuleMatcher.countMinRating(userInput),results);
		validate(RuleMatcher.getPeopleGivenMinRating(userInput),results);
		validate(RuleMatcher.countRating(userInput),results);
		validate(RuleMatcher.goodComments(userInput),results);
		validate(RuleMatcher.sendMail(userInput),results);
		ProcessedResult processedResult=null;
		if(!results.isEmpty()){
			processedResult= processRuleMatcherResult(results.get(results.size()-1),userInput);
		}
		return processedResult;
	}

	public ProcessedResult processRuleMatcherResult(RuleMatcherResultEnum ruleMatcherResultEnum,String userInput) {
		ProcessedResult processedResult=new ProcessedResult();
		switch (ruleMatcherResultEnum) {
		case COUNT_MAX_RATING:
			processedResult.setInput(RuleMatcherResultEnum.COUNT_MAX_RATING);
			processedResult.setResult(getCountRating(5));
			break;
		case PEOPLE_MAX_RATING:
			processedResult.setInput(RuleMatcherResultEnum.PEOPLE_MAX_RATING);
			processedResult.setResult(getPeopleWithRating(5));
			break;
		case COUNT_MIN_RATING:
			processedResult.setInput(RuleMatcherResultEnum.COUNT_MIN_RATING);
			processedResult.setResult(getCountRating(1));
			break;
		case PEOPLE_MIN_RATING:
			processedResult.setInput(RuleMatcherResultEnum.PEOPLE_MIN_RATING);
			processedResult.setResult(getPeopleWithRating(1));
			break;
		case COUNT_RATING:
			processedResult.setInput(RuleMatcherResultEnum.COUNT_RATING);
			if(BotUtil.checkOccurenceAndReturnKeyword(userInput, BotApplicationConstant.ratings)!=null){
				processedResult.setResult(getCountRating(Integer.valueOf(BotUtil.checkOccurenceAndReturnKeyword(userInput, BotApplicationConstant.ratings))));
			}else{
				processedResult.setResult(RuleMatcherResultEnum.SORRY_CANT_UNDERSTAND);
			}
			break;
		case GOOD_COMMENTS:
			processedResult.setInput(RuleMatcherResultEnum.GOOD_COMMENTS);
			processedResult.setResult(getGoodComments());
			break;
		case MAIL:
			processedResult.setInput(RuleMatcherResultEnum.MAIL);
			processedResult.setResult(sendMail());
			processedResult.setMessage("Sending Mail....");
			break;
		default:
			processedResult.setResult(RuleMatcherResultEnum.SORRY_CANT_UNDERSTAND);
			break;
		}
		return processedResult;
	}

	private Object sendMail() {
		if(goodFeedbacks.isEmpty()){
			getGoodComments();
		}
		for (Feedback feedback : goodFeedbacks) {
			SendMailService.sendOutlookMail(feedback.getEmailId(), "We were glad that you liked our event", "e-Zest Hackathon 2016");
		}
		return "Mail sent successfully !!!";
	}

	private Object getGoodComments() {
		if(goodFeedbacks.isEmpty()){
			List<Feedback>feedbacks=XmlDAO.INSTANCE.fetchAllFeedbacks();
			for (Feedback feedback : feedbacks) {
				for (QuestionAnswer questionAnswer : feedback.getQuestions()) {
					if(questionAnswer.getQuestion()!=null && questionAnswer.getQuestion().equalsIgnoreCase(QuestionConstant.COMMENTS)){
						//fetch comments
						if(questionAnswer.getAnswer()!=null){
							if(isGoodComment(questionAnswer.getAnswer())){
								goodFeedbacks.add(feedback);
								break;
							}
						}
					}
				}
			}
		}
		return goodFeedbacks;
	}

	private boolean isGoodComment(String answer) {
		return BotUtil.checkOccurence(answer, BotApplicationConstant.goodSemantics);
	}

	private Object getPeopleWithRating(int rating) {
		if(feedbackCountMap.isEmpty()){
			List<Feedback>feedbacks=XmlDAO.INSTANCE.fetchAllFeedbacks();
			for (Feedback feedback : feedbacks) {
				for (QuestionAnswer questionAnswer : feedback.getQuestions()) {
					if(questionAnswer.getQuestion()!=null && questionAnswer.getQuestion().equalsIgnoreCase(QuestionConstant.RATING_QUES)){
						//fetch existing list of feedbacks
						Integer ratingFromAnswer=Integer.valueOf(questionAnswer.getAnswer());
						List<Feedback>feedbacksOfRating=feedbackCountMap.get(ratingFromAnswer);
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
