package com.manjemu.plagiarisimdetector.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PlagiarismReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "document_id")
    private Document document;
    private double similarityScore;
    @Lob
    private String matchedText;
}
