package com.chris.pdf;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PDFManager {
	private PDFParser parser;
	private PDFTextStripper pdfStp;
	private PDDocument pdDoc;
	private COSDocument cosDoc;
	
	private String text;
	private String filePath;
	private File file;
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String toText() throws IOException {
		this.pdfStp = null;
		this.pdDoc = null;
		this.cosDoc = null;
		
		file = new File(filePath);
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
		text = pdfStp.getText(pdDoc);	
		return text;
	} 
	
}