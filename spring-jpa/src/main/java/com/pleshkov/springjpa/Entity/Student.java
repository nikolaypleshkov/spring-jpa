package com.pleshkov.springjpa.Entity;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity(name = "Student")
@Table(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_email ",
                columnNames = "email")
        })
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Column(
            name = "id",
            updatable = false)
    private Long id;
    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT")
    private String f_name;
    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT")
    private String l_name;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT")
    private String email;

    @Column(
            name = "age",
            nullable = false)
    private int age;

    public Student(Long id, String f_name, String l_name, String email, int age) {
        this.id = id;
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.age = age;
    }

    public Student(String f_name, String l_name, String email, int age) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.age = age;
    }

    public Student() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", f_name='" + f_name + '\'' +
                ", l_name='" + l_name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
