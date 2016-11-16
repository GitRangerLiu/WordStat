package com.chris.pdf;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.FileInputStream;
import java.io.File;
import org.apache.pdfbox.cos.COSDocument;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PDFManager {
	private PDFParser parser;
	private PDFTextStripper pdfStp;
	private PDDocument pdDoc;
	private COSDocument cosDoc;
	
	private String text;
	private String filePath = ".\\resource\\pdf";
	private File filedir;
	private File[] files;
	private String[] nameExtra;
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	private  String toText(File file) throws IOException {
		parser = new PDFParser(new FileInputStream(file));
		//Parse the stream
		parser.parse();
		cosDoc = parser.getDocument();
		pdDoc = new PDDocument(cosDoc);

		//This class will strip out all the formatting
		pdfStp = new PDFTextStripper();
		int endPage = pdDoc.getNumberOfPages();
		pdfStp.setStartPage(1);
		pdfStp.setEndPage(endPage);
		return pdfStp.getText(pdDoc);
	}
	
	public void toTextFile() throws IOException {
		PDFManager pdfManager = new PDFManager();

		filedir = new File(filePath);
		files = filedir.listFiles();
		for(File file : files) {			
			//System.out.println(pdfManager.toText());
			String pdf = pdfManager.toText(file);
			String filename = file.getName();
			System.out.println(filename);
			nameExtra = filename.split("\\.");
//			for(String str : nameExtra) {
//				System.out.println(str);
//			}
			filename = nameExtra[0] + ".txt";
			RandomAccessFile out = new RandomAccessFile(".\\resource\\txt\\" + filename, "rw");
			out.write(pdf.getBytes());
			out.close();
			
		}
		System.out.println("Test output finished!");
	}
	
}