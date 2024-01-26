package ru.pimalex1978.read_doc;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class DocxFileProcessor {
    public static void main(String[] args) {
        String inputFilePath = "D:\\001 English\\emil\\time-is-money-british-english-teacher.docx";
        //String inputFilePath = "path/to/input.docx";
        String outputFilePath = "D:\\001 English\\emil\\vocabulary-teacher.docx";
        //String outputFilePath = "path/to/output.docx";
        String[] targetWords = {"his", "questions", "provide"};
        //String[] targetWords = {"word1", "word2", "word3"};

        try {
            processDocxFile(inputFilePath, outputFilePath, targetWords);
            System.out.println("Processing completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processDocxFile(String inputFilePath, String outputFilePath, String[] targetWords)
            throws IOException {
        try (FileInputStream fis = new FileInputStream(inputFilePath);
             FileOutputStream fos = new FileOutputStream(outputFilePath)) {

            XWPFDocument document = new XWPFDocument(fis);

            // Iterate through paragraphs
            Iterator paragraphs = document.getParagraphsIterator();
            while (paragraphs.hasNext()) {
                XWPFRun run = ((XWPFParagraph) paragraphs.next()).createRun();

                // Iterate through words in the paragraph
                String paragraphText = run.toString();
                //String paragraphText = run.text();
                for (String targetWord : targetWords) {
                    if (paragraphText.contains(targetWord)) {
                        // Write the word to the output file
                        fos.write((targetWord + " ").getBytes());
                    }
                }
            }
        }
    }
}
