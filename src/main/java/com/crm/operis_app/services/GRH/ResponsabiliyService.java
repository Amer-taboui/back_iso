package com.crm.operis_app.services.GRH;

import com.crm.operis_app.model.GRH.Responsability;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ResponsabiliyService {
    List<Responsability> getAllResponsabiliy( );
    Responsability createResponsabiliy(Long posteId, Responsability responsability);
    Optional<Responsability> getResponsabiliyById(Long ficheFonctionId);
    Responsability updateResponsabiliyById(Long ficheFonctionId, Responsability responsabilityRequest);
    ResponseEntity<Object> deleteResponsabiliyById(Long ficheFonctionId);
}
