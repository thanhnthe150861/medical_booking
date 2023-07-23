package selenium.result;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import selenium.login.RunLogin;

import java.io.FileOutputStream;
import java.io.IOException;

public class fileLoginTest {
    public static void main(String[] args) {
        // Khởi tạo trình duyệt Chrome
         RunLogin runLogin =new RunLogin();
        // Thực hiện các test case và ghi kết quả vào biến 'testResult'
        String testResult = "Pass"; // Hoặc "Fail", tùy thuộc vào kết quả thực tế của test
        // Ghi kết quả vào tập tin Excel
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Test Results");
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(testResult);

            // Đường dẫn đến tập tin Excel mà bạn muốn lưu kết quả
            String excelFilePath = "G:\\SWR302\\fileLogin.xlsx";
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        // Đóng trình duyệt
        // ...
    }
}