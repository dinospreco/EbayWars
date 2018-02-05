package ebaywars.run;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ebaywars.searchengines.Olx;
import ebaywars.searchengines.OlxArticle;

public class EbayWarsRun {

	public static void main(String[] args) {
		
		Set<OlxArticle> artikli = new HashSet<OlxArticle>();
		
		System.out.println("Kreno program");
		
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		XSSFRow header;

			workbook = new XSSFWorkbook();
			System.out.println("Napravio workbook");
			
			sheet = workbook.createSheet("Olx");
			System.out.println("Napravio sheet");
			
			header = sheet.createRow(0);
			System.out.println("Napravio header");
			
			XSSFCell headerCell = header.createCell(0);
			headerCell.setCellValue("Id artikla");
			headerCell = header.createCell(1);
			headerCell.setCellValue("Naslov");
			headerCell = header.createCell(2);
			headerCell.setCellValue("Cijena");
			headerCell = header.createCell(3);
			headerCell.setCellValue("Lokacija");
			headerCell = header.createCell(4);
			headerCell.setCellValue("Datum objave");
			headerCell = header.createCell(5);
			headerCell.setCellValue("Link");
		
		System.out.println("Pravim Olx objekat");
		
		Olx pik = new Olx();
		
		System.out.println("Napravi Olx objekat");
		System.out.println("Pravim set");
		
		Set<String> linkovi = new HashSet<String>();
		
		System.out.println("Napravio set");
		System.out.println("Pokrecem getArticleLinks metodu");
		
		linkovi = pik.getArticleLinks("fiat+punto",20);
		
		System.out.println("Pokreno gerArticleLinks metodu");
		System.out.println("Broj artikala: " + linkovi.size());
		System.out.println("Izlistavam linkove");
		
		int brojac = 1;
		for (String link : linkovi)
		{
			System.out.println("Artkal broj: " + brojac);
			OlxArticle artikal = new OlxArticle(link);
			artikli.add(artikal);
			brojac++;
		}
		int brojacRedova = 2;
		for (OlxArticle artikal : artikli)
		{
			XSSFRow row = sheet.createRow(brojacRedova);
			XSSFCell cell = row.createCell(0);
			
			cell.setCellValue(artikal.getArticleId());
			cell = row.createCell(1);
			cell.setCellValue(artikal.getArticleTitle());
			cell = row.createCell(2);
			cell.setCellValue(artikal.getArticlePrice());
			cell = row.createCell(3);
			cell.setCellValue(artikal.getArticleLocation());
			cell = row.createCell(4);
			cell.setCellValue(artikal.getArticleDate());
			cell = row.createCell(5);
			cell.setCellValue(artikal.getArticleLink());
			brojacRedova++;
		}
		
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";
		 
		try {
			FileOutputStream outputStream = new FileOutputStream(fileLocation);
			
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Gotovo");

	}

}
