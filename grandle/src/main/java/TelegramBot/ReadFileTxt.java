package TelegramBot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileTxt {
    public static String text (String file_name) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(Constants.downloadPlace + file_name));
        String s = null;
        try {
            s = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        br.close();
        return s;
    }
}
