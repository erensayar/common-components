package com.erensayar.core.log.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LOG_DATA")
@Entity
public class LogData {

  @Id
  @GeneratedValue
  private Long id;

  private String dataKey;

  private String dataValue;


}
