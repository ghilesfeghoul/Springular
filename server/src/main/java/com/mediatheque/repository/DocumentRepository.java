package com.mediatheque.repository;

import com.mediatheque.model.Document;
import com.mediatheque.model.Localisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * @Author Ghiles FEGHOUL
 * @Date 27/01/2018
 * @Licence MIT
 */
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findDocumentsByLocalization(Localisation localisation);
}
