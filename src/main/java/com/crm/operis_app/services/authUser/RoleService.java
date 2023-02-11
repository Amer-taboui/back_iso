package com.crm.operis_app.services.authUser;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.authUser.Role;
import com.crm.operis_app.repository.authUser.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }


    public Optional<Role> getRoleById(Long roleId) {
        if (!roleRepository.existsById(roleId)) {
            throw new ResourceNotFoundException("ROLE with id " + roleId + " not found");
        }
        return roleRepository.findById(roleId);
    }

    public Role updateRoleById(Long roleId, Role RoleRequest) {
        if (!roleRepository.existsById(roleId)) {
            throw new ResourceNotFoundException("ROLE with id " + roleId + " not found");
        }
        Optional<Role> role = roleRepository.findById(roleId);
        if (!role.isPresent()) {
            throw new ResourceNotFoundException("ROLE with id " + roleId + " not found");
        }
        Role role1 = role.get();
        role1.setName(RoleRequest.getName());
        return roleRepository.save(role1);
    }

    public ResponseEntity<Object> deleteRoleById(long roleId) {
        if (!roleRepository.existsById(roleId)) {
            throw new ResourceNotFoundException("ROLE with id " + roleId + " not found");
        }
        roleRepository.deleteById(roleId);
        return ResponseEntity.ok().build();
    }

}
