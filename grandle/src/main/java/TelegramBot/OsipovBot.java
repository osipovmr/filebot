package TelegramBot;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;

public class OsipovBot extends AbilityBot {

    @Override
    public long creatorId() {
        return Constants.CREATOR_ID;
    }

    public OsipovBot() {
        this(Constants.BOT_TOKEN, Constants.BOT_USERNAME);
    }

    private OsipovBot(String botToken, String botUsername) {
        super(botToken, botUsername);
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message inMess = update.getMessage();
        String chatId = inMess.getChatId().toString();
        SendMessage outMess = new SendMessage();
        String inMessText = inMess.getText();
        String start = "/start";
        final Document document = update.getMessage().getDocument();
        if (document != null) {
            final String fileId = document.getFileId();
            final String fileName = document.getFileName();
            Main.logger.info("File name: " + fileName);
            Main.logger.info("File ID: " + fileId);
            try {
                UploadFile.uploadFile(fileName, fileId);    //скачал файл
                String requestBody = ReadFileXlxs.xlxs(fileName);   //вытащил из файла текст запроса, прочитав файл
                String answer = Post.http(requestBody);   //ответ
                JSON2CSV.Format(answer);
                outMess.setText("Get file " + fileName + "\n" + "Request: " + requestBody + "\n" + "Answer: " + answer);
                outMess.setChatId(chatId);
                execute(outMess);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TelegramApiException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            if ((update.hasMessage() && inMess.hasText()) && (inMessText.equals(start))) {
                outMess.setText("Hello!");
                outMess.setChatId(chatId);
                execute(outMess);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
