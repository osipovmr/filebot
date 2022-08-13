package TelegramBot;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.net.URL;


public class OsipovBot extends AbilityBot {
    public static Logger logger = LoggerFactory.getLogger(OsipovBot.class);

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


    public void uploadFile(String file_name, String file_id) throws IOException {
        URL url = new URL("https://api.telegram.org/bot" + Constants.BOT_TOKEN + "/getFile?file_id=" + file_id);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String res = in.readLine();
        JSONObject jresult = new JSONObject(res);
        JSONObject path = jresult.getJSONObject("result");
        String file_path = path.getString("file_path");
        File lokalFile = new File("D:/IT/filebot/grandle/src/main/resources" + file_name);
        InputStream is = new URL("https://api.telegram.org/file/bot" + Constants.BOT_TOKEN + "/" + file_path).openStream();
        FileUtils.copyInputStreamToFile(is, lokalFile);
        in.close();
        is.close();
        logger.info("Uploaded!");
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
            logger.info(fileName);
            logger.info(fileId);

            try {
                uploadFile(fileName, fileId);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            if ((update.hasMessage() && inMess.hasText()) && (inMessText.equals(start))) {
                outMess.setText("Привет!");
                outMess.setChatId(chatId);
                execute(outMess);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
