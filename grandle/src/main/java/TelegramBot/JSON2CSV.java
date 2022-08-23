package TelegramBot;


import org.apache.commons.io.FileUtils;
import org.json.*;

import java.io.File;
import java.io.IOException;

public class JSON2CSV {
    public static  File file = new File("D:/IT/filebot/grandle/src/main/resources/fromJSON.csv");
    public static void Format(String answer) {
        JSONObject output;
        StringBuilder stringBuilder = new StringBuilder("{\"order\": ");
        stringBuilder.append(answer);
        stringBuilder.append("}");
        String code = stringBuilder.toString();
        try {
            output = new JSONObject(code);
            JSONArray docs = output.getJSONArray("order");
            String csv = CDL.toString(docs);
            FileUtils.writeStringToFile(file, csv);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

