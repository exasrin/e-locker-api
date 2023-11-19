package com.example.lock_and_load.repository;

import com.example.lock_and_load.entity.Locker;
import com.example.lock_and_load.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository {
    public void saveAndFlush(User user);
    public User findById(String id);
    public List<User> findAll();
    public void update(User user);
    public void deleteById(String id);
}
