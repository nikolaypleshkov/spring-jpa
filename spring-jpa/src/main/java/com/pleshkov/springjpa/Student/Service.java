package com.pleshkov.springjpa.Student;

import com.pleshkov.springjpa.DAO.StudentDAO;
import com.pleshkov.springjpa.Entity.Student;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class Service implements UserDetailsService {
    private StudentDAO dao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSenderService emailSenderService;

    void sendConfirmationEmail(String email, String token) {
        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Confirm email");
        mailMessage.setFrom("<MAIL>");
        mailMessage.setText(
                "Click on the link to activate your account." + "http://localhost:8080/sign-up/confirm?token="
                        + token);
        emailSenderService.sendEmail(mailMessage);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        final Optional<Student> studentOptional = dao.findStudentByEmail(email);

        return studentOptional.orElseThrow(() ->
                new UsernameNotFoundException(
                        MessageFormat.format("User with email {0} cannot be found!",email)));
    }

    public void signUpStudent(Student student){
        final String encryptedPassword = bCryptPasswordEncoder.encode(student.getPassword());
        student.setPassword(encryptedPassword);

        final Student createStudent = dao.save(student);

        final ConfirmationToken confirmationToken = new ConfirmationToken(student);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        sendConfirmationEmail(student.getEmail(), confirmationToken.getConfirmationToken());

    }

    public void confirmStudent(ConfirmationToken confirmationToken){
    final Student student = confirmationToken.getStudent();

    student.setEnable(true);
    //student.setEnabled(true);
    dao.save(student);
    confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());

    }


    /*
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

     */

}
