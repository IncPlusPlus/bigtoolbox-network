package io.github.incplusplus.bigtoolbox.network;

import java.io.Closeable;
import java.io.IOException;
import java.lang.ref.Cleaner;

/**
 * A NetworkController acts as a handle for the networking API. It is <b><i>not</i></b> a
 * representative of a physical networking card or adapter. You <i>may</i>, however, use it to
 * obtain a list of wifi adapters.
 *
 * <p><b><i>IMPORTANT:</i></b> Treat this class like a resource. Either use the .close method when
 * you are done with it (or intend to exit the program) or use this within a try-with-resources
 * block. See {@link NetworkControllerFactory#createNetworkController()} for more details.
 *
 * @see NetworkControllerFactory#createNetworkController()
 */
public abstract class NetworkController implements Closeable {
  private static volatile boolean firstInit = false;
  private static volatile boolean closed = false;
  private static final Cleaner cleaner = Cleaner.create();
  private static Cleaner.Cleanable cleanable;
  private static CleaningAction cleaningAction;

  public NetworkController() {
    ensureInit();
    // System.out.println("IN NetworkController CONSTRUCTOR");
  }

  /**
   * Ask all WiFi adapters to scan for networks (a.k.a. "refresh").
   *
   * @return true if all scans completed successfully; false if any failed
   * @throws IOException if the controller has already run {@link NetworkController#close()} or if
   *     some other IOException occurs.
   */
  //	public abstract boolean scanAll() throws IOException;

  /**
   * @return a list of {@linkplain AccessPoint}s. This contains the list of all access points seen
   *     by all {@linkplain Interface}s. This means it will contain duplicates.
   * @throws IOException if the controller has already run {@link NetworkController#close()}
   */
  //	public abstract AccessPoint[] getAllAccessPoints() throws IOException;

  /**
   * Get all wireless adapters/interfaces.
   *
   * @return a list of all accessible interfaces
   * @throws IOException if any issues occur communicating with the system
   */
  public abstract Interface[] getInterfaces() throws IOException;

  /** Close any programs opened for this object and ready this object for disposal */
  protected abstract void conclude() throws IOException;

  /** Closes any open resources */
  public void close() {
    // System.out.println("INSIDE NetworkController.CLOSE()");
    if (!isClosed()) {
      // System.out.println("CONTINUING WITH NetworkController.CLOSE()");
      /*
       * TODO add an option (maybe a class with static booleans) for using this library without
       *  having it try to clean up for you. Create a contract with a boolean that the user will
       *  properly use the library. Effectively make an "I know what I'm doing." button.
       */
      if (shutdownHooksAllowed()) {
        // Remove hook on close in the case that close() isn't being called from a shutdown hook
        final Thread mainThread = Thread.currentThread();
        try {
          // System.out.println("TRYING TO REMOVE SHUTDOWN HOOK");
          Runtime.getRuntime().removeShutdownHook(mainThread);
          // System.out.println("REMOVED SHUTDOWN HOOK");
        } catch (IllegalStateException e) {
          // System.out.println("FAILED TO REMOVE SHUTDOWN HOOK");
          /*
           * This catch block is only reached if the JVM is shutting down
           * In that case, it's okay that we can't remove this shutdown hook because
           * the close() is being run before shutdown as it should be.
           *
           */
        }
      }
      try {
        conclude();
        closed = true;
        cleanable.clean();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /** @return whether or not this controller is closed (no longer usable) */
  public final boolean isClosed() {
    return closed;
  }

  /** Checks to make sure that the streams have not been closed */
  protected final void ensureOpen() throws IOException {
    if (isClosed())
      throw new IOException("NetworkController closed. Create a new instance to use the WiFi API.");
  }

  private void ensureInit() {
    if (!firstInit) {
      cleaningAction = new CleaningAction(this);
      cleanable = cleaner.register(this, cleaningAction);
      firstInit = true;

      /*
       * TODO add an option (maybe a class with static booleans) for using this library without
       *  having it try to clean up for you. Create a contract with a boolean that the user will
       *  properly use the library. Effectively make an "I know what I'm doing." button.
       */
      if (shutdownHooksAllowed()) {
        final Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(getRunnableShutdownHook(mainThread));
      }
    }
  }

  private Thread getRunnableShutdownHook(Thread mainThread) {
    return new Thread(
        () -> {
          // System.out.println("SHUTDOWN HOOK RUNNING");
          try {
            mainThread.join();
            this.close();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        });
  }

  private boolean shutdownHooksAllowed() {
    SecurityManager securityManager = System.getSecurityManager();
    if (securityManager != null) {
      try {
        securityManager.checkPermission(new RuntimePermission("shutdownHooks"));
        // Security manager is playing nice
        return true;
      } catch (SecurityException e) {
        // Security manager is not playing nice
        return false;
      }
    } else {
      // There's no security manager
      return true;
    }
  }

  static class CleaningAction implements Runnable {
    private NetworkController controllerToBeClosed;

    private CleaningAction(NetworkController controllerToBeClosed) {
      this.controllerToBeClosed = controllerToBeClosed;
    }

    @Override
    public void run() {
      // System.out.println("INSIDE CLEANINGACTION.RUN()");
      if (!controllerToBeClosed.isClosed()) {
        // System.out.println("RUNNING CLEANINGACTION.RUN()");
        controllerToBeClosed.close();
      }
      controllerToBeClosed = null;
    }
  }
}
