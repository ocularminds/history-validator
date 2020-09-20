package com.leadway.ps.repository;

import com.leadway.ps.model.Record;
import com.leadway.ps.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record, String>{
    List<Record> findAllByStatement(Statement statement);
}