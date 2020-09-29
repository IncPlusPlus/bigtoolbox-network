package io.github.incplusplus.bigtoolbox.network.interop;

public class SingletonUnavailableException extends RuntimeException {
  /**
   * This is thrown when proceeding with the desired method call would violate the singleton pattern
   * of this factory
   */
  public SingletonUnavailableException(Class<?> singletonClass) {
    super("Could not instantiate a singleton of " + singletonClass.getName() + " as there is en existing non-closed copy of it in existence.");
  }
}
