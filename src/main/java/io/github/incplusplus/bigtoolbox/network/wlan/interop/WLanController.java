package io.github.incplusplus.bigtoolbox.network.wlan.interop;

import io.github.incplusplus.bigtoolbox.network.wlan.AvailableAccessPointPack;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.Closeable;
import java.io.IOException;
import java.lang.ref.Cleaner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO add more documentation
 * IMPORTANT: Treat this class like a resource. Either use the .close method when you are
 * done with it (or intend to exit the program) or use this within a try-with-resources block.
 * See {@link WLanControllerFactory#createWLanController()} for more details.
 *
 * @see WLanControllerFactory#createWLanController()
 */
public abstract class WLanController implements Closeable
{
	private static boolean firstInit = false;
	private static boolean closed = false;
	private static final Cleaner cleaner = Cleaner.create();
	private static  Cleaner.Cleanable cleanable;
	private static  CleaningAction cleaningAction;

	public WLanController()
	{
		ensureInit();
		System.out.println("IN WLANCONTROLLER CONSTRUCTOR");
	}

	/**
	 * Force the WiFi adapter to scan for networks (a.k.a. "refresh").
	 * @throws UnsupportedOperationException if the {@code scan}
	 *         operation is not supported by this adapter implementation
	 * @throws IOException if the controller has already run {@link WLanController#close()}
	 * @return whether a scan succeeded or not
	 */
	public boolean scan() throws IOException
	{
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @return a new {@link AvailableAccessPointPack}
	 * @throws IOException if the controller has already run {@link WLanController#close()}
	 */
	public abstract AvailableAccessPointPack getAccessPoints() throws IOException;

	/**
	 * Close any programs opened for this object and
	 * ready this object for disposal
	 */
	abstract void conclude() throws IOException;

	/**
	 * Closes any open resources
	 */
	public void close()
	{
		System.out.println("INSIDE WLANCONTROLLER.CLOSE()");
			if(!isClosed())
			{
				try
				{
					conclude();
					closed=true;
					cleanable.clean();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
	}

	/**
	 * @return whether or not this controller is closed (no longer usable)
	 */
	public final boolean isClosed()
	{
		return closed;
	}

	/**
	 * Checks to make sure that the streams have not been closed
	 */
	final void ensureOpen() throws IOException
	{
		if(isClosed())
			throw new IOException("WLanController closed");
	}

	private void ensureInit()
	{
		if(!firstInit)
		{
			cleaningAction = new CleaningAction("");
			cleanable = cleaner.register(this,cleaningAction);
			firstInit=true;
		}
	}

	static class CleaningAction implements Runnable
	{
		private String controllerToBeClosed;

		private CleaningAction(String controllerToBeClosed)
		{
			this.controllerToBeClosed = controllerToBeClosed;
		}

		@Override
		public void run()
		{
			System.out.println("INSIDE CLEANINGACTION.RUN()");
			//if(!controllerToBeClosed.isClosed())
			//{
			//	System.out.println("RUNNING CLEANINGACTION.RUN()");
			//	controllerToBeClosed.close();
			//}
			controllerToBeClosed = null;
		}
	}
}
