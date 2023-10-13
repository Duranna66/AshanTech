package com.example.apollotabacco.services;

import com.example.apollotabacco.dto.UserDTO;
import com.example.apollotabacco.entities.User;
import com.example.apollotabacco.repositories.RoleRepo;
import com.example.apollotabacco.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    public UserDTO findByName(String name) {
        User user = userRepo.findByName(name);
        return new UserDTO(user.getId(),
                user.getName(),
                user.getRoles(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getBucket());
    }
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByName(username);
        return new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(),
                user.getRoles().stream().map(x -> new SimpleGrantedAuthority(x.getName())).toList());
    }
    public void createNewUser(User user) {
        user.setRoles(List.of(roleRepo.findByName("USER_ROLE")));
        userRepo.save(user);
    }
}
