package com.leadway.ps.repository;

import com.leadway.ps.model.Resource;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, String> {
  Optional<Resource> findByLink(String link);
}
