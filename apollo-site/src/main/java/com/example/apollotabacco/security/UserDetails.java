//package com.example.apollotabacco.security;
//
//import com.example.apollotabacco.entities.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//
//public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
//    private final User user;
//    public UserDetails(User user) {
//        this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return this.user.getName();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
