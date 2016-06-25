/**
 * 
 */
package hackathon.bot.application.service;

import hackathon.bot.application.model.BotApplicationConstant;
import hackathon.bot.application.model.RuleMatcherResultEnum;
import hackathon.bot.application.util.BotUtil;

/**
 * @author avinash
 *
 */
public final class RuleMatcher {

	public static RuleMatcherResultEnum isMaxRating(String inputText){
		if(BotUtil.checkOccurence(inputText, "max")){
			return RuleMatcherResultEnum.MAX_RATING;
		}
		return RuleMatcherResultEnum.NONE;
	}

	public static RuleMatcherResultEnum countMaxRating(String inputText){
		if(isMaxRating(inputText)==RuleMatcherResultEnum.MAX_RATING&& BotUtil.checkOccurence(inputText, "count")){
			return RuleMatcherResultEnum.COUNT_MAX_RATING;
		}
		return RuleMatcherResultEnum.NONE;
	}

	public static RuleMatcherResultEnum getPeopleGivenMaxRating(String inputText){
		if(isMaxRating(inputText)==RuleMatcherResultEnum.MAX_RATING&& (BotUtil.checkOccurence(inputText, "people") || BotUtil.checkOccurence(inputText, "who"))){
			return RuleMatcherResultEnum.PEOPLE_MAX_RATING;
		}
		return RuleMatcherResultEnum.NONE;
	}

	public static RuleMatcherResultEnum isMinRating(String inputText){
		if(BotUtil.checkOccurence(inputText, "min")){
			return RuleMatcherResultEnum.MIN_RATING;
		}
		return RuleMatcherResultEnum.NONE;
	}

	public static RuleMatcherResultEnum countMinRating(String inputText){
		if(isMinRating(inputText)==RuleMatcherResultEnum.MIN_RATING&& BotUtil.checkOccurence(inputText, "count")){
			return RuleMatcherResultEnum.COUNT_MIN_RATING;
		}
		return RuleMatcherResultEnum.NONE;
	}

	public static RuleMatcherResultEnum getPeopleGivenMinRating(String inputText){
		if(isMinRating(inputText)==RuleMatcherResultEnum.MIN_RATING&& (BotUtil.checkOccurence(inputText, "people") || BotUtil.checkOccurence(inputText, "who"))){
			return RuleMatcherResultEnum.PEOPLE_MIN_RATING;
		}
		return RuleMatcherResultEnum.NONE;
	}

	public static RuleMatcherResultEnum countRating(String inputText){
		if(BotUtil.checkOccurence(inputText, "count") && BotUtil.checkOccurence(inputText, BotApplicationConstant.ratings)){
			return RuleMatcherResultEnum.COUNT_RATING;
		}
		return RuleMatcherResultEnum.NONE;
	}
}
