/**
 * 
 */
package hackathon.bot.application.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author avinash
 *
 */
@XmlRootElement(name = "feedbacks")
@XmlAccessorType (XmlAccessType.FIELD)
public class Feedbacks {
	@XmlElement(name = "feedback")
	private List<Feedback>feedbacks;

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}
}