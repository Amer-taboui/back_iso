package com.crm.operis_app.controller.Utils;

import com.crm.operis_app.model.Utils.Settings;
import com.crm.operis_app.services.Utils.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class SettingsController {

    @Autowired
    SettingsService settingsService;

    @RequestMapping(value = "/createSettings", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Settings createSettings(@RequestBody Settings settings) {
        return settingsService.createSettings(settings);
    }

    @RequestMapping(value = "/settings/{settingsId}", method = RequestMethod.GET)
    public Optional<Settings> getSettingsById(@PathVariable(value = "settingsId") Long settingsId) {
        return settingsService.getSettingsById(settingsId);
    }

    @RequestMapping(value = "/settings/{settingsId}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Settings updateSettings(@PathVariable(value = "settingsId") Long settingsId, @RequestBody Settings settings) {
        return settingsService.updateSettingsById(settingsId, settings);
    }

    @RequestMapping(value = "/settings/{settingsId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSettingsById(@PathVariable(value = "settingsId") Long settingsId) {
        return settingsService.deleteSettingsById(settingsId);
    }

}
