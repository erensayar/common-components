package com.erensayar.core.util.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemUtilImpl implements SystemUtil {

  @Override
  public String executeCommandInBash(String commands) {
    try {
      ProcessBuilder processBuilder = new ProcessBuilder();
      // processBuilder.command("/bin/bash", "-c", "echo Hello World && ls && pwd && whoami");
      processBuilder.command("/bin/bash", "-c", commands);
      Process process = processBuilder.start();
      return readExecutedCommandOutput(process);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private String readExecutedCommandOutput(Process process) {
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      StringBuilder output = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line + "\n");
      }
      int exitVal = process.waitFor();
      if (exitVal == 0) {
        return output.toString();
      } else {
        return "Failed";
      }
    } catch (InterruptedException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * netstat -anp | grep <app_port>
   */
  @Override
  public String findProcessId(String commandOutput) {
    int indexOfTheEnd = commandOutput.indexOf("/java");
    int indexOfTheBeginningTemp = indexOfTheEnd - 3;

    while (commandOutput.charAt(indexOfTheBeginningTemp) != ' ') {
      indexOfTheBeginningTemp--;
    }

    int indexOfTheBeginning = indexOfTheBeginningTemp + 1;

    StringBuilder pid = new StringBuilder();
    for (int i = indexOfTheBeginning; i < indexOfTheEnd; i++) {
      pid.append(commandOutput.charAt(i));
    }
    return pid.toString();
  }

}
