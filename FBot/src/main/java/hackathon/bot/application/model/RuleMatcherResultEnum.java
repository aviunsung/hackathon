/**
 * 
 */
package hackathon.bot.application.model;

/**
 * @author avinash
 *
 */
public enum RuleMatcherResultEnum {
	NONE,
	MAX_RATING,
	COUNT_MAX_RATING,
	PEOPLE_MAX_RATING,
	MIN_RATING,
	COUNT_MIN_RATING,
	PEOPLE_MIN_RATING,
	COUNT_RATING,
	GOOD_COMMENTS,
	MAIL;
	
	public static final String SORRY_CANT_UNDERSTAND="Sorry...Couldnot understand";
}
