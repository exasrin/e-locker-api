package com.example.lock_and_load.repository.impl;

import com.example.lock_and_load.entity.User;
import com.example.lock_and_load.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;
    @Override
    public void saveAndFlush(User user) {
        entityManager.createNativeQuery("INSERT INTO m_user (id, name, address, phone_number, password) VALUES (?,?,?,?,?)")
                .setParameter(1, user.getId())
                .setParameter(2 ,user.getName())
                .setParameter(3,user.getAddress())
                .setParameter(4,user.getPhoneNumber())
                .setParameter(5,user.getPassword())
                .executeUpdate();
        entityManager.flush();
    }

    @Override
    public User findById(String id) {
        return (User) entityManager.createNativeQuery("SELECT * FROM m_user WHERE id = ?", User.class)
                .setParameter(1, id)
                .getSingleResult();
    }

    @Override
    public List findAll() {
        return entityManager.createNativeQuery("SELECT * FROM m_user", User.class)
                .getResultList();
    }

    @Override
    public void update(User user) {
        entityManager.createNativeQuery("UPDATE m_user SET name = ?, address = ?, phone_number = ?, password = ? WHERE id = ?")
                .setParameter(1,user.getName())
                .setParameter(2,user.getAddress())
                .setParameter(3,user.getPhoneNumber())
                .setParameter(4,user.getPassword())
                .setParameter(5, user.getId())
                .executeUpdate();
    }

    @Override
    public void deleteById(String id) {
        entityManager.createNativeQuery("DELETE FROM m_user WHERE id = ?")
                .setParameter(1, id)
                .executeUpdate();
    }
}
