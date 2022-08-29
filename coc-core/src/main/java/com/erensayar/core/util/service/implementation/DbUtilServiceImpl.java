package com.erensayar.core.util.service.implementation;

import com.erensayar.core.util.service.DbUtilService;
import com.erensayar.core.util.service.UtilService;
import java.lang.reflect.Field;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DbUtilServiceImpl implements DbUtilService {

  private final UtilService utilService;

  // (id,email,password,apiKey,apiUsageLimit,userRoles)
  @Override
  public String generateFieldNames(Object object) {
    Field[] fields = utilService.getClassFields(object);
    StringBuilder fieldsSB = new StringBuilder();
    for (Field f : fields) {
      fieldsSB.append(",").append(f.getName());
    }
    return "(" + fieldsSB.substring(4) + ")"; // Start without ",id,"
  }

  // (?,?,?,?,?,?)
  @Override
  public String generateQuestionMarks(Object object) {
    Field[] fields = utilService.getClassFields(object);
    StringBuilder questionMarksSB = new StringBuilder();
    for (int i = 0; i < fields.length; i++) {
      questionMarksSB.append(",?");
    }
    return "(" + questionMarksSB.substring(3) + ")"; // Start without ",?,"
  }

  // UPDATE ACCOUNT SET email=?, password=?, apiKey=?, apiUsageLimit=? WHERE id=?
  @Override
  public String generateUpdateQuery(Object obj, String entityName) {
    String tableName = entityName.toUpperCase();
    Field[] fields = utilService.getClassFields(obj);
    StringBuilder fieldsSB = new StringBuilder();
    for (Field f : fields) {
      fieldsSB.append(f.getName()).append("=?").append(", ");
    }
    return "UPDATE " + tableName + " SET " + fieldsSB.substring(6, fieldsSB.length() - 2)
        + " WHERE id=?";
  }

}
