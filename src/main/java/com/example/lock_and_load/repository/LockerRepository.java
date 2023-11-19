package com.example.lock_and_load.repository;

import com.example.lock_and_load.entity.Locker;
import java.util.List;

public interface LockerRepository {
    public void saveAndFlush(Locker locker);
    public Locker findById(String id);
    public List<Locker> findAll();
    public void update(Locker locker);
    public void deleteById(String id);
}
