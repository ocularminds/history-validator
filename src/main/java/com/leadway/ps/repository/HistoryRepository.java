package com.leadway.ps.repository;

import com.leadway.ps.model.StatementRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistoryRepository extends JpaRepository<StatementRequest, String>{
    List<StatementRequest> findAllByStatus(String status);
}