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
}