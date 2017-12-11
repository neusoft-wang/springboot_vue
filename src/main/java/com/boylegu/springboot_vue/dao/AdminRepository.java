package com.boylegu.springboot_vue.dao;

import com.boylegu.springboot_vue.entities.Administrators;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepository extends JpaRepository<Administrators, Long> {

    String FIND_JURISDICTION = "select DISTINCT Jurisdiction from Administrators a";
    String FIND_PASSWORD = "select password from Administrators a where username = :username";

    @Query(FIND_JURISDICTION)
    List<Administrators> findJurisdiction();

    @Query(FIND_PASSWORD)
    String findByUsername1(@Param("username")String username);

    Page<Administrators> findAll(Pageable pageable);

    Page<Administrators> findByUsernameAndNumberContains(String username, String number, Pageable pageable);

    Page<Administrators> findByUsername(String username, Pageable pageable);

    Administrators findById(Long id);

}
