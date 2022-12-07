package com.springbootplayground.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/student")
@Validated
public class StudentController {

    /**
     * Best practice is to use Construction Injection instead of Field Injection
     * <a href="https://stackoverflow.com/questions/40620000/spring-autowire-on-properties-vs-constructor">Injection</a>
     */
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // enable cors for React to call get API
    @CrossOrigin
    @GetMapping
    public List<StudentEntity> getStudents(){
        return studentService.getStudents();
    }

    // find single student
    @GetMapping("/{id}")
    public Optional<StudentEntity> getSingleStudent(@PathVariable Long id){
        return studentService.getSingleStudent(id);

    }

    @PostMapping
    public void registerNewStudent(@RequestBody StudentEntity studentEntity){
        studentService.addNewStudent(studentEntity);
    }

    @DeleteMapping(path = "/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "/{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email){
        studentService.updateStudent(studentId, name, email);
    }
}
