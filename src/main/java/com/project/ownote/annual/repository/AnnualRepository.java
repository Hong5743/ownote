package com.project.ownote.annual.repository;

import com.project.ownote.annual.dto.Annual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnualRepository extends JpaRepository<Annual,Long> {
}
