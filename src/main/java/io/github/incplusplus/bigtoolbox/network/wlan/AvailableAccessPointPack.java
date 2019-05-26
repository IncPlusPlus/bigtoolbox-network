package io.github.incplusplus.bigtoolbox.network.wlan;

import java.util.Arrays;

public class AvailableAccessPointPack
{
	public final AccessPoint[] accessPoints;

	AvailableAccessPointPack(AccessPoint[] accessPoints)
	{
		this.accessPoints = accessPoints;
	}

	public String toString()
	{
		return Arrays.toString(accessPoints);
	}
}
