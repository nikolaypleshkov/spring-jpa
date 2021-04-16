package com.pleshkov.springjpa.Entity;

import com.pleshkov.springjpa.DAO.StudentDAO;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Service {
    private StudentDAO dao;

    public Service(StudentDAO dao){
        this.dao = dao;
    }

    public List<Student> getStudent(){
        return  dao.findAll();
    }

    public void Post(Student student){
        Optional<Student> email = dao.findStudentByEmail(student.getEmail());

        if(email.isPresent()){
            throw new IllegalStateException("Student exist!");
        }
        dao.save(student);
    }

    public void delete(Long id){
        boolean isPresent = dao.existsById(id);
        if(!isPresent){
            throw  new IllegalStateException("Student with id "+id+" does not exist!");
        }
        dao.deleteById(id);
    }

    public void put(Long id, String name, String email){
        Student student = dao.findById(id).orElseThrow(() ->
                new IllegalStateException("Student with id "+id+" does not exist!"));
        if(name != null && name.length() > 0 && !Objects.equals(student.getF_name(), name)){
            student.setF_name(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = dao.findStudentByEmail(email);

            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email exist");
            }
            student.setEmail(email);
        }


    }
}
