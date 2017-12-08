package com.boylegu.springboot_vue.dao;

import com.boylegu.springboot_vue.entities.Administrators;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<Administrators, Long> {

    public static final String FIND_JURISDICTION = "select DISTINCT Jurisdiction from Administrators a";

    @Query(FIND_JURISDICTION)
    List<Administrators> findJurisdiction();

    Page<Administrators> findAll(Pageable pageable);

    Page<Administrators> findByUsernameAndNumberContains(String username, String number, Pageable pageable);

    Page<Administrators> findByUsername(String username, Pageable pageable);

    Administrators findById(Long id);

}
