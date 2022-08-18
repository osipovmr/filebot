package TelegramBot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileTxt {
    public static String text (String file_name) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(Constants.downloadPlace + file_name));
        StringBuilder everything = null;
        try {
            everything = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                everything.append(line +"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return everything.toString();
    }
}
