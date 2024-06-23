package com.manjemu.plagiarisimdetector.service;

import com.manjemu.plagiarisimdetector.model.Document;
import com.manjemu.plagiarisimdetector.model.PlagiarismReport;
import com.manjemu.plagiarisimdetector.repository.DocumentRepository;
import com.manjemu.plagiarisimdetector.repository.PlagiarismReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PlagiarimService {
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private PlagiarismReportRepository plagiarismReportRepository;
    @Autowired
    private ScholarApi scholarApi;
    @Autowired
    private WebScraper webScraper;

    public PlagiarismReport checkPlagiarism(Document newDocument){
        List<Document> allDocuments = documentRepository.findAll();
        double maxSimilarity = 0;
        Document mostSimilarDocument = null;
        String matchedText="";

        List<String> newDocumentTokens = TextPreprocessor.preprocess(newDocument.getContent());
        Set<String> newDocumentShingles = ShingleGenerator.generateShingles(newDocumentTokens,3); //using trigrams

        //checking against local documents
        for (Document document: allDocuments){
            if (!document.getId().equals(newDocument.getId())){
                List<String> documentTokens = TextPreprocessor.preprocess(document.getContent());
                Set<String> documentShingles = ShingleGenerator.generateShingles(documentTokens,3);

                double similarity = SimilarityCalculator.calculateJaccardSimilarity(newDocumentShingles,documentShingles);
                if (similarity> maxSimilarity){
                    maxSimilarity = similarity;
                    mostSimilarDocument = document;
                    matchedText = document.getContent();
                }
            }
        }

        PlagiarismReport report = new PlagiarismReport();
        report.setDocument(newDocument);
        report.setSimilarityScore(maxSimilarity);
        report.setMatchedText(matchedText);
        plagiarismReportRepository.save(report);

        return report;
    }
}
