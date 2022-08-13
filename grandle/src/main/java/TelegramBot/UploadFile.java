package TelegramBot;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;

public class UploadFile {
    public static void uploadFile(String file_name, String file_id) throws IOException {
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
        Main.logger.info("File "+ file_name + " was downloaded on PC.");
    }
}
