package com.pleshkov.springjpa.DAO;

import com.pleshkov.springjpa.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentDAO extends JpaRepository<Student, Long> {

    @Query("SELECT st FROM Student st WHERE st.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
