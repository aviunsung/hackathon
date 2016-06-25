package hackathon.bot.application.service;


import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class FeedbackBot extends TelegramLongPollingBot {

	@Override
	public String getBotUsername() {
		return "myprojectusername";
	}

	@Override
	public void onUpdateReceived(Update update) {
		
		//check if the update has a message
        if(update.hasMessage()){
                Message message = update.getMessage();

                //check if the message has text. it could also  contain for example a location ( message.hasLocation() )
                if(message.hasText()){

                        //create a object that contains the information to send back the message
                        SendMessage sendMessageRequest = new SendMessage();
                        sendMessageRequest.setChatId(message.getChatId().toString()); //who should get the message? the sender from which we got the message...
                        sendMessageRequest.setText("you said: " + message.getText());
                        try {
                                sendMessage(sendMessageRequest); //at the end, so some magic and send the message ;)
                        } catch (TelegramApiException e) {
                                //do some error handling
                        }//end catch()
                }//end if()
        }//end  if()
		
	}

	@Override
	public String getBotToken() {
		return "TOP_SECRET_TOKEN";
	}
	
	public static void main(String[] args) {
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new FeedbackBot());
//            telegramBotsApi.registerBot(new DirectionsHandlers());
//            telegramBotsApi.registerBot(new RaeHandlers());
//            telegramBotsApi.registerBot(new WeatherHandlers());
//            telegramBotsApi.registerBot(new TransifexHandlers());
//            telegramBotsApi.registerBot(new FilesHandlers());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
	}

}
