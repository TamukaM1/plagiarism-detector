package com.manjemu.plagiarisimdetector.repository;

import com.manjemu.plagiarisimdetector.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {
}
