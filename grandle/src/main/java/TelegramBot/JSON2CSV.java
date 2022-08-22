package TelegramBot;


import com.google.gson.JsonArray;
import org.apache.commons.io.FileUtils;
import org.json.*;

import java.io.File;
import java.io.IOException;

public class JSON2CSV {
    public static void Format(String answer) {
        JSONArray output;
        try {
           output = new JSONArray(answer);
            File file = new File("D:/IT/filebot/grandle/src/main/resources/fromJSON.csv");
            String csv = CDL.toString(output);
            FileUtils.writeStringToFile(file, csv);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

