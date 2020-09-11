package com.leadway.ps.repository;

import com.leadway.ps.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, String> {}
