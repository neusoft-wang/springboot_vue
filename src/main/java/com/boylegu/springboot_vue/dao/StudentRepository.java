package com.boylegu.springboot_vue.dao;

import com.boylegu.springboot_vue.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    public static final String FIND_SEX = "select DISTINCT sex from Student s";

    @Query(FIND_SEX)
    List<Student> findSex();

    Page<Student> findAll(Pageable pageable);

    Page<Student> findByNameAndNumberContains(String name, String number, Pageable pageable);

    Page<Student> findByName(String name, Pageable pageable);

    Student findById(Long id);

}
