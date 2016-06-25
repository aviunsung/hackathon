/**
 * 
 */
package hackathon.bot.application.service;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import hackathon.bot.application.model.Feedback;
import hackathon.bot.application.model.Feedbacks;

/**
 * @author avinash
 *
 */
public class XmlDAO implements DAO {

	public static final DAO INSTANCE=new XmlDAO();
	private XmlDAO(){}
	/* (non-Javadoc)
	 * @see hackathon.bot.application.service.DAO#fetchAllFeedbacks()
	 */
	@Override
	public List<Feedback> fetchAllFeedbacks() {
		Feedbacks feedbacks=null;
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Feedbacks.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			//We had written this file in marshalling example
			feedbacks = (Feedbacks) jaxbUnmarshaller.unmarshal( new File("input2.xml") );
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return feedbacks!=null?feedbacks.getFeedbacks():null;

	}
	
}
