package com.crm.operis_app.services.Utils;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.Utils.Settings;
import com.crm.operis_app.repository.Utils.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class SettingsService {

    @Autowired
    SettingsRepository settingsRepository;


    public Optional<Settings> getSettingsById(Long settingsId) {
        if (!settingsRepository.existsById(settingsId)) {
            throw new ResourceNotFoundException(" Settings with id " + settingsId + " not found");
        }
        return settingsRepository.findById(settingsId);
    }


    public Settings createSettings(Settings settings) {
        return settingsRepository.save(settings);
    }

    public Settings updateSettingsById(Long settingsId, Settings settingsRequest) {

        Optional<Settings> settings = settingsRepository.findById(settingsId);
        if (!settingsRepository.existsById(settingsId)) {
            throw new ResourceNotFoundException(" Settings with id " + settingsId + " not found");
        }
        Settings settings1 = settings.get();
        settings1.setGlobalCost(settingsRequest.getGlobalCost());
        settings1.setHumanCost(settingsRequest.getHumanCost());
        settings1.setHorsTaxes(settingsRequest.getHorsTaxes());

        return settingsRepository.save(settings1);
    }


    public ResponseEntity<Object> deleteSettingsById(Long settingsId) {
        if (!settingsRepository.existsById(settingsId)) {
            throw new ResourceNotFoundException("Settings with id " + settingsId + " not found");
        }
        settingsRepository.deleteById(settingsId);
        return ResponseEntity.ok().build();
    }


}
