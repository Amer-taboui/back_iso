package com.crm.operis_app.repository.files;

import com.crm.operis_app.model.files.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface FileRepository extends JpaRepository<FileModel, Long> {
    List<FileModel> findByFormationId(Long id);
    List<FileModel> findByListeReclamationId(Long id);
    List<FileModel> findByPostId(Long id);
    List<FileModel> findByListeActionCorrectionId(Long id);


}


