package io.github.incplusplus.bigtoolbox.network.interop.lin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class CLIUtils {
  public static ProcessResult exec(String command) throws IOException, InterruptedException {
    ProcessBuilder builder = new ProcessBuilder("sh", "-c", command);
    builder.redirectErrorStream(false);
    Process process = builder.start();

    BufferedReader stdOut = new BufferedReader(new InputStreamReader(process.getInputStream()));
    BufferedReader stdErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

    List<String> stdOutLines = stdOut.lines().collect(Collectors.toList());
    List<String> stdErrLines = stdErr.lines().collect(Collectors.toList());

    stdOut.close();
    stdErr.close();

    StringBuilder standardOutput = new StringBuilder();
    for (String line : stdOutLines) {
      standardOutput.append(line).append(System.lineSeparator());
    }

    StringBuilder standardError = new StringBuilder();
    for (String line : stdErrLines) {
      standardError.append(line).append(System.lineSeparator());
    }

    int exitStatus = process.waitFor();

    return new ProcessResult(exitStatus, standardOutput.toString(), standardError.toString());
  }

  public static class ProcessResult {
    private final int exitCode;
    private final String stdOut;
    private final String stdErr;

    public ProcessResult(int exitCode, String stdOut, String stdErr) {
      this.exitCode = exitCode;
      this.stdOut = stdOut;
      this.stdErr = stdErr;
    }

    public int getExitCode() {
      return exitCode;
    }

    public String getStdOut() {
      return stdOut;
    }

    public String getStdErr() {
      return stdErr;
    }
  }
}
