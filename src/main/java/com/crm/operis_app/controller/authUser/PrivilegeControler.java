package com.crm.operis_app.controller.authUser;

import com.crm.operis_app.model.authUser.Privilege;
import com.crm.operis_app.services.authUser.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")

public class PrivilegeControler {

    @Autowired
    PrivilegeService privilegeService;

    @RequestMapping(value = "/createPrivilege", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Privilege createPrivilege(@RequestBody Privilege privilege) {
        return privilegeService.createPrivilege(privilege);
    }

    @RequestMapping(value = "/privilege/{privilegeId}", method = RequestMethod.GET)
    public Optional<Privilege> getPrivilegeById(@PathVariable(value = "privilegeId") Long privilegeId) {
        return privilegeService.getPrivilegeById(privilegeId);
    }

    @RequestMapping(value = "/privileges/module/{module}", method = RequestMethod.GET)
    public List<Privilege> getPrivilegeByModule(@PathVariable(value = "module") String module) {
        return privilegeService.getPrivilegeByModule(module);
    }

    @RequestMapping(value = "/privilegesByRole", method = RequestMethod.GET)
    public List<Privilege> getPrivilegeByRole(@RequestParam(value = "roleId") Long roleId,@RequestParam(value = "module") String module) {
        return privilegeService.getPrivilegeByRole(roleId,module);
    }

    @RequestMapping(value = "/modules", method = RequestMethod.GET)
    public List<String> getModuleList() {
        return privilegeService.getModuleList();
    }

    @RequestMapping(value = "/parametre/modules", method = RequestMethod.GET)
    public List<String> getParametreModuleList() {
        return privilegeService.getParametreModuleList();
    }


    @RequestMapping(value = "/privilege/{roleId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Privilege updatePrivilege(@PathVariable(value = "roleId") Long roleId, @RequestBody Privilege privilege) {
        return privilegeService.updatePrivilegeById(roleId, privilege);
    }

    @RequestMapping(value = "/privilege/{roleId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePrivilegeById(@PathVariable(value = "roleId") Long roleId) {
        return privilegeService.deletePrivilegeById(roleId);
    }



    @PostMapping(value = "/role/{roleId}/privilege/{privilegeId}")
    public void addSiteToAudit(@PathVariable(value = "roleId") Long roleId, @PathVariable(value = "privilegeId") Long privilegeId) {
        privilegeService.addPrivilegeToRole(roleId,privilegeId);
    }

    @PostMapping(value = "/roles/{roleId}/privilege/{privilegeId}")
    public void removeSiteFromAudit(@PathVariable(value = "roleId") Long roleId, @PathVariable(value = "privilegeId") Long privilegeId) {
        privilegeService.removePrivilegeFromRole(roleId,privilegeId);
    }

}
