package com.evotek.nagakawa_be.service;


import com.evotek.nagakawa_be.model.Role;
import com.evotek.nagakawa_be.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements GeneralService<Role> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public void update(Long id, Role role) {
        Role role1 = roleRepository.findById(id).get();
        if(role1 == null){
            System.out.println("Không tìm thấy role có id: " + id + " để update");
            return;
        }
        role.setId(id);
        roleRepository.delete(role1);
        roleRepository.save(role);
        System.out.println("Update Successful!");
    }

    @Override
    public void remove(Long id) {
        Role role = roleRepository.findById(id).get();
        if(role == null){
            System.out.println("Không tìm thấy role có id: " + id + " để remove");
            return;
        }
        roleRepository.delete(role);
        System.out.println("Remove Successful!");
    }
}
