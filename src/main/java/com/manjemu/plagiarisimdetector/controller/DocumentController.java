package com.manjemu.plagiarisimdetector.controller;

import com.manjemu.plagiarisimdetector.model.Document;
import com.manjemu.plagiarisimdetector.service.DocumentService;
import com.manjemu.plagiarisimdetector.service.PlagiarimService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private PlagiarimService plagiarismService;

    @Operation(summary = "Upload a document for plagiarism detection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="File uploaded successfully"),
            @ApiResponse(responseCode = "500", description ="Failed to upload file")
    })
    @PostMapping("/upload")
    public ResponseEntity<Document> uploadDocument(@RequestParam("file") MultipartFile file) {
        try {
            String content = documentService.extractTextFromFile(file);
            Document document = new Document();
            document.setTitle(file.getOriginalFilename());
            document.setContent(content);

            Document savedDocument = documentService.saveDocument(document);
            plagiarismService.checkPlagiarism(savedDocument);
            return ResponseEntity.ok(savedDocument);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
