package com.chris.pdf;

import java.io.IOException;
import java.io.RandomAccessFile;

public class PDFManagerTest {

	public static void main(String[] args) throws IOException {
		PDFManager pdfManager = new PDFManager();
		//The file path is transmitted by program parameters.
		pdfManager.setFilePath(args[0]);
		//System.out.println(pdfManager.toText());
		String pdf = pdfManager.toText();
		RandomAccessFile out = new RandomAccessFile(".\\resource\\txt\\testpdf2.txt", "rw");
		out.write(pdf.getBytes());
		out.close();
			
		}	

}
