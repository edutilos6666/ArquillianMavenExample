package com.edutilos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by edutilos on 05.08.18.
 */
@Entity
@Table(name="worker")
public class Worker {
    @Id
    @Column
    private long id;
    @Column
    private String name;
    @Column
    private int age;
    @Column
    private double wage;
    @Column
    private boolean active;

    public Worker() {

    }
    public Worker(long id, String name, int age, double wage, boolean active) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.wage = wage;
        this.active = active;
    }

    public Worker(String name, int age, double wage, boolean active) {
        this.name = name;
        this.age = age;
        this.wage = wage;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                        ", name='" + name + '\'' +
                        ", age=" + age +
                        ", wage=" + wage +
                        ", active=" + active;
    }
}