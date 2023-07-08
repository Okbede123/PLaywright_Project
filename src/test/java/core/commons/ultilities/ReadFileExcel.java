package core.commons.ultilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadFileExcel {

    public static List<String> handleExcelDocHangNgang(String pathExcel, String nameFileExcel, String nameSheet, int row)throws IOException {
        ArrayList<String> arrayList = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(pathExcel+ nameFileExcel);
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        // lấy ra sheet mong muốn
        Sheet sheet = workbook.getSheet(nameSheet);
        //lấy ra vị trí dòng mong muốn
        Row row1 = sheet.getRow(row);
        //duyệt từng giá trị tại dòng đó
        Iterator<Cell> checkCell = row1.cellIterator();
        while (checkCell.hasNext()){
            Cell cell = checkCell.next();
            if(cell.getCellType() == CellType.STRING){
                arrayList.add(cell.getStringCellValue());
            }
            else {
                arrayList.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
            }
        }
        return arrayList;
    }
}
