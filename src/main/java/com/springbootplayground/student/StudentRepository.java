package com.springbootplayground.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JpaRepository<T,ID> , basically it accept Class/Table as T and the type of ID of that Class/Table in the ID
 * JpaRepository<StudentEntity,Long>
 */

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {

    // SELECT * FROM student WHERE email = ?
    @Query("SELECT s FROM StudentEntity s WHERE s.email = ?1")
    Optional<StudentEntity> findStudentByEmail(String email);

}
