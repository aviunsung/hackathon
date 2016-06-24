package hackathon.bot.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hackathon.bot.application.model.Feedback;
import hackathon.bot.application.model.Question;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody Feedback home(Locale locale, Model model) {
		
		Feedback feedback=new Feedback();
		feedback.setParticipantName("Avinash");
		feedback.setEmailId("avibokaro@gmail.com");
		List<Question>questions=new ArrayList<Question>();
		Question question=new Question();
		question.setQuestion("How would you rate?");
		question.setAnswer("3");
		questions.add(question);
		feedback.setQuestions(questions);
		return feedback;
	}
	
}
