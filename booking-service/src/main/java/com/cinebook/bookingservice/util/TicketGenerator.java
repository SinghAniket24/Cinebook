package com.cinebook.bookingservice.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

@Component
public class TicketGenerator {

    // CineBook Brand Colors
    private static final BaseColor BRAND_RED = new BaseColor(229, 9, 20); // Netflix Red
    private static final BaseColor DARK_GREY = new BaseColor(40, 40, 40);
    private static final BaseColor LIGHT_GREY = new BaseColor(240, 240, 240);

    public byte[] generateTicket(Long bookingId, String movieName, String seatNumber, String date, String amount) {
        try {
            // A5 Page Size (Half of A4 - Perfect for a Ticket)
            Document document = new Document(PageSize.A5, 20, 20, 20, 20);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, out);

            document.open();

            // --- 1. DRAW BORDER (The "Card" Effect) ---
            PdfContentByte canvas = writer.getDirectContent();
            Rectangle rect = new Rectangle(10, 10, PageSize.A5.getWidth() - 10, PageSize.A5.getHeight() - 10);
            rect.setBorder(Rectangle.BOX);
            rect.setBorderWidth(4);
            rect.setBorderColor(BRAND_RED);
            canvas.rectangle(rect);

            // --- 2. HEADER: LOGO & TITLE ---
            Font logoFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 26, BRAND_RED);
            Paragraph logo = new Paragraph("CINEBOOK", logoFont);
            logo.setAlignment(Element.ALIGN_CENTER);
            logo.setSpacingBefore(10);
            document.add(logo);

            Font subFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 10, BaseColor.GRAY);
            Paragraph subtitle = new Paragraph("PREMIUM ACCESS PASS", subFont);
            subtitle.setAlignment(Element.ALIGN_CENTER);
            subtitle.setSpacingAfter(20);
            document.add(subtitle);

            // --- 3. MOVIE TITLE HERO ---
            PdfPTable heroTable = new PdfPTable(1);
            heroTable.setWidthPercentage(100);
            PdfPCell movieCell = new PdfPCell(new Phrase(movieName.toUpperCase(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, BaseColor.BLACK)));
            movieCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            movieCell.setBorder(Rectangle.NO_BORDER);
            movieCell.setPaddingBottom(10);
            heroTable.addCell(movieCell);
            document.add(heroTable);

            // --- 4. DETAILS GRID (2 Columns) ---
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(90);
            table.setSpacingBefore(10);
            table.setSpacingAfter(20);

            // Row 1
            addDetailCell(table, "DATE & TIME", date);
            addDetailCell(table, "SEATS", seatNumber);
            
            // Row 2
            addDetailCell(table, "SCREEN", "AUDI 04 (IMAX)"); // Static placeholder for now
            addDetailCell(table, "AMOUNT PAID", "Rs. " + amount);
            
            // Row 3
            addDetailCell(table, "BOOKING ID", "#" + bookingId);
            addDetailCell(table, "STATUS", "CONFIRMED");

            document.add(table);

            // --- 5. TEAR-OFF LINE SEPARATOR ---
            DottedLineSeparator separator = new DottedLineSeparator();
            separator.setGap(5);
            separator.setLineWidth(1);
            Chunk linebreak = new Chunk(separator);
            document.add(new Paragraph(linebreak));
            document.add(new Paragraph("\n"));

            // --- 6. QR CODE (Contains JSON Data) ---
            // Real scanners look for structured data
            String qrContent = String.format("{\"app\":\"CineBook\",\"id\":%d,\"seats\":\"%s\",\"status\":\"VALID\"}", bookingId, seatNumber);
            
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, 250, 250);
            
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            Image qrImage = Image.getInstance(pngOutputStream.toByteArray());
            qrImage.setAlignment(Element.ALIGN_CENTER);
            qrImage.scalePercent(60); // Resize to fit nicely
            
            document.add(qrImage);

            // --- 7. FOOTER & INSTRUCTIONS ---
            Font footerFont = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 9, BaseColor.GRAY);
            Paragraph footer = new Paragraph("Scan this QR code at the cinema entrance.\nEnjoy your movie!", footerFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            footer.setSpacingBefore(5);
            document.add(footer);

            document.close();
            return out.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Helper Method for Grid Cells
    private void addDetailCell(PdfPTable table, String label, String value) {
        // Label Style (Small, Grey)
        Font labelFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.GRAY);
        // Value Style (Big, Bold, Dark)
        Font valueFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK);

        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPaddingBottom(15);

        Paragraph pLabel = new Paragraph(label, labelFont);
        cell.addElement(pLabel);

        Paragraph pValue = new Paragraph(value, valueFont);
        cell.addElement(pValue);

        table.addCell(cell);
    }
}