package application;


import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * 
 * @author Angelo
 *
 */

public class WorkBookReader {
	
	
	private Workbook wb;;
	private Sheet qwSheet;
	
	
	public WorkBookReader() {
		
	}
	
	public Sheet readWorkBook(Stage window) throws EncryptedDocumentException, IOException {
		FileChooser fc = new FileChooser();
		File file = fc.showOpenDialog(window);
		wb = WorkbookFactory.create(file);
		return createSheet(wb);
	}
	
	/**
	 * creates the QW workbook sheet from the workbook
	 * @param wb
	 * @return
	 */
	private Sheet createSheet(Workbook wb) {
		if(wb != null) {
			for(Sheet s : wb) {
				if(s.getSheetName().equalsIgnoreCase("G FLIGHT")) {
					return qwSheet = s;
				}
			}
			if(qwSheet == null) {
				return qwSheet = new XSSFWorkbook().createSheet("");
			}
		}else {
			return qwSheet = new XSSFWorkbook().createSheet("");
		}
		return qwSheet;
	}
	
	public Sheet getQW() {
		if(qwSheet != null) {
			return qwSheet;
		}else {
			return qwSheet = wb.createSheet("Blank");
		}
	}

}
