package com.springbootplayground.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JpaRepository<T,ID> , basically it accept Class/Table as T and the type of ID of that Class/Table in the ID
 * JpaRepository<StudentEntity,Long>
 */

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {

}
