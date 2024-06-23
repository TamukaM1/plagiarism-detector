package com.manjemu.plagiarisimdetector.controller;

import com.manjemu.plagiarisimdetector.model.PlagiarismReport;
import com.manjemu.plagiarisimdetector.repository.PlagiarismReportRepository;
import com.manjemu.plagiarisimdetector.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private PlagiarismReportRepository plagiarismReportRepository;

    @Autowired
    private ReportService reportService;

    @GetMapping("/plagiarism/{documentId}")
    public ResponseEntity<InputStreamResource> downloadPlagiarismReport(@PathVariable Long documentId) {
        PlagiarismReport report = plagiarismReportRepository.findByDocumentId(documentId);

        ByteArrayInputStream bis = reportService.generatePlagiarismReport(report);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=plagiarism_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
