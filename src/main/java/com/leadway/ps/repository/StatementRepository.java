package com.leadway.ps.repository;

import com.leadway.ps.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StatementRepository extends JpaRepository<Statement, String>{
    List<Statement> findAllByStatus(String status);
}