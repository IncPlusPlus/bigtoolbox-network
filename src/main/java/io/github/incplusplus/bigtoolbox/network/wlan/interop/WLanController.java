package io.github.incplusplus.bigtoolbox.network.wlan.interop;

import io.github.incplusplus.bigtoolbox.network.wlan.AvailableAccessPointPack;

import java.io.Closeable;
import java.io.IOException;
import java.lang.ref.Cleaner;

//TODO add more documentation

/**
 * IMPORTANT: Treat this class like a resource. Either use the .close method when you are
 * done with it (or intend to exit the program) or use this within a try-with-resources block.
 * See {@link WLanControllerFactory#createWLanController()} for more details.
 *
 * @see WLanControllerFactory#createWLanController()
 */
public abstract class WLanController implements Closeable
{
	private static volatile boolean firstInit = false;
	private static volatile boolean closed = false;
	private static final Cleaner cleaner = Cleaner.create();
	private static Cleaner.Cleanable cleanable;
	private static CleaningAction cleaningAction;

	public WLanController()
	{
		ensureInit();
		//System.out.println("IN WLANCONTROLLER CONSTRUCTOR");
	}

	/**
	 * Force the WiFi adapter to scan for networks (a.k.a. "refresh").
	 *
	 * @return whether a scan succeeded or not
	 * @throws UnsupportedOperationException if the {@code scan}
	 *                                       operation is not supported by this adapter implementation
	 * @throws IOException                   if the controller has already run {@link WLanController#close()}
	 */
	public boolean scan() throws IOException
	{
		throw new UnsupportedOperationException();
	}

	/**
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
		//System.out.println("INSIDE WLANCONTROLLER.CLOSE()");
		if(! isClosed())
		{
			//System.out.println("CONTINUING WITH WLANCONTROLLER.CLOSE()");
			/*
			 * TODO add an option (maybe a class with static booleans) for using this library without
			 *  having it try to clean up for you. Create a contract with a boolean that the user will
			 *  properly use the library. Effectively make an "I know what I'm doing." button.
			 */
			if(shutdownHooksAllowed())
			{
				//Remove hook on close in the case that close() isn't being called from a shutdown hook
				final Thread mainThread = Thread.currentThread();
				try
				{
					//System.out.println("TRYING TO REMOVE SHUTDOWN HOOK");
					Runtime.getRuntime().removeShutdownHook(mainThread);
					//System.out.println("REMOVED SHUTDOWN HOOK");
				}
				catch(IllegalStateException e)
				{
					//System.out.println("FAILED TO REMOVE SHUTDOWN HOOK");
					/*
					 * This catch block is only reached if the JVM is shutting down
					 * In that case, it's okay that we can't remove this shutdown hook because
					 * the close() is being run before shutdown as it should be.
					 *
					 */
				}
			}
			try
			{
				conclude();
				closed = true;
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
		if(! firstInit)
		{
			cleaningAction = new CleaningAction(this);
			cleanable = cleaner.register(this, cleaningAction);
			firstInit = true;

			/*
			 * TODO add an option (maybe a class with static booleans) for using this library without
			 *  having it try to clean up for you. Create a contract with a boolean that the user will
			 *  properly use the library. Effectively make an "I know what I'm doing." button.
			 */
			if(shutdownHooksAllowed())
			{
				final Thread mainThread = Thread.currentThread();
				Runtime.getRuntime().addShutdownHook(getRunnableShutdownHook(mainThread));
			}
		}
	}

	private Thread getRunnableShutdownHook(Thread mainThread)
	{
		return new Thread(() ->
		{
			//System.out.println("SHUTDOWN HOOK RUNNING");
			try
			{
				mainThread.join();
				this.close();
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		});
	}

	private boolean shutdownHooksAllowed()
	{
		SecurityManager securityManager = System.getSecurityManager();
		if(securityManager != null)
		{
			try
			{
				securityManager.checkPermission(new RuntimePermission("shutdownHooks"));
				//Security manager is playing nice
				return true;
			}
			catch(SecurityException e)
			{
				//Security manager is not playing nice
				return false;
			}
		}
		else
		{
			//There's no security manager
			return true;
		}
	}

	static class CleaningAction implements Runnable
	{
		private WLanController controllerToBeClosed;

		private CleaningAction(WLanController controllerToBeClosed)
		{
			this.controllerToBeClosed = controllerToBeClosed;
		}

		@Override
		public void run()
		{
			//System.out.println("INSIDE CLEANINGACTION.RUN()");
			if(!controllerToBeClosed.isClosed())
			{
				//System.out.println("RUNNING CLEANINGACTION.RUN()");
				controllerToBeClosed.close();
			}
			controllerToBeClosed = null;
		}
	}
}
