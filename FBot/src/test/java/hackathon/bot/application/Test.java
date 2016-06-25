package hackathon.bot.application;

import hackathon.bot.application.service.XmlDAO;

public class Test {

	@org.junit.Test
	public void test() {
		System.out.println(XmlDAO.INSTANCE.fetchAllFeedbacks());
	}

}
