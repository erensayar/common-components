package com.erensayar.core.log.repository;

import com.erensayar.core.log.model.entity.LogData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDataRepository extends JpaRepository<LogData, Long> {

}
