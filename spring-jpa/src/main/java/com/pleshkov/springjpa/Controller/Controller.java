package com.pleshkov.springjpa.Controller;

import com.pleshkov.springjpa.Student.ConfirmationToken;
import com.pleshkov.springjpa.Student.ConfirmationTokenService;
import com.pleshkov.springjpa.Student.Service;
import com.pleshkov.springjpa.Entity.Student;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


//@RestController
//@RequestMapping(path = "web/api/student")

@org.springframework.stereotype.Controller
@AllArgsConstructor
public class Controller {
    private final Service service;
    private final ConfirmationTokenService confirmationTokenService;

    @GetMapping("/sign-in")
    String signIn(){
        return "sign-in";
    }

    @GetMapping("/sign-up")
    String signUpPage(Student student){
        return "sign-up";
    }

    @PostMapping("/sign-up")
    String signUp(Student student){
        service.signUpStudent(student);
        return "redirect:/sign-in";
    }

    @PostMapping("/sign-up/confirm")
    String confirmEmail(@RequestParam("token") String token){
        Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);
        optionalConfirmationToken.ifPresent(service::confirmStudent);

        return "redirect:/sign-in";
    }
    /*
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

 */



}
