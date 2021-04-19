package com.pleshkov.springjpa.DAO;

import com.pleshkov.springjpa.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentDAO extends CrudRepository<Student, Long> {
    Optional<Student> findStudentByEmail(String email);
}
