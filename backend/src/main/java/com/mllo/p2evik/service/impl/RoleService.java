package com.mllo.p2evik.service.impl;

import com.mllo.p2evik.entity.Role;
import com.mllo.p2evik.exception.RoleNotFoundException;
import com.mllo.p2evik.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RoleService {
    RoleRepository roleRepository;

    public Role getInstance(String role) throws RoleNotFoundException {
        return roleRepository.findByName(role).orElseThrow(() -> new RoleNotFoundException("Role not found"));
        }
}