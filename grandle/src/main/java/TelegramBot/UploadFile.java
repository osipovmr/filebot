package TelegramBot;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;

public class UploadFile {

    public static void uploadFile(String file_name, String file_id) throws IOException {
        String telegramSourse = "https://api.telegram.org/bot";
        String param = "/getFile?file_id=";
        URL url = new URL(telegramSourse + Constants.BOT_TOKEN + param + file_id); //file adress
        BufferedReader searchFile = new BufferedReader(new InputStreamReader(url.openStream())); //read file info
        String res = searchFile.readLine();
        JSONObject jresult = new JSONObject(res);
        JSONObject path = jresult.getJSONObject("result");
        String file_path = path.getString("file_path");
        Long file_size = path.getLong("file_size");
        File copyFile = new File(Constants.downloadPlace + file_name);
        InputStream getFile = new URL("https://api.telegram.org/file/bot" + Constants.BOT_TOKEN + "/" + file_path).openStream();
        FileUtils.copyInputStreamToFile(getFile, copyFile);
        searchFile.close();
        getFile.close();
        String info = "File "+ file_name + " was downloaded on PC. " + "File size =" + file_size;
        Main.logger.info(info);
    }
}
