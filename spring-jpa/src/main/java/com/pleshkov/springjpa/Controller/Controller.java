package com.pleshkov.springjpa.Controller;

import com.pleshkov.springjpa.Entity.Service;
import com.pleshkov.springjpa.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "web/api/student")
public class Controller {
    private final Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> getStudent(){
        return service.getStudent();
    }

    @PostMapping
    public void Post(@RequestBody Student student){
        service.Post(student);
    }

    @DeleteMapping
    public void delete(@PathVariable("studentId") Long id){
        service.delete(id);
    }

    @PutMapping
    public void put(@PathVariable("student_id") Long id,
                    @RequestParam(required = false) String name,
                    @RequestParam(required = false) String email){
        service.put(id,name,email);
    }

}
