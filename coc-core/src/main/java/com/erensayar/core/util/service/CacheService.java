package com.erensayar.core.util.service;

public interface CacheService<T> {

  void put(String key, T t);

  T findByKey(String key);

}
