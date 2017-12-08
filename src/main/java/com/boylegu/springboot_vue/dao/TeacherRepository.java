package com.boylegu.springboot_vue.dao;

import com.boylegu.springboot_vue.entities.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    public static final String FIND_SUBJECT = "select DISTINCT subject from Teacher t";

    @Query(FIND_SUBJECT)
    List<Teacher> findSubject();

    Page<Teacher> findAll(Pageable pageable);

    Page<Teacher> findByusernameAndNumberContains(String username, String number, Pageable pageable);

    Page<Teacher> findByusername(String username, Pageable pageable);

    Teacher findById(Long id);

}
