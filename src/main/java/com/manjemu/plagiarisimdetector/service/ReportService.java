package com.manjemu.plagiarisimdetector.service;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.manjemu.plagiarisimdetector.model.PlagiarismReport;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class ReportService {

    public ByteArrayInputStream generatePlagiarismReport(PlagiarismReport report) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        Paragraph title = new Paragraph("Plagiarism Report")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(20);
        document.add(title);

        Paragraph similarity = new Paragraph("Similarity Score: " + report.getSimilarityScore() * 100 + "%")
                .setFontSize(12);
        document.add(similarity);

        Paragraph originalText = new Paragraph("Original Document:")
                .setFontSize(12)
                .setBold();
        document.add(originalText);

        Paragraph content = new Paragraph(report.getDocument().getContent())
                .setFontSize(12);
        document.add(content);

        Paragraph matchedText = new Paragraph("Matched Text:")
                .setFontSize(12)
                .setBold();
        document.add(matchedText);

        Paragraph plagiarizedContent = new Paragraph(report.getMatchedText())
                .setFontSize(12)
                .setFontColor(new DeviceRgb(255, 0, 0)); // Highlight in red
        document.add(plagiarizedContent);

        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
