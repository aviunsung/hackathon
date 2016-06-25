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
@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class Feedback {
	private String participantName;
	private String emailId;
	@XmlElement(name = "QuestionAnswer")
	private List<QuestionAnswer>questions;
	public String getParticipantName() {
		return participantName;
	}
	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public List<QuestionAnswer> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionAnswer> questions) {
		this.questions = questions;
	}
	@Override
	public String toString() {
		return "Feedback of participant : " + participantName + " with emailId : " + emailId + " \n All list of questions : \n" + questions;
	}
}
