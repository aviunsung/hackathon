/**
 * 
 */
package hackathon.bot.application.util;

/**
 * @author avinash
 *
 */
public final class BotUtil {

	public static boolean checkOccurence(String inputText,String keyword){
		if(inputText.contains(keyword) || inputText.contains(keyword.toUpperCase())){
			return true;
		}
		return false;
	}

	public static String checkOccurenceAndReturnKeyword(String inputText,String[] keyword){
		for(int i =0; i < keyword.length; i++)
		{
			if(inputText.contains(keyword[i]))
			{
				return keyword[i];
			}
		}
		return null;
	}
	public static boolean checkOccurence(String inputText,String[] keyword){
		if(checkOccurenceAndReturnKeyword(inputText,keyword)!=null){
			return true;
		}
		return false;
	}
}
