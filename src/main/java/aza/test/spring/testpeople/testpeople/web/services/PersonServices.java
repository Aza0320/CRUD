package aza.test.spring.testpeople.testpeople.web.services;

import aza.test.spring.testpeople.testpeople.web.models.Person;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PersonServices {
    public static byte[] createPDF(Person person) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc, PageSize.B4);

        ArrayList<String> list = new ArrayList<>();
        list.add("FAMILIYASI / SURNAME");
        list.add("ISMI / GIVEN NAME");
        list.add("FUQAROLIGI / NATIONALITY");
        list.add("TUG'ILGAN SANASI / DATE OF BIRTH");
        list.add("JINSI / SEX");
        list.add("TUG'ILGAN JOYI / PLACE OF BRITH");
        list.add("BERILGAN SANASI / DATE OF ISSUE");
        list.add("AMAL QLISH MUDDATI / DATE OF EXPIRY");
        list.add("PERSONALLASHTRISH ORGANI / AUTHORITY");

        ArrayList<String> listPerson = new ArrayList<>();
        listPerson.add(person.getSurname().toUpperCase(Locale.ROOT));
        listPerson.add(person.getName().toUpperCase(Locale.ROOT));
        listPerson.add("UZBEKISTAN");
        listPerson.add(person.getDobString());
        listPerson.add(person.getSex().toUpperCase(Locale.ROOT));
        listPerson.add(person.getCountry());
        listPerson.add(person.getDoiString());
        listPerson.add(person.getDoeString());
        listPerson.add("STATE PERSONALIZATION CENTRE");

        ArrayList<String> listHeader = new ArrayList<>();
        listHeader.add("PASSPORT");
        listHeader.add("TURN/TYPE");
        listHeader.add("DAVLAT KODI / COUNTRY CODE");
        listHeader.add("PASSPORT RAQAMI / PASSPORT No.");

        ArrayList<String> listHeaderNest = new ArrayList<>();
        listHeaderNest.add("PASSPORT");
        listHeaderNest.add("P");
        listHeaderNest.add("UZB");
        listHeaderNest.add(person.getPassport());

        Table header = new Table(new float[]{200, 100, 250, 300});
        header.setMarginBottom(20);
        header.setTextAlignment(TextAlignment.CENTER);

        for (int i = 0; i < listHeader.size(); i++) {
            List<Cell> cellList = new ArrayList<>(listCell(listHeader.get(i), listHeaderNest.get(i)));

            Table headerNestTable = new Table(1);
            if (i == 0) headerNestTable = new Table(2);

            headerNestTable.addCell(cellList.get(0));
            headerNestTable.addCell(cellList.get(1));

            header.addCell(headerNestTable);
        }
        header.setBorder(new SolidBorder(Color.WHITE, 2));
        header.setBorderTop(new SolidBorder(Color.BLACK, 0.75f));
        doc.add(header);

        Table table = new Table(new float[]{150, 500});
        Cell cell1 = new Cell();
        File file = new ClassPathResource("static/images/M/Yangiyo'l shahar.jpg").getFile();
        String path = file.toString().split("target")[0] + "src/main/resources/static/images/" + person.getSex() + "/" + person.getRegion() + ".jpg";
        System.out.println(path);
        ImageData data = ImageDataFactory.create(path);
        Image img = new Image(data);
        img.setHeight(450);
        img.setWidth(350);
        cell1.add(img);
        cell1.setBorder(Border.NO_BORDER);
        table.addCell(cell1);

        Table nestedTable = new Table(new float[]{500});
        Table innerTable = new Table(new float[]{75, 300});
        Table footerTable = new Table(new float[]{300, 300});
        Table footerInnerTable = new Table(new float[]{300});

        for (int i = 0; i < list.size(); i++) {
            List<Cell> cellList = new ArrayList<>(listCell(list.get(i), listPerson.get(i)));
            if (i > 3) {
                Table innerNestTable = new Table(new float[]{300});

                innerNestTable.addCell(cellList.get(0).setFontSize(10));
                innerNestTable.addCell(cellList.get(1).setFontSize(15).setBold());
                innerNestTable.setBorder(new SolidBorder(Color.WHITE, 0.75f));

                if (i >= 6 && i < 8) footerInnerTable.addCell(innerNestTable);
                else if (i == 8) {
                    footerTable.addCell(footerInnerTable);
                    footerTable.addCell(innerNestTable);
                } else innerTable.addCell(innerNestTable);
            } else {
                nestedTable.addCell(cellList.get(0).setFontSize(10));
                nestedTable.addCell(cellList.get(1).setFontSize(15).setBold());
            }
        }

        innerTable.setTextAlignment(TextAlignment.CENTER);
        innerTable.setBorder(new SolidBorder(Color.WHITE, 0.75f));
        footerInnerTable.setBorder(new SolidBorder(Color.WHITE, 0.75f));
        footerTable.setBorder(new SolidBorder(Color.WHITE, 0.75f));
        nestedTable.setBorder(new SolidBorder(Color.WHITE, 0.75f));
        table.setBorder(new SolidBorder(Color.WHITE, 0.75f));

        nestedTable.addCell(innerTable);
        nestedTable.addCell(footerTable);
        table.addCell(nestedTable);
        doc.add(table);
        doc.close();
        return baos.toByteArray();
    }

    private static List<Cell> listCell(String header, String nest) {
        Cell cellHeader = new Cell();
        Cell cellNest = new Cell();
        cellHeader.add(header);
        cellNest.add(nest);

        cellHeader.setFontColor(Color.GREEN);
        cellHeader.setBorder(Border.NO_BORDER);
        cellNest.setBorder(Border.NO_BORDER);

        List<Cell> list = new ArrayList<>();
        list.add(cellHeader);
        list.add(cellNest);
        return list;
    }

    public static byte[] createImg(byte[] bytesPDF, Person person) throws Exception {
        File pdfFile = File.createTempFile(person.getPassport(), "pdf");
        FileUtils.writeByteArrayToFile(pdfFile, bytesPDF);
        RandomAccessFile raf = new RandomAccessFile(pdfFile, "r");
        FileChannel channel = raf.getChannel();
        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        PDFFile pdf = new PDFFile(buf);
        PDFPage page = pdf.getPage(0);

        Rectangle rect = new Rectangle(0, 0, 700,1000);
        BufferedImage bufferedImage = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_RGB);

        java.awt.Image image = page.getImage(rect.width, rect.height, rect, null, true, true);
        Graphics2D bufImageGraphics = bufferedImage.createGraphics();
        bufImageGraphics.drawImage(image, 0, 0, null);
        File file = File.createTempFile(person.getPassport(), "jpg");
        ImageIO.write(bufferedImage, "JPG", file);
        return Files.readAllBytes(Paths.get(String.valueOf(file)));
    }
}