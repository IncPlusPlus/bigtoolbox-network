package io.github.incplusplus.bigtoolbox.network.interop;

import io.github.incplusplus.bigtoolbox.network.NetworkController;
import io.github.incplusplus.bigtoolbox.network.NetworkControllerFactory;
import io.github.incplusplus.bigtoolbox.network.wlan.AccessPoint;
import io.github.incplusplus.bigtoolbox.os.UnsupportedOSException;

import java.io.IOException;

public class ScratchPad {
	//	public static void main(String[] args) {
	//		try(NetworkController controller = NetworkControllerFactory.createNetworkController())
	//		{
	//			controller.scanAll();
	//			AccessPoint[] aps = controller.getAllAccessPoints();
	//			System.out.println();
	//			for(AccessPoint ap : aps)
	//			{
	//				System.out.println("Name: "+ap.getName());
	//				System.out.println("Signal Strength: "+ap.getSignalStrength());
	//				System.out.println();
	//			}
	//		}
	//		catch(IOException | UnsupportedOSException e)
	//		{
	//			e.printStackTrace();
	//		}
	//	}
//	public static void main(String[] args) {
//		try (NetworkController controller = NetworkControllerFactory.createNetworkController()) {
//			controller.scanAll();
//			AccessPoint[] aps = controller.getAllAccessPoints();
//			System.out.println();
//			for (AccessPoint ap : aps) {
//				System.out.println("Name: " + ap.getName());
//				System.out.println("Signal Strength: " + ap.getSignalStrength());
//				System.out.println();
//			}
//		}
//		catch (IOException | UnsupportedOSException e) {
//			e.printStackTrace();
//		}
//	}
}
