package TelegramBot;

import com.google.gson.Gson;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadFileXlxs {
    public static String xlxs(String fileName) {
        List <String> valueList = new ArrayList<>();
        String json = null;
        try {
            OPCPackage pkg = OPCPackage.open(new File(Constants.downloadPlace + fileName));
            XSSFWorkbook wb = new XSSFWorkbook(pkg);
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row;
            XSSFCell cell;
            int rows;
            rows = sheet.getPhysicalNumberOfRows();
            int cols = 0;
            int tmp = 0;
            for (int i = 0; i < 10 || i < rows; i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if (tmp > cols) cols = tmp;
                }
            }
            for (int r = 1; r < rows; r++) {
                row = sheet.getRow(r);
                if (row != null) {
                    for (int c = 0; c < cols; c++) {
                        cell = row.getCell((short) c);
                        if (cell != null) {
                            String value = String.valueOf(cell);
                            valueList.add(value);
                        }
                    }
                }
            }
            json = new Gson().toJson(valueList );
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
        return json;
    }
}
