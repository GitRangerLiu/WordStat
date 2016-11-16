# WordStat
This is a interesting exercise which count the frequency of words that appear in some papers in the form of PDF format. Later on, we can call the dictionary API to get the meaning of words. Even, we can arrange a schedule which can meet the need of English word learners
### Goal

- Understand the function of different IO class
- Understand the procedure of optimization


### pdf to txt
public 

Use org.apache.pdfbox package to parse pdf file.


```java
//PDFManager.java
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
	
	public String toText() throws IOException {
		this.pdfStp = null;
		this.pdDoc = null;
		this.cosDoc = null;
		
		file = new File(filePath);
		parser = new PDFParser(new FileInputStream(file));
		//Parse the stream and populate the COSDocument object
		parser.parse();
        //COSDocument: in-memory representation of the PDF document
		cosDoc = parser.getDocument();
        //PDFTextStripper: This class will take a pdf document and strip out all of the text and ignore the formatting and such
		pdfStp = new PDFTextStripper();
		pdDoc = new PDDocument(cosDoc);
		int endPage = pdDoc.getNumberOfPages();
		pdfStp.setStartPage(1);
		pdfStp.setEndPage(endPage);
		
		text = pdfStp.getText(pdDoc);
		return text;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
```


junit test
test codes are parts of source codes, so it should be inside the `src` folder.
**Note** I need to know the basics of junit if I want to use the `junit` package.


### count the frequency of words in txt
public
#### Steps
- Read txt file
- Separate words using regex
- Use `Set` collection to calculate the frequency.

#### Consideration
- 1 way : read txt file once into String buffer, then separate in case of io many times.
- 2 way:  read txt file once into String, then then separate according to regex expression.

#### Sort the result(map) according to value



### Call dictionary API
public 

### Remember curve

