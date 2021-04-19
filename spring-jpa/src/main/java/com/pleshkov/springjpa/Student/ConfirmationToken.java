package com.pleshkov.springjpa.Student;

import com.pleshkov.springjpa.Entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String confirmationToken;
    private LocalDate date;

    @OneToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
    private Student student;

    ConfirmationToken(Student student){
        this.student = student;
        this.date = LocalDate.now();
        this.confirmationToken = UUID.randomUUID().toString();
    }
}
