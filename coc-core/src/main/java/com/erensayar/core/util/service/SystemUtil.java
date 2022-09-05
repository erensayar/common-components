package com.erensayar.core.util.service;

public interface SystemUtil {

   String executeCommandInBash(String command);

   String findProcessId(String commandOutput);

}
