/**
 * 
 */
package hackathon.bot.application.model;

/**
 * @author avinash
 *
 */
public class ProcessedResult {

	private Object result;
	private RuleMatcherResultEnum input;
    private String message;
    
	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public RuleMatcherResultEnum getInput() {
		return input;
	}

	public void setInput(RuleMatcherResultEnum input) {
		this.input = input;
	}

	public String getMessage() {
		if(message==null){
			return this.toString();
		}
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ProcessedResult [result=" + result + ", input=" + input + "]";
	}
}
