package ru.pimalex1978.read_doc;

import java.io.*;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDocFile {
    public static void main(String[] args) {
        File file = null;
        WordExtractor extractor = null;
        try {

            //file = new File("c:\\New.doc");
            file = new File("D:\\001 English\\emil\\time-is-money-british-english-teacher.docx");
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            HWPFDocument document = new HWPFDocument(fis);
            extractor = new WordExtractor(document);
            String[] fileData = extractor.getParagraphText();
            for (int i = 0; i < fileData.length; i++) {
                if (fileData[i] != null)
                    System.out.println(fileData[i]);
            }
        } catch (Exception exep) {
            exep.printStackTrace();
        }
    }

    /**
     * Создает соответствующую Xssfworkbook / XSSFWorkbook для работы с XSSFWorkbook
     * из заданного InputStream.
     * Your input stream MUST either support mark/reset, or
     * be wrapped as a {@link PushbackInputStream}!
     */
    public static Workbook create(InputStream inp) throws IOException, InvalidFormatException {
        // If clearly doesn't do mark/reset, wrap up
        if (!inp.markSupported()) {
            inp = new PushbackInputStream(inp, 8);
        }
        if (POIFSFileSystem.hasPOIFSHeader(inp)) {
            return new HSSFWorkbook(inp);
        }
        if (POIXMLDocument.hasOOXMLHeader(inp)) {
            return new XSSFWorkbook(OPCPackage.open(inp));
        }
        throw new IllegalArgumentException("Your InputStream was neither an OLE2 stream, nor an OOXML stream");
    }
}
