package com.pleshkov.springjpa.Config;

import com.pleshkov.springjpa.DAO.StudentDAO;
import com.pleshkov.springjpa.Entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Config {
    @Bean
    CommandLineRunner commandLineRunner (StudentDAO dao){
        return args ->{
            Student st1 = new Student(
                    "Nikolay",
                    "Pleshkov",
                    "stu2001321014@uni-plovdiv.bg",
                    19
            );

            dao.saveAll(List.of(st1));
        };
    }
}
