package com.springbootplayground.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("studentService")
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentEntity> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(StudentEntity studentEntity) {
        Optional<StudentEntity> studentNewEmail = studentRepository
                .findStudentByEmail(studentEntity.getEmail());

        if (studentNewEmail.isPresent()){
            throw new IllegalStateException("Email already exist !");
        }

        studentRepository.save(studentEntity);
    }

    public void deleteStudent(Long studentId) {

        boolean exist = studentRepository.existsById(studentId);

        if (!exist){
            throw new IllegalStateException("Student with id " + studentId + " does not exist !");
        }

        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        StudentEntity studentEntity = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id " + studentId + " does not exist !"));

        if (name != null && name.length() > 0 && !Objects.equals(studentEntity.getName(), name)){
            studentEntity.setName(name);
        }

        if (email != null && email.length() > 0){
            Optional<StudentEntity> studentEntityOptional = studentRepository.findStudentByEmail(email);
            if (studentEntityOptional.isPresent()){
                throw new IllegalStateException("Email already exist !");
            }
            studentEntity.setEmail(email);
        }
        /*
          REASON we dont use studentRepository.save(studentEntity); in this method
          because we already have the @Transactional
         */
    }
}
