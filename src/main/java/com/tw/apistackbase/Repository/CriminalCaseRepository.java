package com.tw.apistackbase.Repository;

import com.tw.apistackbase.Entity.CriminalCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CriminalCaseRepository extends JpaRepository<CriminalCase, Long> {
    List<CriminalCase> findByOrderByTimeDesc();

    List<CriminalCase> findCriminalCaseByName(String name);
}
