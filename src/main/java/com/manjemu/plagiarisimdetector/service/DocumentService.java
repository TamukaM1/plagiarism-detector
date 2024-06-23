package com.manjemu.plagiarisimdetector.service;

import com.manjemu.plagiarisimdetector.model.Document;
import com.manjemu.plagiarisimdetector.repository.DocumentRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public Document saveDocument(Document document){
        return documentRepository.save(document);
    }

    public List<Document> getAllDocuments(){
        return documentRepository.findAll();
    }

    public Document getDocumentById(Long id){
        return documentRepository.findById(id).orElse(null);
    }

    public void deleteDocument(Long id){
        documentRepository.deleteById(id);
    }

    public String extractTextFromFile(MultipartFile file) throws IOException {
        String fileType = file.getContentType();
        InputStream inputStream = file.getInputStream();

        switch (fileType) {
            case "text/plain":
                return new String(inputStream.readAllBytes());

            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
                try (XWPFDocument doc = new XWPFDocument(inputStream);
                     XWPFWordExtractor extractor = new XWPFWordExtractor(doc)) {
                    return extractor.getText();
                }

            case "application/pdf":
                try (PDDocument pdfDocument = PDDocument.load(inputStream)) {
                    PDFTextStripper pdfStripper = new PDFTextStripper();
                    return pdfStripper.getText(pdfDocument);
                }

            default:
                throw new IOException("Unsupported file type: " + fileType);
        }
    }
}
