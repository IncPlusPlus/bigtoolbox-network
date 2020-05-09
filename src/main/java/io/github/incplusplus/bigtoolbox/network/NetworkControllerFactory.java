package io.github.incplusplus.bigtoolbox.network;

import io.github.incplusplus.bigtoolbox.network.interop.SingletonUnavailableException;
import io.github.incplusplus.bigtoolbox.network.interop.lin.LinuxNetToolFinder;
import io.github.incplusplus.bigtoolbox.network.interop.win.WindowsInterop;
import io.github.incplusplus.bigtoolbox.os.UnsupportedOSException;
import io.github.incplusplus.bigtoolbox.os.opsys.OperatingSystem;
import java.io.IOException;

/**
 * The {@link NetworkControllerFactory} is a pseudo-singleton pattern factory. The goal of this
 * class is to allow for the creation of {@link NetworkController}s one at a time.
 */
public final class NetworkControllerFactory {
  private static NetworkController primaryController;
  private static int numberInstantiated = 0;

  /**
   * Create and return a new NetworkController implementation based on the host's OS. It is highly
   * discouraged to use this method outside of a try-with-resources block. A recommended usage
   * example follows:
   *
   * <pre>{@code
   * try(NetworkController controller = NetworkControllerFactory.createNetworkController())
   * {
   * 	controller.scanAll();
   * 	AccessPoint[] aps = controller.getAllAccessPoints();
   * 	System.out.println();
   * 	for(AccessPoint ap : aps)
   *    {
   * 		System.out.println("Name: "+ap.getName());
   * 		System.out.println("Signal Strength: "+ap.getSignalStrength());
   * 		System.out.println();
   *    }
   * }
   * catch(IOException e)
   * {
   * 	e.printStackTrace();
   * }
   * }</pre>
   *
   * If there already is a non-closed NetworkController, close it first before calling this method.
   * You should not need to, however, if you use this method within a try-with-resources block as
   * demonstrated. You will not be able to use anything related to this {@link NetworkController}
   * after the try-with-resources block or after calling {@link NetworkController#close()} and all
   * objects generated using it will also become unusable.
   *
   * @throws IllegalStateException if the factory is in an unexpected state or if there already is a
   *     non-closed NetworkController instance.
   * @throws UnsupportedOSException if the current OS is not supported by this library
   * @return a new NetworkController implementation based on the host's OS
   */
  public static NetworkController createNetworkController()
      throws UnsupportedOSException, IOException {
    if (numberInstantiated == 0) {
      if (primaryController != null) {
        throw new IllegalStateException(
            "Somehow the primary network controller variable is "
                + "not null but the number of instantiations is at 0");
      } else {
        primaryController = getByOSFamily(OperatingSystem.getOSFamily());
        numberInstantiated++;
        return primaryController;
      }
    } else if (numberInstantiated == 1) {
      if (primaryController == null) {
        throw new IllegalStateException(
            "Somehow the primary network controller variable is "
                + "null but the number of instantiations is at 1");
      } else if (primaryController.isClosed()) {
        primaryController = getByOSFamily(OperatingSystem.getOSFamily());
        return primaryController;
      } else if (!primaryController.isClosed()) {
        throw new SingletonUnavailableException();
      }
    }
    throw new IllegalStateException(
        "The number of instantiated controllers is neither 0 nor 1 "
            + "despite the fact that this is a singleton factory.");
  }

  private static NetworkController getByOSFamily(OperatingSystem.OSFamily family)
      throws UnsupportedOSException, IOException {
    switch (family) {
      case Windows:
        return new WindowsInterop();
      case Linux:
        return LinuxNetToolFinder.getControllerByInstalledTool();
      case Mac:
        throw new UnsupportedOSException();
      default:
        throw new UnsupportedOSException();
    }
  }
}
