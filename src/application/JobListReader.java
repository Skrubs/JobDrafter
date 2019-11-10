package application;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class JobListReader {

	private DataFormatter dataFormatter;
	private Workbook inputWorkBook;
	private FileChooser fileChooser;
	private Sheet masterJobSheet;
	private int jobColumn;
	private int lineColumn;
	private int eadColumn;

	public JobListReader() {
		jobColumn = 0;
		lineColumn = 0;
		eadColumn = 0;
	}

	/**
	 * Reads in the file if the file is not a .xlsx file type this method will throw
	 * an IOException. The method formats all cell data into a type String.
	 * 
	 * @param window
	 * @throws IOException
	 */
	public ArrayList<Job> readJobListFile(Stage window) throws IOException {
		ZipSecureFile.setMinInflateRatio(0);
		dataFormatter = new DataFormatter();
		fileChooser = new FileChooser();
		inputWorkBook = WorkbookFactory.create(fileChooser.showOpenDialog(window));
		inputWorkBook.close();
		masterJobSheet = findJobSheet(inputWorkBook);
		if (masterJobSheet != null) {
			findColumn(masterJobSheet);
			return createJobArray(masterJobSheet);
		}
		return new ArrayList<Job>();
	}

	/**
	 * creates an arrayList of type Job to return to Main for the Job list view.
	 * @param masterJobSheet
	 * @return
	 */
	private ArrayList<Job> createJobArray(Sheet masterJobSheet) {

		ArrayList<Job> jobList = new ArrayList<>();

		for (Row r : masterJobSheet) {
			try {
				int temp = Integer.parseInt(dataFormatter.formatCellValue(r.getCell(lineColumn)));
					if(temp > 0) {
						String jobName = dataFormatter.formatCellValue(r.getCell(jobColumn));
						String ead = dataFormatter.formatCellValue(r.getCell(eadColumn));
						jobList.add(new Job(jobName,ead));
					}
				} catch (NumberFormatException nfe) {
			}
		}
		return jobList;	
	}

	/**
	 * Finds the column for Line to determine number of AFSCs. AFSC column to
	 * extract the AFSCs. EAD column to extract the EADs.
	 * 
	 * @param masterJobSheet
	 */
	private void findColumn(Sheet masterJobSheet) {
		for (Row r : masterJobSheet) {
			for (int i = 0; i < 5; i++) {
				String tmp = dataFormatter.formatCellValue(r.getCell(i));
				if (tmp.equalsIgnoreCase("Line #")) {
					lineColumn = i;
				} else if (tmp.equalsIgnoreCase("AFSC")) {
					jobColumn = i;
				} else if (tmp.equalsIgnoreCase("EAD")) {
					eadColumn = i;
				}
			}
		}

	}

	/**
	 * Finds the master job sheet for the Excel workbook selected for input. If the
	 * MASTER JOB SHEET is not found it will return a new blank sheet and throw a
	 * custom sheetNotFound error.
	 * 
	 * @param inputWorkBook
	 * @return
	 */
	private Sheet findJobSheet(Workbook inputWorkBook) {
		boolean masterSheetFound = false;
		for (Sheet s : inputWorkBook) {
			if (s.getSheetName().equalsIgnoreCase("MASTER JOB SHEET")) {
				masterSheetFound = true;
				return s;
			} else {
				masterSheetFound = false;
			}
		}
		if (masterSheetFound == false) {
			// THROW CUSTOM EXCEPTION
		}
		return new XSSFWorkbook().createSheet();
	}

}
