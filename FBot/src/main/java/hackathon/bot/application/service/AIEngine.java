/**
 * 
 */
package hackathon.bot.application.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hackathon.bot.application.model.RuleMatcherResultEnum;

/**
 * @author avinash
 *
 */
public class AIEngine {
	public static HashMap<RuleMatcherResultEnum,List<RuleMatcherResultEnum>>reccomendationMap=new HashMap<RuleMatcherResultEnum,List<RuleMatcherResultEnum>>();
	public static void createRecommendation(){
		List<RuleMatcherResultEnum>reccomendations=new ArrayList<RuleMatcherResultEnum>();
		reccomendations.add(RuleMatcherResultEnum.PEOPLE_MAX_RATING);
		reccomendations.add(RuleMatcherResultEnum.GOOD_COMMENTS);
		reccomendations.add(RuleMatcherResultEnum.MAIL);
		reccomendationMap.put(RuleMatcherResultEnum.COUNT_MAX_RATING, reccomendations);
	}
	public static HashMap<RuleMatcherResultEnum, List<RuleMatcherResultEnum>> getReccomendationMap() {
		if(reccomendationMap.isEmpty()){
			createRecommendation();
		}
		return reccomendationMap;
	}
	public static void setReccomendationMap(HashMap<RuleMatcherResultEnum, List<RuleMatcherResultEnum>> reccomendationMap) {
		AIEngine.reccomendationMap = reccomendationMap;
	}

}
