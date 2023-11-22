package com.example.apollotabacco.services;


import com.example.apollotabacco.entities.User;
import com.example.apollotabacco.repositories.RoleRepo;
import com.example.apollotabacco.repositories.UserRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService  {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private RoleService roleService;

    public User update(User user) {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            User user1 = session.get(User.class, user.getId());
            user1.setName(user.getName());
            user1.setPhoneNumber(user.getPhoneNumber());
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return user;
    }

    public List<User> getAll() {
        return userRepo.getAllBy();
    }

    public List<User> save(List<User> users) {
        userRepo.saveAll(users);
        return users;
    }

}
