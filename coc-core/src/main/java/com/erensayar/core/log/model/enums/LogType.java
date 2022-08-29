package com.erensayar.core.log.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description
 * <pre>
 * |-----------------|---------------------------|------------------------------------|-----------------|-------------------------------------------|
 * |     LogType     |        Description        |              Example               |   Store Rule    |               Storage Time                |
 * |-----------------|---------------------------|------------------------------------|-----------------|-------------------------------------------|
 * | INFO            | Info.                     | Info of anything.                  | Will be deleted | Delete after 1 week                       |
 * | WARNING         | Insignificant exceptions. | NoContentByThisId                  | Will be deleted | Delete after 2 week                       |
 * | TECHNICAL_ERROR | Technic exceptions.       | Can't put the object in cache.Etc. | Will be stored  | Won't be deleted or delete after 2 years  |
 * | BUSINESS_ERROR  | Business exceptions.      | Reached the limit by account plan. | Will be stored  | Won't be deleted or delete after 3 years  |
 * |-----------------|---------------------------|------------------------------------|-----------------|-------------------------------------------|
 * </pre>
 */
@Getter
@AllArgsConstructor
public enum LogType {

  INFO(1),
  WARNING(2),
  TECHNICAL_ERROR(3),
  BUSINESS_ERROR(4);

  private final int val;

  public static LogType getValById(Integer id) {
    for (LogType e : values()) {
      if (e.val == id) {
        return e;
      }
    }
    return null;
  }

  public static LogType[] getLogTypes() {
    return values();
  }

}
