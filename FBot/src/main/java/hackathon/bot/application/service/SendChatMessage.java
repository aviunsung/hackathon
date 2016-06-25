package hackathon.bot.application.service;

import hackathon.bot.application.model.BotApplicationConstant;
import hackathon.bot.application.util.BotUtil;

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
	                     
	                    System.out.print(sender.getId() +" say:");
	                    System.out.println(" "+received.getContent() );
	                     
	                   
	                     
	                    if(received.getContent()!=null || received.getContent().trim().length()>0){
	                    	if(BotUtil.checkOccurence(received.getContent().toLowerCase(), BotApplicationConstant.hello)){
	                    		received.getSender().send("Hello "+ sender.getFullName());
	                    	}else{
	                    		received.getSender().send("I'm working. Please, wait a moment.");
	                    		System.out.println("Inside BotInputProcessor Call");
	                    		Object object = BotInputProcessor.INSTANCE.process(received.getContent().toLowerCase());
	                    		System.out.println("Object Value : "+object);
	                    		if(object !=null){
	                    			received.getSender().send(object.toString());
	                    		}
	                    	}
	                    	
	                    }
	                }
	            }
	        });
	         
	         
	        System.out.println("Auto Answering started!");
	}
	
	public String formattedText(Object object){
		return null;
	}

}
