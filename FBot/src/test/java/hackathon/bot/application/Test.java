package hackathon.bot.application;

import hackathon.bot.application.service.XmlDAO;

public class Test {

	@org.junit.Test
	public void test() {
		XmlDAO xmlDAO=new XmlDAO();
		System.out.println(xmlDAO.fetchAllFeedbacks());
	}

}
