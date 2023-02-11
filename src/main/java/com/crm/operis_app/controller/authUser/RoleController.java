package com.crm.operis_app.controller.authUser;

import com.crm.operis_app.model.authUser.Role;
import com.crm.operis_app.services.authUser.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")

public class RoleController {


    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/allRoles", method = RequestMethod.GET)
    public List<Role> getAllRoles() {
        return roleService.getRoles();
    }


    @RequestMapping(value = "/createRole", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @RequestMapping(value = "/role/{roleId}", method = RequestMethod.GET)
    public Optional<Role> getRoleById(@PathVariable(value = "roleId") Long roleId) {
        return roleService.getRoleById(roleId);
    }

    @RequestMapping(value = "/role/{roleId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Role updateRole(@PathVariable(value = "roleId") Long roleId, @RequestBody Role role) {
        return roleService.updateRoleById(roleId, role);
    }

    @RequestMapping(value = "/role/{roleId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteRoleById(@PathVariable(value = "roleId") Long roleId) {
        return roleService.deleteRoleById(roleId);
    }

}
