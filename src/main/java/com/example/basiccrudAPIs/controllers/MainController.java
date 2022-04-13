package com.example.basiccrudAPIs.controllers;

import com.example.basiccrudAPIs.entites.Student;
import com.example.basiccrudAPIs.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/students")
@Transactional(Transactional.TxType.SUPPORTS)
public class MainController {

    @Autowired
    StudentRepository repository;

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        System.out.println("Student:"+student);
        Student student1 =  repository.save(student);
        return student1;
    }

    @GetMapping
    public List<Student> getStudents() {
        List<Student> students =  repository.findAll();
        return students;
    }

    @PutMapping
    @Transactional(rollbackOn = Exception.class)
    public Student updateStudentAddress(@RequestParam("Address") String address,@RequestParam("Id") Integer Id) {
        repository.updateAddressById(address, Id);
        Student student =  repository.getById(Id);
        return student;
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteStudent(@RequestParam("Id") Integer Id) {
        repository.deleteById(Id);
        Boolean studentExists = repository.existsById(Id);
        if(!studentExists)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
