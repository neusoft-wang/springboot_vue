package com.boylegu.springboot_vue.dao;

import com.boylegu.springboot_vue.entities.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    public static final String FIND_SUBJECT = "select DISTINCT subject from Teacher t";
    String FIND_PASSWORD = "select passWord from Teacher t where username = :username";
    String FIND_CLASS = "select classInfo from Teacher t where username = :username";

    @Query(FIND_PASSWORD)
    String findByUsername1(@Param("username")String username);

    @Query(FIND_CLASS)
    String findClassByUsername1(@Param("username")String username);

    @Query(FIND_SUBJECT)
    List<Teacher> findSubject();

    Page<Teacher> findAll(Pageable pageable);

    Page<Teacher> findBySubjectAndNumberContains(String subject, String number, Pageable pageable);

    Page<Teacher> findBySubject(String subject, Pageable pageable);

    Teacher findById(Long id);

}
