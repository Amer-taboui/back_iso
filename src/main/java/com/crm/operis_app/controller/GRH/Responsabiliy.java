package com.crm.operis_app.controller.GRH;

import com.crm.operis_app.model.GRH.Responsability;
import com.crm.operis_app.services.GRH.ResponsabiliyServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")

public class Responsabiliy {

    @Autowired
    ResponsabiliyServiceImp responsabiliyServiceImp;

    @RequestMapping(value = "/allResponsabiliy", method = RequestMethod.GET)
    public List<Responsability> getAllResponsabiliy() {
        return responsabiliyServiceImp.getAllResponsabiliy();
    }


    @RequestMapping(value = "/responsabiliy/{responsabiliyId}", method = RequestMethod.GET)
    public Optional<Responsability> getFicheFonctionById(@PathVariable(value = "responsabiliyId") Long responsabiliyId) {
        return responsabiliyServiceImp.getResponsabiliyById(responsabiliyId);
    }

    @RequestMapping(value = "/createResponsabiliy/posteListe/{posteId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Responsability createResponsabiliy(@PathVariable(value = "posteId") Long posteId, @RequestBody Responsability responsability) {
        return responsabiliyServiceImp.createResponsabiliy(posteId, responsability);
    }

    @RequestMapping(value = "/responsabiliy/{responsabiliyId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Responsability updateResponsabiliyById(@PathVariable(value = "responsabiliyId") Long responsabiliyId, @RequestBody Responsability responsability) {
        return responsabiliyServiceImp.updateResponsabiliyById(responsabiliyId, responsability);
    }

    @RequestMapping(value = "/responsabiliy/{responsabiliyId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteResponsabiliyById(@PathVariable(value = "responsabiliyId") Long responsabiliyId) {
        return responsabiliyServiceImp.deleteResponsabiliyById(responsabiliyId);
    }
}
