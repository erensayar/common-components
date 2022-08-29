package com.erensayar.core.util.service;

public interface DbUtilService {

  String generateFieldNames(Object object);

  String generateQuestionMarks(Object object);

  String generateUpdateQuery(Object obj, String entityName);

}
