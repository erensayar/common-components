package com.erensayar.core.util.service;

import java.lang.reflect.Field;
import java.util.Set;

public interface UtilService {

  String generateRandomString(int len);

  String[] getActiveProfiles();

  boolean activeProfileCheck(String profile);

  Integer generateRandomNumberAdjustedRange(int min, int max);

  Field[] getClassFields(Object object);

  void printClassFields(Object object);

  <T> boolean isRetainAllInClassTheseFields(Class<T> c, Set<String> inputFieldNames);

  String fixUnsafeString(String unsafeString);

}
