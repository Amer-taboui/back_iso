package com.crm.operis_app.services.authUser;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.authUser.Privilege;
import com.crm.operis_app.model.authUser.Role;
import com.crm.operis_app.repository.authUser.PrivilegeRepository;
import com.crm.operis_app.repository.authUser.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    private RoleRepository roleRepository;


    public Privilege createPrivilege(Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

    public Optional<Privilege> getPrivilegeById(Long privilegeId) {
        if (!privilegeRepository.existsById(privilegeId)) {
            throw new ResourceNotFoundException("privilege with id " + privilegeId + " not found");
        }
        return privilegeRepository.findById(privilegeId);
    }

    public List<Privilege> getPrivilegeByModule(String module) {
        return privilegeRepository.findByModule(module);
    }

    public List<Privilege> getPrivilegeByRole(Long roleId, String module) {
        return privilegeRepository.findByRoleAndModule(roleId, module);
    }


    public List<String> getModuleList() {
        return privilegeRepository.findModuleList();
    }

    public List<String> getParametreModuleList() {
        return privilegeRepository.findParametreModuleList();
    }


    public Privilege updatePrivilegeById(Long privilegeId, Privilege PrivilegeRequest) {
        if (!privilegeRepository.existsById(privilegeId)) {
            throw new ResourceNotFoundException("privilege with id " + privilegeId + " not found");
        }
        Optional<Privilege> privilege = privilegeRepository.findById(privilegeId);
        if (!privilege.isPresent()) {
            throw new ResourceNotFoundException("privilege with id " + privilegeId + " not found");
        }
        Privilege privilege1 = privilege.get();
        privilege1.setName(PrivilegeRequest.getName());
        return privilegeRepository.save(privilege1);
    }

    public ResponseEntity<Object> deletePrivilegeById(long privilegeId) {
        if (!privilegeRepository.existsById(privilegeId)) {
            throw new ResourceNotFoundException("privilege with id " + privilegeId + " not found");
        }
        privilegeRepository.deleteById(privilegeId);
        return ResponseEntity.ok().build();
    }


    public void addPrivilegeToRole(Long roleId, Long privilegeId) {

        Optional<Privilege> privilegeById = privilegeRepository.findById(privilegeId);
        if (!privilegeById.isPresent()) {
            throw new ResourceNotFoundException("privilege with id " + privilegeId + " does not exist");
        }
        Privilege privilege = privilegeById.get();

        Optional<Role> roleById = roleRepository.findById(roleId);
        if (!roleById.isPresent()) {
            throw new ResourceNotFoundException("role with id " + roleId + " does not exist");
        }
        Role role = roleById.get();

        role.getPrivileges().add(privilege);
        roleRepository.save(role);
    }

    public void removePrivilegeFromRole(Long roleId, Long privilegeId) {

        Optional<Privilege> privilegeById = privilegeRepository.findById(privilegeId);
        if (!privilegeById.isPresent()) {
            throw new ResourceNotFoundException("privilege with id " + privilegeId + " does not exist");
        }
        Privilege privilege = privilegeById.get();

        Optional<Role> roleById = roleRepository.findById(roleId);
        if (!roleById.isPresent()) {
            throw new ResourceNotFoundException("role with id " + roleId + " does not exist");
        }
        Role role = roleById.get();
        role.getPrivileges().remove(privilege);
        roleRepository.save(role);
    }

}
