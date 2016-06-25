package hackathon.bot.application;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws InterruptedException 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public  String home(Locale locale, Model model) throws InterruptedException {

		/*Feedback feedback=new Feedback();
		feedback.setParticipantName("Avinash");
		feedback.setEmailId("avibokaro@gmail.com");
		List<QuestionAnswer>questions=new ArrayList<QuestionAnswer>();
		QuestionAnswer question=new QuestionAnswer();
		question.setQuestion("How would you rate?");
		question.setAnswer("3");
		questions.add(question);
		feedback.setQuestions(questions);
		List<Feedback>feedbacks=new ArrayList<Feedback>();
		feedbacks.add(feedback);
		Feedbacks feedbacks2=new Feedbacks();
		feedbacks2.setFeedbacks(feedbacks);
		return feedbacks2;*/

		BotChat.main(null);
		return "Welcome to Bot World";
	}

}
