package com.manjemu.plagiarisimdetector.repository;

import com.manjemu.plagiarisimdetector.model.PlagiarismReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlagiarismReportRepository extends JpaRepository<PlagiarismReport,Long> {
    PlagiarismReport findByDocumentId(Long documentId);
}
