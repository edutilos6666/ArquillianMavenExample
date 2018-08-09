package com.edutilos.dao;

import com.edutilos.model.Worker;

import java.util.List;

/**
 * Created by edutilos on 05.08.18.
 */
public interface WorkerDAO {
    void insert(Worker worker);
    void update(long id, Worker newWorker);
    void delete(long id);
    Worker findById(long id);
    List<Worker> findByName(String name);
    List<Worker> findByAge(int age);
    List<Worker> findByWage(double wage);
    List<Worker> findByActive(boolean active);
    List<Worker> findAll();
}