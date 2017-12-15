package com.boylegu.springboot_vue.dao;

import com.boylegu.springboot_vue.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    public static final String FIND_CLASS = "select DISTINCT classInfo from Student s";
    String FIND_PASSWORD = "select passWord from Student s where username = :username";

    @Query(FIND_PASSWORD)
    String findByUsername1(@Param("username")String username);

    @Query(FIND_CLASS)
    List<Student> findClass();

    Page<Student> findAll(Pageable pageable);

    Page<Student> findByClassInfoAndNumberContains(String classInfo, String number, Pageable pageable);

    Page<Student> findByClassInfo(String classInfo, Pageable pageable);

    Student findById(Long id);

}
