package com.erensayar.core.log.repository;

import com.erensayar.core.log.model.entity.LogEntity;
import com.erensayar.core.log.model.enums.LogType;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogEntityRepository extends JpaRepository<LogEntity, Long> {

  Optional<LogEntity> findByTraceId(String traceId);

  List<LogEntity> findAllByUserId(String userId);

  List<LogEntity> findAllByErrorCode(Integer errorCode);

  List<LogEntity> findAllByLogType(LogType logType);

  void deleteAllByLogType(LogType logType);

  void deleteAllByLogTypeAndTimeStampBefore(LogType logType, LocalDateTime referenceOfTime);

  void deleteAllByUserId(String userId);

}
