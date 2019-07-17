package com.tw.apistackbase.Repository;

import com.tw.apistackbase.Entity.CriminalCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriminalCaseRepository extends JpaRepository<CriminalCase, Long> {
}
