package hackathon.bot.application.service;

import com.skype.ChatMessage;
import com.skype.ChatMessageAdapter;
import com.skype.Skype;
import com.skype.SkypeException;
import com.skype.User;

public class SendChatMessage {

	public static void main1(String[] args) {
		
		final String friend = "avi_unsung";

		try {
			String message = "Hello, This is test message";

			Skype.chat(friend).send(message);
//			Skype.call(friend);
			
//			Profile profile =Skype.getProfile();
//	           profile.setMoodMessage("Enjoying #HackathonPune");

			System.out.println("Message sent!");

		} catch (SkypeException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) throws Exception {
		 System.out.println("Start Auto Answering ...");
         
	        // To prevent exiting from this program
	        Skype.setDaemon(false);
	         
	         
	        Skype.addChatMessageListener(new ChatMessageAdapter() {
	            public void chatMessageReceived(ChatMessage received)
	                    throws SkypeException {
	                if (received.getType().equals(ChatMessage.Type.SAID)) {
	 
	                    // Sender
	                    User sender =received.getSender();    
	                     
	                    System.out.println(sender.getId() +" say:");
	                    System.out.println(" "+received.getContent() );
	                     
	                    received.getSender().send(
	                            "I'm working. Please, wait a moment.");
	                     
	                    System.out.println(" - Auto answered!");
	                    if(received.getContent()!=null || received.getContent().trim().length()>0){
	                    	System.out.println("Inside BotInputProcessor Call");
	                    	Object object = BotInputProcessor.INSTANCE.process(received.getContent());
	                    	System.out.println("Object Value : "+object);
	                    	received.getSender().send(object.toString());
	                    }
	                }
	            }
	        });
	         
	         
	        System.out.println("Auto Answering started!");
	}

}
