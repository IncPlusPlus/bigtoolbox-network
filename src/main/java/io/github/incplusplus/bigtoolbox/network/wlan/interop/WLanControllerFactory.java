package io.github.incplusplus.bigtoolbox.network.wlan.interop;

import io.github.incplusplus.bigtoolbox.os.UnsupportedOSException;
import io.github.incplusplus.bigtoolbox.os.opsys.OperatingSystem;

/**
 * The {@link WLanControllerFactory} is a pseudo-singleton pattern factory. The goal of this class is to allow for
 * the creation of {@link WLanController}s one at a time.
 */
public final class WLanControllerFactory
{
	private static WLanController primaryController;
	private static int numberInstantiated = 0;

	/**
	 * Create and return a new WLanController implementation based on the host's OS.
	 * It is highly discouraged to use this method outside of a try-with-resources block.
	 * A recommended usage example follows:
	 * <pre>
	 * {@code
	 * try(WLanController controller = WLanControllerFactory.createWLanController())
	 * {
	 * 	controller.scan();
	 * 	AvailableAccessPointPack pack = controller.getAccessPoints();
	 * 	System.out.println();
	 * 	for(AccessPoint ap : pack.accessPoints)
	 * 	{
	 * 		System.out.println("Name: "+ap.name);
	 * 		System.out.println("Signal Strength: "+ap.signalStrength);
	 * 		System.out.println();
	 * 	}
	 * }
	 * catch(IOException e)
	 * {
	 * 	e.printStackTrace();
	 * }
	 * }
	 * </pre>
	 * If there already is a non-closed WLanController, close it first before calling this method.
	 * You should not need to, however, if you use this method within a try-with-resources block as demonstrated.
	 * @throws IllegalStateException if the factory is in an unexpected state or if
	 *                         there already is a non-closed WLanController instance.
	 * @return a new WLanController implementation based on the host's OS
	 */
	public static WLanController createWLanController()
	{

		if(numberInstantiated == 0)
		{
			if(primaryController != null)
			{
				throw new IllegalStateException("Somehow the primary network controller variable is " +
						"not null but the number of instantiations is at 0");
			}
			else
			{
				numberInstantiated++;
				primaryController=getByOSFamily(OperatingSystem.getOSFamily());
				return primaryController;
			}
		}
		else if(numberInstantiated == 1)
		{
			if(primaryController == null)
			{
				throw new IllegalStateException("Somehow the primary network controller variable is " +
						"null but the number of instantiations is at 1");
			}
			else if(primaryController.isClosed())
			{
				primaryController=getByOSFamily(OperatingSystem.getOSFamily());
				return primaryController;
			}
			else if(!primaryController.isClosed())
			{
				throw new SingletonUnavailableException();
			}
		}
		else
		{
			throw new IllegalStateException("The number of instantiated controllers is neither 0 nor 1 " +
					"despite the fact that this is a singleton factory.");
		}
		return null;
	}

	private static WLanController getByOSFamily(OperatingSystem.OSFamily family)
	{
		switch(family)
		{
			case Windows:
				return new WindowsInterop();
			case Linux:
				throw new UnsupportedOSException();
			case Mac:
				throw new UnsupportedOSException();
			default:
				throw new UnsupportedOSException();
		}
	}
}
