package io.github.incplusplus.bigtoolbox.network.wlan.interop;

import io.github.incplusplus.bigtoolbox.os.OperationNotImplementedException;

public abstract class WLanController implements java.io.Closeable
{
	private boolean closed = false;

	/**
	 * Force the WiFi adapter to scan for networks (a.k.a. "refresh").
	 * @throws UnsupportedOperationException if the {@code scan}
	 *         operation is not supported by this adapter implementation
	 * @return whether a scan succeeded or not
	 */
	public boolean scan()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Close any programs opened for this object and
	 * ready this object for disposal
	 */
	abstract void conclude();

	/**
	 * Closes any open resources
	 */
	public final void close()
	{
		this.conclude();
		closed=true;
	}

	/**
	 * @return whether or not this controller is closed (no longer usable)
	 */
	public boolean isClosed()
	{
		return closed;
	}
}
