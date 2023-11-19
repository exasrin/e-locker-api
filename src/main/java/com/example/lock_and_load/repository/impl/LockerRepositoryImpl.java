package com.example.lock_and_load.repository.impl;

import com.example.lock_and_load.entity.Locker;
import com.example.lock_and_load.entity.User;
import com.example.lock_and_load.repository.LockerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@AllArgsConstructor
public class LockerRepositoryImpl implements LockerRepository {
    private final EntityManager entityManager;
    @Override
    public void saveAndFlush(Locker locker) {
        entityManager.createNativeQuery("INSERT INTO m_locker (id, capacity, status, user_id) VALUES (?,?,?,?)")
                .setParameter(1, locker.getId())
                .setParameter(2, locker.getCapacity())
                .setParameter(3, locker.getStatus())
                .setParameter(4, locker.getUser())
                .executeUpdate();
        entityManager.flush();
    }

    @Override
    public Locker findById(String id) {
        return (Locker) entityManager.createNativeQuery("SELECT * FROM m_locker WHERE id = :id", Locker.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List findAll() {
        return entityManager.createNativeQuery("SELECT * FROM m_locker", Locker.class)
                .getResultList();
    }

    @Override
    public void update(Locker locker) {
        entityManager.createNativeQuery("UPDATE m_locker SET capacity = ?, status = ?, user_id = ? WHERE id = ?")
                .setParameter(1,locker.getCapacity())
                .setParameter(2,locker.getStatus())
                .setParameter(3,locker.getUser())
                .setParameter(4,locker.getId())
                .executeUpdate();
    }

    @Override
    public void deleteById(String id) {
        entityManager.createNativeQuery("DELETE FROM m_locker WHERE id = ?")
                .setParameter(1, id)
                .executeUpdate();
    }
}
