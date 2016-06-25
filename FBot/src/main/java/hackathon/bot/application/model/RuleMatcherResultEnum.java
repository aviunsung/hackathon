/**
 * 
 */
package hackathon.bot.application.model;

/**
 * @author avinash
 *
 */
public enum RuleMatcherResultEnum {
	NONE("None"),
	MAX_RATING("Max Rating"),
	COUNT_MAX_RATING("Hey shall I count how many have given max ratings?"),
	PEOPLE_MAX_RATING("Hey shall I show who have given max ratings?"),
	MIN_RATING("Min Rating"),
	COUNT_MIN_RATING("Hey shall I count how many have given min ratings?"),
	PEOPLE_MIN_RATING("Hey shall I show who many have given min ratings?"),
	COUNT_RATING("count rating"),
	GOOD_COMMENTS("Hey shall I show whoever has given good comments?"),
	MAIL("Hey shall I send mail?");

	public String value;

	RuleMatcherResultEnum(String value){
		this.value=value;
	}

	public static final String SORRY_CANT_UNDERSTAND="Sorry...could not understand?";
}
