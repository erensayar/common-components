package com.erensayar.core.log.model.entity;

import com.erensayar.core.log.model.enums.LogType;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LOG")
@Entity
public class LogEntity {

  // Default Created
  // <====================================================================================>

  @Id
  @GeneratedValue
  private Long id;

  @Column(unique = true, nullable = false)
  private String traceId;

  @Column(nullable = false)
  private LocalDateTime timeStamp;

  private String classNameOfLogPrinted;

  private String methodNameOfLogPrinted;

  // Custom Created
  // <====================================================================================>

  @Column(nullable = false)
  private Integer errorCode;

  @Column(nullable = false)
  private String description;

  private String userId;

  @Enumerated
  private LogType logType;

  private String javaExceptionName;

  private String javaExceptionMessage;

  @JoinTable(name = "RLT_LOG_LOG_DATA",
      joinColumns = @JoinColumn(name = "log_id"),
      inverseJoinColumns = @JoinColumn(name = "log_data_id"))
  @OneToMany(cascade = CascadeType.ALL)
  private List<LogData> logData; // (ApiError)

}