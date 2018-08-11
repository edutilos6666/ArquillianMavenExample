package com.edutilos.dao;

import com.edutilos.model.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by edutilos on 05.08.18.
 */
@Component
public class WorkerDAOJPAImpl implements WorkerDAO {
    @PersistenceContext(name = "worker-unit")
    private EntityManager em;
    private final Logger logger = LoggerFactory.getLogger(WorkerDAOJPAImpl.class);
    public WorkerDAOJPAImpl() {
  /*      em = Persistence.createEntityManagerFactory("worker-unit")
                .createEntityManager();*/
    }
    public void close() {
        em.close();
    }
    @Override
    @Transactional
    public void insert(Worker worker) {
//        em.getTransaction().begin();
        em.persist(worker);
//        em.getTransaction().commit();
    }

    @Override
    @Transactional
    public void update(long id, Worker newWorker) {
/*        Worker oldWorker = em.find(Worker.class, id);
//        em.getTransaction().begin();
        oldWorker.setName(newWorker.getName());
        oldWorker.setAge(newWorker.getAge());
        oldWorker.setWage(newWorker.getWage());
        oldWorker.setActive(newWorker.isActive());*/
        em.merge(newWorker);
//        em.getTransaction().commit();
    }

    @Override
    @Transactional
    public void delete(long id) {
//        em.getTransaction().begin();
        Worker w = em.find(Worker.class, id);
        em.remove(w);
//        em.getTransaction().commit();
    }

    @Override
    public Worker findById(long id) {
        Worker w = em.find(Worker.class, id);
        return w;
    }

    @Override
    public List<Worker> findByName(String name) {
        try {
            List<Worker> res = em.createQuery("from Worker w where w.name = :name", Worker.class)
                    .setParameter("name", name)
                    .getResultList();
            return res;
        } catch(NoResultException ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Worker> findByAge(int age) {
        try {
            return em.createQuery("from Worker w where w.age = :age", Worker.class)
                    .setParameter("age", age)
                    .getResultList();
        } catch(NoResultException ex) {
            logger.error(ex.getMessage());
            return null;
        }

    }

    @Override
    public List<Worker> findByWage(double wage) {
        try {
            return em.createQuery("from Worker w where w.wage = :wage", Worker.class)
                    .setParameter("wage",wage)
                    .getResultList();
        } catch(NoResultException ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Worker> findByActive(boolean active) {
        try {
            return em.createQuery("from Worker w where w.active = :active", Worker.class)
                    .setParameter("active", active)
                    .getResultList();
        } catch(NoResultException ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Worker> findAll() {
        try {
            return em.createQuery("select w from Worker w", Worker.class)
                    .getResultList();
        } catch(NoResultException ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }
}