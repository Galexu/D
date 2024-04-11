import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class A {
    public static void main(String[] args) {
        String inputFilePath = "D:\\Stuff\\Desktop\\Grabar Datos\\sim3\\asd.txt";
        String excelFilePath = "D:\\Stuff\\Desktop\\Grabar Datos\\sim3\\new_asd.xlsx";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(); // Create a new sheet

            String line;
            String content = "";
            int rowIndex = 1; // Start from the second row
            boolean isContent = false;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty() && Character.isDigit(line.charAt(0))) {
                    isContent = true;
                    content = line;
                } else if (line.startsWith("la respuesta")) {
                    isContent = false;
                    Row row = sheet.createRow(rowIndex);
                    Cell cell = row.createCell(2); // Create the third column
                    cell.setCellValue(content);
                    rowIndex++;
                    content = "";
                } else if (isContent) {
                    content += " " + line;
                    System.out.println("hola");
                }
            }

            // Write changes to the new Excel file
            try (FileOutputStream fos = new FileOutputStream(new File(excelFilePath))) {
                workbook.write(fos);
            }

            System.out.println("New Excel file created successfully.");
        } catch (IOException e) {
            System.err.println("Error al leer o escribir el archivo: " + e.getMessage());
        }
    }
}