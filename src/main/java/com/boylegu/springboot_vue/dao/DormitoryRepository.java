package com.boylegu.springboot_vue.dao;

import com.boylegu.springboot_vue.entities.Dormitory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DormitoryRepository extends JpaRepository<Dormitory, Long> {

    public static final String FIND_STATE = "select DISTINCT state from Dormitory d";
    public static final String FIND_COUNT = "select count (state) from Dormitory d";

    @Query(FIND_STATE)
    List<Dormitory> findSTATE();

    @Query(FIND_COUNT)
    long count();

    Page<Dormitory> findAll(Pageable pageable);

    Page<Dormitory> findByStateAndName(String state,String name, Pageable pageable);

    Page<Dormitory> findByState(String is_use, Pageable pageable);

    Dormitory findById(Long id);

    Page<Dormitory> findById(Long id, Pageable pageable);
}
