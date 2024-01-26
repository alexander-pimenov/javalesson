package ru.pimalex1978.read_doc;

import org.apache.poi.hpsf.PropertySetFactory;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.poifs.eventfilesystem.POIFSReader;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderEvent;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderListener;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadSummaryInformation {
    public static void main(final String[] args) throws IOException {
        //final String filename = "C://file.docx";
        final String filename = "D:\\001 English\\emil\\time-is-money-british-english-teacher.docx";
        POIFSReader r = new POIFSReader();
        r.registerListener(new MyPOIFSReaderListener(),
                "\005SummaryInformation");
        r.read(new FileInputStream(filename));
    }

    static class MyPOIFSReaderListener implements POIFSReaderListener {
        public void processPOIFSReaderEvent(final POIFSReaderEvent event) {
            SummaryInformation si = null;
            try {
                si = (SummaryInformation)
                        PropertySetFactory.create(event.getStream());
            } catch (Exception ex) {
                throw new RuntimeException
                        ("Property set stream \"" +
                                event.getPath() + event.getName() + "\": " + ex);
            }
            final String title = si.getTitle();
            if (title != null)
                System.out.println("Title: \"" + title + "\"");
            else
                System.out.println("Document has no title.");
        }
    }
}
//OPCPackage pkg = OPCPackage.open(new File("file.xlsx"));
//POIXMLProperties props = new POIXMLProperties(pkg);
//System.out.println("The title is " + props.getCorePart().getTitle());

//Package fs = Package.open(new ByteArrayInputStream(container.getContent()));
//            XSSFWorkbook wb = new XSSFWorkbook(fs);
//            XSSFSheet sheet = wb.getSheetAt(0);
//            XSSFRow row;
//            XSSFCell cell;
