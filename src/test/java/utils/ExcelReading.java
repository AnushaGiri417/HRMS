    package utils;

    import org.apache.poi.ss.usermodel.Sheet;
    import org.apache.poi.ss.usermodel.Workbook;
    import org.apache.poi.xssf.usermodel.XSSFWorkbook;

    import java.io.FileInputStream;
    import java.io.FileNotFoundException;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.LinkedHashMap;
    import java.util.List;
    import java.util.Map;

    public class ExcelReading {

        static Workbook book;
        static Sheet sheet;

        /**
         * This method opens Excel
         * @param filePath
         */

        public static void openExcel(String filePath) {
            try {
                FileInputStream fis = new FileInputStream(filePath);
                book = new XSSFWorkbook(fis);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * this method can access the sheet we want
         * @param sheetName
         */
        public static void getSheet(String sheetName) {
            sheet = book.getSheet(sheetName);
        }

        public static int getRowCount() {
            return sheet.getPhysicalNumberOfRows();
        }

        public static int getColumnCount(int rowNum) {
            return sheet.getRow(rowNum).getPhysicalNumberOfCells();
        }

        /**
         * This method gets data from one row of excel file
         * @param rowIndex
         * @param colIndex
         * @return
         */
        public static String getCellData(int rowIndex, int colIndex) {
            return sheet.getRow(rowIndex).getCell(colIndex).toString();
        }

        /**
         * this method gets data from multiple rows of the excel sheet and stores them into  a map
         * those collection of maps are stored in maps
         * @param filePath
         * @param sheetName
         * @return
         */
        public static List<Map<String, String>> excelIntoListMap(String filePath, String sheetName) {
            openExcel(filePath); //we don't know which excel so we need a filepath parameter
            getSheet(sheetName); //we don't know which excel sheet to get the data from, so mention sheetname as parameter
            List<Map<String, String>> ListData = new ArrayList<>();
            for (int row = 1; row < getRowCount(); row++) {  //loop through all the rows
                //create a map for every single row
                Map<String, String> map = new LinkedHashMap<>();
                //loop through all the columns (cell)
                for (int col = 0; col < getColumnCount(row); col++) {
                    map.put(getCellData(0, col), getCellData(row, col)); //because each cell will have row and col index
    //                            (key)                 (Value)
    //              Because all the keys (header) are in row 0
                }
                ListData.add(map);
            }
            return ListData;
        }

    }
