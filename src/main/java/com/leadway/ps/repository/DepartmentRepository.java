package com.leadway.ps.repository;

import com.leadway.ps.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository
  extends JpaRepository<Department, String> {}
